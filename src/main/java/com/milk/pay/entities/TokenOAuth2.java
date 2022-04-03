package com.milk.pay.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.milk.pay.entities.pattern.DafeEntity;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_TOKEN_OAUTH2")
public class TokenOAuth2 extends DafeEntity {

    @Id
    @SequenceGenerator(name = "ID_MILK_TOKEN_OAUTH2", sequenceName = "GEN_MILK_TOKEN_OAUTH2", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK_TOKEN_OAUTH2")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;

    @Column(name = "OWNER_ENTITY")
    private String ownerEntity;

    @Column(name = "OWNER_ENTITY_ID")
    private Integer ownerEntityId;

    @Column(name = "ACCESS_TOKEN", nullable = false)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN")
    private String refreshToken;

    @Column(name = "SCOPE", length = 1000)
    private String scope;

    @Column(name = "TOKEN_TYPE")
    private String tokenType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_DATE", nullable = false)
    private Date expirationDate;

    public TokenOAuth2() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerEntity() {
        return ownerEntity;
    }

    public void setOwnerEntity(String ownerEntity) {
        this.ownerEntity = ownerEntity;
    }

    public Integer getOwnerEntityId() {
        return ownerEntityId;
    }

    public void setOwnerEntityId(Integer ownerEntityId) {
        this.ownerEntityId = ownerEntityId;
    }

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return Calendar.getInstance().getTime().after(this.expirationDate);
    }

}
