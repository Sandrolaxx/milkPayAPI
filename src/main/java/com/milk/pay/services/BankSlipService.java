package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import com.milk.pay.dto.bankslip.BankSlipCelcoinBarcodeDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.milk.pay.dto.bankslip.BankSlipConsultDto;
import com.milk.pay.entities.enums.EnumErrorCode;
import com.milk.pay.restClient.RestClientCelcoin;
import com.milk.pay.utils.Utils;

import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class BankSlipService {
    
    @Inject
    TokenServiceCelcoin tokenService;

    @Inject
    @RestClient
    RestClientCelcoin restClient;

    public BankSlipCelcoinResponseConsultDto consult(BankSlipConsultDto dto) {

        try {//TODO fazer o mapping para BankSlipConsultResponseDto
            return restClient.consultBankSlip(tokenService.getToken(), new BankSlipCelcoinBarcodeDto(dto));
        } catch (WebApplicationException wae) {
            throw Utils.handleException(wae, EnumErrorCode.ERRO_AO_CADASTRAR_USUARIO);
        }

    }

}
