package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import com.milk.pay.dto.user.CreateUserKeycloakDto;
import com.milk.pay.dto.user.TokenResponseDto;
import com.milk.pay.restClient.RestClientKeycloak;
import com.milk.pay.utils.RequestUtil;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class KeycloakService {

    @Inject
    @RestClient
    RestClientKeycloak restClientKey;

    @ConfigProperty(name = "the-ashen-one-username")
    String username;

    @ConfigProperty(name = "the-ashen-one-password")
    String password;

    @ConfigProperty(name = "the-ashen-one-auth")
    String auth;

    public String getNewToken() {

        var grantType = "password";
        var basicToken = RequestUtil.onlyBasic(auth);

        Form tokenReq = new Form()
                .param("grant_type", grantType)
                .param("username", username)
                .param("password", password);

        TokenResponseDto tokenDto = restClientKey.getNewToken(basicToken, tokenReq);
        var token = tokenDto.getTokenType() + " " + tokenDto.getAccessToken();

        return token;

    }

    public Response createUserKeycloak(CreateUserKeycloakDto userDto) {
        return restClientKey.createUserKeycloak(getNewToken(), userDto);
    }

}
