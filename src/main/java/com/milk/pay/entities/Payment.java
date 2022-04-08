package com.milk.pay.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MILK_PAYMENT")
public class Payment extends DafeEntity {

    @Id
    @SequenceGenerator(name = "ID_MILK_PIX_PAYMENT", sequenceName = "GEN_MILK_PIX_PAYMENT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK_PIX_PAYMENT")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;
    
    @Column(name = "EXTERNAL_TX_ID", unique = true)
    private Long externalTxId;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "TITLE_FINE")
    private Double titleFine;

    @Column(name = "END_TO_END_ID", unique = true)
    private String endToEndId;

    @Column(name = "EXTERNAL_RECEIPT")
    private String externalReceipt;

    @Column(name = "RECEIPT_IMAGE")
    private String receiptImage;

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
    
    @Column(name = "ERROR_MESSAGE")
    private String errorMessage;

    @ManyToOne()
    @JoinColumn(name = "ID_TITLE", referencedColumnName = "ID")
    private Title title;

    public static Payment findByTxId(Long txId) {
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

    public Double getTitleFine() {
        return titleFine;
    }

    public void setTitleFine(Double titleFine) {
        this.titleFine = titleFine;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getExternalReceipt() {
        return externalReceipt;
    }

    public void setExternalReceipt(String externalReceipt) {
        this.externalReceipt = externalReceipt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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

}
