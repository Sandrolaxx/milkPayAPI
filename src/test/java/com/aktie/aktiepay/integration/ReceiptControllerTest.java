package com.aktie.aktiepay.integration;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.aktie.aktiepay.MilkPayTestLifecycleManager;
import com.aktie.aktiepay.entities.enums.EnumErrorCode;
import com.aktie.aktiepay.util.TokenUtils;
import com.aktie.aktiepay.utils.AktiePayExceptionResponseDto;
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
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class ReceiptControllerTest {

    @Inject
    private TokenUtils tokenUtils;

    private String token;

    @BeforeEach
    void genereteToken() throws Exception {
        if (token == null) {
            token = tokenUtils.generateTokenTest("10564574902", "saporoxo");
        }
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token));
    }

    @Test
    @DataSet("scenario-test-receipt.json")
    public void whenGetValidReceipt() {

        var receipt = given()
                .when()
                .headers("txId", 1)
                .get("/milkpay-api/v1/receipt")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verifyJson(receipt);
    }

    @Test
    public void whenGetInvalidReceiptThenError() {

        var exceptionResponse = given()
                .when()
                .headers("txId", 47)
                .get("/milkpay-api/v1/receipt")
                .then()
                .statusCode(404)
                .extract().as(AktiePayExceptionResponseDto.class);

        assertTrue(exceptionResponse.getError().equalsIgnoreCase(EnumErrorCode.TX_ID_NAO_ENCONTRADO.getErro()));
        assertTrue(exceptionResponse.getErrorCode().equalsIgnoreCase("027"));
        assertTrue(exceptionResponse.getHttpCodeMessage().equalsIgnoreCase("Not Found"));
    }

}