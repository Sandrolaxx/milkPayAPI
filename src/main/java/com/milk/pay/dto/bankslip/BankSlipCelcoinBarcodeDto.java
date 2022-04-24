package com.milk.pay.dto.bankslip;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinBarcodeDto {
    
    private Integer type;

    private String barCode;

    private String digitable;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDigitable() {
        return digitable;
    }

    public void setDigitable(String digitable) {
        this.digitable = digitable;
    }

}
