package com.milk.pay.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.milk.pay.dto.pix.PixKeyConsultResponseCelcoinDto;
import com.milk.pay.dto.pix.PixPaymentDto;
import com.milk.pay.dto.pix.PixPaymentResponseDto;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.services.PixService;
import com.milk.pay.services.PixServiceCelcoin;
import com.milk.pay.services.TitleService;
import com.milk.pay.utils.MilkPayException;
import com.milk.pay.utils.MilkPayExceptionResponseDto;
import com.milk.pay.utils.ValidateUtil;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

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
    PixService pixService;

    @Inject
    TitleService titleService;

    @Inject
    PixServiceCelcoin celcoinPixService;

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna Chave Pix consultada.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @GET
    @Path("/key")
    public PixKeyConsultResponseCelcoinDto consultKey(@HeaderParam String key) {

        if (key == null) {
            throw new MilkPayException(EnumErrorCode.CHAVE_PIX_NAO_INFORMADA);
        }

        return celcoinPixService.consultKey(key);

    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna comprovante de pagamento")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = MilkPayExceptionResponseDto.class)))
    @POST
    @Path("/payment")
    public PixPaymentResponseDto payment(PixPaymentDto paymentDto) {

        ValidateUtil.validatePixPaymentDto(paymentDto);

        var payment = pixService.persistPayment(paymentDto);

        paymentDto.setAmount(payment.getAmount());
        paymentDto.setTxId(payment.getId());

        var paymentCelcoinDto = pixService.createCelcoinDto(paymentDto);
        var responseDto = celcoinPixService.makePayment(paymentCelcoinDto);
        var receipt = pixService.savePaymentReceipt(responseDto, paymentDto);

        titleService.finishTitle(receipt.getPayment().getTitle().getId());
        
        responseDto.setSlip(receipt.getReceiptResume());

        return responseDto;

    }

}
