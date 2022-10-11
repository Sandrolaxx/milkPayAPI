package com.aktie.aktiepay.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 *
 * @author SRamos
 */
@Entity
@Table(name = "MILK_ISPB_CODES")
public class IspbCode extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "ID_MILK_ISPB", sequenceName = "GEN_MILK_ISPB", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MILK_ISPB")
    @Column(name = "ID", nullable = false, precision = 0, scale = -127)
    private Integer id;

    @Column(name = "INCLUSION_DATE")
    private String inclusionDate;
    
    @Column(name = "TYPE")
    private String type;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "OPERTATION_DATE")
    private String operationDate;
    
    @Column(name = "ISPB_CODE")
    private String ispbCode;

    public static IspbCode findByCode(String ispbCode) {
        return IspbCode.find("ispbCode", ispbCode).firstResult();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInclusionDate() {
        return inclusionDate;
    }

    public void setInclusionDate(String inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public String getIspbCode() {
        return ispbCode;
    }

    public void setIspbCode(String ispbCode) {
        this.ispbCode = ispbCode;
    }
    
}
