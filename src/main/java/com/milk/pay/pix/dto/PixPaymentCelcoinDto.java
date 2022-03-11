package com.milk.pay.pix.dto;

import javax.json.bind.annotation.JsonbProperty;

import com.milk.pay.entities.enums.EnumInitiationType;

public class PixPaymentCelcoinDto {

    private Double amount;

    private String clientCode;

    private String endToEndId;

    private PixPaymentDebitPartyDto debitParty;

    private PixPaymentCreditPartyDto creditParty;

    @JsonbProperty("transactionIdentification")
    private String txId;

    private EnumInitiationType initiationType;

    private String taxIdPaymentInitiator;

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getEndToEndId() {
        return this.endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public PixPaymentDebitPartyDto getDebitParty() {
        return this.debitParty;
    }

    public void setDebitParty(PixPaymentDebitPartyDto debitParty) {
        this.debitParty = debitParty;
    }

    public PixPaymentCreditPartyDto getCreditParty() {
        return this.creditParty;
    }

    public void setCreditParty(PixPaymentCreditPartyDto creditParty) {
        this.creditParty = creditParty;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public EnumInitiationType getInitiationType() {
        return initiationType;
    }

    public void setInitiationType(EnumInitiationType initiationType) {
        this.initiationType = initiationType;
    }

    public String getTaxIdPaymentInitiator() {
        return taxIdPaymentInitiator;
    }

    public void setTaxIdPaymentInitiator(String taxIdPaymentInitiator) {
        this.taxIdPaymentInitiator = taxIdPaymentInitiator;
    }

}
