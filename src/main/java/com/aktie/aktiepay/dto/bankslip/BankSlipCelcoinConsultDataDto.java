package com.aktie.aktiepay.dto.bankslip;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinConsultDataDto {
    
    private String documentRecipient;

    private String documentPayer;

    private LocalDateTime payDueDate;

    private LocalDateTime nextBusinessDay;

    private LocalDateTime dueDateRegister;

    private boolean allowChangeValue;

    private String recipient;

    private String payer;

    private BigDecimal discountValue;

    private BigDecimal interestValueCalculated;

    private BigDecimal maxValue;

    private BigDecimal minValue;

    private BigDecimal fineValueCalculated;

    private BigDecimal originalValue;

    private BigDecimal totalUpdated;

    private BigDecimal totalWithDiscount;

    private BigDecimal totalWithAdditional;

    public String getDocumentRecipient() {
        return documentRecipient;
    }

    public void setDocumentRecipient(String documentRecipient) {
        this.documentRecipient = documentRecipient;
    }

    public String getDocumentPayer() {
        return documentPayer;
    }

    public void setDocumentPayer(String documentPayer) {
        this.documentPayer = documentPayer;
    }

    public LocalDateTime getPayDueDate() {
        return payDueDate;
    }

    public void setPayDueDate(LocalDateTime payDueDate) {
        this.payDueDate = payDueDate;
    }

    public LocalDateTime getDueDateRegister() {
        return dueDateRegister;
    }

    public void setDueDateRegister(LocalDateTime dueDateRegister) {
        this.dueDateRegister = dueDateRegister;
    }

    public LocalDateTime getNextBusinessDay() {
        return nextBusinessDay;
    }

    public void setNextBusinessDay(LocalDateTime nextBusinessDay) {
        this.nextBusinessDay = nextBusinessDay;
    }

    public boolean isAllowChangeValue() {
        return allowChangeValue;
    }

    public void setAllowChangeValue(boolean allowChangeValue) {
        this.allowChangeValue = allowChangeValue;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getInterestValueCalculated() {
        return interestValueCalculated;
    }

    public void setInterestValueCalculated(BigDecimal interestValueCalculated) {
        this.interestValueCalculated = interestValueCalculated;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getFineValueCalculated() {
        return fineValueCalculated;
    }

    public void setFineValueCalculated(BigDecimal fineValueCalculated) {
        this.fineValueCalculated = fineValueCalculated;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public BigDecimal getTotalUpdated() {
        return totalUpdated;
    }

    public void setTotalUpdated(BigDecimal totalUpdated) {
        this.totalUpdated = totalUpdated;
    }

    public BigDecimal getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(BigDecimal totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }

    public BigDecimal getTotalWithAdditional() {
        return totalWithAdditional;
    }

    public void setTotalWithAdditional(BigDecimal totalWithAdditional) {
        this.totalWithAdditional = totalWithAdditional;
    }

}
