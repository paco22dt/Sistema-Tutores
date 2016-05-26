/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Tutor;
import Model.CursoTe;
import Model.NotaCurso;
import Model.NotaCursoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Francisco
 */
public class NotaCursoJpaController implements Serializable {

    public NotaCursoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaCurso notaCurso) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (notaCurso.getNotaCursoPK() == null) {
            notaCurso.setNotaCursoPK(new NotaCursoPK());
        }
        notaCurso.getNotaCursoPK().setTUTORcarnet(notaCurso.getTutor().getCarnet());
        notaCurso.getNotaCursoPK().setCURSOTEidcurso(notaCurso.getCursoTe().getIdCurso());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tutor tutor = notaCurso.getTutor();
            if (tutor != null) {
                tutor = em.getReference(tutor.getClass(), tutor.getCarnet());
                notaCurso.setTutor(tutor);
            }
            CursoTe cursoTe = notaCurso.getCursoTe();
            if (cursoTe != null) {
                cursoTe = em.getReference(cursoTe.getClass(), cursoTe.getIdCurso());
                notaCurso.setCursoTe(cursoTe);
            }
            em.persist(notaCurso);
            if (tutor != null) {
                tutor.getNotaCursoCollection().add(notaCurso);
                tutor = em.merge(tutor);
            }
            if (cursoTe != null) {
                cursoTe.getNotaCursoCollection().add(notaCurso);
                cursoTe = em.merge(cursoTe);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findNotaCurso(notaCurso.getNotaCursoPK()) != null) {
                throw new PreexistingEntityException("NotaCurso " + notaCurso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotaCurso notaCurso) throws NonexistentEntityException, RollbackFailureException, Exception {
        notaCurso.getNotaCursoPK().setTUTORcarnet(notaCurso.getTutor().getCarnet());
        notaCurso.getNotaCursoPK().setCURSOTEidcurso(notaCurso.getCursoTe().getIdCurso());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            NotaCurso persistentNotaCurso = em.find(NotaCurso.class, notaCurso.getNotaCursoPK());
            Tutor tutorOld = persistentNotaCurso.getTutor();
            Tutor tutorNew = notaCurso.getTutor();
            CursoTe cursoTeOld = persistentNotaCurso.getCursoTe();
            CursoTe cursoTeNew = notaCurso.getCursoTe();
            if (tutorNew != null) {
                tutorNew = em.getReference(tutorNew.getClass(), tutorNew.getCarnet());
                notaCurso.setTutor(tutorNew);
            }
            if (cursoTeNew != null) {
                cursoTeNew = em.getReference(cursoTeNew.getClass(), cursoTeNew.getIdCurso());
                notaCurso.setCursoTe(cursoTeNew);
            }
            notaCurso = em.merge(notaCurso);
            if (tutorOld != null && !tutorOld.equals(tutorNew)) {
                tutorOld.getNotaCursoCollection().remove(notaCurso);
                tutorOld = em.merge(tutorOld);
            }
            if (tutorNew != null && !tutorNew.equals(tutorOld)) {
                tutorNew.getNotaCursoCollection().add(notaCurso);
                tutorNew = em.merge(tutorNew);
            }
            if (cursoTeOld != null && !cursoTeOld.equals(cursoTeNew)) {
                cursoTeOld.getNotaCursoCollection().remove(notaCurso);
                cursoTeOld = em.merge(cursoTeOld);
            }
            if (cursoTeNew != null && !cursoTeNew.equals(cursoTeOld)) {
                cursoTeNew.getNotaCursoCollection().add(notaCurso);
                cursoTeNew = em.merge(cursoTeNew);
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
                NotaCursoPK id = notaCurso.getNotaCursoPK();
                if (findNotaCurso(id) == null) {
                    throw new NonexistentEntityException("The notaCurso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(NotaCursoPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            NotaCurso notaCurso;
            try {
                notaCurso = em.getReference(NotaCurso.class, id);
                notaCurso.getNotaCursoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaCurso with id " + id + " no longer exists.", enfe);
            }
            Tutor tutor = notaCurso.getTutor();
            if (tutor != null) {
                tutor.getNotaCursoCollection().remove(notaCurso);
                tutor = em.merge(tutor);
            }
            CursoTe cursoTe = notaCurso.getCursoTe();
            if (cursoTe != null) {
                cursoTe.getNotaCursoCollection().remove(notaCurso);
                cursoTe = em.merge(cursoTe);
            }
            em.remove(notaCurso);
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

    public List<NotaCurso> findNotaCursoEntities() {
        return findNotaCursoEntities(true, -1, -1);
    }

    public List<NotaCurso> findNotaCursoEntities(int maxResults, int firstResult) {
        return findNotaCursoEntities(false, maxResults, firstResult);
    }

    private List<NotaCurso> findNotaCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotaCurso.class));
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

    public NotaCurso findNotaCurso(NotaCursoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaCurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotaCurso> rt = cq.from(NotaCurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
