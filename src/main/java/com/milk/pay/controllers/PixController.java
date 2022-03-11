package com.milk.pay.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.pix.dto.PixKeyConsultResponseDto;
import com.milk.pay.pix.dto.PixPaymentResponseDto;
import com.milk.pay.pix.dto.PixTransferDto;
import com.milk.pay.pix.service.PixService;
import com.milk.pay.pix.service.PixServiceCelcoin;
import com.milk.pay.utils.MilkPayException;
import com.milk.pay.utils.MilkPayExceptionResponseDto;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/milkPay/v1/pix")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pix")
public class PixController {

    @Inject
    PixServiceCelcoin celcoinPixService;

    @Inject
    PixService pixService;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Chave Pix consultada.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    @Path("/key/{key}")
    public PixKeyConsultResponseDto consultKey(@PathParam("key") String key) {

        var responseDto = new PixKeyConsultResponseDto();

        if (key == null) {
            throw new MilkPayException(EnumErrorCode.CHAVE_PIX_NAO_INFORMADA);
        }

        responseDto = celcoinPixService.consultKey(key);

        if (responseDto == null) {
            throw new MilkPayException(EnumErrorCode.ERRO_CONSULTAR_CHAVE_PIX_CELCOIN);
        }

        return responseDto;

    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna comprovante de pagamento")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @POST
    @Path("/payment/{titleId}/{amount}")
    public PixPaymentResponseDto payment(@PathParam("titleId") Integer titleId, @PathParam("amount")  Double amount) {

        PixTransferDto dto = new PixTransferDto();
        dto.setTitleId(titleId);
        dto.setAmount(amount);

        var paymentDto = celcoinPixService.getPixTransferDto(dto);
        var paymentCelcoinDto = pixService.createCelcoinDto(paymentDto, dto.getTitleId().toString());
        var responseDto = celcoinPixService.makePayment(paymentCelcoinDto);

        if (responseDto == null
            || responseDto.getEndToEndId() == null) {
            throw new MilkPayException(EnumErrorCode.ERRO_PAGAMENTO_PIX_CELCOIN);
        }

        responseDto.setSlip(pixService.savePaymentReceipt(responseDto, paymentDto));
        celcoinPixService.finishTitle(responseDto, dto.getAmount(), dto.getTitleId());

        return responseDto;

    }

}
