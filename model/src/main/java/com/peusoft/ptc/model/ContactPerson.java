/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table(name = "CONTACT_PERSON", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME", "SNAME", "ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContactPerson.findAll", query = "SELECT c FROM ContactPerson c"),
    @NamedQuery(name = "ContactPerson.findById", query = "SELECT c FROM ContactPerson c WHERE c.id = :id"),
    @NamedQuery(name = "ContactPerson.findByTitle", query = "SELECT c FROM ContactPerson c WHERE c.title = :title"),
    @NamedQuery(name = "ContactPerson.findByName", query = "SELECT c FROM ContactPerson c WHERE c.name = :name"),
    @NamedQuery(name = "ContactPerson.findBySname", query = "SELECT c FROM ContactPerson c WHERE c.sname = :sname"),
    @NamedQuery(name = "ContactPerson.findByTel", query = "SELECT c FROM ContactPerson c WHERE c.tel = :tel"),
    @NamedQuery(name = "ContactPerson.findByMobile", query = "SELECT c FROM ContactPerson c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "ContactPerson.findByFax", query = "SELECT c FROM ContactPerson c WHERE c.fax = :fax"),
    @NamedQuery(name = "ContactPerson.findByEmail", query = "SELECT c FROM ContactPerson c WHERE c.email = :email")})
public class ContactPerson implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "TITLE", length = 10)
    private String title;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @Column(name = "SNAME", nullable = false, length = 50)
    private String sname;
    @Column(name = "TEL", length = 20)
    private String tel;
    @Column(name = "MOBILE", length = 20)
    private String mobile;
    @Column(name = "FAX", length = 20)
    private String fax;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Embedded
    private Address address;

    public ContactPerson() {
    }

    public ContactPerson(Integer id) {
        this.id = id;
    }

    public ContactPerson(Integer id, String name, String sname) {
        this.id = id;
        this.name = name;
        this.sname = sname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        if (!(object instanceof ContactPerson)) {
            return false;
        }
        ContactPerson other = (ContactPerson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.peusoft.ptc.model.ContactPerson[ id=" + id + " ]";
    }
    
}
