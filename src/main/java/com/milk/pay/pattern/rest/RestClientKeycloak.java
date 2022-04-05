package com.milk.pay.pattern.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.milk.pay.dto.user.CreateUserKeycloakDto;
import com.milk.pay.dto.user.TokenResponseDto;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author SRamos
 */
@RegisterRestClient(baseUri = "http://localhost:9091/auth")
public interface RestClientKeycloak {

    @POST
    @Path("/realms/DonaFrost/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    TokenResponseDto getNewToken(@HeaderParam("Authorization") String basicToken, Form tokenForm);

    @POST
    @Path("/admin/realms/DonaFrost/users")
    Response createUserKeycloak(@HeaderParam("Authorization") String tokenKeycloak,
            CreateUserKeycloakDto dto);
            
}
