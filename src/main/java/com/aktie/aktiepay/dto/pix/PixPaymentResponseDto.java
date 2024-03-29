package com.aktie.aktiepay.dto.pix;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author SRamos
 */
public class PixPaymentResponseDto {

    @JsonbProperty("transactionId")
    private Long txId;

    private String slip;

    private String slipAuth;

    private String endToEndId;

    private String message;

    private String errorCode;

    private String description;

    public Long getTxId() {
        return this.txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public String getSlip() {
        return this.slip;
    }

    public void setSlip(String slip) {
        this.slip = slip;
    }

    public String getSlipAuth() {
        return this.slipAuth;
    }

    public void setSlipAuth(String slipAuth) {
        this.slipAuth = slipAuth;
    }

    public String getEndToEndId() {
        return this.endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
