package com.aktie.aktiepay.dto.bankslip;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author SRamos
 */
public class BankSlipCelcoinPaymentDto {

    @JsonbProperty("cpfCnpj")
    private String document;

    private String dueDate;

    @JsonbProperty("transactionIdAuthorize")
    private Long transactionId;
    
    @JsonbProperty("barCode")
    private BankSlipConsultDto barcode;

    private BankSlipCelcoinBillDataDto billData;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
    
    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BankSlipConsultDto getBarcode() {
        return barcode;
    }

    public void setBarcode(BankSlipConsultDto barcode) {
        this.barcode = barcode;
    }

    public BankSlipCelcoinBillDataDto getBillData() {
        return billData;
    }

    public void setBillData(BankSlipCelcoinBillDataDto billData) {
        this.billData = billData;
    }

}
