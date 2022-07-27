package com.smartbr.dafe.util;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import com.milk.pay.dto.user.TokenResponseDto;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

@RegisterRestClient(configKey = "base-uri-keycloak")
public interface RestClientTokenTest {
    
    @POST
    @Path("/auth/realms/DAFEPAY/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    TokenResponseDto getTokenTest(@HeaderParam String Authorization, Form tokenForm);

}
