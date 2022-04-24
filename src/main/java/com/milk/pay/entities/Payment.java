package com.milk.pay.entities;

import java.time.LocalDateTime;

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

import com.milk.pay.entities.enums.EnumInitiationType;
import com.milk.pay.entities.pattern.DafeEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    
    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "INTEREST_PERCENTAGE")
    private Double interestPercentage;

    @Column(name = "INTEREST_VALUE")
    private Double interestValue;

    @Column(name = "PIX_KEY")
    private String pixKey;

    @Column(name = "END_TO_END_ID", unique = true)
    private String endToEndId;

    @Column(name = "RECEIPT_IMAGE", length = 1500)
    private String receiptImage;

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

    @Column(name = "PAID_AT")
    private LocalDateTime paidAt;
    
    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @ManyToOne()
    @JoinColumn(name = "ID_TITLE", referencedColumnName = "ID")
    private Title title;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIPT_ID", referencedColumnName = "ID")
    private ReceiptInfo receipt;

    public static Payment findById(Integer id) {
        return find("id", id).firstResult();
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

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(String receiptImage) {
        this.receiptImage = receiptImage;
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

    public Double getInterestPercentage() {
        return interestPercentage;
    }

    public void setInterestPercentage(Double interestPercentage) {
        this.interestPercentage = interestPercentage;
    }

    public Double getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(Double interestValue) {
        this.interestValue = interestValue;
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
