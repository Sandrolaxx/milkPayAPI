package com.milk.pay.dto.title;

import java.sql.Date;

import javax.json.bind.annotation.JsonbDateFormat;

/**
 *
 * @author SRamos
 */
public class TitleCreateDto {

    private Integer externalId;

    private String userDocument;

    private Double amount;

    private Double dailyFine;

    @JsonbDateFormat(value = "dd/MM/yyyy", locale = "Locale.ENGLISH")
    private Date dueDate;
    
    @JsonbDateFormat(value = "dd/MM/yyyy", locale = "Locale.ENGLISH")
    private Date inclusionDate;

    private String nfNumber;

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

    public Double getDailyFine() {
        return dailyFine;
    }

    public void setDailyFine(Double dailyFine) {
        this.dailyFine = dailyFine;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(Date inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public String getNfNumber() {
        return nfNumber;
    }

    public void setNfNumber(String nfNumber) {
        this.nfNumber = nfNumber;
    }

}
