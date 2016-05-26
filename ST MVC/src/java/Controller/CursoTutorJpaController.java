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
import Model.Tutor;
import Model.Docente;
import Model.CursoLab;
import Model.CursoTutor;
import Model.Periodo;
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
public class CursoTutorJpaController implements Serializable {

    public CursoTutorJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CursoTutor cursoTutor) throws IllegalOrphanException, PreexistingEntityException, RollbackFailureException, Exception {
        if (cursoTutor.getPeriodoCollection() == null) {
            cursoTutor.setPeriodoCollection(new ArrayList<Periodo>());
        }
        if (cursoTutor.getCursoLabCollection() == null) {
            cursoTutor.setCursoLabCollection(new ArrayList<CursoLab>());
        }
        List<String> illegalOrphanMessages = null;
        CursoLab CURSOLABseccionOrphanCheck = cursoTutor.getCURSOLABseccion();
        if (CURSOLABseccionOrphanCheck != null) {
            CursoTutor oldCURSOTEidcursoOfCURSOLABseccion = CURSOLABseccionOrphanCheck.getCURSOTEidcurso();
            if (oldCURSOTEidcursoOfCURSOLABseccion != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The CursoLab " + CURSOLABseccionOrphanCheck + " already has an item of type CursoTutor whose CURSOLABseccion column cannot be null. Please make another selection for the CURSOLABseccion field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tutor TUTORcarnet = cursoTutor.getTUTORcarnet();
            if (TUTORcarnet != null) {
                TUTORcarnet = em.getReference(TUTORcarnet.getClass(), TUTORcarnet.getCarnet());
                cursoTutor.setTUTORcarnet(TUTORcarnet);
            }
            Docente DOCENTEiddocente = cursoTutor.getDOCENTEiddocente();
            if (DOCENTEiddocente != null) {
                DOCENTEiddocente = em.getReference(DOCENTEiddocente.getClass(), DOCENTEiddocente.getIdDocente());
                cursoTutor.setDOCENTEiddocente(DOCENTEiddocente);
            }
            CursoLab CURSOLABseccion = cursoTutor.getCURSOLABseccion();
            if (CURSOLABseccion != null) {
                CURSOLABseccion = em.getReference(CURSOLABseccion.getClass(), CURSOLABseccion.getSeccion());
                cursoTutor.setCURSOLABseccion(CURSOLABseccion);
            }
            Collection<Periodo> attachedPeriodoCollection = new ArrayList<Periodo>();
            for (Periodo periodoCollectionPeriodoToAttach : cursoTutor.getPeriodoCollection()) {
                periodoCollectionPeriodoToAttach = em.getReference(periodoCollectionPeriodoToAttach.getClass(), periodoCollectionPeriodoToAttach.getIdPeriodo());
                attachedPeriodoCollection.add(periodoCollectionPeriodoToAttach);
            }
            cursoTutor.setPeriodoCollection(attachedPeriodoCollection);
            Collection<CursoLab> attachedCursoLabCollection = new ArrayList<CursoLab>();
            for (CursoLab cursoLabCollectionCursoLabToAttach : cursoTutor.getCursoLabCollection()) {
                cursoLabCollectionCursoLabToAttach = em.getReference(cursoLabCollectionCursoLabToAttach.getClass(), cursoLabCollectionCursoLabToAttach.getSeccion());
                attachedCursoLabCollection.add(cursoLabCollectionCursoLabToAttach);
            }
            cursoTutor.setCursoLabCollection(attachedCursoLabCollection);
            em.persist(cursoTutor);
            if (TUTORcarnet != null) {
                TUTORcarnet.getCursoTutorCollection().add(cursoTutor);
                TUTORcarnet = em.merge(TUTORcarnet);
            }
            if (DOCENTEiddocente != null) {
                DOCENTEiddocente.getCursoTutorCollection().add(cursoTutor);
                DOCENTEiddocente = em.merge(DOCENTEiddocente);
            }
            if (CURSOLABseccion != null) {
                CURSOLABseccion.setCURSOTEidcurso(cursoTutor);
                CURSOLABseccion = em.merge(CURSOLABseccion);
            }
            for (Periodo periodoCollectionPeriodo : cursoTutor.getPeriodoCollection()) {
                CursoTutor oldCURSOTUTORidcursotutorOfPeriodoCollectionPeriodo = periodoCollectionPeriodo.getCURSOTUTORidcursotutor();
                periodoCollectionPeriodo.setCURSOTUTORidcursotutor(cursoTutor);
                periodoCollectionPeriodo = em.merge(periodoCollectionPeriodo);
                if (oldCURSOTUTORidcursotutorOfPeriodoCollectionPeriodo != null) {
                    oldCURSOTUTORidcursotutorOfPeriodoCollectionPeriodo.getPeriodoCollection().remove(periodoCollectionPeriodo);
                    oldCURSOTUTORidcursotutorOfPeriodoCollectionPeriodo = em.merge(oldCURSOTUTORidcursotutorOfPeriodoCollectionPeriodo);
                }
            }
            for (CursoLab cursoLabCollectionCursoLab : cursoTutor.getCursoLabCollection()) {
                CursoTutor oldCURSOTEidcursoOfCursoLabCollectionCursoLab = cursoLabCollectionCursoLab.getCURSOTEidcurso();
                cursoLabCollectionCursoLab.setCURSOTEidcurso(cursoTutor);
                cursoLabCollectionCursoLab = em.merge(cursoLabCollectionCursoLab);
                if (oldCURSOTEidcursoOfCursoLabCollectionCursoLab != null) {
                    oldCURSOTEidcursoOfCursoLabCollectionCursoLab.getCursoLabCollection().remove(cursoLabCollectionCursoLab);
                    oldCURSOTEidcursoOfCursoLabCollectionCursoLab = em.merge(oldCURSOTEidcursoOfCursoLabCollectionCursoLab);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCursoTutor(cursoTutor.getIdCursoTutor()) != null) {
                throw new PreexistingEntityException("CursoTutor " + cursoTutor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CursoTutor cursoTutor) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CursoTutor persistentCursoTutor = em.find(CursoTutor.class, cursoTutor.getIdCursoTutor());
            Tutor TUTORcarnetOld = persistentCursoTutor.getTUTORcarnet();
            Tutor TUTORcarnetNew = cursoTutor.getTUTORcarnet();
            Docente DOCENTEiddocenteOld = persistentCursoTutor.getDOCENTEiddocente();
            Docente DOCENTEiddocenteNew = cursoTutor.getDOCENTEiddocente();
            CursoLab CURSOLABseccionOld = persistentCursoTutor.getCURSOLABseccion();
            CursoLab CURSOLABseccionNew = cursoTutor.getCURSOLABseccion();
            Collection<Periodo> periodoCollectionOld = persistentCursoTutor.getPeriodoCollection();
            Collection<Periodo> periodoCollectionNew = cursoTutor.getPeriodoCollection();
            Collection<CursoLab> cursoLabCollectionOld = persistentCursoTutor.getCursoLabCollection();
            Collection<CursoLab> cursoLabCollectionNew = cursoTutor.getCursoLabCollection();
            List<String> illegalOrphanMessages = null;
            if (CURSOLABseccionOld != null && !CURSOLABseccionOld.equals(CURSOLABseccionNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain CursoLab " + CURSOLABseccionOld + " since its CURSOTEidcurso field is not nullable.");
            }
            if (CURSOLABseccionNew != null && !CURSOLABseccionNew.equals(CURSOLABseccionOld)) {
                CursoTutor oldCURSOTEidcursoOfCURSOLABseccion = CURSOLABseccionNew.getCURSOTEidcurso();
                if (oldCURSOTEidcursoOfCURSOLABseccion != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The CursoLab " + CURSOLABseccionNew + " already has an item of type CursoTutor whose CURSOLABseccion column cannot be null. Please make another selection for the CURSOLABseccion field.");
                }
            }
            for (Periodo periodoCollectionOldPeriodo : periodoCollectionOld) {
                if (!periodoCollectionNew.contains(periodoCollectionOldPeriodo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Periodo " + periodoCollectionOldPeriodo + " since its CURSOTUTORidcursotutor field is not nullable.");
                }
            }
            for (CursoLab cursoLabCollectionOldCursoLab : cursoLabCollectionOld) {
                if (!cursoLabCollectionNew.contains(cursoLabCollectionOldCursoLab)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CursoLab " + cursoLabCollectionOldCursoLab + " since its CURSOTEidcurso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (TUTORcarnetNew != null) {
                TUTORcarnetNew = em.getReference(TUTORcarnetNew.getClass(), TUTORcarnetNew.getCarnet());
                cursoTutor.setTUTORcarnet(TUTORcarnetNew);
            }
            if (DOCENTEiddocenteNew != null) {
                DOCENTEiddocenteNew = em.getReference(DOCENTEiddocenteNew.getClass(), DOCENTEiddocenteNew.getIdDocente());
                cursoTutor.setDOCENTEiddocente(DOCENTEiddocenteNew);
            }
            if (CURSOLABseccionNew != null) {
                CURSOLABseccionNew = em.getReference(CURSOLABseccionNew.getClass(), CURSOLABseccionNew.getSeccion());
                cursoTutor.setCURSOLABseccion(CURSOLABseccionNew);
            }
            Collection<Periodo> attachedPeriodoCollectionNew = new ArrayList<Periodo>();
            for (Periodo periodoCollectionNewPeriodoToAttach : periodoCollectionNew) {
                periodoCollectionNewPeriodoToAttach = em.getReference(periodoCollectionNewPeriodoToAttach.getClass(), periodoCollectionNewPeriodoToAttach.getIdPeriodo());
                attachedPeriodoCollectionNew.add(periodoCollectionNewPeriodoToAttach);
            }
            periodoCollectionNew = attachedPeriodoCollectionNew;
            cursoTutor.setPeriodoCollection(periodoCollectionNew);
            Collection<CursoLab> attachedCursoLabCollectionNew = new ArrayList<CursoLab>();
            for (CursoLab cursoLabCollectionNewCursoLabToAttach : cursoLabCollectionNew) {
                cursoLabCollectionNewCursoLabToAttach = em.getReference(cursoLabCollectionNewCursoLabToAttach.getClass(), cursoLabCollectionNewCursoLabToAttach.getSeccion());
                attachedCursoLabCollectionNew.add(cursoLabCollectionNewCursoLabToAttach);
            }
            cursoLabCollectionNew = attachedCursoLabCollectionNew;
            cursoTutor.setCursoLabCollection(cursoLabCollectionNew);
            cursoTutor = em.merge(cursoTutor);
            if (TUTORcarnetOld != null && !TUTORcarnetOld.equals(TUTORcarnetNew)) {
                TUTORcarnetOld.getCursoTutorCollection().remove(cursoTutor);
                TUTORcarnetOld = em.merge(TUTORcarnetOld);
            }
            if (TUTORcarnetNew != null && !TUTORcarnetNew.equals(TUTORcarnetOld)) {
                TUTORcarnetNew.getCursoTutorCollection().add(cursoTutor);
                TUTORcarnetNew = em.merge(TUTORcarnetNew);
            }
            if (DOCENTEiddocenteOld != null && !DOCENTEiddocenteOld.equals(DOCENTEiddocenteNew)) {
                DOCENTEiddocenteOld.getCursoTutorCollection().remove(cursoTutor);
                DOCENTEiddocenteOld = em.merge(DOCENTEiddocenteOld);
            }
            if (DOCENTEiddocenteNew != null && !DOCENTEiddocenteNew.equals(DOCENTEiddocenteOld)) {
                DOCENTEiddocenteNew.getCursoTutorCollection().add(cursoTutor);
                DOCENTEiddocenteNew = em.merge(DOCENTEiddocenteNew);
            }
            if (CURSOLABseccionNew != null && !CURSOLABseccionNew.equals(CURSOLABseccionOld)) {
                CURSOLABseccionNew.setCURSOTEidcurso(cursoTutor);
                CURSOLABseccionNew = em.merge(CURSOLABseccionNew);
            }
            for (Periodo periodoCollectionNewPeriodo : periodoCollectionNew) {
                if (!periodoCollectionOld.contains(periodoCollectionNewPeriodo)) {
                    CursoTutor oldCURSOTUTORidcursotutorOfPeriodoCollectionNewPeriodo = periodoCollectionNewPeriodo.getCURSOTUTORidcursotutor();
                    periodoCollectionNewPeriodo.setCURSOTUTORidcursotutor(cursoTutor);
                    periodoCollectionNewPeriodo = em.merge(periodoCollectionNewPeriodo);
                    if (oldCURSOTUTORidcursotutorOfPeriodoCollectionNewPeriodo != null && !oldCURSOTUTORidcursotutorOfPeriodoCollectionNewPeriodo.equals(cursoTutor)) {
                        oldCURSOTUTORidcursotutorOfPeriodoCollectionNewPeriodo.getPeriodoCollection().remove(periodoCollectionNewPeriodo);
                        oldCURSOTUTORidcursotutorOfPeriodoCollectionNewPeriodo = em.merge(oldCURSOTUTORidcursotutorOfPeriodoCollectionNewPeriodo);
                    }
                }
            }
            for (CursoLab cursoLabCollectionNewCursoLab : cursoLabCollectionNew) {
                if (!cursoLabCollectionOld.contains(cursoLabCollectionNewCursoLab)) {
                    CursoTutor oldCURSOTEidcursoOfCursoLabCollectionNewCursoLab = cursoLabCollectionNewCursoLab.getCURSOTEidcurso();
                    cursoLabCollectionNewCursoLab.setCURSOTEidcurso(cursoTutor);
                    cursoLabCollectionNewCursoLab = em.merge(cursoLabCollectionNewCursoLab);
                    if (oldCURSOTEidcursoOfCursoLabCollectionNewCursoLab != null && !oldCURSOTEidcursoOfCursoLabCollectionNewCursoLab.equals(cursoTutor)) {
                        oldCURSOTEidcursoOfCursoLabCollectionNewCursoLab.getCursoLabCollection().remove(cursoLabCollectionNewCursoLab);
                        oldCURSOTEidcursoOfCursoLabCollectionNewCursoLab = em.merge(oldCURSOTEidcursoOfCursoLabCollectionNewCursoLab);
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
                Integer id = cursoTutor.getIdCursoTutor();
                if (findCursoTutor(id) == null) {
                    throw new NonexistentEntityException("The cursoTutor with id " + id + " no longer exists.");
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
            CursoTutor cursoTutor;
            try {
                cursoTutor = em.getReference(CursoTutor.class, id);
                cursoTutor.getIdCursoTutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cursoTutor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            CursoLab CURSOLABseccionOrphanCheck = cursoTutor.getCURSOLABseccion();
            if (CURSOLABseccionOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CursoTutor (" + cursoTutor + ") cannot be destroyed since the CursoLab " + CURSOLABseccionOrphanCheck + " in its CURSOLABseccion field has a non-nullable CURSOTEidcurso field.");
            }
            Collection<Periodo> periodoCollectionOrphanCheck = cursoTutor.getPeriodoCollection();
            for (Periodo periodoCollectionOrphanCheckPeriodo : periodoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CursoTutor (" + cursoTutor + ") cannot be destroyed since the Periodo " + periodoCollectionOrphanCheckPeriodo + " in its periodoCollection field has a non-nullable CURSOTUTORidcursotutor field.");
            }
            Collection<CursoLab> cursoLabCollectionOrphanCheck = cursoTutor.getCursoLabCollection();
            for (CursoLab cursoLabCollectionOrphanCheckCursoLab : cursoLabCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CursoTutor (" + cursoTutor + ") cannot be destroyed since the CursoLab " + cursoLabCollectionOrphanCheckCursoLab + " in its cursoLabCollection field has a non-nullable CURSOTEidcurso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tutor TUTORcarnet = cursoTutor.getTUTORcarnet();
            if (TUTORcarnet != null) {
                TUTORcarnet.getCursoTutorCollection().remove(cursoTutor);
                TUTORcarnet = em.merge(TUTORcarnet);
            }
            Docente DOCENTEiddocente = cursoTutor.getDOCENTEiddocente();
            if (DOCENTEiddocente != null) {
                DOCENTEiddocente.getCursoTutorCollection().remove(cursoTutor);
                DOCENTEiddocente = em.merge(DOCENTEiddocente);
            }
            em.remove(cursoTutor);
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

    public List<CursoTutor> findCursoTutorEntities() {
        return findCursoTutorEntities(true, -1, -1);
    }

    public List<CursoTutor> findCursoTutorEntities(int maxResults, int firstResult) {
        return findCursoTutorEntities(false, maxResults, firstResult);
    }

    private List<CursoTutor> findCursoTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CursoTutor.class));
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

    public CursoTutor findCursoTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CursoTutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CursoTutor> rt = cq.from(CursoTutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
