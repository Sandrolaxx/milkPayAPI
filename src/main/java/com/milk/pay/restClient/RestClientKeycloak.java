package com.milk.pay.restClient;

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
@RegisterRestClient(configKey = "base-uri-keycloak")
public interface RestClientKeycloak {

    @POST
    @Path("/realms/MilkPay/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    TokenResponseDto getNewToken(@HeaderParam("Authorization") String basicToken, Form tokenForm);
    
    @POST
    @Path("/admin/realms/MilkPay/users")
    @Consumes(MediaType.APPLICATION_JSON)
    Response createUserKeycloak(@HeaderParam("Authorization") String tokenKeycloak,
            CreateUserKeycloakDto dto);
            
}
