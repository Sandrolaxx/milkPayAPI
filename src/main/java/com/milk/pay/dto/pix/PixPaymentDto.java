package com.milk.pay.dto.pix;

import java.math.BigDecimal;

import com.milk.pay.entities.enums.EnumAccountType;

/**
 *
 * @author SRamos
 */ 
public class PixPaymentDto {

    private BigDecimal amount;

    private Integer titleId;

    private String endToEndId;

    private String receiverKey;

    private String receiverBank;

    private String receiverAccount;

    private Integer receiverBranch;

    private String receiverDocument;

    private EnumAccountType receiverAccountType;

    private String receiverName;

    private Integer txId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getReceiverKey() {
        return receiverKey;
    }

    public void setReceiverKey(String receiverKey) {
        this.receiverKey = receiverKey;
    }

    public String getReceiverBank() {
        return receiverBank;
    }

    public void setReceiverBank(String receiverBank) {
        this.receiverBank = receiverBank;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public Integer getReceiverBranch() {
        return receiverBranch;
    }

    public void setReceiverBranch(Integer receiverBranch) {
        this.receiverBranch = receiverBranch;
    }

    public String getReceiverDocument() {
        return receiverDocument;
    }

    public void setReceiverDocument(String receiverDocument) {
        this.receiverDocument = receiverDocument;
    }

    public EnumAccountType getReceiverAccountType() {
        return receiverAccountType;
    }

    public void setReceiverAccountType(EnumAccountType receiverAccountType) {
        this.receiverAccountType = receiverAccountType;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Integer getTxId() {
        return txId;
    }

    public void setTxId(Integer txId) {
        this.txId = txId;
    }

}
