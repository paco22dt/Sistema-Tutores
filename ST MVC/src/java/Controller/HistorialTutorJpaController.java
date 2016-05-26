/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import Model.HistorialTutor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Tutor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Francisco
 */
public class HistorialTutorJpaController implements Serializable {

    public HistorialTutorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialTutor historialTutor) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tutor TUTORcarnet = historialTutor.getTUTORcarnet();
            if (TUTORcarnet != null) {
                TUTORcarnet = em.getReference(TUTORcarnet.getClass(), TUTORcarnet.getCarnet());
                historialTutor.setTUTORcarnet(TUTORcarnet);
            }
            em.persist(historialTutor);
            if (TUTORcarnet != null) {
                TUTORcarnet.getHistorialTutorCollection().add(historialTutor);
                TUTORcarnet = em.merge(TUTORcarnet);
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

    public void edit(HistorialTutor historialTutor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            HistorialTutor persistentHistorialTutor = em.find(HistorialTutor.class, historialTutor.getIdHistorialTutor());
            Tutor TUTORcarnetOld = persistentHistorialTutor.getTUTORcarnet();
            Tutor TUTORcarnetNew = historialTutor.getTUTORcarnet();
            if (TUTORcarnetNew != null) {
                TUTORcarnetNew = em.getReference(TUTORcarnetNew.getClass(), TUTORcarnetNew.getCarnet());
                historialTutor.setTUTORcarnet(TUTORcarnetNew);
            }
            historialTutor = em.merge(historialTutor);
            if (TUTORcarnetOld != null && !TUTORcarnetOld.equals(TUTORcarnetNew)) {
                TUTORcarnetOld.getHistorialTutorCollection().remove(historialTutor);
                TUTORcarnetOld = em.merge(TUTORcarnetOld);
            }
            if (TUTORcarnetNew != null && !TUTORcarnetNew.equals(TUTORcarnetOld)) {
                TUTORcarnetNew.getHistorialTutorCollection().add(historialTutor);
                TUTORcarnetNew = em.merge(TUTORcarnetNew);
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
                Integer id = historialTutor.getIdHistorialTutor();
                if (findHistorialTutor(id) == null) {
                    throw new NonexistentEntityException("The historialTutor with id " + id + " no longer exists.");
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
            HistorialTutor historialTutor;
            try {
                historialTutor = em.getReference(HistorialTutor.class, id);
                historialTutor.getIdHistorialTutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialTutor with id " + id + " no longer exists.", enfe);
            }
            Tutor TUTORcarnet = historialTutor.getTUTORcarnet();
            if (TUTORcarnet != null) {
                TUTORcarnet.getHistorialTutorCollection().remove(historialTutor);
                TUTORcarnet = em.merge(TUTORcarnet);
            }
            em.remove(historialTutor);
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

    public List<HistorialTutor> findHistorialTutorEntities() {
        return findHistorialTutorEntities(true, -1, -1);
    }

    public List<HistorialTutor> findHistorialTutorEntities(int maxResults, int firstResult) {
        return findHistorialTutorEntities(false, maxResults, firstResult);
    }

    private List<HistorialTutor> findHistorialTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialTutor.class));
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

    public HistorialTutor findHistorialTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialTutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialTutor> rt = cq.from(HistorialTutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
