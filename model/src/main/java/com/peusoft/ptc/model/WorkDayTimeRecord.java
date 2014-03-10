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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhenja
 */
@Entity
@Table(name = "WORK_DAY_TIME_RECORD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkDayTimeRecord.findAll", query = "SELECT w FROM WorkDayTimeRecord w"),
    @NamedQuery(name = "WorkDayTimeRecord.findById", query = "SELECT w FROM WorkDayTimeRecord w WHERE w.id = :id"),
    @NamedQuery(name = "WorkDayTimeRecord.findByBeginTime", query = "SELECT w FROM WorkDayTimeRecord w WHERE w.beginTime = :beginTime"),
    @NamedQuery(name = "WorkDayTimeRecord.findByEndTime", query = "SELECT w FROM WorkDayTimeRecord w WHERE w.endTime = :endTime"),
    @NamedQuery(name = "WorkDayTimeRecord.findByAmountOfHours", query = "SELECT w FROM WorkDayTimeRecord w WHERE w.amountOfHours = :amountOfHours"),
    @NamedQuery(name = "WorkDayTimeRecord.findByComment", query = "SELECT w FROM WorkDayTimeRecord w WHERE w.comment = :comment")})
public class WorkDayTimeRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "BEGIN_TIME")
    @Temporal(TemporalType.TIME)
    private Date beginTime;
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "AMOUNT_OF_HOURS", nullable = false, precision = 4, scale = 2)
    private BigDecimal amountOfHours;
    @Column(name = "COMMENT", length = 100)
    private String comment;
    @JoinColumn(name = "DAY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private WorkDay day;
    @JoinColumn(name = "USER_PROJECT_ACTIVITY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserProjectActivity userProjectActivity;

    public WorkDayTimeRecord() {
    }

    public WorkDayTimeRecord(Integer id) {
        this.id = id;
    }

    public WorkDayTimeRecord(Integer id, BigDecimal amountOfHours) {
        this.id = id;
        this.amountOfHours = amountOfHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAmountOfHours() {
        return amountOfHours;
    }

    public void setAmountOfHours(BigDecimal amountOfHours) {
        this.amountOfHours = amountOfHours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public WorkDay getDay() {
        return day;
    }

    public void setDay(WorkDay day) {
        this.day = day;
    }

    public UserProjectActivity getUserProjectActivityId() {
        return userProjectActivity;
    }

    public void setUserProjectActivityId(UserProjectActivity userProjectActivityId) {
        this.userProjectActivity = userProjectActivityId;
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
        if (!(object instanceof WorkDayTimeRecord)) {
            return false;
        }
        WorkDayTimeRecord other = (WorkDayTimeRecord) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.WorkDayTimeRecord[ id=" + id + " ]";
    }
    
}
