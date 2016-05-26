/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.HistorialTutor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Francisco
 */
public class HistorialTutorConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        HistorialTutorController controller = (HistorialTutorController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "historialTutor");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof HistorialTutor) {
            HistorialTutor o = (HistorialTutor) object;
            return o.getIdHistorialTutor() == null ? "" : o.getIdHistorialTutor().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: Model.HistorialTutor");
        }
    }
    
}
