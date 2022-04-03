package com.milk.pay.pattern.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.milk.pay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

/**
 *
 * @author SRamos
 */
@RegisterRestClient(configKey = "base-uri-openfinance-celcoin")
public interface RestClientPixCelcoin {

    @POST
    @Path("/pix/v1/dict/key/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    PixKeyConsultResponseCelcoinDto consultKey(@HeaderParam("Authorization") String token, @PathParam("key") String key);

    @POST
    @Path("/pix/v1/payment")
    @Produces(MediaType.APPLICATION_JSON)
    PixPaymentResponseDto makePayment(@HeaderParam("Authorization") String token, PixPaymentCelcoinDto dto);

}
