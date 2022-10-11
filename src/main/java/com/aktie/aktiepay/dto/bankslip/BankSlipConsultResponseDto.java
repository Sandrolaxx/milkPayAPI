package com.aktie.aktiepay.dto.bankslip;

import java.math.BigDecimal;

/**
 *
 * @author SRamos
 */
public class BankSlipConsultResponseDto {

    private Long transactionId;
    
    private String bank;

    private String digitable;

    private String payer;
    
    private String recipient;

    private String documentPayer;

    private String documentRecipient;

    private String dueDate;

    private BigDecimal discount;

    private BigDecimal fine;

    private BigDecimal interest;

    private BigDecimal amount;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDigitable() {
        return digitable;
    }

    public void setDigitable(String digitable) {
        this.digitable = digitable;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDocumentPayer() {
        return documentPayer;
    }

    public void setDocumentPayer(String documentPayer) {
        this.documentPayer = documentPayer;
    }

    public String getDocumentRecipient() {
        return documentRecipient;
    }

    public void setDocumentRecipient(String documentRecipient) {
        this.documentRecipient = documentRecipient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
    
}
