package com.milk.pay.services;

import javax.enterprise.context.ApplicationScoped;

import com.milk.pay.dto.bankslip.BankSlipCelcoinBarcodeDto;
import com.milk.pay.dto.bankslip.BankSlipCelcoinResponseConsultDto;
import com.milk.pay.dto.bankslip.BankSlipConsultDto;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class BankSlipService {
    
    public BankSlipCelcoinResponseConsultDto consult(BankSlipConsultDto dto) {

        var barcodeDto = new BankSlipCelcoinBarcodeDto();

        barcodeDto.setDigitable(dto.getDigitable());
        barcodeDto.setBarCode(dto.getBarcode());

        //TODO
        return null;
    }

}
