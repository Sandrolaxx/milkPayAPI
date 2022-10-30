package com.aktie.aktiepay.integration;

import java.util.Map;

import javax.inject.Inject;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class TitleControllerTest {

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
    @Order(1)
    @DataSet("scenario-test-title.json")
    public void whenPostTitle() {
        var titleToCreate = Map.of(
                "externalId", 3433223,
                "userDocument", "10564574902",
                "amount", 126,
                "dailyInterest", 0.3,
                "dueDate", "15/11/2024",
                "inclusionDate", "29/11/2022 16:23:13",
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
    @Order(2)
    @DataSet("scenario-test-title.json")
    public void whenGetTitles() {
        var filterParams = Map.of(
                "offset", "22/06/2024",
                "limit", "20/07/2024",
                "pageIndex", "0",
                "pageSize", "5");

        var result = given()
                .when()
                .header("liquidated", false)
                .queryParams(filterParams)
                .get("/milkpay-api/v1/title")
                .then()
                .statusCode(200)
                .extract().asString();

        Approvals.verifyJson(result);
    }

    @Test
    @Order(3)
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