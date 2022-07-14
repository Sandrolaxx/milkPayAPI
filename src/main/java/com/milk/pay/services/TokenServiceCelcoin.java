package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;

import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.restClient.RestClientTokenCelcoin;
import com.milk.pay.utils.MilkPayException;

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

    public String getToken() {

        Form tokenReq = new Form()
                .param("client_id", clientId)
                .param("client_secret", clientSecret)
                .param("grant_type", grantType);

        try {
            var tokenDto = restClient.getNewToken(tokenReq);
    
            return tokenDto.getTokenType() + " " + tokenDto.getAccessToken();
        } catch (Exception e) {
            throw new MilkPayException(EnumErrorCode.PARCEIRO_INDISPONIVEL);
        }    
    }

}
