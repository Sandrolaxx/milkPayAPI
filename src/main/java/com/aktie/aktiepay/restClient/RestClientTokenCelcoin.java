package com.aktie.aktiepay.restClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.aktie.aktiepay.dto.auth.TokenResponseCelcoinDto;

/**
 *
 * @author SRamos
 */
@RegisterRestClient(configKey = "base-uri-celcoin")
public interface RestClientTokenCelcoin {

    @POST
    @Path("/v5/token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    TokenResponseCelcoinDto getNewToken(Form tokenForm);

}
