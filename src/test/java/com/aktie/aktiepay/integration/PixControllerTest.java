package com.aktie.aktiepay.integration;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.aktie.aktiepay.MilkPayTestLifecycleManager;
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
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
public class PixControllerTest {

    @Inject
    TokenUtils tokenUtils;

    private String token;

    @BeforeEach
    public void genereteToken() throws Exception {
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
    @DataSet(value = "scenario-test-pix.json", executeStatementsBefore = "SELECT SETVAL('GEN_MILK_PIX_PAYMENT', floor(random() * 10000000 + 1)::int)")
    public void whenPostPixPayment() {
        var endToEnd = "E139358932022080719".concat(String.valueOf(new Date().getTime()));

        var pixToPay = Map.of(
                "titleId", 26,
                "endToEndId", endToEnd,
                "receiverKey", "email@gmail.com",
                "receiverBank", "18236120",
                "receiverAccount", "0000372163",
                "receiverBranch", 3,
                "receiverDocument", "04219219000480",
                "receiverAccountType", "CACC",
                "receiverName", "FLOHA COM. DE ALIMENTOS LTDA");

        var result = given()
                .when()
                .body(pixToPay)
                .post("/milkpay-api/v1/pix/payment")
                .then()
                .statusCode(200)
                .extract().asString();

        assertTrue(result.contains("receiptImage"));
    }

    @Test
    public void whenGetPixKey() {
        var expectedTaxId = "04219219000480";
        var expectedAccountNumber = "0000372163";

        var result = given()
                .when()
                .header("key", "email@gmail.com")
                .get("/milkpay-api/v1/pix/key")
                .then()
                .statusCode(200)
                .extract().asString();

        assertTrue(result.contains(expectedTaxId));
        assertTrue(result.contains(expectedAccountNumber));
    }

}