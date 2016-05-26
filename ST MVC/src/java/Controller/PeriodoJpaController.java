/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.CursoTutor;
import Model.Periodo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Francisco
 */
public class PeriodoJpaController implements Serializable {

    public PeriodoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodo periodo) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CursoTutor CURSOTUTORidcursotutor = periodo.getCURSOTUTORidcursotutor();
            if (CURSOTUTORidcursotutor != null) {
                CURSOTUTORidcursotutor = em.getReference(CURSOTUTORidcursotutor.getClass(), CURSOTUTORidcursotutor.getIdCursoTutor());
                periodo.setCURSOTUTORidcursotutor(CURSOTUTORidcursotutor);
            }
            em.persist(periodo);
            if (CURSOTUTORidcursotutor != null) {
                CURSOTUTORidcursotutor.getPeriodoCollection().add(periodo);
                CURSOTUTORidcursotutor = em.merge(CURSOTUTORidcursotutor);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodo periodo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Periodo persistentPeriodo = em.find(Periodo.class, periodo.getIdPeriodo());
            CursoTutor CURSOTUTORidcursotutorOld = persistentPeriodo.getCURSOTUTORidcursotutor();
            CursoTutor CURSOTUTORidcursotutorNew = periodo.getCURSOTUTORidcursotutor();
            if (CURSOTUTORidcursotutorNew != null) {
                CURSOTUTORidcursotutorNew = em.getReference(CURSOTUTORidcursotutorNew.getClass(), CURSOTUTORidcursotutorNew.getIdCursoTutor());
                periodo.setCURSOTUTORidcursotutor(CURSOTUTORidcursotutorNew);
            }
            periodo = em.merge(periodo);
            if (CURSOTUTORidcursotutorOld != null && !CURSOTUTORidcursotutorOld.equals(CURSOTUTORidcursotutorNew)) {
                CURSOTUTORidcursotutorOld.getPeriodoCollection().remove(periodo);
                CURSOTUTORidcursotutorOld = em.merge(CURSOTUTORidcursotutorOld);
            }
            if (CURSOTUTORidcursotutorNew != null && !CURSOTUTORidcursotutorNew.equals(CURSOTUTORidcursotutorOld)) {
                CURSOTUTORidcursotutorNew.getPeriodoCollection().add(periodo);
                CURSOTUTORidcursotutorNew = em.merge(CURSOTUTORidcursotutorNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = periodo.getIdPeriodo();
                if (findPeriodo(id) == null) {
                    throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Periodo periodo;
            try {
                periodo = em.getReference(Periodo.class, id);
                periodo.getIdPeriodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.", enfe);
            }
            CursoTutor CURSOTUTORidcursotutor = periodo.getCURSOTUTORidcursotutor();
            if (CURSOTUTORidcursotutor != null) {
                CURSOTUTORidcursotutor.getPeriodoCollection().remove(periodo);
                CURSOTUTORidcursotutor = em.merge(CURSOTUTORidcursotutor);
            }
            em.remove(periodo);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodo> findPeriodoEntities() {
        return findPeriodoEntities(true, -1, -1);
    }

    public List<Periodo> findPeriodoEntities(int maxResults, int firstResult) {
        return findPeriodoEntities(false, maxResults, firstResult);
    }

    private List<Periodo> findPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Periodo findPeriodo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodo> rt = cq.from(Periodo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
