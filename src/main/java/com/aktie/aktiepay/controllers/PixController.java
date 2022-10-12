package com.aktie.aktiepay.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.aktie.aktiepay.dto.ReceiptDto;
import com.aktie.aktiepay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.aktie.aktiepay.dto.pix.PixPaymentDto;
import com.aktie.aktiepay.services.PixServiceCelcoin;
import com.aktie.aktiepay.utils.AktiePayExceptionResponseDto;
import com.aktie.aktiepay.utils.ValidateUtil;

/**
 *
 * @author SRamos
 */
@Path("/milkpay-api/v1/pix")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pix")
public class PixController {

    @Inject
    PixServiceCelcoin celcoinPixService;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Chave Pix consultada.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @GET
    @Path("/key")
    public PixKeyConsultResponseCelcoinDto consultKey(@HeaderParam String key) {

        ValidateUtil.validatePixKey(key);

        return celcoinPixService.consultKey(key);

    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna comprovante de pagamento")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @POST
    @Path("/payment")
    public ReceiptDto payment(PixPaymentDto paymentDto) {

        ValidateUtil.validatePixPaymentDto(paymentDto);

        return celcoinPixService.makePayment(paymentDto);

    }

}
