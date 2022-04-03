package com.milk.pay.entities;

import java.math.BigDecimal;
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

import com.milk.pay.entities.enums.EnumMovementCode;
import com.milk.pay.entities.pattern.DafeEntity;

import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_RECEIPT_INFO")
public class ReceiptInfo extends DafeEntity {

    @Id
    @SequenceGenerator(name = "ID_MILK_RECEIPT_INFO", sequenceName = "GEN_MILK_RECEIPT_INFO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK_RECEIPT_INFO")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;

    @Column(name = "TX_ID")
    private String txId;

    @Column(name = "EXTERNAL_TX_ID")
    private String externalTxId;

    @Column(name = "AUTHENTICATION")
    private String authentication;

    @Column(name = "LAST_AUTHENTICATION")
    private String lastAuthentication;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PAID_AMOUNT")
    private BigDecimal paidAmount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PAID_AT")
    private Date paidAt;

    @Column(name = "PAYER_NAME")
    private String payerName;

    @Column(name = "PAYER_DOCUMENT")
    private String payerDocument;

    @Column(name = "PAYER_KEY")
    private String payerAccountKey;

    @Column(name = "PAYER_BANK")
    private String payerAccountBank;

    @Column(name = "PAYER_ACCOUNT")
    private String payerAccount;

    @Column(name = "PAYER_BRANCH")
    private String payerAccountBranch;

    @Column(name = "PAYER_ACCOUNT_TYPE")
    private String payerAccountType;

    @Column(name = "RECEIVER_NAME")
    private String receiverName;

    @Column(name = "RECEIVER_DOCUMENT")
    private String receiverDocument;

    @Column(name = "RECEIVER_KEY")
    private String receiverAccountKey;

    @Column(name = "RECEIVER_BANK")
    private String receiverAccountBank;

    @Column(name = "RECEIVER_ACCOUNT")
    private String receiverAccount;

    @Column(name = "RECEIVER_BRANCH")
    private String receiverAccountBranch;

    @Column(name = "RECEIVER_ACCOUNT_TYPE")
    private String receiverAccountType;

    @Column(name = "END_TO_END_ID")
    private String endToEndId;

    @Column(name = "EXTERNAL_AUTH")
    private String externalAuth;

    @Column(name = "EXTERNAL_AUTH_FIRST_BLOCK")
    private String externalAuthFirstBlock;

    @Column(name = "EXTERNAL_AUTH_SECOND_BLOCK")
    private String externalAuthSecondBlock;

    @Column(name = "MOVEMENT_CODE")
    @Enumerated(EnumType.STRING)
    private EnumMovementCode movementCode;

    public static ReceiptInfo findLastReceipt() {
        return find("select dri from ReceiptInfo dri where dri.id = (select max(id) from ReceiptInfo)")
                    .firstResult();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getExternalTxId() {
        return externalTxId;
    }

    public void setExternalTxId(String externalTxId) {
        this.externalTxId = externalTxId;
    }

    public String getLastAuthentication() {
        return lastAuthentication;
    }

    public void setLastAuthentication(String lastAuthentication) {
        this.lastAuthentication = lastAuthentication;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerDocument() {
        return payerDocument;
    }

    public void setPayerDocument(String payerDocument) {
        this.payerDocument = payerDocument;
    }

    public String getPayerAccountKey() {
        return payerAccountKey;
    }

    public void setPayerAccountKey(String payerAccountKey) {
        this.payerAccountKey = payerAccountKey;
    }

    public String getPayerAccountBank() {
        return payerAccountBank;
    }

    public void setPayerAccountBank(String payerAccountBank) {
        this.payerAccountBank = payerAccountBank;
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    public String getPayerAccountBranch() {
        return payerAccountBranch;
    }

    public void setPayerAccountBranch(String payerAccountBranch) {
        this.payerAccountBranch = payerAccountBranch;
    }

    public String getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(String payerAccountType) {
        this.payerAccountType = payerAccountType;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverDocument() {
        return receiverDocument;
    }

    public void setReceiverDocument(String receiverDocument) {
        this.receiverDocument = receiverDocument;
    }

    public String getReceiverAccountKey() {
        return receiverAccountKey;
    }

    public void setReceiverAccountKey(String receiverAccountKey) {
        this.receiverAccountKey = receiverAccountKey;
    }

    public String getReceiverAccountBank() {
        return receiverAccountBank;
    }

    public void setReceiverAccountBank(String receiverAccountBank) {
        this.receiverAccountBank = receiverAccountBank;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getReceiverAccountBranch() {
        return receiverAccountBranch;
    }

    public void setReceiverAccountBranch(String receiverAccountBranch) {
        this.receiverAccountBranch = receiverAccountBranch;
    }

    public String getReceiverAccountType() {
        return receiverAccountType;
    }

    public void setReceiverAccountType(String receiverAccountType) {
        this.receiverAccountType = receiverAccountType;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getExternalAuth() {
        return externalAuth;
    }

    public void setExternalAuth(String externalAuth) {
        this.externalAuth = externalAuth;
    }

    public String getExternalAuthFirstBlock() {
        return externalAuthFirstBlock;
    }

    public void setExternalAuthFirstBlock(String externalAuthFirstBlock) {
        this.externalAuthFirstBlock = externalAuthFirstBlock;
    }

    public String getExternalAuthSecondBlock() {
        return externalAuthSecondBlock;
    }

    public void setExternalAuthSecondBlock(String externalAuthSecondBlock) {
        this.externalAuthSecondBlock = externalAuthSecondBlock;
    }

    public EnumMovementCode getMovementCode() {
        return movementCode;
    }

    public void setMovementCode(EnumMovementCode movementCode) {
        this.movementCode = movementCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

}
