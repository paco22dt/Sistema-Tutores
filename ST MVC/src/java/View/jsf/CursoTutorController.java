/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.CursoTutor;
import View.CursoTutorFacade;
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
public class CursoTutorController {

    public CursoTutorController() {
        pagingInfo = new PagingInfo();
        converter = new CursoTutorConverter();
    }
    private CursoTutor cursoTutor = null;
    private List<CursoTutor> cursoTutorItems = null;
    private CursoTutorFacade jpaController = null;
    private CursoTutorConverter converter = null;
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

    public CursoTutorFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (CursoTutorFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cursoTutorJpa");
        }
        return jpaController;
    }

    public SelectItem[] getCursoTutorItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getCursoTutorItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public CursoTutor getCursoTutor() {
        if (cursoTutor == null) {
            cursoTutor = (CursoTutor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCursoTutor", converter, null);
        }
        if (cursoTutor == null) {
            cursoTutor = new CursoTutor();
        }
        return cursoTutor;
    }

    public String listSetup() {
        reset(true);
        return "cursoTutor_list";
    }

    public String createSetup() {
        reset(false);
        cursoTutor = new CursoTutor();
        return "cursoTutor_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(cursoTutor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("CursoTutor was successfully created.");
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
        return scalarSetup("cursoTutor_detail");
    }

    public String editSetup() {
        return scalarSetup("cursoTutor_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        cursoTutor = (CursoTutor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentCursoTutor", converter, null);
        if (cursoTutor == null) {
            String requestCursoTutorString = JsfUtil.getRequestParameter("jsfcrud.currentCursoTutor");
            JsfUtil.addErrorMessage("The cursoTutor with id " + requestCursoTutorString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String cursoTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, cursoTutor);
        String currentCursoTutorString = JsfUtil.getRequestParameter("jsfcrud.currentCursoTutor");
        if (cursoTutorString == null || cursoTutorString.length() == 0 || !cursoTutorString.equals(currentCursoTutorString)) {
            String outcome = editSetup();
            if ("cursoTutor_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit cursoTutor. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(cursoTutor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("CursoTutor was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentCursoTutor");
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
                JsfUtil.addSuccessMessage("CursoTutor was successfully deleted.");
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

    public List<CursoTutor> getCursoTutorItems() {
        if (cursoTutorItems == null) {
            getPagingInfo();
            cursoTutorItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return cursoTutorItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "cursoTutor_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "cursoTutor_list";
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
        cursoTutor = null;
        cursoTutorItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        CursoTutor newCursoTutor = new CursoTutor();
        String newCursoTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, newCursoTutor);
        String cursoTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, cursoTutor);
        if (!newCursoTutorString.equals(cursoTutorString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
