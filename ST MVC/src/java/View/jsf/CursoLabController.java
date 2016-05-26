/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.CursoLab;
import View.CursoLabFacade;
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
public class CursoLabController {

    public CursoLabController() {
        pagingInfo = new PagingInfo();
        converter = new CursoLabConverter();
    }
    private CursoLab cursoLab = null;
    private List<CursoLab> cursoLabItems = null;
    private CursoLabFacade jpaController = null;
    private CursoLabConverter converter = null;
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

    public CursoLabFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (CursoLabFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cursoLabJpa");
        }
        return jpaController;
    }

    public SelectItem[] getCursoLabItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getCursoLabItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public CursoLab getCursoLab() {
        if (cursoLab == null) {
            cursoLab = (CursoLab) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCursoLab", converter, null);
        }
        if (cursoLab == null) {
            cursoLab = new CursoLab();
        }
        return cursoLab;
    }

    public String listSetup() {
        reset(true);
        return "cursoLab_list";
    }

    public String createSetup() {
        reset(false);
        cursoLab = new CursoLab();
        return "cursoLab_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(cursoLab);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("CursoLab was successfully created.");
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
        return scalarSetup("cursoLab_detail");
    }

    public String editSetup() {
        return scalarSetup("cursoLab_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cursoLab = (CursoLab) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCursoLab", converter, null);
        if (cursoLab == null) {
            String requestCursoLabString = JsfUtil.getRequestParameter("jsfcrud.currentCursoLab");
            JsfUtil.addErrorMessage("The cursoLab with id " + requestCursoLabString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cursoLabString = converter.getAsString(FacesContext.getCurrentInstance(), null, cursoLab);
        String currentCursoLabString = JsfUtil.getRequestParameter("jsfcrud.currentCursoLab");
        if (cursoLabString == null || cursoLabString.length() == 0 || !cursoLabString.equals(currentCursoLabString)) {
            String outcome = editSetup();
            if ("cursoLab_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cursoLab. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(cursoLab);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("CursoLab was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCursoLab");
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
                JsfUtil.addSuccessMessage("CursoLab was successfully deleted.");
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

    public List<CursoLab> getCursoLabItems() {
        if (cursoLabItems == null) {
            getPagingInfo();
            cursoLabItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return cursoLabItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cursoLab_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cursoLab_list";
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
        cursoLab = null;
        cursoLabItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        CursoLab newCursoLab = new CursoLab();
        String newCursoLabString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCursoLab);
        String cursoLabString = converter.getAsString(FacesContext.getCurrentInstance(), null, cursoLab);
        if (!newCursoLabString.equals(cursoLabString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
