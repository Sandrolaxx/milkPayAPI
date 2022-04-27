package com.milk.pay.dto.title;

import javax.json.bind.annotation.JsonbTypeAdapter;

import com.milk.pay.entities.enums.EnumPaymentType;
import com.milk.pay.utils.EnumPaymentTypeAdapter;

/**
 *
 * @author SRamos
 */
public class TitleCreateDto {

    private Integer externalId;

    private String userDocument;

    private Double amount;

    private Double dailyInterest;

    private String dueDate;
    
    private String inclusionDate;

    private String nfNumber;

    @JsonbTypeAdapter(EnumPaymentTypeAdapter.class)
    private EnumPaymentType paymentType;

    private String barcode;

    private String digitabe;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public Double getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(Double dailyInterest) {
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

    public String getDigitabe() {
        return digitabe;
    }

    public void setDigitabe(String digitabe) {
        this.digitabe = digitabe;
    }

}
