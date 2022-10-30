package com.aktie.aktiepay.integration;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import javax.inject.Inject;

import org.approvaltests.Approvals;
import org.jboss.resteasy.annotations.Body;
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
public class UserControllerTest {

    @Inject
    private TokenUtils tokenUtils;

    private String token;

    @BeforeEach
    void genereteToken() throws Exception {
        if (token == null) {
            token = tokenUtils.generateTokenTest("milkpay-admin", "ad4cefd5-98fc-437e-a4bb-9ce6f3b9614d");
        }
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token));
    }

    @Test
    public void whenPostValidUser() {
        var testUserBody = Map.of(
                "document", "10664574901",
                "email", "teste@teste.com",
                "name", "Teste user 1",
                "password", "1234",
                "phone", "45991039812",
                "pixKey", "email@gmail.com");

        given()
                .when()
                .body(testUserBody)
                .post("/milkpay-api/v1/user")
                .then()
                .statusCode(201);
    }

    @Test
    public void whenGetInvalidUserThenError() {
        var testUserBody = Map.of("email", "teste@teste.com");

        given()
                .when()
                .body(testUserBody)
                .post("/milkpay-api/v1/user")
                .then()
                .statusCode(400);
    }

}