package com.milk.pay.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(name = "PAY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paidDate;

    @Column(name = "TX_ID")
    private String txId;

    @Column(name = "COMPANY_ID")
    private Integer companyId;
    
    @Column(name = "LIQUIDATED")
    private boolean liquidated;

    @Column(name = "DAYLI_FINE")
    private Double dailyFine;

    @ManyToOne()
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private Date updatedAt;

    public Title() {
    }

    public static Title findByTxId(String txId) {
        return find("txId", txId).firstResult();
    }

    public static Title findById(Integer id) {
        return find("id", id).firstResult();
    }

    public static List<Title> listByCompanyId(Integer companyId) {
        return find("companyId", companyId).list();
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


    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

}
