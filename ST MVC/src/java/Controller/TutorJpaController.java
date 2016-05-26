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
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.HistorialTutor;
import java.util.ArrayList;
import java.util.Collection;
import Model.NotaCurso;
import Model.CursoTutor;
import Model.Tutor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Francisco
 */
public class TutorJpaController implements Serializable {

    public TutorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutor tutor) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (tutor.getHistorialTutorCollection() == null) {
            tutor.setHistorialTutorCollection(new ArrayList<HistorialTutor>());
        }
        if (tutor.getNotaCursoCollection() == null) {
            tutor.setNotaCursoCollection(new ArrayList<NotaCurso>());
        }
        if (tutor.getCursoTutorCollection() == null) {
            tutor.setCursoTutorCollection(new ArrayList<CursoTutor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<HistorialTutor> attachedHistorialTutorCollection = new ArrayList<HistorialTutor>();
            for (HistorialTutor historialTutorCollectionHistorialTutorToAttach : tutor.getHistorialTutorCollection()) {
                historialTutorCollectionHistorialTutorToAttach = em.getReference(historialTutorCollectionHistorialTutorToAttach.getClass(), historialTutorCollectionHistorialTutorToAttach.getIdHistorialTutor());
                attachedHistorialTutorCollection.add(historialTutorCollectionHistorialTutorToAttach);
            }
            tutor.setHistorialTutorCollection(attachedHistorialTutorCollection);
            Collection<NotaCurso> attachedNotaCursoCollection = new ArrayList<NotaCurso>();
            for (NotaCurso notaCursoCollectionNotaCursoToAttach : tutor.getNotaCursoCollection()) {
                notaCursoCollectionNotaCursoToAttach = em.getReference(notaCursoCollectionNotaCursoToAttach.getClass(), notaCursoCollectionNotaCursoToAttach.getNotaCursoPK());
                attachedNotaCursoCollection.add(notaCursoCollectionNotaCursoToAttach);
            }
            tutor.setNotaCursoCollection(attachedNotaCursoCollection);
            Collection<CursoTutor> attachedCursoTutorCollection = new ArrayList<CursoTutor>();
            for (CursoTutor cursoTutorCollectionCursoTutorToAttach : tutor.getCursoTutorCollection()) {
                cursoTutorCollectionCursoTutorToAttach = em.getReference(cursoTutorCollectionCursoTutorToAttach.getClass(), cursoTutorCollectionCursoTutorToAttach.getIdCursoTutor());
                attachedCursoTutorCollection.add(cursoTutorCollectionCursoTutorToAttach);
            }
            tutor.setCursoTutorCollection(attachedCursoTutorCollection);
            em.persist(tutor);
            for (HistorialTutor historialTutorCollectionHistorialTutor : tutor.getHistorialTutorCollection()) {
                Tutor oldTUTORcarnetOfHistorialTutorCollectionHistorialTutor = historialTutorCollectionHistorialTutor.getTUTORcarnet();
                historialTutorCollectionHistorialTutor.setTUTORcarnet(tutor);
                historialTutorCollectionHistorialTutor = em.merge(historialTutorCollectionHistorialTutor);
                if (oldTUTORcarnetOfHistorialTutorCollectionHistorialTutor != null) {
                    oldTUTORcarnetOfHistorialTutorCollectionHistorialTutor.getHistorialTutorCollection().remove(historialTutorCollectionHistorialTutor);
                    oldTUTORcarnetOfHistorialTutorCollectionHistorialTutor = em.merge(oldTUTORcarnetOfHistorialTutorCollectionHistorialTutor);
                }
            }
            for (NotaCurso notaCursoCollectionNotaCurso : tutor.getNotaCursoCollection()) {
                Tutor oldTutorOfNotaCursoCollectionNotaCurso = notaCursoCollectionNotaCurso.getTutor();
                notaCursoCollectionNotaCurso.setTutor(tutor);
                notaCursoCollectionNotaCurso = em.merge(notaCursoCollectionNotaCurso);
                if (oldTutorOfNotaCursoCollectionNotaCurso != null) {
                    oldTutorOfNotaCursoCollectionNotaCurso.getNotaCursoCollection().remove(notaCursoCollectionNotaCurso);
                    oldTutorOfNotaCursoCollectionNotaCurso = em.merge(oldTutorOfNotaCursoCollectionNotaCurso);
                }
            }
            for (CursoTutor cursoTutorCollectionCursoTutor : tutor.getCursoTutorCollection()) {
                Tutor oldTUTORcarnetOfCursoTutorCollectionCursoTutor = cursoTutorCollectionCursoTutor.getTUTORcarnet();
                cursoTutorCollectionCursoTutor.setTUTORcarnet(tutor);
                cursoTutorCollectionCursoTutor = em.merge(cursoTutorCollectionCursoTutor);
                if (oldTUTORcarnetOfCursoTutorCollectionCursoTutor != null) {
                    oldTUTORcarnetOfCursoTutorCollectionCursoTutor.getCursoTutorCollection().remove(cursoTutorCollectionCursoTutor);
                    oldTUTORcarnetOfCursoTutorCollectionCursoTutor = em.merge(oldTUTORcarnetOfCursoTutorCollectionCursoTutor);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTutor(tutor.getCarnet()) != null) {
                throw new PreexistingEntityException("Tutor " + tutor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tutor tutor) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getCarnet());
            Collection<HistorialTutor> historialTutorCollectionOld = persistentTutor.getHistorialTutorCollection();
            Collection<HistorialTutor> historialTutorCollectionNew = tutor.getHistorialTutorCollection();
            Collection<NotaCurso> notaCursoCollectionOld = persistentTutor.getNotaCursoCollection();
            Collection<NotaCurso> notaCursoCollectionNew = tutor.getNotaCursoCollection();
            Collection<CursoTutor> cursoTutorCollectionOld = persistentTutor.getCursoTutorCollection();
            Collection<CursoTutor> cursoTutorCollectionNew = tutor.getCursoTutorCollection();
            List<String> illegalOrphanMessages = null;
            for (HistorialTutor historialTutorCollectionOldHistorialTutor : historialTutorCollectionOld) {
                if (!historialTutorCollectionNew.contains(historialTutorCollectionOldHistorialTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistorialTutor " + historialTutorCollectionOldHistorialTutor + " since its TUTORcarnet field is not nullable.");
                }
            }
            for (NotaCurso notaCursoCollectionOldNotaCurso : notaCursoCollectionOld) {
                if (!notaCursoCollectionNew.contains(notaCursoCollectionOldNotaCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain NotaCurso " + notaCursoCollectionOldNotaCurso + " since its tutor field is not nullable.");
                }
            }
            for (CursoTutor cursoTutorCollectionOldCursoTutor : cursoTutorCollectionOld) {
                if (!cursoTutorCollectionNew.contains(cursoTutorCollectionOldCursoTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CursoTutor " + cursoTutorCollectionOldCursoTutor + " since its TUTORcarnet field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<HistorialTutor> attachedHistorialTutorCollectionNew = new ArrayList<HistorialTutor>();
            for (HistorialTutor historialTutorCollectionNewHistorialTutorToAttach : historialTutorCollectionNew) {
                historialTutorCollectionNewHistorialTutorToAttach = em.getReference(historialTutorCollectionNewHistorialTutorToAttach.getClass(), historialTutorCollectionNewHistorialTutorToAttach.getIdHistorialTutor());
                attachedHistorialTutorCollectionNew.add(historialTutorCollectionNewHistorialTutorToAttach);
            }
            historialTutorCollectionNew = attachedHistorialTutorCollectionNew;
            tutor.setHistorialTutorCollection(historialTutorCollectionNew);
            Collection<NotaCurso> attachedNotaCursoCollectionNew = new ArrayList<NotaCurso>();
            for (NotaCurso notaCursoCollectionNewNotaCursoToAttach : notaCursoCollectionNew) {
                notaCursoCollectionNewNotaCursoToAttach = em.getReference(notaCursoCollectionNewNotaCursoToAttach.getClass(), notaCursoCollectionNewNotaCursoToAttach.getNotaCursoPK());
                attachedNotaCursoCollectionNew.add(notaCursoCollectionNewNotaCursoToAttach);
            }
            notaCursoCollectionNew = attachedNotaCursoCollectionNew;
            tutor.setNotaCursoCollection(notaCursoCollectionNew);
            Collection<CursoTutor> attachedCursoTutorCollectionNew = new ArrayList<CursoTutor>();
            for (CursoTutor cursoTutorCollectionNewCursoTutorToAttach : cursoTutorCollectionNew) {
                cursoTutorCollectionNewCursoTutorToAttach = em.getReference(cursoTutorCollectionNewCursoTutorToAttach.getClass(), cursoTutorCollectionNewCursoTutorToAttach.getIdCursoTutor());
                attachedCursoTutorCollectionNew.add(cursoTutorCollectionNewCursoTutorToAttach);
            }
            cursoTutorCollectionNew = attachedCursoTutorCollectionNew;
            tutor.setCursoTutorCollection(cursoTutorCollectionNew);
            tutor = em.merge(tutor);
            for (HistorialTutor historialTutorCollectionNewHistorialTutor : historialTutorCollectionNew) {
                if (!historialTutorCollectionOld.contains(historialTutorCollectionNewHistorialTutor)) {
                    Tutor oldTUTORcarnetOfHistorialTutorCollectionNewHistorialTutor = historialTutorCollectionNewHistorialTutor.getTUTORcarnet();
                    historialTutorCollectionNewHistorialTutor.setTUTORcarnet(tutor);
                    historialTutorCollectionNewHistorialTutor = em.merge(historialTutorCollectionNewHistorialTutor);
                    if (oldTUTORcarnetOfHistorialTutorCollectionNewHistorialTutor != null && !oldTUTORcarnetOfHistorialTutorCollectionNewHistorialTutor.equals(tutor)) {
                        oldTUTORcarnetOfHistorialTutorCollectionNewHistorialTutor.getHistorialTutorCollection().remove(historialTutorCollectionNewHistorialTutor);
                        oldTUTORcarnetOfHistorialTutorCollectionNewHistorialTutor = em.merge(oldTUTORcarnetOfHistorialTutorCollectionNewHistorialTutor);
                    }
                }
            }
            for (NotaCurso notaCursoCollectionNewNotaCurso : notaCursoCollectionNew) {
                if (!notaCursoCollectionOld.contains(notaCursoCollectionNewNotaCurso)) {
                    Tutor oldTutorOfNotaCursoCollectionNewNotaCurso = notaCursoCollectionNewNotaCurso.getTutor();
                    notaCursoCollectionNewNotaCurso.setTutor(tutor);
                    notaCursoCollectionNewNotaCurso = em.merge(notaCursoCollectionNewNotaCurso);
                    if (oldTutorOfNotaCursoCollectionNewNotaCurso != null && !oldTutorOfNotaCursoCollectionNewNotaCurso.equals(tutor)) {
                        oldTutorOfNotaCursoCollectionNewNotaCurso.getNotaCursoCollection().remove(notaCursoCollectionNewNotaCurso);
                        oldTutorOfNotaCursoCollectionNewNotaCurso = em.merge(oldTutorOfNotaCursoCollectionNewNotaCurso);
                    }
                }
            }
            for (CursoTutor cursoTutorCollectionNewCursoTutor : cursoTutorCollectionNew) {
                if (!cursoTutorCollectionOld.contains(cursoTutorCollectionNewCursoTutor)) {
                    Tutor oldTUTORcarnetOfCursoTutorCollectionNewCursoTutor = cursoTutorCollectionNewCursoTutor.getTUTORcarnet();
                    cursoTutorCollectionNewCursoTutor.setTUTORcarnet(tutor);
                    cursoTutorCollectionNewCursoTutor = em.merge(cursoTutorCollectionNewCursoTutor);
                    if (oldTUTORcarnetOfCursoTutorCollectionNewCursoTutor != null && !oldTUTORcarnetOfCursoTutorCollectionNewCursoTutor.equals(tutor)) {
                        oldTUTORcarnetOfCursoTutorCollectionNewCursoTutor.getCursoTutorCollection().remove(cursoTutorCollectionNewCursoTutor);
                        oldTUTORcarnetOfCursoTutorCollectionNewCursoTutor = em.merge(oldTUTORcarnetOfCursoTutorCollectionNewCursoTutor);
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
                Integer id = tutor.getCarnet();
                if (findTutor(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getCarnet();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<HistorialTutor> historialTutorCollectionOrphanCheck = tutor.getHistorialTutorCollection();
            for (HistorialTutor historialTutorCollectionOrphanCheckHistorialTutor : historialTutorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutor (" + tutor + ") cannot be destroyed since the HistorialTutor " + historialTutorCollectionOrphanCheckHistorialTutor + " in its historialTutorCollection field has a non-nullable TUTORcarnet field.");
            }
            Collection<NotaCurso> notaCursoCollectionOrphanCheck = tutor.getNotaCursoCollection();
            for (NotaCurso notaCursoCollectionOrphanCheckNotaCurso : notaCursoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutor (" + tutor + ") cannot be destroyed since the NotaCurso " + notaCursoCollectionOrphanCheckNotaCurso + " in its notaCursoCollection field has a non-nullable tutor field.");
            }
            Collection<CursoTutor> cursoTutorCollectionOrphanCheck = tutor.getCursoTutorCollection();
            for (CursoTutor cursoTutorCollectionOrphanCheckCursoTutor : cursoTutorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tutor (" + tutor + ") cannot be destroyed since the CursoTutor " + cursoTutorCollectionOrphanCheckCursoTutor + " in its cursoTutorCollection field has a non-nullable TUTORcarnet field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tutor);
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

    public List<Tutor> findTutorEntities() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor findTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
