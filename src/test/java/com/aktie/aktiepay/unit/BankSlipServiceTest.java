package com.aktie.aktiepay.unit;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.aktie.aktiepay.MilkPayTestLifecycleManager;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinBillDataDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentResposeAuthDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinPaymentResposeDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipPaymentDto;
import com.aktie.aktiepay.services.BankSlipService;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@DBRider
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class BankSlipServiceTest {

    @Inject
    BankSlipService bankSlipService;
    
    @Test
    @DataSet(value = "scenario-test-bankslip.json")
    public void whenPersistSuccessfulPayment() {
        var celcoinDto = new BankSlipCelcoinPaymentDto();
        var barcodeDto = new BankSlipConsultDto();
        var billDataDto = new BankSlipCelcoinBillDataDto();

        barcodeDto.setDigitable("23793381286008301352856000063307789840000150000");
        billDataDto.setValue(new BigDecimal(1500));
        
        celcoinDto.setDocument("40828547000102");
        celcoinDto.setDueDate("2022-11-01T00:00:00Z");
        celcoinDto.setTransactionId(Long.valueOf(816873880));
        celcoinDto.setBarcode(barcodeDto);
        celcoinDto.setBillData(billDataDto);

        var payment = bankSlipService.persistSuccessfulPayment(celcoinDto, 1);

        assertTrue(payment.getRequestedAmount().equals(BigDecimal.valueOf(1500)));
        assertTrue(payment.getTitle().getId().equals(1));
    }

    @Test
    @DataSet(value = "scenario-test-bankslip-receipt.json")
    public void whenPersistReceipt() {
        var responseDto = new BankSlipCelcoinPaymentResposeDto();
        var auth = new BankSlipCelcoinPaymentResposeAuthDto();
        var paymentDto = getBankSlipPaymentDto();

        auth.setExternalAuth("53.45.C5.29.AA.C5.16.88.D7.28.CA.1F.73.44.0D.71");
        responseDto.setTxId(Long.valueOf(816874281));
        responseDto.setAuthentication(auth);
        
        var receiptBase64 = bankSlipService.persistReceipt(responseDto, paymentDto);

        assertTrue(receiptBase64 != null && receiptBase64 instanceof String);
    }
    
    @Test
    public void whenParseToConsultResponseDto() {
        var paymentDto = getBankSlipPaymentDto();
        
        var celcoinPaymentDto = bankSlipService.parseToCelcoinPaymentDto(paymentDto);

        assertTrue(celcoinPaymentDto != null);
        assertTrue(celcoinPaymentDto.getBarcode().getDigitable().equals(paymentDto.getDigitable()));
        assertTrue(celcoinPaymentDto.getTransactionId() == paymentDto.getTransactionId());
    }

    @Test
    public void whenParseToCelcoinPaymentDto() {
        var consultResponseDto = getBankSlipCelcoinResponseConsultDto();

        var parsedConsultResponse = bankSlipService.parseToConsultResponseDto(consultResponseDto);

        assertTrue(parsedConsultResponse != null);
        assertTrue(parsedConsultResponse.getDigitable().equals(consultResponseDto.getDigitable()));
        assertTrue(parsedConsultResponse.getTransactionId() == consultResponseDto.getTransactionId());
    }

    private BankSlipPaymentDto getBankSlipPaymentDto() {
        var paymentDto = new BankSlipPaymentDto();

        paymentDto.setDigitable("23793381286008301352856000063307789840000150000");
        paymentDto.setAmount(new BigDecimal(1500));
        paymentDto.setDueDate(LocalDate.now());
        paymentDto.setTransactionId(Long.valueOf(816873880));
        paymentDto.setReceiverBank("BANCO BRADESCO S.A.");
        paymentDto.setReceiverName("BENEFICIARIO AMBIENTE HOMOLOGACAO");
        paymentDto.setTitleId(1);

        return paymentDto;
    }

    private BankSlipCelcoinResponseConsultDto getBankSlipCelcoinResponseConsultDto() {
        var consultResponseDto = new BankSlipCelcoinResponseConsultDto();

        consultResponseDto.setAssignor("BANCO BRADESCO S.A.");
        consultResponseDto.setDigitable("23793381286008301352856000063307789840000150000");
        consultResponseDto.setTransactionId(Long.valueOf(816874280));
        consultResponseDto.setValue(new BigDecimal(1500));
        consultResponseDto.setDueDate(LocalDateTime.now());

        return consultResponseDto;
    }

}
