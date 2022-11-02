package com.aktie.aktiepay.integration;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import com.aktie.aktiepay.MilkPayTestLifecycleManager;
import com.aktie.aktiepay.dto.user.UserInfoDto;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTestResource(MilkPayTestLifecycleManager.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
public class UserControllerTest {

    @Inject
    private TokenUtils tokenUtils;

    @ConfigProperty(name = "the-ashen-one-username")
    private String admUser;

    @ConfigProperty(name = "the-ashen-one-password")
    private String admPass;

    @ConfigProperty(name = "base-uri-keycloak/mp-rest/url")
    private String keycloakUrl;

    private String tokenAdm;

    private String tokenUserTest;

    private String TEST_USER_DOCUMENT = "10664571101";

    private String TEST_USER_PASSWORD = "1234";

    private boolean IS_TEST_USER_CREATED = false;

    @BeforeEach
    void genereteToken() throws Exception {
        if (tokenAdm == null) {
            tokenAdm = tokenUtils.generateTokenTest(admUser, admPass);
        }
    }

    @AfterAll
    private void removeCreatedUser() throws Exception {
        var path = keycloakUrl.concat("/auth/admin/realms/MilkPay/users");

        var createdTestUser = given(tokenAdm)
                .when()
                .get(path)
                .getBody()
                .jsonPath()
                .getList("", KeycloakUserDto.class);

        createdTestUser.stream()
                .filter(user -> user.getUsername().equals(TEST_USER_DOCUMENT))
                .findFirst()
                .ifPresent(user -> {
                    given(tokenAdm)
                            .when()
                            .delete(path.concat("/").concat(user.getId()));
                });
    }

    @Test
    @Order(1)
    public void whenPostValidUser() throws Exception {
        var testUserBody = Map.of(
                "document", TEST_USER_DOCUMENT,
                "email", "teste@teste.com",
                "name", "Teste user 1",
                "password", TEST_USER_PASSWORD,
                "phone", "45991039812",
                "pixKey", "email@gmail.com");

        given(tokenAdm)
                .when()
                .body(testUserBody)
                .post("/milkpay-api/v1/user")
                .then()
                .statusCode(201);

        IS_TEST_USER_CREATED = true;
    }

    @Test
    @Order(2)
    public void whenPostInvalidUserThenError() {
        var testUserBody = Map.of("email", "teste@teste.com");

        given(tokenAdm)
                .when()
                .body(testUserBody)
                .post("/milkpay-api/v1/user")
                .then()
                .statusCode(400);
    }

    @Test
    @Order(3)
    public void whenPutUpdateUser() throws Exception {
        if (!IS_TEST_USER_CREATED) {
            whenPostValidUser();
        }

        if (tokenUserTest == null) {
            tokenUserTest = tokenUtils.generateTokenTest(TEST_USER_DOCUMENT, TEST_USER_PASSWORD);
        }

        var testUpdateUserBody = Map.of(
                "email", "teste_update@teste.com",
                "name", "Teste user updated",
                "password", "saporoxo");

        var updatedUser = given(tokenUserTest)
                .when()
                .body(testUpdateUserBody)
                .put("/milkpay-api/v1/user")
                .then()
                .statusCode(200)
                .extract().as(UserInfoDto.class);

        assertTrue(updatedUser.getEmail().equals("teste_update@teste.com"));
        assertTrue(updatedUser.getName().equals("Teste user updated"));
        assertTrue(updatedUser.getPassword().equals("saporoxo"));
    }

    @Test
    @Order(4)
    public void whenGetUser() throws Exception {
        var expectedPassword = TEST_USER_PASSWORD;

        if (!IS_TEST_USER_CREATED) {
            whenPostValidUser();
        }

        if (tokenUserTest == null) {
            tokenUserTest = tokenUtils.generateTokenTest(TEST_USER_DOCUMENT, TEST_USER_PASSWORD);
        } else {
            expectedPassword = "saporoxo";
        }

        var testUser = given(tokenUserTest)
                .when()
                .get("/milkpay-api/v1/user")
                .then()
                .statusCode(200)
                .extract().as(UserInfoDto.class);

        assertTrue(testUser.getDocument().equals(TEST_USER_DOCUMENT));
        assertTrue(testUser.getPassword().equals(expectedPassword));
    }

    private RequestSpecification given(String token) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header(new Header("Authorization", "Bearer ".concat(token)));
    }
}