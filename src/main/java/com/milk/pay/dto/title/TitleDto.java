package com.milk.pay.dto.title;

/**
 *
 * @author SRamos
 */
public class TitleDto {

    private Integer id;
    
    private String userId;

    private Double amount;

    private Double balance;
    
    private String paidAt;

    private String dueDate;

    private String txId;

    private String endToEndId;

    private String barcode;

    private String digitabe;

    private String responseSlip;

    private boolean liquidated;
    
    private Double dailyInterest;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
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

    public Double getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(Double dailyInterest) {
        this.dailyInterest = dailyInterest;
    }

}
