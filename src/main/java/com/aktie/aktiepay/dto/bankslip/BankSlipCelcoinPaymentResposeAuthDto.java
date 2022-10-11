package com.aktie.aktiepay.dto.bankslip;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinPaymentResposeAuthDto {
    
    @JsonbProperty("BlocoCompleto")
    private String externalAuth;

    public String getExternalAuth() {
        return externalAuth;
    }

    public void setExternalAuth(String externalAuth) {
        this.externalAuth = externalAuth;
    }

}
