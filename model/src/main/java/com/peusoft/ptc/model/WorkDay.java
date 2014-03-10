/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zhenja
 */
@Entity
@Table(name = "WORK_DAY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DAY_DATE", "USER_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkDay.findAll", query = "SELECT w FROM WorkDay w"),
    @NamedQuery(name = "WorkDay.findById", query = "SELECT w FROM WorkDay w WHERE w.id = :id"),
    @NamedQuery(name = "WorkDay.findByDayDate", query = "SELECT w FROM WorkDay w WHERE w.dayDate = :dayDate"),
    @NamedQuery(name = "WorkDay.findByBeginTime", query = "SELECT w FROM WorkDay w WHERE w.beginTime = :beginTime"),
    @NamedQuery(name = "WorkDay.findByEndTime", query = "SELECT w FROM WorkDay w WHERE w.endTime = :endTime")})
public class WorkDay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DAY_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "BEGIN_TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date beginTime;
    @Basic(optional = false)
    @Column(name = "END_TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "day", fetch = FetchType.EAGER)
    private Collection<WorkDayTimeRecord> workDayTimeRecords;

    public WorkDay() {
    }

    public WorkDay(Integer id) {
        this.id = id;
    }

    public WorkDay(Integer id, Date dayDate, Date beginTime, Date endTime) {
        this.id = id;
        this.date = dayDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlTransient
    public Collection<WorkDayTimeRecord> getWorkDayTimeRecords() {
        return workDayTimeRecords;
    }

    public void setWorkDayTimeRecords(Collection<WorkDayTimeRecord> workDayTimeRecords) {
        this.workDayTimeRecords = workDayTimeRecords;
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
        if (!(object instanceof WorkDay)) {
            return false;
        }
        WorkDay other = (WorkDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.WorkDay[ id=" + id + " ]";
    }
    
}
