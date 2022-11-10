package com.aktie.aktiepay.integration;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.aktie.aktiepay.MilkPayTestLifecycleManager;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultResponseDto;
import com.aktie.aktiepay.util.TokenUtils;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

@DBRider
@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class BankSlipControllerTest {

    @Inject
    TokenUtils tokenUtils;

    private String token;

    private BankSlipConsultResponseDto responseConsultTest;

    private final String DIGITABLE_TEST = "23793381286008301352856000063307789840000150000";

    @BeforeEach
    public void genereteToken() throws Exception {
        if (token == null) {
            token = tokenUtils.generateTokenTest("00000000000", "teste");
        }
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token));
    }

    @Test
    @Order(1)
    public void whenPostBankslipConsult() {
        var expectedPayer = "PAGADOR AMBIENTE HOMOLOGACAO";
        var expectedRecipient = "BENEFICIARIO AMBIENTE HOMOLOGACAO";

        responseConsultTest = getBankslipConsultData();

        assertTrue(responseConsultTest.getPayerName().equalsIgnoreCase(expectedPayer));
        assertTrue(responseConsultTest.getReceiverName().equalsIgnoreCase(expectedRecipient));
        assertTrue(responseConsultTest.getDigitable().equalsIgnoreCase(DIGITABLE_TEST));
    }

    @Test
    @Order(2)
    @DataSet(value = "scenario-test-bankslip.json")
    public void whenPostBankSlipPayment() {
        System.out.println(token);
        
        if (responseConsultTest == null) {
            responseConsultTest = getBankslipConsultData();
        }

        var bankSlipToPay = Map.of(
                "titleId", "1",
                "digitable", responseConsultTest.getDigitable(),
                "amount", responseConsultTest.getAmount(),
                "dueDate", responseConsultTest.getDueDate(),
                "receiverBank", responseConsultTest.getReceiverBank(),
                "receiverName", responseConsultTest.getReceiverName(),
                "receiverDocument", responseConsultTest.getReceiverDocument(),
                "transactionId", responseConsultTest.getTransactionId());

        var result = given()
                .when()
                .body(bankSlipToPay)
                .post("/milkpay-api/v1/bankslip/payment")
                .then()
                .statusCode(200)
                .extract().asString();

        assertTrue(result.contains("receiptImage"));
    }

    private BankSlipConsultResponseDto getBankslipConsultData() {
        return given()
                .when()
                .body(Map.of("digitable", DIGITABLE_TEST))
                .post("/milkpay-api/v1/bankslip/consult")
                .then()
                .statusCode(200)
                .extract().as(BankSlipConsultResponseDto.class);
    }

}