/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import Model.CursoTe;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.NotaCurso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Francisco
 */
public class CursoTeJpaController implements Serializable {

    public CursoTeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CursoTe cursoTe) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cursoTe.getNotaCursoCollection() == null) {
            cursoTe.setNotaCursoCollection(new ArrayList<NotaCurso>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<NotaCurso> attachedNotaCursoCollection = new ArrayList<NotaCurso>();
            for (NotaCurso notaCursoCollectionNotaCursoToAttach : cursoTe.getNotaCursoCollection()) {
                notaCursoCollectionNotaCursoToAttach = em.getReference(notaCursoCollectionNotaCursoToAttach.getClass(), notaCursoCollectionNotaCursoToAttach.getNotaCursoPK());
                attachedNotaCursoCollection.add(notaCursoCollectionNotaCursoToAttach);
            }
            cursoTe.setNotaCursoCollection(attachedNotaCursoCollection);
            em.persist(cursoTe);
            for (NotaCurso notaCursoCollectionNotaCurso : cursoTe.getNotaCursoCollection()) {
                CursoTe oldCursoTeOfNotaCursoCollectionNotaCurso = notaCursoCollectionNotaCurso.getCursoTe();
                notaCursoCollectionNotaCurso.setCursoTe(cursoTe);
                notaCursoCollectionNotaCurso = em.merge(notaCursoCollectionNotaCurso);
                if (oldCursoTeOfNotaCursoCollectionNotaCurso != null) {
                    oldCursoTeOfNotaCursoCollectionNotaCurso.getNotaCursoCollection().remove(notaCursoCollectionNotaCurso);
                    oldCursoTeOfNotaCursoCollectionNotaCurso = em.merge(oldCursoTeOfNotaCursoCollectionNotaCurso);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCursoTe(cursoTe.getIdCurso()) != null) {
                throw new PreexistingEntityException("CursoTe " + cursoTe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoTe cursoTe) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CursoTe persistentCursoTe = em.find(CursoTe.class, cursoTe.getIdCurso());
            Collection<NotaCurso> notaCursoCollectionOld = persistentCursoTe.getNotaCursoCollection();
            Collection<NotaCurso> notaCursoCollectionNew = cursoTe.getNotaCursoCollection();
            List<String> illegalOrphanMessages = null;
            for (NotaCurso notaCursoCollectionOldNotaCurso : notaCursoCollectionOld) {
                if (!notaCursoCollectionNew.contains(notaCursoCollectionOldNotaCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NotaCurso " + notaCursoCollectionOldNotaCurso + " since its cursoTe field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<NotaCurso> attachedNotaCursoCollectionNew = new ArrayList<NotaCurso>();
            for (NotaCurso notaCursoCollectionNewNotaCursoToAttach : notaCursoCollectionNew) {
                notaCursoCollectionNewNotaCursoToAttach = em.getReference(notaCursoCollectionNewNotaCursoToAttach.getClass(), notaCursoCollectionNewNotaCursoToAttach.getNotaCursoPK());
                attachedNotaCursoCollectionNew.add(notaCursoCollectionNewNotaCursoToAttach);
            }
            notaCursoCollectionNew = attachedNotaCursoCollectionNew;
            cursoTe.setNotaCursoCollection(notaCursoCollectionNew);
            cursoTe = em.merge(cursoTe);
            for (NotaCurso notaCursoCollectionNewNotaCurso : notaCursoCollectionNew) {
                if (!notaCursoCollectionOld.contains(notaCursoCollectionNewNotaCurso)) {
                    CursoTe oldCursoTeOfNotaCursoCollectionNewNotaCurso = notaCursoCollectionNewNotaCurso.getCursoTe();
                    notaCursoCollectionNewNotaCurso.setCursoTe(cursoTe);
                    notaCursoCollectionNewNotaCurso = em.merge(notaCursoCollectionNewNotaCurso);
                    if (oldCursoTeOfNotaCursoCollectionNewNotaCurso != null && !oldCursoTeOfNotaCursoCollectionNewNotaCurso.equals(cursoTe)) {
                        oldCursoTeOfNotaCursoCollectionNewNotaCurso.getNotaCursoCollection().remove(notaCursoCollectionNewNotaCurso);
                        oldCursoTeOfNotaCursoCollectionNewNotaCurso = em.merge(oldCursoTeOfNotaCursoCollectionNewNotaCurso);
                    }
                }
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
                Integer id = cursoTe.getIdCurso();
                if (findCursoTe(id) == null) {
                    throw new NonexistentEntityException("The cursoTe with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CursoTe cursoTe;
            try {
                cursoTe = em.getReference(CursoTe.class, id);
                cursoTe.getIdCurso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoTe with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<NotaCurso> notaCursoCollectionOrphanCheck = cursoTe.getNotaCursoCollection();
            for (NotaCurso notaCursoCollectionOrphanCheckNotaCurso : notaCursoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CursoTe (" + cursoTe + ") cannot be destroyed since the NotaCurso " + notaCursoCollectionOrphanCheckNotaCurso + " in its notaCursoCollection field has a non-nullable cursoTe field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cursoTe);
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

    public List<CursoTe> findCursoTeEntities() {
        return findCursoTeEntities(true, -1, -1);
    }

    public List<CursoTe> findCursoTeEntities(int maxResults, int firstResult) {
        return findCursoTeEntities(false, maxResults, firstResult);
    }

    private List<CursoTe> findCursoTeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoTe.class));
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

    public CursoTe findCursoTe(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoTe.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoTeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoTe> rt = cq.from(CursoTe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
