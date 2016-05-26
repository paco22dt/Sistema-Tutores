/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.HistorialTutor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Francisco
 */
@Stateless
public class HistorialTutorFacade extends AbstractFacade<HistorialTutor> {

    @PersistenceContext(unitName = "ST_MVCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialTutorFacade() {
        super(HistorialTutor.class);
    }
    
}
