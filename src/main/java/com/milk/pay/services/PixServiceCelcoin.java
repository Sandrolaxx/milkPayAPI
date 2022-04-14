package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.milk.pay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.IPixMapper;
import com.milk.pay.pattern.rest.RestClientPixCelcoin;
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
            return restClientPix.consultKey(tokenService.tokenDto(), key);
        } catch (WebApplicationException wae) {
            if (wae.getResponse() != null && wae.getResponse().getStatus() == 404) {
                throw new MilkPayException(EnumErrorCode.CHAVE_CONSULTADA_INEXISTENTE);
            }

            throw handlException(wae, EnumErrorCode.ERRO_CONSULTAR_CHAVE_PIX_CELCOIN);
        }
    }

    public PixPaymentResponseDto makePayment(PixPaymentCelcoinDto dto) {

        try {
            return restClientPix.makePayment(tokenService.tokenDto(), dto);
        } catch (WebApplicationException wae) {
            throw handlException(wae, EnumErrorCode.ERRO_PAGAMENTO_PIX_CELCOIN);
        }

    }

    public MilkPayException handlException(WebApplicationException wae, EnumErrorCode defaultError) {
        final PixPaymentResponseDto resp = WebApplicationExceptionConverter.convertExceptionToObject(wae,
                PixPaymentResponseDto.class);

        if (resp != null) {
            return new MilkPayException(
                    resp.getMessage() != null ? resp.getMessage() : resp.getDescription(),
                    StringUtil.toString(resp.getErrorCode()));
        } else {
            return new MilkPayException(defaultError);
        }
    }

}
