/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Tutor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Francisco
 */
@Stateless
public class TutorFacade extends AbstractFacade<Tutor> {

    @PersistenceContext(unitName = "ST_MVCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TutorFacade() {
        super(Tutor.class);
    }
    
}
