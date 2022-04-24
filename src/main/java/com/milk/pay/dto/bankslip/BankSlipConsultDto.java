package com.milk.pay.dto.bankslip;

/**
 *
 * @author SRamos
 */
public class BankSlipConsultDto {
    
    private String barcode;

    private String digitable;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDigitable() {
        return digitable;
    }

    public void setDigitable(String digitable) {
        this.digitable = digitable;
    }
    
}
