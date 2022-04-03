package com.milk.pay.dto.pix;

/**
 *
 * @author SRamos
 */
public class PixPaymentDebitPartyCelcoinDto  {

    private String account;

    private String branch;

    private String taxId;

    private String accountType;

    private String name;

    private String bankISPB;

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBranch() {
        return this.branch;
    }

    public void setBranch(String branch) {
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

    public String getBankISPB() {
        return bankISPB;
    }

    public void setBankISPB(String bankISPB) {
        this.bankISPB = bankISPB;
    }

}
