package com.milk.pay.restClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.milk.pay.dto.user.CreateUserDto;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author SRamos
 */
@RegisterRestClient(configKey = "base-uri-external")
public interface RestClientUser {

    @POST
    @Path("/v1/3beb7c5e-440e-4fcc-b862-76207ae5d990")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    CreateUserDto getUserData(@HeaderParam("Authorization") String basicToken);
    
}
