package com.milk.pay.pix.dto;

public class PixReversalCelcoinDto {

    private Long transactionIdentification;

    private Double amount;

    private String reason;

    private String additionalInformation;

    public Long getTransactionIdentification() {
        return this.transactionIdentification;
    }

    public void setTransactionIdentification(Long transactionIdentification) {
        this.transactionIdentification = transactionIdentification;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
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
