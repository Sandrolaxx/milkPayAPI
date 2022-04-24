package com.milk.pay.entities;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.milk.pay.entities.enums.EnumAccountType;
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
    private Integer txId;

    @Column(name = "AUTHENTICATION")
    private String authentication;

    @Column(name = "LAST_AUTHENTICATION")
    private String lastAuthentication;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "PAYER_NAME")
    private String payerName;

    @Column(name = "PAYER_DOCUMENT")
    private String payerDocument;

    @Column(name = "PAYER_BANK")
    private String payerAccountBank;

    @Column(name = "PAYER_ACCOUNT")
    private String payerAccount;

    @Column(name = "PAYER_BRANCH")
    private String payerAccountBranch;

    @Column(name = "PAYER_ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    private EnumAccountType payerAccountType;

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
    @Enumerated(EnumType.STRING)
    private EnumAccountType receiverAccountType;

    @Column(name = "END_TO_END_ID")
    private String endToEndId;

    @Column(name = "EXTERNAL_AUTH")
    private String externalAuth;

    @Column(name = "EXTERNAL_RECEIPT")
    private String externalReceipt;

    @Column(name = "EXTERNAL_TX_ID")
    private String externalTxid;

    @Column(name = "RECEIPT_RESUME", length = 1500)
    private String receiptResume;

    @CreationTimestamp
    @Column(name = "PAID_AT")
    private LocalDateTime paidAt;

    @Column(name = "MOVEMENT_CODE")
    @Enumerated(EnumType.STRING)
    private EnumMovementCode movementCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ISPB_CODE", referencedColumnName = "ID")
    private IspbCode ispbCode;

    public static ReceiptInfo findLastReceipt() {
        return find("select dri from ReceiptInfo dri where dri.id = (select max(id) from ReceiptInfo)")
                .firstResult();
    }

    public static ReceiptInfo findById(Integer id) {
        return find("id", id).firstResult();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTxId() {
        return txId;
    }

    public void setTxId(Integer txId) {
        this.txId = txId;
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

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
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

    public EnumAccountType getPayerAccountType() {
        return payerAccountType;
    }

    public void setPayerAccountType(EnumAccountType payerAccountType) {
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

    public EnumAccountType getReceiverAccountType() {
        return receiverAccountType;
    }

    public void setReceiverAccountType(EnumAccountType receiverAccountType) {
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

    public String getExternalReceipt() {
        return externalReceipt;
    }

    public void setExternalReceipt(String externalReceipt) {
        this.externalReceipt = externalReceipt;
    }

    public String getExternalTxid() {
        return externalTxid;
    }

    public void setExternalTxid(String externalTxid) {
        this.externalTxid = externalTxid;
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

    public IspbCode getIspbCode() {
        return ispbCode;
    }

    public void setIspbCode(IspbCode ispbCode) {
        this.ispbCode = ispbCode;
    }

    public String getReceiptResume() {
        return receiptResume;
    }

    public void setReceiptResume(String receiptResume) {
        this.receiptResume = receiptResume;
    }

}
