package com.milk.pay.dto.auth;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author SRamos
 */
public class TokenResponseCelcoinDto {

    @JsonbProperty("access_token")
    private String accessToken;
    
    @JsonbProperty("access_token")
    private String refreshToken;
    
    @JsonbProperty("expires_in")
    private Long expiresIn;
    
    @JsonbProperty("token_type")
    private String tokenType;
    
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

}
