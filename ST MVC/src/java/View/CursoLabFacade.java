/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.CursoLab;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Francisco
 */
@Stateless
public class CursoLabFacade extends AbstractFacade<CursoLab> {

    @PersistenceContext(unitName = "ST_MVCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoLabFacade() {
        super(CursoLab.class);
    }
    
}
