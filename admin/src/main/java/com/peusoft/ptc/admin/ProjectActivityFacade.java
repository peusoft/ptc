/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.admin;

import com.peusoft.ptc.model.ProjectActivity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhenja
 */
@Stateless
public class ProjectActivityFacade extends AbstractFacade<ProjectActivity> {
    @PersistenceContext(unitName = "ptc")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectActivityFacade() {
        super(ProjectActivity.class);
    }
    
}
