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
public class TitleControllerTest {

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
    @DataSet("scenario-test-title.json")
    public void whenPostTitle() {

        var titleToCreate = Map.of(
                "externalId", 3433223,
                "userDocument", "10564574902",
                "amount", 126,
                "dailyInterest", 0.3,
                "dueDate", "15/08/2024",
                "inclusionDate", "29/06/2022 16:23:13",
                "nfNumber", "NF-322444",
                "paymentType", "PIX");

        given()
                .when()
                .body(titleToCreate)
                .post("/milkpay-api/v1/title")
                .then()
                .statusCode(201);
    }

    @Test
    @DataSet("scenario-test-title.json")
    public void whenGetTitle() {
        var result = given()
                .when().get("/milkpay-api/v1/title")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verifyJson(result);
    }

    @Test
    @DataSet("scenario-test-title.json")
    public void whenGetTotalizers() {
        var result = given()
                .when().get("/milkpay-api/v1/title/totalizers")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verifyJson(result);
    }

}