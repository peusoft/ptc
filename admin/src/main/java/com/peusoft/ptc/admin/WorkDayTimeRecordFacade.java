/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.peusoft.ptc.admin;

import com.peusoft.ptc.model.WorkDayTimeRecord;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhenja
 */
@Stateless
public class WorkDayTimeRecordFacade extends AbstractFacade<WorkDayTimeRecord> {
    @PersistenceContext(unitName = "ptc")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkDayTimeRecordFacade() {
        super(WorkDayTimeRecord.class);
    }
    
}