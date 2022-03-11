package com.milk.pay.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.milk.pay.entities.enums.EnumInitiationType;
import com.milk.pay.entities.pattern.DafeEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_PIX_PAYMENT")
public class PixPayment extends DafeEntity {

    @Id
    @SequenceGenerator(name = "ID_MILK_PIX_PAYMENT", sequenceName = "GEN_MILK_PIX_PAYMENT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK_PIX_PAYMENT")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "EXTERNAL_TX_ID", unique = true)
    private Long externalTxId;

    @Column(name = "TX_ID")
    private String txId;
    
    @Column(name = "ACCOUNT_ID")
    private Integer accountId;

    @Column(name = "END_TO_END_ID", unique = true)
    private String endToEndId;

    @Column(name = "KEY")
    private String creditAccountKey;

    @Column(name = "BANK")
    private String creditAccountBank;

    @Column(name = "ACCOUNT")
    private String creditAccount;

    @Column(name = "BRANCH")
    private Integer creditAccountBranch;

    @Column(name = "TAX_ID")
    private String creditAccountTaxId;

    @Column(name = "ACCOUNT_TYPE")
    private String creditAccountType;

    @Column(name = "ACCOUNT_NAME")
    private String creditAccountName;

    @Column(name = "RESPONSE_CODE")
    private String responseCode;

    @Column(name = "RESPONSE_SLIP", length = 4000)
    private String responseSlip;

    @Column(name = "RESPONSE_SLIP_AUTH")
    private String responseSlipAuth;

    @Column(name = "LIQUIDATED")
    private boolean liquidated = true;

    @Column(name = "NOTIFIED")
    private boolean notified = false;

    @Column(name = "INITIATION_TYPE")
    @Enumerated(EnumType.STRING)
    private EnumInitiationType initiationType;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public PixPayment() {
    }

    public static PixPayment findByTxId(Long txId) {
        return find("txId", txId).firstResult();
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getExternalTxId() {
        return externalTxId;
    }

    public void setExternalTxId(Long externalTxId) {
        this.externalTxId = externalTxId;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public EnumInitiationType getInitiationType() {
        return initiationType;
    }

    public void setInitiationType(EnumInitiationType initiationType) {
        this.initiationType = initiationType;
    }

    public String getEndToEndId() {
        return this.endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getCreditAccountKey() {
        return this.creditAccountKey;
    }

    public void setCreditAccountKey(String creditAccountKey) {
        this.creditAccountKey = creditAccountKey;
    }

    public String getCreditAccountBank() {
        return this.creditAccountBank;
    }

    public void setCreditAccountBank(String creditAccountBank) {
        this.creditAccountBank = creditAccountBank;
    }

    public String getCreditAccount() {
        return this.creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Integer getCreditAccountBranch() {
        return this.creditAccountBranch;
    }

    public void setCreditAccountBranch(Integer creditAccountBranch) {
        this.creditAccountBranch = creditAccountBranch;
    }

    public String getCreditAccountTaxId() {
        return this.creditAccountTaxId;
    }

    public void setCreditAccountTaxId(String creditAccountTaxId) {
        this.creditAccountTaxId = creditAccountTaxId;
    }

    public String getCreditAccountType() {
        return this.creditAccountType;
    }

    public void setCreditAccountType(String creditAccountType) {
        this.creditAccountType = creditAccountType;
    }

    public String getCreditAccountName() {
        return this.creditAccountName;
    }

    public void setCreditAccountName(String creditAccountName) {
        this.creditAccountName = creditAccountName;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseSlip() {
        return this.responseSlip;
    }

    public void setResponseSlip(String responseSlip) {
        this.responseSlip = responseSlip;
    }

    public String getResponseSlipAuth() {
        return this.responseSlipAuth;
    }

    public void setResponseSlipAuth(String responseSlipAuth) {
        this.responseSlipAuth = responseSlipAuth;
    }

    public boolean isLiquidated() {
        return this.liquidated;
    }

    public void setLiquidated(boolean liquidated) {
        this.liquidated = liquidated;
    }

    public boolean isNotified() {
        return this.notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
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

}
