package com.aktie.aktiepay.dto.bankslip;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinPaymentResposeDto {
    
    @JsonbProperty("transactionId")
    private Long txId;

    @JsonbProperty("authenticationAPI")
    private BankSlipCelcoinPaymentResposeAuthDto authentication;

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public BankSlipCelcoinPaymentResposeAuthDto getAuthentication() {
        return authentication;
    }

    public void setAuthentication(BankSlipCelcoinPaymentResposeAuthDto authentication) {
        this.authentication = authentication;
    }

}
