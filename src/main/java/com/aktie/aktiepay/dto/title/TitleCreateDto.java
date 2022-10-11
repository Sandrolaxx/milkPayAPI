package com.aktie.aktiepay.dto.title;

import java.math.BigDecimal;

import javax.json.bind.annotation.JsonbTypeAdapter;

import com.aktie.aktiepay.entities.enums.EnumPaymentType;
import com.aktie.aktiepay.utils.EnumPaymentTypeAdapter;

/**
 *
 * @author SRamos
 */
public class TitleCreateDto {

    private Integer externalId;

    private String userDocument;

    private BigDecimal amount;

    private BigDecimal dailyInterest;

    private String dueDate;
    
    private String inclusionDate;

    private String nfNumber;

    @JsonbTypeAdapter(EnumPaymentTypeAdapter.class)
    private EnumPaymentType paymentType;

    private String barcode;

    private String digitable;

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(BigDecimal dailyInterest) {
        this.dailyInterest = dailyInterest;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(String inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public String getNfNumber() {
        return nfNumber;
    }

    public void setNfNumber(String nfNumber) {
        this.nfNumber = nfNumber;
    }

    public EnumPaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(EnumPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDigitable() {
        return digitable;
    }

    public void setDigitable(String digitable) {
        this.digitable = digitable;
    }
    
}
