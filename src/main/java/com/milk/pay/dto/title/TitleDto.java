package com.milk.pay.dto.title;

import com.milk.pay.dto.user.CreateUserDto;

/**
 *
 * @author SRamos
 */
public class TitleDto {

    private Integer id;
    
    private Integer userId;

    private Double amount;

    private Double balance;
    
    private String paidDate;

    private String dueDate;

    private String txId;

    private Integer companyId;
    
    private String endToEndId;

    private String responseSlip;

    private CreateUserDto user;

    private boolean liquidated;
    
    private Double dailyFine;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getResponseSlip() {
        return responseSlip;
    }

    public void setResponseSlip(String responseSlip) {
        this.responseSlip = responseSlip;
    }

    public CreateUserDto getUser() {
        return user;
    }

    public void setUser(CreateUserDto user) {
        this.user = user;
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

    public Double getDailyFine() {
        return dailyFine;
    }

    public void setDailyFine(Double dailyFine) {
        this.dailyFine = dailyFine;
    }

}
