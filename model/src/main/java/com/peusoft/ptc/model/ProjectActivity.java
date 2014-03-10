/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.model;

import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zhenja
 */
@Entity
@Table(name = "PROJECT_ACTIVITY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PROJECT_ACTIVITY_NAME", "PROJECT_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectActivity.findAll", query = "SELECT p FROM ProjectActivity p"),
    @NamedQuery(name = "ProjectActivity.findById", query = "SELECT p FROM ProjectActivity p WHERE p.id = :id"),
    @NamedQuery(name = "ProjectActivity.findByProjectActivityName", query = "SELECT p FROM ProjectActivity p WHERE p.projectActivityName = :projectActivityName")})
public class ProjectActivity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PROJECT_ACTIVITY_NAME", nullable = false, length = 100)
    private String name;
    @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Project project;

    public ProjectActivity() {
    }

    public ProjectActivity(Integer id) {
        this.id = id;
    }

    public ProjectActivity(Integer id, String projectActivityName) {
        this.id = id;
        this.name = projectActivityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
        if (!(object instanceof ProjectActivity)) {
            return false;
        }
        ProjectActivity other = (ProjectActivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.ProjectActivity[ id=" + id + " ]";
    }
    
}
