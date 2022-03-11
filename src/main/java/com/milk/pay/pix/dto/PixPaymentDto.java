package com.milk.pay.pix.dto;

import javax.json.bind.annotation.JsonbProperty;

import com.milk.pay.entities.enums.EnumInitiationType;

public class PixPaymentDto {

    private Double amount;

    private String endToEndId;

    private String creditAccountKey;

    private String creditAccountBank;

    private String creditAccount;

    private Integer creditAccountBranch;

    private String creditAccountTaxId;

    private String creditAccountType;

    private String creditAccountName;

    private EnumInitiationType initiationType;

    @JsonbProperty("transactionIdentification")
    private String txId;

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEndToEndId() {
        return this.endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getCreditAccountKey() {
        return this.creditAccountKey;
    }

    public void setCreditAccountKey(String creditAccountKey) {
        this.creditAccountKey = creditAccountKey;
    }

    public String getCreditAccountBank() {
        return this.creditAccountBank;
    }

    public void setCreditAccountBank(String creditAccountBank) {
        this.creditAccountBank = creditAccountBank;
    }

    public String getCreditAccount() {
        return this.creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Integer getCreditAccountBranch() {
        return this.creditAccountBranch;
    }

    public void setCreditAccountBranch(Integer creditAccountBranch) {
        this.creditAccountBranch = creditAccountBranch;
    }

    public String getCreditAccountTaxId() {
        return this.creditAccountTaxId;
    }

    public void setCreditAccountTaxId(String creditAccountTaxId) {
        this.creditAccountTaxId = creditAccountTaxId;
    }

    public String getCreditAccountType() {
        return this.creditAccountType;
    }

    public void setCreditAccountType(String creditAccountType) {
        this.creditAccountType = creditAccountType;
    }

    public String getCreditAccountName() {
        return this.creditAccountName;
    }

    public void setCreditAccountName(String creditAccountName) {
        this.creditAccountName = creditAccountName;
    }

    public EnumInitiationType getInitiationType() {
        return initiationType;
    }

    public void setInitiationType(EnumInitiationType initiationType) {
        this.initiationType = initiationType;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

}
