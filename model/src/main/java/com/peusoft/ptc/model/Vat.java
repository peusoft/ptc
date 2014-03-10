/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhenja
 */
@Entity
@Table(name = "VAT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"BEGIN_PERIOD", "END_PERIOD"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vat.findAll", query = "SELECT v FROM Vat v"),
    @NamedQuery(name = "Vat.findByVatId", query = "SELECT v FROM Vat v WHERE v.vatId = :vatId"),
    @NamedQuery(name = "Vat.findByBeginPeriod", query = "SELECT v FROM Vat v WHERE v.beginPeriod = :beginPeriod"),
    @NamedQuery(name = "Vat.findByEndPeriod", query = "SELECT v FROM Vat v WHERE v.endPeriod = :endPeriod"),
    @NamedQuery(name = "Vat.findByPrice", query = "SELECT v FROM Vat v WHERE v.price = :price")})
public class Vat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VAT_ID", nullable = false)
    private Integer vatId;
    @Basic(optional = false)
    @Column(name = "BEGIN_PERIOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date beginPeriod;
    @Basic(optional = false)
    @Column(name = "END_PERIOD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endPeriod;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PRICE", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    public Vat() {
    }

    public Vat(Integer vatId) {
        this.vatId = vatId;
    }

    public Vat(Integer vatId, Date beginPeriod, Date endPeriod, BigDecimal price) {
        this.vatId = vatId;
        this.beginPeriod = beginPeriod;
        this.endPeriod = endPeriod;
        this.price = price;
    }

    public Integer getVatId() {
        return vatId;
    }

    public void setVatId(Integer vatId) {
        this.vatId = vatId;
    }

    public Date getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(Date beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vatId != null ? vatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vat)) {
            return false;
        }
        Vat other = (Vat) object;
        if ((this.vatId == null && other.vatId != null) || (this.vatId != null && !this.vatId.equals(other.vatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.Vat[ vatId=" + vatId + " ]";
    }
    
}
