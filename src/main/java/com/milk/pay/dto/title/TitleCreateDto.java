package com.milk.pay.dto.title;

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

}