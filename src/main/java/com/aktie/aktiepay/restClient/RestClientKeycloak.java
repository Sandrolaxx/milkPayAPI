package com.aktie.aktiepay.restClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.aktie.aktiepay.dto.user.CreateUserKeycloakDto;
import com.aktie.aktiepay.dto.user.TokenResponseDto;

/**
 *
 * @author SRamos
 */
@RegisterRestClient(configKey = "base-uri-keycloak")
public interface RestClientKeycloak {

    @POST
    @Path("/auth/realms/MilkPay/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    TokenResponseDto getNewToken(@HeaderParam("Authorization") String basicToken, Form tokenForm);
    
    @POST
    @Path("/auth/admin/realms/MilkPay/users")
    @Consumes(MediaType.APPLICATION_JSON)
    Response createUserKeycloak(@HeaderParam("Authorization") String tokenKeycloak,
            CreateUserKeycloakDto dto);
            
}
