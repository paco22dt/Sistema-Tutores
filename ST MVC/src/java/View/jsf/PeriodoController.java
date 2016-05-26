/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.Periodo;
import View.PeriodoFacade;
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
public class PeriodoController {

    public PeriodoController() {
        pagingInfo = new PagingInfo();
        converter = new PeriodoConverter();
    }
    private Periodo periodo = null;
    private List<Periodo> periodoItems = null;
    private PeriodoFacade jpaController = null;
    private PeriodoConverter converter = null;
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

    public PeriodoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (PeriodoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "periodoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getPeriodoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getPeriodoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Periodo getPeriodo() {
        if (periodo == null) {
            periodo = (Periodo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPeriodo", converter, null);
        }
        if (periodo == null) {
            periodo = new Periodo();
        }
        return periodo;
    }

    public String listSetup() {
        reset(true);
        return "periodo_list";
    }

    public String createSetup() {
        reset(false);
        periodo = new Periodo();
        return "periodo_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(periodo);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Periodo was successfully created.");
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
        return scalarSetup("periodo_detail");
    }

    public String editSetup() {
        return scalarSetup("periodo_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        periodo = (Periodo) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentPeriodo", converter, null);
        if (periodo == null) {
            String requestPeriodoString = JsfUtil.getRequestParameter("jsfcrud.currentPeriodo");
            JsfUtil.addErrorMessage("The periodo with id " + requestPeriodoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String periodoString = converter.getAsString(FacesContext.getCurrentInstance(), null, periodo);
        String currentPeriodoString = JsfUtil.getRequestParameter("jsfcrud.currentPeriodo");
        if (periodoString == null || periodoString.length() == 0 || !periodoString.equals(currentPeriodoString)) {
            String outcome = editSetup();
            if ("periodo_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit periodo. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(periodo);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Periodo was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentPeriodo");
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
                JsfUtil.addSuccessMessage("Periodo was successfully deleted.");
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

    public List<Periodo> getPeriodoItems() {
        if (periodoItems == null) {
            getPagingInfo();
            periodoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return periodoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "periodo_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "periodo_list";
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
        periodo = null;
        periodoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Periodo newPeriodo = new Periodo();
        String newPeriodoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newPeriodo);
        String periodoString = converter.getAsString(FacesContext.getCurrentInstance(), null, periodo);
        if (!newPeriodoString.equals(periodoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
