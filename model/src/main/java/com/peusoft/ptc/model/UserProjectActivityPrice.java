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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "USER_PROJECT_ACTIVITY_PRICE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"UPA_ID", "BEGIN_PERIOD", "END_PERIOD"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserProjectActivityPrice.findAll", query = "SELECT u FROM UserProjectActivityPrice u"),
    @NamedQuery(name = "UserProjectActivityPrice.findById", query = "SELECT u FROM UserProjectActivityPrice u WHERE u.id = :id"),
    @NamedQuery(name = "UserProjectActivityPrice.findByBeginPeriod", query = "SELECT u FROM UserProjectActivityPrice u WHERE u.beginPeriod = :beginPeriod"),
    @NamedQuery(name = "UserProjectActivityPrice.findByEndPeriod", query = "SELECT u FROM UserProjectActivityPrice u WHERE u.endPeriod = :endPeriod"),
    @NamedQuery(name = "UserProjectActivityPrice.findByPrice", query = "SELECT u FROM UserProjectActivityPrice u WHERE u.price = :price"),
    @NamedQuery(name = "UserProjectActivityPrice.findByListCnt", query = "SELECT u FROM UserProjectActivityPrice u WHERE u.listCnt = :listCnt")})
public class UserProjectActivityPrice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
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
    @Basic(optional = false)
    @Column(name = "LIST_CNT", nullable = false)
    private int listCnt;
    @JoinColumn(name = "UPA_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserProjectActivity userProjectActivity;

    public UserProjectActivityPrice() {
    }

    public UserProjectActivityPrice(Integer id) {
        this.id = id;
    }

    public UserProjectActivityPrice(Integer id, Date beginPeriod, Date endPeriod, BigDecimal price, int listCnt) {
        this.id = id;
        this.beginPeriod = beginPeriod;
        this.endPeriod = endPeriod;
        this.price = price;
        this.listCnt = listCnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getListCnt() {
        return listCnt;
    }

    public void setListCnt(int listCnt) {
        this.listCnt = listCnt;
    }

    public UserProjectActivity getUserProjectActivity() {
        return userProjectActivity;
    }

    public void setUserProjectActivity(UserProjectActivity userProjectActivity) {
        this.userProjectActivity = userProjectActivity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserProjectActivityPrice)) {
            return false;
        }
        UserProjectActivityPrice other = (UserProjectActivityPrice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.UserProjectActivityPrice[ id=" + id + " ]";
    }
    
}
