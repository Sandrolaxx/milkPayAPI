package com.aktie.aktiepay.dto.pix;

import com.aktie.aktiepay.entities.enums.EnumAccountType;

/**
 *
 * @author SRamos
 */
public class PixPaymentDebitPartyCelcoinDto  {

    private String account;

    private String branch;

    private String taxId;

    private EnumAccountType accountType;

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

    public EnumAccountType getAccountType() {
        return this.accountType;
    }

    public void setAccountType(EnumAccountType accountType) {
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
