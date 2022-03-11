package com.milk.pay.pix.dto;

public class PixKeyAccountDto  {

    private String openingDate;

    private String participant;

    private Integer branch;

    private String accountNumber;

    private String accountType;

    public String getOpeningDate() {
        return this.openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getParticipant() {
        return this.participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public Integer getBranch() {
        return this.branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
