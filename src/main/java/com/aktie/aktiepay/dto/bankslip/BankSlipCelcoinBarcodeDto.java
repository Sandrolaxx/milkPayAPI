package com.aktie.aktiepay.dto.bankslip;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinBarcodeDto {
    
    private BankSlipConsultDto barcode;

    public BankSlipCelcoinBarcodeDto() {
    }

    public BankSlipCelcoinBarcodeDto(BankSlipConsultDto barcode) {
        this.barcode = barcode;
    }

    public BankSlipConsultDto getBarcode() {
        return barcode;
    }

    public void setBarcode(BankSlipConsultDto barcode) {
        this.barcode = barcode;
    }

}
