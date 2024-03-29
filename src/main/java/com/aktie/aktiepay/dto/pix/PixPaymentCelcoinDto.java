package com.aktie.aktiepay.dto.pix;

import java.math.BigDecimal;

import com.aktie.aktiepay.entities.enums.EnumInitiationType;

/**
 *
 * @author SRamos
 */
public class PixPaymentCelcoinDto {

    private BigDecimal amount;

    private String clientCode;

    private String endToEndId;

    private PixPaymentDebitPartyCelcoinDto debitParty;

    private PixPaymentCreditPartyCelcoinDto creditParty;

    private EnumInitiationType initiationType;

    private String taxIdPaymentInitiator;

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public PixPaymentDebitPartyCelcoinDto getDebitParty() {
        return this.debitParty;
    }

    public void setDebitParty(PixPaymentDebitPartyCelcoinDto debitParty) {
        this.debitParty = debitParty;
    }

    public PixPaymentCreditPartyCelcoinDto getCreditParty() {
        return this.creditParty;
    }

    public void setCreditParty(PixPaymentCreditPartyCelcoinDto creditParty) {
        this.creditParty = creditParty;
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
