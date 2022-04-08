package com.milk.pay.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.milk.pay.entities.pattern.DafeEntity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_TITLE")
public class Title extends DafeEntity {

    @Id
    @SequenceGenerator(name = "ID_MILK__TITLE", sequenceName = "GEN_MILK__TITLE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK__TITLE")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "BALANCE")
    private Double balance;

    @Column(name = "DAYLI_FINE")
    private Double dailyFine;

    @Column(name = "EXTERNAL_TX_ID")
    private Integer externalTxId;
    
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(name = "PAID_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidAt;

    @Column(name = "LIQUIDATED")
    private boolean liquidated;
    
    @Column(name = "NF_NUMBER")
    private String nfNumber;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PAYMENT")
    private List<Payment> listPayment;

    public static Title findByTxId(String txId) {
        return find("txId", txId).firstResult();
    }

    public static Title findById(Integer id) {
        return find("id", id).firstResult();
    }

    public static List<Title> listByUserId(Integer userId) {
        return find("user.id", userId).list();
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
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLiquidated() {
        return liquidated;
    }

    public void setLiquidated(boolean liquidated) {
        this.liquidated = liquidated;
    }

    public Double getDailyFine() {
        return dailyFine;
    }

    public void setDailyFine(Double dailyFine) {
        this.dailyFine = dailyFine;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getExternalTxId() {
        return externalTxId;
    }

    public void setExternalTxId(Integer externalTxId) {
        this.externalTxId = externalTxId;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public String getNfNumber() {
        return nfNumber;
    }

    public void setNfNumber(String nfNumber) {
        this.nfNumber = nfNumber;
    }

    public List<Payment> getListPayment() {
        return listPayment;
    }

    public void setListPayment(List<Payment> listPayment) {
        this.listPayment = listPayment;
    }

}
