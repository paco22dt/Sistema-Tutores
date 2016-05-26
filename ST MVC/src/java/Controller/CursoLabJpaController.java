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
import Model.CursoLab;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.CursoTutor;
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
public class CursoLabJpaController implements Serializable {

    public CursoLabJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CursoLab cursoLab) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (cursoLab.getCursoTutorCollection() == null) {
            cursoLab.setCursoTutorCollection(new ArrayList<CursoTutor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CursoTutor CURSOTEidcurso = cursoLab.getCURSOTEidcurso();
            if (CURSOTEidcurso != null) {
                CURSOTEidcurso = em.getReference(CURSOTEidcurso.getClass(), CURSOTEidcurso.getIdCursoTutor());
                cursoLab.setCURSOTEidcurso(CURSOTEidcurso);
            }
            Collection<CursoTutor> attachedCursoTutorCollection = new ArrayList<CursoTutor>();
            for (CursoTutor cursoTutorCollectionCursoTutorToAttach : cursoLab.getCursoTutorCollection()) {
                cursoTutorCollectionCursoTutorToAttach = em.getReference(cursoTutorCollectionCursoTutorToAttach.getClass(), cursoTutorCollectionCursoTutorToAttach.getIdCursoTutor());
                attachedCursoTutorCollection.add(cursoTutorCollectionCursoTutorToAttach);
            }
            cursoLab.setCursoTutorCollection(attachedCursoTutorCollection);
            em.persist(cursoLab);
            if (CURSOTEidcurso != null) {
                CURSOTEidcurso.getCursoLabCollection().add(cursoLab);
                CURSOTEidcurso = em.merge(CURSOTEidcurso);
            }
            for (CursoTutor cursoTutorCollectionCursoTutor : cursoLab.getCursoTutorCollection()) {
                CursoLab oldCURSOLABseccionOfCursoTutorCollectionCursoTutor = cursoTutorCollectionCursoTutor.getCURSOLABseccion();
                cursoTutorCollectionCursoTutor.setCURSOLABseccion(cursoLab);
                cursoTutorCollectionCursoTutor = em.merge(cursoTutorCollectionCursoTutor);
                if (oldCURSOLABseccionOfCursoTutorCollectionCursoTutor != null) {
                    oldCURSOLABseccionOfCursoTutorCollectionCursoTutor.getCursoTutorCollection().remove(cursoTutorCollectionCursoTutor);
                    oldCURSOLABseccionOfCursoTutorCollectionCursoTutor = em.merge(oldCURSOLABseccionOfCursoTutorCollectionCursoTutor);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCursoLab(cursoLab.getSeccion()) != null) {
                throw new PreexistingEntityException("CursoLab " + cursoLab + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoLab cursoLab) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CursoLab persistentCursoLab = em.find(CursoLab.class, cursoLab.getSeccion());
            CursoTutor CURSOTEidcursoOld = persistentCursoLab.getCURSOTEidcurso();
            CursoTutor CURSOTEidcursoNew = cursoLab.getCURSOTEidcurso();
            Collection<CursoTutor> cursoTutorCollectionOld = persistentCursoLab.getCursoTutorCollection();
            Collection<CursoTutor> cursoTutorCollectionNew = cursoLab.getCursoTutorCollection();
            List<String> illegalOrphanMessages = null;
            for (CursoTutor cursoTutorCollectionOldCursoTutor : cursoTutorCollectionOld) {
                if (!cursoTutorCollectionNew.contains(cursoTutorCollectionOldCursoTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CursoTutor " + cursoTutorCollectionOldCursoTutor + " since its CURSOLABseccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (CURSOTEidcursoNew != null) {
                CURSOTEidcursoNew = em.getReference(CURSOTEidcursoNew.getClass(), CURSOTEidcursoNew.getIdCursoTutor());
                cursoLab.setCURSOTEidcurso(CURSOTEidcursoNew);
            }
            Collection<CursoTutor> attachedCursoTutorCollectionNew = new ArrayList<CursoTutor>();
            for (CursoTutor cursoTutorCollectionNewCursoTutorToAttach : cursoTutorCollectionNew) {
                cursoTutorCollectionNewCursoTutorToAttach = em.getReference(cursoTutorCollectionNewCursoTutorToAttach.getClass(), cursoTutorCollectionNewCursoTutorToAttach.getIdCursoTutor());
                attachedCursoTutorCollectionNew.add(cursoTutorCollectionNewCursoTutorToAttach);
            }
            cursoTutorCollectionNew = attachedCursoTutorCollectionNew;
            cursoLab.setCursoTutorCollection(cursoTutorCollectionNew);
            cursoLab = em.merge(cursoLab);
            if (CURSOTEidcursoOld != null && !CURSOTEidcursoOld.equals(CURSOTEidcursoNew)) {
                CURSOTEidcursoOld.getCursoLabCollection().remove(cursoLab);
                CURSOTEidcursoOld = em.merge(CURSOTEidcursoOld);
            }
            if (CURSOTEidcursoNew != null && !CURSOTEidcursoNew.equals(CURSOTEidcursoOld)) {
                CURSOTEidcursoNew.getCursoLabCollection().add(cursoLab);
                CURSOTEidcursoNew = em.merge(CURSOTEidcursoNew);
            }
            for (CursoTutor cursoTutorCollectionNewCursoTutor : cursoTutorCollectionNew) {
                if (!cursoTutorCollectionOld.contains(cursoTutorCollectionNewCursoTutor)) {
                    CursoLab oldCURSOLABseccionOfCursoTutorCollectionNewCursoTutor = cursoTutorCollectionNewCursoTutor.getCURSOLABseccion();
                    cursoTutorCollectionNewCursoTutor.setCURSOLABseccion(cursoLab);
                    cursoTutorCollectionNewCursoTutor = em.merge(cursoTutorCollectionNewCursoTutor);
                    if (oldCURSOLABseccionOfCursoTutorCollectionNewCursoTutor != null && !oldCURSOLABseccionOfCursoTutorCollectionNewCursoTutor.equals(cursoLab)) {
                        oldCURSOLABseccionOfCursoTutorCollectionNewCursoTutor.getCursoTutorCollection().remove(cursoTutorCollectionNewCursoTutor);
                        oldCURSOLABseccionOfCursoTutorCollectionNewCursoTutor = em.merge(oldCURSOLABseccionOfCursoTutorCollectionNewCursoTutor);
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
                Integer id = cursoLab.getSeccion();
                if (findCursoLab(id) == null) {
                    throw new NonexistentEntityException("The cursoLab with id " + id + " no longer exists.");
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
            CursoLab cursoLab;
            try {
                cursoLab = em.getReference(CursoLab.class, id);
                cursoLab.getSeccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoLab with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CursoTutor> cursoTutorCollectionOrphanCheck = cursoLab.getCursoTutorCollection();
            for (CursoTutor cursoTutorCollectionOrphanCheckCursoTutor : cursoTutorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CursoLab (" + cursoLab + ") cannot be destroyed since the CursoTutor " + cursoTutorCollectionOrphanCheckCursoTutor + " in its cursoTutorCollection field has a non-nullable CURSOLABseccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CursoTutor CURSOTEidcurso = cursoLab.getCURSOTEidcurso();
            if (CURSOTEidcurso != null) {
                CURSOTEidcurso.getCursoLabCollection().remove(cursoLab);
                CURSOTEidcurso = em.merge(CURSOTEidcurso);
            }
            em.remove(cursoLab);
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

    public List<CursoLab> findCursoLabEntities() {
        return findCursoLabEntities(true, -1, -1);
    }

    public List<CursoLab> findCursoLabEntities(int maxResults, int firstResult) {
        return findCursoLabEntities(false, maxResults, firstResult);
    }

    private List<CursoLab> findCursoLabEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoLab.class));
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

    public CursoLab findCursoLab(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoLab.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoLabCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoLab> rt = cq.from(CursoLab.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
