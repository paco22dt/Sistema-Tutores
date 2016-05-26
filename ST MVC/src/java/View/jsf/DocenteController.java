/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.Docente;
import View.DocenteFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import View.jsf.util.JsfUtil;
import View.jsf.util.PagingInfo;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Francisco
 */
public class DocenteController {

    public DocenteController() {
        pagingInfo = new PagingInfo();
        converter = new DocenteConverter();
    }
    private Docente docente = null;
    private List<Docente> docenteItems = null;
    private DocenteFacade jpaController = null;
    private DocenteConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "ST_MVCPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public DocenteFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (DocenteFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "docenteJpa");
        }
        return jpaController;
    }

    public SelectItem[] getDocenteItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getDocenteItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Docente getDocente() {
        if (docente == null) {
            docente = (Docente) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDocente", converter, null);
        }
        if (docente == null) {
            docente = new Docente();
        }
        return docente;
    }

    public String listSetup() {
        reset(true);
        return "docente_list";
    }

    public String createSetup() {
        reset(false);
        docente = new Docente();
        return "docente_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(docente);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Docente was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("docente_detail");
    }

    public String editSetup() {
        return scalarSetup("docente_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        docente = (Docente) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDocente", converter, null);
        if (docente == null) {
            String requestDocenteString = JsfUtil.getRequestParameter("jsfcrud.currentDocente");
            JsfUtil.addErrorMessage("The docente with id " + requestDocenteString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String docenteString = converter.getAsString(FacesContext.getCurrentInstance(), null, docente);
        String currentDocenteString = JsfUtil.getRequestParameter("jsfcrud.currentDocente");
        if (docenteString == null || docenteString.length() == 0 || !docenteString.equals(currentDocenteString)) {
            String outcome = editSetup();
            if ("docente_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit docente. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(docente);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Docente was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDocente");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Docente was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        /*if ((ERROR)) {
            return relatedControllerOutcome;
        }*/
        return listSetup();
    }

    public List<Docente> getDocenteItems() {
        if (docenteItems == null) {
            getPagingInfo();
            docenteItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return docenteItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "docente_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "docente_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        docente = null;
        docenteItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Docente newDocente = new Docente();
        String newDocenteString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDocente);
        String docenteString = converter.getAsString(FacesContext.getCurrentInstance(), null, docente);
        if (!newDocenteString.equals(docenteString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
