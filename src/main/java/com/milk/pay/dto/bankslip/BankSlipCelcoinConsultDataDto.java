package com.milk.pay.dto.bankslip;

import java.time.LocalDateTime;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinConsultDataDto {
    
    private String documentRecipient;

    private String documentPayer;

    private LocalDateTime payDueLocalDate;

    private LocalDateTime nextBusinessDay;

    private LocalDateTime dueLocalDateTimeRegister;

    private boolean allowChangeValue;

    private String recipient;

    private String payer;

    private Double discountValue;

    private Double interestValueCalculated;

    private Double maxValue;

    private Double minValue;

    private Double fineValueCalculated;

    private Double originalValue;

    private Double totalUpdated;

    private Double totalWithDiscount;

    private Double totalWithAdditional;

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

    public LocalDateTime getPayDueLocalDate() {
        return payDueLocalDate;
    }

    public void setPayDueLocalDate(LocalDateTime payDueLocalDate) {
        this.payDueLocalDate = payDueLocalDate;
    }

    public LocalDateTime getNextBusinessDay() {
        return nextBusinessDay;
    }

    public void setNextBusinessDay(LocalDateTime nextBusinessDay) {
        this.nextBusinessDay = nextBusinessDay;
    }

    public LocalDateTime getDueLocalDateTimeRegister() {
        return dueLocalDateTimeRegister;
    }

    public void setDueLocalDateTimeRegister(LocalDateTime dueLocalDateTimeRegister) {
        this.dueLocalDateTimeRegister = dueLocalDateTimeRegister;
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

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getInterestValueCalculated() {
        return interestValueCalculated;
    }

    public void setInterestValueCalculated(Double interestValueCalculated) {
        this.interestValueCalculated = interestValueCalculated;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getFineValueCalculated() {
        return fineValueCalculated;
    }

    public void setFineValueCalculated(Double fineValueCalculated) {
        this.fineValueCalculated = fineValueCalculated;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Double originalValue) {
        this.originalValue = originalValue;
    }

    public Double getTotalUpdated() {
        return totalUpdated;
    }

    public void setTotalUpdated(Double totalUpdated) {
        this.totalUpdated = totalUpdated;
    }

    public Double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(Double totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }

    public Double getTotalWithAdditional() {
        return totalWithAdditional;
    }

    public void setTotalWithAdditional(Double totalWithAdditional) {
        this.totalWithAdditional = totalWithAdditional;
    }

}
