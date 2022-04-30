package com.milk.pay.dto.bankslip;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;

public class BankSlipCelcoinResponseConsultDto {
    
    private String assignor;

    private BankSlipCelcoinConsultDataDto registerData;

    @JsonbDateFormat(value = "dd/MM/yyyy")
    private LocalDate settleDate;

    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dueDate;

    private String endHour;

    private String initHour;

    private String nextSettle;

    private String digitable;

    private Long transactionId;

    private Integer type;

    private BigDecimal value;

    private BigDecimal maxValue;

    private BigDecimal minValue;

    private String errorCode;

    private String message;

    private Integer status;

    public String getAssignor() {
        return assignor;
    }

    public void setAssignor(String assignor) {
        this.assignor = assignor;
    }

    public BankSlipCelcoinConsultDataDto getRegisterData() {
        return registerData;
    }

    public void setRegisterData(BankSlipCelcoinConsultDataDto registerData) {
        this.registerData = registerData;
    }

    public LocalDate getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(LocalDate settleDate) {
        this.settleDate = settleDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getInitHour() {
        return initHour;
    }

    public void setInitHour(String initHour) {
        this.initHour = initHour;
    }

    public String getNextSettle() {
        return nextSettle;
    }

    public void setNextSettle(String nextSettle) {
        this.nextSettle = nextSettle;
    }

    public String getDigitable() {
        return digitable;
    }

    public void setDigitable(String digitable) {
        this.digitable = digitable;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    

}
