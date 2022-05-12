package com.milk.pay.dto.bankslip;

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
    private Long txId;
    
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

    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
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
