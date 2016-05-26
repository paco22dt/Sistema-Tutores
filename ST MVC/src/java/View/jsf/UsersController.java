/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.Users;
import View.UsersFacade;
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
public class UsersController {

    public UsersController() {
        pagingInfo = new PagingInfo();
        converter = new UsersConverter();
    }
    private Users users = null;
    private List<Users> usersItems = null;
    private UsersFacade jpaController = null;
    private UsersConverter converter = null;
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

    public UsersFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (UsersFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "usersJpa");
        }
        return jpaController;
    }

    public SelectItem[] getUsersItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getUsersItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Users getUsers() {
        if (users == null) {
            users = (Users) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUsers", converter, null);
        }
        if (users == null) {
            users = new Users();
        }
        return users;
    }

    public String listSetup() {
        reset(true);
        return "users_list";
    }

    public String createSetup() {
        reset(false);
        users = new Users();
        return "users_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(users);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Users was successfully created.");
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
        return scalarSetup("users_detail");
    }

    public String editSetup() {
        return scalarSetup("users_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        users = (Users) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentUsers", converter, null);
        if (users == null) {
            String requestUsersString = JsfUtil.getRequestParameter("jsfcrud.currentUsers");
            JsfUtil.addErrorMessage("The users with id " + requestUsersString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String usersString = converter.getAsString(FacesContext.getCurrentInstance(), null, users);
        String currentUsersString = JsfUtil.getRequestParameter("jsfcrud.currentUsers");
        if (usersString == null || usersString.length() == 0 || !usersString.equals(currentUsersString)) {
            String outcome = editSetup();
            if ("users_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit users. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(users);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Users was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentUsers");
        String id = idAsString;
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
                JsfUtil.addSuccessMessage("Users was successfully deleted.");
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

    public List<Users> getUsersItems() {
        if (usersItems == null) {
            getPagingInfo();
            usersItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return usersItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "users_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "users_list";
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
        users = null;
        usersItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Users newUsers = new Users();
        String newUsersString = converter.getAsString(FacesContext.getCurrentInstance(), null, newUsers);
        String usersString = converter.getAsString(FacesContext.getCurrentInstance(), null, users);
        if (!newUsersString.equals(usersString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }

}
