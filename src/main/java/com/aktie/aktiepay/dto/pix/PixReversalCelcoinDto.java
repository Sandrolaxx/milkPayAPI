package com.aktie.aktiepay.dto.pix;

import java.math.BigDecimal;

/**
 *
 * @author SRamos
 */
public class PixReversalCelcoinDto {

    private Long transactionIdentification;

    private BigDecimal amount;

    private String reason;

    private String additionalInformation;

    public Long getTransactionIdentification() {
        return this.transactionIdentification;
    }

    public void setTransactionIdentification(Long transactionIdentification) {
        this.transactionIdentification = transactionIdentification;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAdditionalInformation() {
        return this.additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
