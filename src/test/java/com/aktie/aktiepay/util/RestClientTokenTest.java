package com.aktie.aktiepay.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.aktie.aktiepay.dto.user.TokenResponseDto;

@RegisterRestClient(configKey = "base-uri-keycloak")
public interface RestClientTokenTest {
    
    @POST
    @Path("/auth/realms/MilkPay/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    TokenResponseDto getTokenTest(@HeaderParam("Authorization") String basicToken, Form tokenForm);

}
