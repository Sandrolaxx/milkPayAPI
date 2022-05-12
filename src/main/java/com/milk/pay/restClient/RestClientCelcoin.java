package com.milk.pay.restClient;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.milk.pay.dto.bankslip.BankSlipCelcoinBarcodeDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinPaymentDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinPaymentResposeDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.milk.pay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

/**
 *
 * @author SRamos
 */
@RegisterRestClient(configKey = "base-uri-pix-celcoin")
public interface RestClientCelcoin {

    // Pix Routes
    @POST
    @Path("/pix/v1/dict/key/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    PixKeyConsultResponseCelcoinDto consultKey(@HeaderParam("Authorization") String token,
            @PathParam("key") String key);

    @POST
    @Path("/pix/v1/payment")
    @Produces(MediaType.APPLICATION_JSON)
    PixPaymentResponseDto makePayment(@HeaderParam("Authorization") String token, PixPaymentCelcoinDto dto);

    // BankSlip Routes
    @POST
    @Path("/v5/transactions/billpayments/authorize")
    @Produces(MediaType.APPLICATION_JSON)
    BankSlipCelcoinResponseConsultDto consult(@HeaderParam("Authorization") String token,
            BankSlipCelcoinBarcodeDto dto);

    @POST
    @Path("/v5/transactions/billpayments")
    @Produces(MediaType.APPLICATION_JSON)
    BankSlipCelcoinPaymentResposeDto payment(@HeaderParam("Authorization") String token,
            BankSlipCelcoinPaymentDto dto);

    @PUT
    @Path("/v5/transactions/billpayments/{txId}/capture")
    @Produces(MediaType.APPLICATION_JSON)
    void confirmPayment(@HeaderParam("Authorization") String token, @PathParam("txId") Long txId, 
        Object emptyObject);

}
