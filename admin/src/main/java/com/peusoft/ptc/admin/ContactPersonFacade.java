/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.admin;

import com.peusoft.ptc.model.ContactPerson;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhenja
 */
@Stateless
public class ContactPersonFacade extends AbstractFacade<ContactPerson> {
    @PersistenceContext(unitName = "ptc")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactPersonFacade() {
        super(ContactPerson.class);
    }
    
}
