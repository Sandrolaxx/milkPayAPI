package com.milk.pay.dto.pix;

/**
 *
 * @author SRamos
 */
public class PixPaymentCreditPartyCelcoinDto  {

    private String key;

    private String bank;

    private String account;

    private Integer branch;

    private String taxId;

    private String accountType;

    private String name;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBank() {
        return this.bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getBranch() {
        return this.branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public String getTaxId() {
        return this.taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
