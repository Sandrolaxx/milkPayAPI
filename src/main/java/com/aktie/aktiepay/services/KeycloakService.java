package com.aktie.aktiepay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.aktie.aktiepay.dto.user.CreateUserKeycloakDto;
import com.aktie.aktiepay.restClient.RestClientKeycloak;
import com.aktie.aktiepay.utils.RequestUtil;

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

        var tokenDto = restClientKey.getNewToken(basicToken, tokenReq);
        var token = tokenDto.getTokenType() + " " + tokenDto.getAccessToken();

        return token;

    }

    public Response createUserKeycloak(CreateUserKeycloakDto userDto) {
        return restClientKey.createUserKeycloak(getNewToken(), userDto);
    }

    public Response updateUserKeycloak(CreateUserKeycloakDto userDto, String userId) {
        return restClientKey.updateUserKeycloak(getNewToken(), userId, userDto);
    }

}
