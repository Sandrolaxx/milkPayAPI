package com.milk.pay.services;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;

import com.milk.pay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;
import com.milk.pay.entities.Title;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.entities.enums.EnumInitiationType;
import com.milk.pay.mapper.IPixMapper;
import com.milk.pay.pattern.rest.RestClientPixCelcoin;
import com.milk.pay.utils.DateUtil;
import com.milk.pay.utils.MilkPayException;
import com.milk.pay.utils.StringUtil;
import com.milk.pay.utils.WebApplicationExceptionConverter;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class PixServiceCelcoin {

    @Inject
    TokenServiceCelcoin tokenService;

    @Inject
    @RestClient
    RestClientPixCelcoin restClientPix;

    @Inject
    IPixMapper pixMapper;

    @Inject
    PixService pixService;

    @ConfigProperty(name = "celcoin.pix.key")
    String pixKey;

    public PixKeyConsultResponseCelcoinDto consultKey(String key) throws MilkPayException {

        try {

            var token = tokenService.tokenDto();

            var responseCelcoin = restClientPix.consultKey(token, key);

            if (responseCelcoin == null) {
                throw new MilkPayException(EnumErrorCode.ERRO_CONSULTAR_CHAVE_PIX_CELCOIN);
            }

            return responseCelcoin;

        } catch (WebApplicationException wae) {

            if (wae.getResponse() != null && wae.getResponse().getStatus() == 404) {
                throw new MilkPayException(EnumErrorCode.CHAVE_CONSULTADA_INEXISTENTE);
            }

            final PixKeyConsultResponseCelcoinDto resp = WebApplicationExceptionConverter.convertExceptionToObject(wae, PixKeyConsultResponseCelcoinDto.class);

            if (resp != null && resp.getError() != null) {
                throw new MilkPayException(
                        resp.getError().getDescription(),
                        resp.getError().getCode()
                );
            }
        } catch (Exception e) {
            throw new MilkPayException(EnumErrorCode.ERRO_CONSULTAR_CHAVE_PIX_CELCOIN);
        }

        throw new MilkPayException(EnumErrorCode.ERRO_CONSULTAR_CHAVE_PIX_CELCOIN);
    }

    public PixPaymentResponseDto makePayment(PixPaymentCelcoinDto dto) {

        try {

            var token = tokenService.tokenDto();

            var responseCelcoin = restClientPix.makePayment(token, dto);

            if (responseCelcoin == null) {
                throw new MilkPayException(EnumErrorCode.ERRO_TRASNF_CHAVE_PIX_CELCOIN);
            }
            
            responseCelcoin.setAmount(dto.getAmount());
            
            return responseCelcoin;

        } catch (WebApplicationException wae) {
            final PixPaymentResponseDto resp = WebApplicationExceptionConverter.convertExceptionToObject(wae, PixPaymentResponseDto.class);

            if (resp != null) {
                throw new MilkPayException(
                        resp.getMessage() != null ? resp.getMessage() : resp.getDescription(),
                        StringUtil.toString(resp.getErrorCode())
                );
            }
            
        } catch(Exception e) {
            throw new MilkPayException(EnumErrorCode.ERRO_TRASNF_CHAVE_PIX_CELCOIN);
        }

        throw new MilkPayException(EnumErrorCode.ERRO_TRASNF_CHAVE_PIX_CELCOIN);
    }

    public PixPaymentDto setKeyInfo(PixPaymentDto pixPayment, PixKeyConsultResponseCelcoinDto keyConsult) {

        pixPayment.setCreditAccountKey(keyConsult.getKey());
        pixPayment.setCreditAccount(keyConsult.getAccount().getAccountNumber());
        pixPayment.setCreditAccountBank(keyConsult.getAccount().getParticipant());
        pixPayment.setCreditAccountBranch(keyConsult.getAccount().getBranch());
        pixPayment.setCreditAccountType(keyConsult.getAccount().getAccountType());
        pixPayment.setCreditAccountTaxId(keyConsult.getOwner().getTaxIdNumber());
        pixPayment.setCreditAccountName(keyConsult.getOwner().getName());
        pixPayment.setEndToEndId(keyConsult.getEndToEndId());
        pixPayment.setInitiationType(EnumInitiationType.DICT);

        return pixPayment;

    }

    public Double resolverAmount(Double total, Date dueDate, Double dailyFine) {
        var numberOfDays =  DateUtil.numberOfDaysBetweenDates(new Date(), dueDate);

        if (numberOfDays == 0) {
            return total;
        }
        var totalWithFine = total - (((numberOfDays * dailyFine) * 100) / total);

        return totalWithFine;
    }

    @Transactional
    public void finishTitle(PixPaymentResponseDto responseDto, Double total, Integer titleId) {
        var title = Title.findById(titleId);

        if (total == title.getBalance()) {
            title.setBalance(0.0d);
            title.setLiquidated(true);
        } else {
            title.setBalance(title.getBalance() - total);
        }

        title.persistAndFlush();
    }

}
