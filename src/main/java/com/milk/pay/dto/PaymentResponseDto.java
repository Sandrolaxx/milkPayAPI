package com.milk.pay.dto;

public class PaymentResponseDto {

    private Integer txId;
    
    private String receipt;

    public PaymentResponseDto() {
    }

    public PaymentResponseDto(Integer txId, String receipt) {
        this.txId = txId;
        this.receipt = receipt;
    }

    public Integer getTxId() {
        return txId;
    }

    public void setTxId(Integer txId) {
        this.txId = txId;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
    
}
