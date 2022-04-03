package com.smartbr.dafe.util;

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

        String basicAuth = "Basic ZGFmZS1hcGk6YzAxMzc2YTUtOWJiMy00N2YxLWFkNDAtYTM4MTc0ZTAzMzdm";

        Form tokenReq = new Form()
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password");

        var tokenTest = restClient.getTokenTest(basicAuth, tokenReq);

        return tokenTest.getAccessToken();
    }

}
