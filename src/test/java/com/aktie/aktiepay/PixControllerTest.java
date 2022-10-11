package com.aktie.aktiepay;

import java.util.Map;

import javax.inject.Inject;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
public class PixControllerTest {

    @Inject
    private TokenUtils tokenUtils;

    private String token;

    @BeforeEach
    public void genereteToken() throws Exception {
        token = tokenUtils.generateTokenTest("10564574902", "1234");
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token));
    }

    @Test
    @DataSet("scenario-test-pix.json")
    public void whenPostPixPayment() {

        var pixToPay = Map.of(
                "titleId", 26,
                "endToEndId", "E1393589320220807192000169617934",
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

        Approvals.verifyJson(result);
    }

    @Test
    public void whenGetPixKey() {
        var result = given()
                .when()
                .header("key", "email@gmail.com")
                .get("/milkpay-api/v1/pix/key")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verifyJson(result);
    }

}