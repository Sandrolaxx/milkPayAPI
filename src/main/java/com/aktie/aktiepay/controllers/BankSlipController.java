package com.aktie.aktiepay.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.aktie.aktiepay.dto.ReceiptDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipConsultResponseDto;
import com.aktie.aktiepay.dto.bankslip.BankSlipPaymentDto;
import com.aktie.aktiepay.services.BankSlipServiceCelcoin;
import com.aktie.aktiepay.utils.AktiePayExceptionResponseDto;
import com.aktie.aktiepay.utils.ValidateUtil;

/**
 *
 * @author SRamos
 */
@Path("/milkpay-api/v1/bankslip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "BankSlip")
public class BankSlipController {

    @Inject
    BankSlipServiceCelcoin bankSlipServiceCelcoin;
    
    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna os dados do boleto consultado.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @POST
    @Path("/consult")
    public BankSlipConsultResponseDto consult(BankSlipConsultDto bankSlipConsultDto) {

        ValidateUtil.validateConsultTitle(bankSlipConsultDto);

        return bankSlipServiceCelcoin.consult(bankSlipConsultDto);
        
    }

    @APIResponse(responseCode = "200", description = "Caso sucesso, retorna os dados do boleto pago.")
    @APIResponse(responseCode = "400", content = @Content(schema = @Schema(allOf = AktiePayExceptionResponseDto.class)))
    @POST
    @Path("/payment")
    public ReceiptDto payment(BankSlipPaymentDto bankSlipPaymentDto) {

        ValidateUtil.validatePaymentTitle(bankSlipPaymentDto);

        return bankSlipServiceCelcoin.payment(bankSlipPaymentDto);
        
    }

}
