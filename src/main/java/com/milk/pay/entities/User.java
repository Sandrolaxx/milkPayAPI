package com.milk.pay.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.milk.pay.utils.EncryptUtil;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.jboss.aerogear.security.otp.api.Base32;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER")
    private List<Title> listTitle;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public static User findUserByDocument(String document) {
        return find("document", document).firstResult();
    }
    public User() {
        super();
        this.secret = Base32.random();
    }

    @PrePersist
    private void encryptSensitiveData() {
        this.name = EncryptUtil.textEncrypt(this.name, secret);
        this.email = EncryptUtil.textEncrypt(this.email, secret);
        this.password = EncryptUtil.textEncrypt(this.password, secret);
        this.phone = EncryptUtil.textEncrypt(this.phone, secret);
        this.pixKey = EncryptUtil.textEncrypt(this.pixKey, secret);
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
    
    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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

}
