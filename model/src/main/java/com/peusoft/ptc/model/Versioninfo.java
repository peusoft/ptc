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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "VERSIONINFO", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"VERSION"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Versioninfo.findAll", query = "SELECT v FROM Versioninfo v"),
    @NamedQuery(name = "Versioninfo.findById", query = "SELECT v FROM Versioninfo v WHERE v.id = :id"),
    @NamedQuery(name = "Versioninfo.findByVersion", query = "SELECT v FROM Versioninfo v WHERE v.version = :version"),
    @NamedQuery(name = "Versioninfo.findByInfo", query = "SELECT v FROM Versioninfo v WHERE v.info = :info")})
public class Versioninfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "VERSION", nullable = false, length = 10)
    private String version;
    @Basic(optional = false)
    @Column(name = "INFO", nullable = false, length = 100)
    private String info;

    public Versioninfo() {
    }

    public Versioninfo(Integer id) {
        this.id = id;
    }

    public Versioninfo(Integer id, String version, String info) {
        this.id = id;
        this.version = version;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        if (!(object instanceof Versioninfo)) {
            return false;
        }
        Versioninfo other = (Versioninfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.Versioninfo[ id=" + id + " ]";
    }
    
}
