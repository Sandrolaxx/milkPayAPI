package com.aktie.aktiepay.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.jboss.aerogear.security.otp.api.Base32;

import com.aktie.aktiepay.entities.enums.EnumUserType;
import com.aktie.aktiepay.utils.EncryptUtil;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_USER")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "DOCUMENT")
    private String document;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PIX_KEY")
    private String pixKey;

    @Column(name = "SECRET")
    private String secret;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "ACCEPT_TERMS")
    private boolean acceptTerms;
    
    @Column(name = "EXTERNAL_ID")
    private Long externalId;
    
    @Column(name = "POSTAL_CODE")
    private String postalCode;
    
    @Column(name = "ADDRESS", length = 400)
    private String address;
    
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private EnumUserType type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    private List<Title> listTitle;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    public static User findUserByDocument(String document) {
        return find("document", document).firstResult();
    }
    public User() {
        super();
        this.secret = Base32.random();
    }

    @PrePersist
    private void encryptSensitiveData() {
        this.password = EncryptUtil.textEncrypt(this.password, secret);
    }

    @PostLoad
    private void decryptSensitiveData() {
        this.password = EncryptUtil.textDecrypt(this.password, secret);

        if (this.pixKey != null) {
            this.pixKey = EncryptUtil.textDecrypt(this.pixKey, secret);
        }

        if (this.name != null) {
            this.name = EncryptUtil.textDecrypt(this.name, secret);
        }

        if (this.email != null) {
            this.email = EncryptUtil.textDecrypt(this.email, secret);
        }

        if (this.phone != null) {
            this.phone = EncryptUtil.textDecrypt(this.phone, secret);
        }

        if (this.address != null) {
            this.address = EncryptUtil.textDecrypt(this.address, secret);
        }

        if (this.postalCode != null) {
            this.postalCode = EncryptUtil.textDecrypt(this.postalCode, secret);
        }
    }

    @PreUpdate
    private void encryptSensitiveOnUpdate() {
        this.password = EncryptUtil.textEncrypt(this.password, secret);

        if (this.name != null) {
            this.name = EncryptUtil.textEncrypt(this.name, secret);
        }

        if (this.email != null) {
            this.email = EncryptUtil.textEncrypt(this.email, secret);
        }

        if (this.phone != null) {
            this.phone = EncryptUtil.textEncrypt(this.phone, secret);
        }

        if (this.pixKey != null) {
            this.pixKey = EncryptUtil.textEncrypt(this.pixKey, secret);
        }

        if (this.address != null) {
            this.address = EncryptUtil.textEncrypt(this.address, secret);
        }

        if (this.postalCode != null) {
            this.postalCode = EncryptUtil.textEncrypt(this.postalCode, secret);
        }
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public List<Title> getListTitle() {
        return listTitle;
    }

    public void setListTitle(List<Title> listTitle) {
        this.listTitle = listTitle;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getExternalId() {
        return externalId;
    }
    
    public void setExternalId(Long externalId) {
        this.externalId = externalId;
    }

    public EnumUserType getType() {
        return type;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }
    
    public void setType(EnumUserType type) {
        this.type = type;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
    
}
