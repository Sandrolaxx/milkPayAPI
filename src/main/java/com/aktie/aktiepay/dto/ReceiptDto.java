package com.aktie.aktiepay.dto;

public class ReceiptDto {

    private String receiptImage;

    public ReceiptDto() {
    }

    public ReceiptDto(String receiptImage) {
        this.receiptImage = receiptImage;
    }

    public String getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(String receiptImage) {
        this.receiptImage = receiptImage;
    }

}
