package com.milk.pay.dto.bankslip;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankSlipConsultResponseDto {

    private Long transactionId;
    
    private String bank;

    private String digitable;

    private String payer;
    
    private String recipient;

    private String documentPayer;

    private String documentRecipient;

    private LocalDateTime dueDate;

    private BigDecimal discountValue;

    private BigDecimal fineValueCalculated;

    private BigDecimal interestValueCalculated;

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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
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

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getFineValueCalculated() {
        return fineValueCalculated;
    }

    public void setFineValueCalculated(BigDecimal fineValueCalculated) {
        this.fineValueCalculated = fineValueCalculated;
    }

    public BigDecimal getInterestValueCalculated() {
        return interestValueCalculated;
    }

    public void setInterestValueCalculated(BigDecimal interestValueCalculated) {
        this.interestValueCalculated = interestValueCalculated;
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
    
}
