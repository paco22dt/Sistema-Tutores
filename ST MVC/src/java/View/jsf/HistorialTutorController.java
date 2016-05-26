/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.HistorialTutor;
import View.HistorialTutorFacade;
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
public class HistorialTutorController {

    public HistorialTutorController() {
        pagingInfo = new PagingInfo();
        converter = new HistorialTutorConverter();
    }
    private HistorialTutor historialTutor = null;
    private List<HistorialTutor> historialTutorItems = null;
    private HistorialTutorFacade jpaController = null;
    private HistorialTutorConverter converter = null;
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

    public HistorialTutorFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (HistorialTutorFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "historialTutorJpa");
        }
        return jpaController;
    }

    public SelectItem[] getHistorialTutorItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getHistorialTutorItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public HistorialTutor getHistorialTutor() {
        if (historialTutor == null) {
            historialTutor = (HistorialTutor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentHistorialTutor", converter, null);
        }
        if (historialTutor == null) {
            historialTutor = new HistorialTutor();
        }
        return historialTutor;
    }

    public String listSetup() {
        reset(true);
        return "historialTutor_list";
    }

    public String createSetup() {
        reset(false);
        historialTutor = new HistorialTutor();
        return "historialTutor_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(historialTutor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("HistorialTutor was successfully created.");
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
        return scalarSetup("historialTutor_detail");
    }

    public String editSetup() {
        return scalarSetup("historialTutor_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        historialTutor = (HistorialTutor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentHistorialTutor", converter, null);
        if (historialTutor == null) {
            String requestHistorialTutorString = JsfUtil.getRequestParameter("jsfcrud.currentHistorialTutor");
            JsfUtil.addErrorMessage("The historialTutor with id " + requestHistorialTutorString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String historialTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, historialTutor);
        String currentHistorialTutorString = JsfUtil.getRequestParameter("jsfcrud.currentHistorialTutor");
        if (historialTutorString == null || historialTutorString.length() == 0 || !historialTutorString.equals(currentHistorialTutorString)) {
            String outcome = editSetup();
            if ("historialTutor_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit historialTutor. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(historialTutor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("HistorialTutor was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentHistorialTutor");
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
                JsfUtil.addSuccessMessage("HistorialTutor was successfully deleted.");
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

    public List<HistorialTutor> getHistorialTutorItems() {
        if (historialTutorItems == null) {
            getPagingInfo();
            historialTutorItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return historialTutorItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "historialTutor_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "historialTutor_list";
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
        historialTutor = null;
        historialTutorItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        HistorialTutor newHistorialTutor = new HistorialTutor();
        String newHistorialTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, newHistorialTutor);
        String historialTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, historialTutor);
        if (!newHistorialTutorString.equals(historialTutorString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
