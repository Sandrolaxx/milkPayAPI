package com.milk.pay.dto;

public class ReceiptDto {

    private Integer txId;

    private String receiptImage;

    public ReceiptDto() {
    }

    public ReceiptDto(Integer txId, String receiptImage) {
        this.txId = txId;
        this.receiptImage = receiptImage;
    }

    public Integer getTxId() {
        return txId;
    }

    public void setTxId(Integer txId) {
        this.txId = txId;
    }

    public String getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(String receiptImage) {
        this.receiptImage = receiptImage;
    }

}
