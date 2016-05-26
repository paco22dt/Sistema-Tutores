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
import Model.CursoTutor;
import Model.Docente;
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
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (docente.getCursoTutorCollection() == null) {
            docente.setCursoTutorCollection(new ArrayList<CursoTutor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<CursoTutor> attachedCursoTutorCollection = new ArrayList<CursoTutor>();
            for (CursoTutor cursoTutorCollectionCursoTutorToAttach : docente.getCursoTutorCollection()) {
                cursoTutorCollectionCursoTutorToAttach = em.getReference(cursoTutorCollectionCursoTutorToAttach.getClass(), cursoTutorCollectionCursoTutorToAttach.getIdCursoTutor());
                attachedCursoTutorCollection.add(cursoTutorCollectionCursoTutorToAttach);
            }
            docente.setCursoTutorCollection(attachedCursoTutorCollection);
            em.persist(docente);
            for (CursoTutor cursoTutorCollectionCursoTutor : docente.getCursoTutorCollection()) {
                Docente oldDOCENTEiddocenteOfCursoTutorCollectionCursoTutor = cursoTutorCollectionCursoTutor.getDOCENTEiddocente();
                cursoTutorCollectionCursoTutor.setDOCENTEiddocente(docente);
                cursoTutorCollectionCursoTutor = em.merge(cursoTutorCollectionCursoTutor);
                if (oldDOCENTEiddocenteOfCursoTutorCollectionCursoTutor != null) {
                    oldDOCENTEiddocenteOfCursoTutorCollectionCursoTutor.getCursoTutorCollection().remove(cursoTutorCollectionCursoTutor);
                    oldDOCENTEiddocenteOfCursoTutorCollectionCursoTutor = em.merge(oldDOCENTEiddocenteOfCursoTutorCollectionCursoTutor);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDocente(docente.getIdDocente()) != null) {
                throw new PreexistingEntityException("Docente " + docente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Docente persistentDocente = em.find(Docente.class, docente.getIdDocente());
            Collection<CursoTutor> cursoTutorCollectionOld = persistentDocente.getCursoTutorCollection();
            Collection<CursoTutor> cursoTutorCollectionNew = docente.getCursoTutorCollection();
            List<String> illegalOrphanMessages = null;
            for (CursoTutor cursoTutorCollectionOldCursoTutor : cursoTutorCollectionOld) {
                if (!cursoTutorCollectionNew.contains(cursoTutorCollectionOldCursoTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CursoTutor " + cursoTutorCollectionOldCursoTutor + " since its DOCENTEiddocente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CursoTutor> attachedCursoTutorCollectionNew = new ArrayList<CursoTutor>();
            for (CursoTutor cursoTutorCollectionNewCursoTutorToAttach : cursoTutorCollectionNew) {
                cursoTutorCollectionNewCursoTutorToAttach = em.getReference(cursoTutorCollectionNewCursoTutorToAttach.getClass(), cursoTutorCollectionNewCursoTutorToAttach.getIdCursoTutor());
                attachedCursoTutorCollectionNew.add(cursoTutorCollectionNewCursoTutorToAttach);
            }
            cursoTutorCollectionNew = attachedCursoTutorCollectionNew;
            docente.setCursoTutorCollection(cursoTutorCollectionNew);
            docente = em.merge(docente);
            for (CursoTutor cursoTutorCollectionNewCursoTutor : cursoTutorCollectionNew) {
                if (!cursoTutorCollectionOld.contains(cursoTutorCollectionNewCursoTutor)) {
                    Docente oldDOCENTEiddocenteOfCursoTutorCollectionNewCursoTutor = cursoTutorCollectionNewCursoTutor.getDOCENTEiddocente();
                    cursoTutorCollectionNewCursoTutor.setDOCENTEiddocente(docente);
                    cursoTutorCollectionNewCursoTutor = em.merge(cursoTutorCollectionNewCursoTutor);
                    if (oldDOCENTEiddocenteOfCursoTutorCollectionNewCursoTutor != null && !oldDOCENTEiddocenteOfCursoTutorCollectionNewCursoTutor.equals(docente)) {
                        oldDOCENTEiddocenteOfCursoTutorCollectionNewCursoTutor.getCursoTutorCollection().remove(cursoTutorCollectionNewCursoTutor);
                        oldDOCENTEiddocenteOfCursoTutorCollectionNewCursoTutor = em.merge(oldDOCENTEiddocenteOfCursoTutorCollectionNewCursoTutor);
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
                Integer id = docente.getIdDocente();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getIdDocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<CursoTutor> cursoTutorCollectionOrphanCheck = docente.getCursoTutorCollection();
            for (CursoTutor cursoTutorCollectionOrphanCheckCursoTutor : cursoTutorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the CursoTutor " + cursoTutorCollectionOrphanCheckCursoTutor + " in its cursoTutorCollection field has a non-nullable DOCENTEiddocente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(docente);
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

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
