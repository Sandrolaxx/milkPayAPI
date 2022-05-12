package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.milk.pay.dto.PaymentResponseDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinBarcodeDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinPaymentResposeDto;
import com.milk.pay.dto.bankslip.BankSlipConsultDto;
import com.milk.pay.dto.bankslip.BankSlipConsultResponseDto;
import com.milk.pay.dto.bankslip.BankSlipPaymentDto;
import com.milk.pay.entities.Payment;
import com.milk.pay.entities.ReceiptInfo;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.mapper.IBankSlipMapper;
import com.milk.pay.restClient.RestClientCelcoin;
import com.milk.pay.utils.DateUtil;
import com.milk.pay.utils.Utils;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.vertx.core.json.Json;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class BankSlipService {

    @Inject
    TokenServiceCelcoin tokenService;

    @Inject
    IBankSlipMapper mapper;

    @Inject
    @RestClient
    RestClientCelcoin restClient;

    @ConfigProperty(name = "milk.taxId")
    String defaultDocument;

    public BankSlipConsultResponseDto consult(BankSlipConsultDto dto) {

        try {
            var response = restClient.consult(tokenService.getToken(), new BankSlipCelcoinBarcodeDto(dto));

            return mapper.bankSlipDtoToResponseDto(response);
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_CONSULTAR_BOLETO);
        }

    }

    public PaymentResponseDto payment(BankSlipPaymentDto dto) {

        var celcoinPaymentDto = mapper.bankSlipPaymentDtoToCelcoinPaymentDto(dto);
        var token = tokenService.getToken();

        celcoinPaymentDto.setDocument(defaultDocument);
        celcoinPaymentDto.setDueDate(DateUtil.LocalDateToYYYYMMDDTHHMMZ(dto.getDueDate()));

        var paymentResponse = makePayment(celcoinPaymentDto, token);

        confirmPayment(paymentResponse.getTxId(), token);

        var paymentEntity = persistSuccessfulPayment(celcoinPaymentDto);
        var receipt = getEndPersistReceipt(paymentEntity, paymentResponse);

        return new PaymentResponseDto(receipt.getPayment().getId(), receipt.getReceiptResume());

    }

    private BankSlipCelcoinPaymentResposeDto makePayment(BankSlipCelcoinPaymentDto dto, String token) {

        try {
            return restClient.payment(token, dto);
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_AO_CADASTRAR_USUARIO);
        }

    }

    private void confirmPayment(Long authenticationTxId, String token) {

        try {
            restClient.confirmPayment(token, authenticationTxId, new Json());
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_AO_CADASTRAR_USUARIO);
        }

    }

    private Payment persistSuccessfulPayment(BankSlipCelcoinPaymentDto celcoinDto) {
        //TODO persistir pagamento realizado
        return null;
    }

    private ReceiptInfo getEndPersistReceipt(Payment paymentEntity,
            BankSlipCelcoinPaymentResposeDto paymentResponse) {
        //TODO criar layout do comprovante
        return null;
    }

}
