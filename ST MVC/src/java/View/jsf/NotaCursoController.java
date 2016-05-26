/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.NotaCurso;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import View.jsf.util.JsfUtil;
import Model.NotaCursoPK;
import View.NotaCursoFacade;
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
public class NotaCursoController {

    public NotaCursoController() {
        pagingInfo = new PagingInfo();
        converter = new NotaCursoConverter();
    }
    private NotaCurso notaCurso = null;
    private List<NotaCurso> notaCursoItems = null;
    private NotaCursoFacade jpaController = null;
    private NotaCursoConverter converter = null;
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

    public NotaCursoFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (NotaCursoFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "notaCursoJpa");
        }
        return jpaController;
    }

    public SelectItem[] getNotaCursoItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getNotaCursoItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public NotaCurso getNotaCurso() {
        if (notaCurso == null) {
            notaCurso = (NotaCurso) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotaCurso", converter, null);
        }
        if (notaCurso == null) {
            notaCurso = new NotaCurso();
        }
        return notaCurso;
    }

    public String listSetup() {
        reset(true);
        return "notaCurso_list";
    }

    public String createSetup() {
        reset(false);
        notaCurso = new NotaCurso();
        notaCurso.setNotaCursoPK(new NotaCursoPK());
        return "notaCurso_create";
    }

    public String create() {
        notaCurso.getNotaCursoPK().setTUTORcarnet(notaCurso.getTutor().getCarnet());
        notaCurso.getNotaCursoPK().setCURSOTEidcurso(notaCurso.getCursoTe().getIdCurso());
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(notaCurso);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("NotaCurso was successfully created.");
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
        return scalarSetup("notaCurso_detail");
    }

    public String editSetup() {
        return scalarSetup("notaCurso_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        notaCurso = (NotaCurso) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNotaCurso", converter, null);
        if (notaCurso == null) {
            String requestNotaCursoString = JsfUtil.getRequestParameter("jsfcrud.currentNotaCurso");
            JsfUtil.addErrorMessage("The notaCurso with id " + requestNotaCursoString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        notaCurso.getNotaCursoPK().setTUTORcarnet(notaCurso.getTutor().getCarnet());
        notaCurso.getNotaCursoPK().setCURSOTEidcurso(notaCurso.getCursoTe().getIdCurso());
        String notaCursoString = converter.getAsString(FacesContext.getCurrentInstance(), null, notaCurso);
        String currentNotaCursoString = JsfUtil.getRequestParameter("jsfcrud.currentNotaCurso");
        if (notaCursoString == null || notaCursoString.length() == 0 || !notaCursoString.equals(currentNotaCursoString)) {
            String outcome = editSetup();
            if ("notaCurso_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit notaCurso. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(notaCurso);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("NotaCurso was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentNotaCurso");
        NotaCursoPK id = converter.getId(idAsString);
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
                JsfUtil.addSuccessMessage("NotaCurso was successfully deleted.");
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

    public List<NotaCurso> getNotaCursoItems() {
        if (notaCursoItems == null) {
            getPagingInfo();
            notaCursoItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return notaCursoItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "notaCurso_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "notaCurso_list";
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
        notaCurso = null;
        notaCursoItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        NotaCurso newNotaCurso = new NotaCurso();
        newNotaCurso.setNotaCursoPK(new NotaCursoPK());
        String newNotaCursoString = converter.getAsString(FacesContext.getCurrentInstance(), null, newNotaCurso);
        String notaCursoString = converter.getAsString(FacesContext.getCurrentInstance(), null, notaCurso);
        if (!newNotaCursoString.equals(notaCursoString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
