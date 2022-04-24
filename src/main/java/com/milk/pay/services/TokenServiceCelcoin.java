package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;

import com.milk.pay.restClient.RestClientTokenCelcoin;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class TokenServiceCelcoin {

    @Inject
    @RestClient
    RestClientTokenCelcoin restClient;

    @ConfigProperty(name = "celcoin.clientId")
    String clientId;

    @ConfigProperty(name = "celcoin.clientSecret")
    String clientSecret;

    @ConfigProperty(name = "celcoin.grantType")
    String grantType;

    public String tokenDto() {

        Form tokenReq = new Form()
                .param("client_id", clientId)
                .param("client_secret", clientSecret)
                .param("grant_type", grantType);

        var tokenDto = restClient.getNewToken(tokenReq);

        return tokenDto.getTokenType() + " " + tokenDto.getAccessToken();
    }

}
