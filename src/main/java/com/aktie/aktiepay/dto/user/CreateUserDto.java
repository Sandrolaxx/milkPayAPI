package com.aktie.aktiepay.dto.user;

/**
 *
 * @author SRamos
 */
public class CreateUserDto {

    private String document;

    private String password;

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
