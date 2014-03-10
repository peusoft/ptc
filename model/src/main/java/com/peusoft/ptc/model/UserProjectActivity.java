/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author zhenja
 */
@Entity
@Table(name = "USER_PROJECT_ACTIVITY_MAPPING", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PROJECT_ACTIVITY_ID", "USER_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserProjectActivityMapping.findAll", query = "SELECT u FROM UserProjectActivityMapping u"),
    @NamedQuery(name = "UserProjectActivityMapping.findById", query = "SELECT u FROM UserProjectActivityMapping u WHERE u.id = :id"),
    @NamedQuery(name = "UserProjectActivityMapping.findByBudgetInHours", query = "SELECT u FROM UserProjectActivityMapping u WHERE u.budgetInHours = :budgetInHours")})
public class UserProjectActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "BUDGET_IN_HOURS", nullable = false, precision = 10, scale = 2)
    private BigDecimal budget;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "PROJECT_ACTIVITY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProjectActivity projectActivity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userProjectActivity", fetch = FetchType.LAZY)
    private Collection<UserProjectActivityPrice> userProjectActivityPrices;

    public UserProjectActivity() {
    }

    public UserProjectActivity(Integer id) {
        this.id = id;
    }

    public UserProjectActivity(Integer id, BigDecimal budgetInHours) {
        this.id = id;
        this.budget = budgetInHours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProjectActivity getProjectActivity() {
        return projectActivity;
    }

    public void setProjectActivity(ProjectActivity projectActivity) {
        this.projectActivity = projectActivity;
    }

    @XmlTransient
    public Collection<UserProjectActivityPrice> getUserProjectActivityPrices() {
        return userProjectActivityPrices;
    }

    public void setUserProjectActivityPrices(Collection<UserProjectActivityPrice> userProjectActivityPrices) {
        this.userProjectActivityPrices = userProjectActivityPrices;
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
        if (!(object instanceof UserProjectActivity)) {
            return false;
        }
        UserProjectActivity other = (UserProjectActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.UserProjectActivityMapping[ id=" + id + " ]";
    }
    
}
