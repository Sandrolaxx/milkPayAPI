package com.aktie.aktiepay.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.aktie.aktiepay.dto.ReceiptDto;
import com.aktie.aktiepay.services.ReceiptService;
import com.aktie.aktiepay.utils.MilkPayExceptionResponseDto;
import com.aktie.aktiepay.utils.ValidateUtil;

/**
 *
 * @author SRamos
 */
@Path("/milkpay-api/v1/receipt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Receipt")
public class ReceiptController {

    @Inject
    ReceiptService receiptService;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Comprovante consultado.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    public ReceiptDto create(@HeaderParam Integer txId) {

        ValidateUtil.validateTxId(txId);

        return receiptService.findAndGenerate(txId);
    
    }

}
