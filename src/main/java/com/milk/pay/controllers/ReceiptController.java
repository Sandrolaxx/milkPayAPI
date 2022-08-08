package com.milk.pay.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

import com.milk.pay.utils.MilkPayExceptionResponseDto;
import com.milk.pay.utils.ReceiptUtil;

/**
 *
 * @author SRamos
 */
@Path("/milkpay-api/v1/receipt")
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Receipt")
public class ReceiptController {

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Comprovante consultado.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @POST
    public String consultKey(@HeaderParam Integer txId) {
        return ReceiptUtil.findAndCreate(txId);
    }

}
