package com.aktie.aktiepay.dto.pix;

/**
 *
 * @author SRamos
 */
public class PixKeyOwnerResponseCelcoinDto {

    private String taxIdNumber;

    private String type;

    private String name;

    public String getTaxIdNumber() {
        return this.taxIdNumber;
    }

    public void setTaxIdNumber(String taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
