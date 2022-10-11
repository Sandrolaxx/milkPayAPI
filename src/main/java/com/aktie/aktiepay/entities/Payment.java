package com.aktie.aktiepay.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.aktie.aktiepay.entities.enums.EnumInitiationType;
import com.aktie.aktiepay.entities.pattern.DafeEntity;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_PAYMENT")
public class Payment extends DafeEntity {

    @Id
    @SequenceGenerator(name = "ID_MILK_PIX_PAYMENT", sequenceName = "GEN_MILK_PIX_PAYMENT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK_PIX_PAYMENT")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;

    @Column(name = "REQUESTED_AMOUNT")
    private BigDecimal requestedAmount;

    @Column(name = "RECIVED_AMOUNT")
    private BigDecimal receivedAmount;

    @Column(name = "INTEREST_PERCENTAGE")
    private BigDecimal interestPercentage;

    @Column(name = "INTEREST_AMOUNT")
    private BigDecimal interestAmount;

    @Column(name = "PIX_KEY")
    private String pixKey;

    @Column(name = "END_TO_END_ID", unique = true)
    private String endToEndId;

    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "DIGITABLE")
    private String digitable;

    @Column(name = "LIQUIDATED")
    private boolean liquidated = true;

    @Column(name = "NOTIFIED")
    private boolean notified = false;

    @Column(name = "INITIATION_TYPE")
    @Enumerated(EnumType.STRING)
    private EnumInitiationType initiationType;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TITLE", referencedColumnName = "ID")
    private Title title;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payment")
    private ReceiptInfo receipt;

    public static Payment findById(Integer id) {
        return find("id", id).firstResult();
    }

    public static Payment findByTitleId(Integer titleId) {
        return find("title.id", titleId).firstResult();
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDigitable() {
        return digitable;
    }

    public void setDigitable(String digitable) {
        this.digitable = digitable;
    }

    public boolean isLiquidated() {
        return liquidated;
    }

    public void setLiquidated(boolean liquidated) {
        this.liquidated = liquidated;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public EnumInitiationType getInitiationType() {
        return initiationType;
    }

    public void setInitiationType(EnumInitiationType initiationType) {
        this.initiationType = initiationType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public BigDecimal getInterestPercentage() {
        return interestPercentage;
    }

    public void setInterestPercentage(BigDecimal interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public ReceiptInfo getReceipt() {
        return receipt;
    }

    public void setReceipt(ReceiptInfo receipt) {
        this.receipt = receipt;
    }

}
