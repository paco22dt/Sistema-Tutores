/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.CursoTe;
import View.CursoTeFacade;
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
public class CursoTeController {

    public CursoTeController() {
        pagingInfo = new PagingInfo();
        converter = new CursoTeConverter();
    }
    private CursoTe cursoTe = null;
    private List<CursoTe> cursoTeItems = null;
    private CursoTeFacade jpaController = null;
    private CursoTeConverter converter = null;
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

    public CursoTeFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (CursoTeFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cursoTeJpa");
        }
        return jpaController;
    }

    public SelectItem[] getCursoTeItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getCursoTeItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public CursoTe getCursoTe() {
        if (cursoTe == null) {
            cursoTe = (CursoTe) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCursoTe", converter, null);
        }
        if (cursoTe == null) {
            cursoTe = new CursoTe();
        }
        return cursoTe;
    }

    public String listSetup() {
        reset(true);
        return "cursoTe_list";
    }

    public String createSetup() {
        reset(false);
        cursoTe = new CursoTe();
        return "cursoTe_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(cursoTe);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("CursoTe was successfully created.");
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
        return scalarSetup("cursoTe_detail");
    }

    public String editSetup() {
        return scalarSetup("cursoTe_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cursoTe = (CursoTe) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCursoTe", converter, null);
        if (cursoTe == null) {
            String requestCursoTeString = JsfUtil.getRequestParameter("jsfcrud.currentCursoTe");
            JsfUtil.addErrorMessage("The cursoTe with id " + requestCursoTeString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cursoTeString = converter.getAsString(FacesContext.getCurrentInstance(), null, cursoTe);
        String currentCursoTeString = JsfUtil.getRequestParameter("jsfcrud.currentCursoTe");
        if (cursoTeString == null || cursoTeString.length() == 0 || !cursoTeString.equals(currentCursoTeString)) {
            String outcome = editSetup();
            if ("cursoTe_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cursoTe. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(cursoTe);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("CursoTe was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCursoTe");
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
                JsfUtil.addSuccessMessage("CursoTe was successfully deleted.");
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

    public List<CursoTe> getCursoTeItems() {
        if (cursoTeItems == null) {
            getPagingInfo();
            cursoTeItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return cursoTeItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cursoTe_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cursoTe_list";
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
        cursoTe = null;
        cursoTeItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        CursoTe newCursoTe = new CursoTe();
        String newCursoTeString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCursoTe);
        String cursoTeString = converter.getAsString(FacesContext.getCurrentInstance(), null, cursoTe);
        if (!newCursoTeString.equals(cursoTeString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
