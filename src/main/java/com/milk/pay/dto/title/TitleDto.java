package com.milk.pay.dto.title;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.milk.pay.entities.enums.EnumPaymentType;

/**
 *
 * @author SRamos
 */
public class TitleDto {

    private Integer id;
    
    private Integer txId;

    private String userId;
    
    private BigDecimal amount;

    private BigDecimal finalAmount;

    private BigDecimal balance;
    
    private String paidAt;

    private String dueDate;

    private String endToEndId;

    private String barcode;

    private String digitable;

    private String nfNumber;

    private String pixKey;

    private String responseSlip;

    private boolean liquidated;
    
    private BigDecimal dailyInterest;
    
    private EnumPaymentType paymentType;

    private LocalDateTime inclusionDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTxId() {
        return txId;
    }

    public void setTxId(Integer txId) {
        this.txId = txId;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
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

    public String getResponseSlip() {
        return responseSlip;
    }

    public void setResponseSlip(String responseSlip) {
        this.responseSlip = responseSlip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isLiquidated() {
        return liquidated;
    }

    public void setLiquidated(boolean liquidated) {
        this.liquidated = liquidated;
    }

    public BigDecimal getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(BigDecimal dailyInterest) {
        this.dailyInterest = dailyInterest;
    }

    public EnumPaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(EnumPaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getNfNumber() {
        return nfNumber;
    }

    public void setNfNumber(String nfNumber) {
        this.nfNumber = nfNumber;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public LocalDateTime getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(LocalDateTime inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

}
