package com.milk.pay.pix.dto;

import javax.json.bind.annotation.JsonbProperty;

public class PixKeyConsultResponseDto {

    private String key;

    private String keyType;

    private PixKeyAccountDto account;

    private PixKeyOwnerResponseDto owner;

    @JsonbProperty("endtoendid")
    private String endToEndId;

    private PixKeyConsultErrorResponseDto error;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public PixKeyAccountDto getAccount() {
        return this.account;
    }

    public void setAccount(PixKeyAccountDto account) {
        this.account = account;
    }

    public PixKeyOwnerResponseDto getOwner() {
        return this.owner;
    }

    public void setOwner(PixKeyOwnerResponseDto owner) {
        this.owner = owner;
    }

    public String getEndToEndId() {
        return this.endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public PixKeyConsultErrorResponseDto getError() {
        return error;
    }

    public void setError(PixKeyConsultErrorResponseDto error) {
        this.error = error;
    }

}
