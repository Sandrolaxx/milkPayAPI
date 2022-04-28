package com.milk.pay.dto.title;

import java.math.BigDecimal;

import com.milk.pay.entities.enums.EnumPaymentType;

/**
 *
 * @author SRamos
 */
public class TitleDto {

    private Integer id;
    
    private String userId;

    private BigDecimal amount;

    private BigDecimal balance;
    
    private String paidAt;

    private String dueDate;

    private String txId;

    private String endToEndId;

    private String barcode;

    private String digitabe;

    private String responseSlip;

    private boolean liquidated;
    
    private BigDecimal dailyInterest;
    
    private EnumPaymentType paymentType;

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

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
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

    public String getDigitabe() {
        return digitabe;
    }

    public void setDigitabe(String digitabe) {
        this.digitabe = digitabe;
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

}
