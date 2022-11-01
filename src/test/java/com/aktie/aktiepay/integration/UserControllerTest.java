package com.aktie.aktiepay.integration;

import java.util.Map;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.aktie.aktiepay.MilkPayTestLifecycleManager;
import com.aktie.aktiepay.integration.dto.KeycloakUserDto;
import com.aktie.aktiepay.util.TokenUtils;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;

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

    @ConfigProperty(name = "the-ashen-one-username")
    private String admUser;

    @ConfigProperty(name = "the-ashen-one-password")
    private String admPass;

    @ConfigProperty(name = "base-uri-keycloak/mp-rest/url")
    private String keycloakUrl;

    private String TEST_USER_DOCUMENT = "10664571101";

    @BeforeEach
    void genereteToken() throws Exception {
        if (token == null) {
            token = tokenUtils.generateTokenTest(admUser, admPass);
        }
    }

    private RequestSpecification given() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + token));
    }

    @Test
    public void whenPostValidUser() throws Exception {
        var testUserBody = Map.of(
                "document", TEST_USER_DOCUMENT,
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

        removeCreatedUser();
    }

    @Test
    public void whenPostInvalidUserThenError() {
        var testUserBody = Map.of("email", "teste@teste.com");

        given()
                .when()
                .body(testUserBody)
                .post("/milkpay-api/v1/user")
                .then()
                .statusCode(400);
    }

    private void removeCreatedUser() throws Exception {
        var path = keycloakUrl.concat("/auth/admin/realms/MilkPay/users");

        var createdTestUser = given()
                .when()
                .get(path)
                .getBody()
                .jsonPath()
                .getList("", KeycloakUserDto.class);

        createdTestUser.stream()
                .filter(user -> user.getUsername().equals(TEST_USER_DOCUMENT))
                .findFirst()
                .ifPresent(user -> {
                    given()
                            .when()
                            .delete(path.concat("/").concat(user.getId()));
                });
    }

}