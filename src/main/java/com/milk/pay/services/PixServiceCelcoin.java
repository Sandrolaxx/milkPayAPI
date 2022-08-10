package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.milk.pay.dto.ReceiptDto;
import com.milk.pay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.IPixMapper;
import com.milk.pay.restClient.RestClientCelcoin;
import com.milk.pay.utils.MilkPayException;
import com.milk.pay.utils.Utils;

@ApplicationScoped
public class PixServiceCelcoin {

    @Inject
    TokenServiceCelcoin tokenService;

    @Inject
    @RestClient
    RestClientCelcoin restClient;

    @Inject
    IPixMapper pixMapper;

    @Inject
    PixService pixService;

    @ConfigProperty(name = "celcoin.pix.key")
    String pixKey;

    public PixKeyConsultResponseCelcoinDto consultKey(String key) throws MilkPayException {

        try {
            return restClient.consultKey(tokenService.getToken(), key);
        } catch (WebApplicationException wae) {
            if (wae.getResponse() != null && wae.getResponse().getStatus() == 404) {
                throw new MilkPayException(EnumErrorCode.CHAVE_CONSULTADA_INEXISTENTE);
            }

            throw Utils.handleException(wae, EnumErrorCode.ERRO_CONSULTAR_CHAVE_PIX);
        }

    }

    public ReceiptDto makePayment(PixPaymentDto paymentDto) {

        try {
            var payment = pixService.prePersistPayment(paymentDto.getTitleId());
            
            paymentDto.setTxId(payment.getId());
            paymentDto.setAmount(payment.getReceivedAmount());

            var paymentCelcoinDto = pixService.createCelcoinDto(paymentDto);
            var paymentResponse = restClient.makePayment(tokenService.getToken(), paymentCelcoinDto);
            var receiptImage = pixService.savePaymentReceipt(paymentResponse, paymentDto);

            pixService.persistSuccessfulPayment(paymentDto, paymentResponse.getEndToEndId());

            return new ReceiptDto(paymentDto.getTxId(), receiptImage);
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_PAGAMENTO_PIX);
        }

    }

}
