package com.smartbr.dafe;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.smartbr.dafe.util.TokenUtils;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(DafeTestLifecycleManager.class)
public class PixControllerTest {

    private String token;

    @BeforeEach
    public void genereteToken() throws Exception {
        token = TokenUtils.generateTokenString("/JWTDafePay.json", null);
    }

    private RequestSpecification given() {
        return RestAssured.given()
        .contentType(ContentType.JSON).header(new Header("Authorization", "Bearer " + token));
    }


    @Test
    @DataSet("cenario-testCase02.json")
    public void testConsultPix() {
        String result = given()
            .header("txId", 1)
            .header("provider", "celcoin")
            .when().get("/dafe-api/v1/pix/static")
            .then()
            .statusCode(200)
            .extract().asString();

        Approvals.verifyJson(result);
    }


}