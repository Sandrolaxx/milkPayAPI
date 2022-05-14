package com.milk.pay.restClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

    @GET
    @Path("/v1/3beb7c5e-440e-4fcc-b862-76207ae5d990")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    CreateUserDto getUserData();
    
}
