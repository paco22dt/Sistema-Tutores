/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.Tutor;
import View.TutorFacade;
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
public class TutorController {

    public TutorController() {
        pagingInfo = new PagingInfo();
        converter = new TutorConverter();
    }
    private Tutor tutor = null;
    private List<Tutor> tutorItems = null;
    private TutorFacade jpaController = null;
    private TutorConverter converter = null;
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

    public TutorFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (TutorFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tutorJpa");
        }
        return jpaController;
    }

    public SelectItem[] getTutorItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getTutorItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Tutor getTutor() {
        if (tutor == null) {
            tutor = (Tutor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTutor", converter, null);
        }
        if (tutor == null) {
            tutor = new Tutor();
        }
        return tutor;
    }

    public String listSetup() {
        reset(true);
        return "tutor_list";
    }

    public String createSetup() {
        reset(false);
        tutor = new Tutor();
        return "tutor_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(tutor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tutor was successfully created.");
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
        return scalarSetup("tutor_detail");
    }

    public String editSetup() {
        return scalarSetup("tutor_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        tutor = (Tutor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentTutor", converter, null);
        if (tutor == null) {
            String requestTutorString = JsfUtil.getRequestParameter("jsfcrud.currentTutor");
            JsfUtil.addErrorMessage("The tutor with id " + requestTutorString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String tutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, tutor);
        String currentTutorString = JsfUtil.getRequestParameter("jsfcrud.currentTutor");
        if (tutorString == null || tutorString.length() == 0 || !tutorString.equals(currentTutorString)) {
            String outcome = editSetup();
            if ("tutor_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit tutor. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(tutor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Tutor was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentTutor");
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
                JsfUtil.addSuccessMessage("Tutor was successfully deleted.");
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

    public List<Tutor> getTutorItems() {
        if (tutorItems == null) {
            getPagingInfo();
            tutorItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return tutorItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "tutor_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "tutor_list";
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
        tutor = null;
        tutorItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Tutor newTutor = new Tutor();
        String newTutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, newTutor);
        String tutorString = converter.getAsString(FacesContext.getCurrentInstance(), null, tutor);
        if (!newTutorString.equals(tutorString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
