package com.aktie.aktiepay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.aktie.aktiepay.dto.ReceiptDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinBarcodeDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentResposeDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultResponseDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipPaymentDto;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.restClient.RestClientCelcoin;
import com.aktie.aktiepay.utils.DateUtil;
import com.aktie.aktiepay.utils.Utils;

import io.vertx.core.json.Json;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class BankSlipServiceCelcoin {

    @Inject
    TokenServiceCelcoin tokenService;

    @Inject
    BankSlipService bankSlipService;

    @Inject
    @RestClient
    RestClientCelcoin restClient;

    @ConfigProperty(name = "milk.taxId")
    String defaultDocument;

    public BankSlipConsultResponseDto consult(BankSlipConsultDto dto) {

        try {
            var response = restClient.consult(tokenService.getToken(), new BankSlipCelcoinBarcodeDto(dto));

            return bankSlipService.parseToConsultResponseDto(response);
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_CONSULTAR_BOLETO);
        }

    }

    public ReceiptDto payment(BankSlipPaymentDto dto) {

        var celcoinPaymentDto = bankSlipService.parseToCelcoinPaymentDto(dto);
        var token = tokenService.getToken();

        celcoinPaymentDto.setDocument(defaultDocument);
        celcoinPaymentDto.setDueDate(DateUtil.LocalDateToYYYYMMDDTHHMMZ(dto.getDueDate()));

        var paymentResponse = makePayment(celcoinPaymentDto, token);

        confirmPayment(paymentResponse.getTxId(), token);
        bankSlipService.persistSuccessfulPayment(celcoinPaymentDto, dto.getTitleId());

        var receiptImage = bankSlipService.persistReceipt(paymentResponse, dto);

        return new ReceiptDto(receiptImage);

    }

    private BankSlipCelcoinPaymentResposeDto makePayment(BankSlipCelcoinPaymentDto dto, String token) {

        try {
            return restClient.payment(token, dto);
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_PAGAMENTO_BOLETO);
        }

    }

    private void confirmPayment(Long authenticationTxId, String token) {

        try {
            restClient.confirmPayment(token, authenticationTxId, new Json());
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_CONFIRMAR_PAGAMENTO_BOLETO);
        }

    }

}
