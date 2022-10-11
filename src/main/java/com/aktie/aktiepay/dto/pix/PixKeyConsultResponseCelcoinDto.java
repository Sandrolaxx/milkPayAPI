package com.aktie.aktiepay.dto.pix;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author SRamos
 */
public class PixKeyConsultResponseCelcoinDto {

    private String key;

    private String keyType;

    private PixKeyAccountCelcoinDto account;

    private PixKeyOwnerResponseCelcoinDto owner;

    @JsonbProperty("endtoendid")
    private String endToEndId;

    private PixKeyConsultErrorResponseCelcoinDto error;

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

    public PixKeyAccountCelcoinDto getAccount() {
        return this.account;
    }

    public void setAccount(PixKeyAccountCelcoinDto account) {
        this.account = account;
    }

    public PixKeyOwnerResponseCelcoinDto getOwner() {
        return this.owner;
    }

    public void setOwner(PixKeyOwnerResponseCelcoinDto owner) {
        this.owner = owner;
    }

    public String getEndToEndId() {
        return this.endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public PixKeyConsultErrorResponseCelcoinDto getError() {
        return error;
    }

    public void setError(PixKeyConsultErrorResponseCelcoinDto error) {
        this.error = error;
    }

}
