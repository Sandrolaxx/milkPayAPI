package com.aktie.aktiepay.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class TokenUtils {

    @Inject
    @RestClient
    RestClientTokenTest restClient;

    public String generateTokenTest(String username, String password) throws Exception {

        var basicAuth = "Basic bWlsa3BheS1hcGk6ZmNhNDRkNzktNDE5OS00ZjE0LTkzOGQtNzEzNGMwZGY4NGU3";

        Form tokenReq = new Form()
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password");

        var tokenTest = restClient.getTokenTest(basicAuth, tokenReq);

        return tokenTest.getAccessToken();
    }

}
