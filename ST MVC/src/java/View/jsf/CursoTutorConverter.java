/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.CursoTutor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Francisco
 */
public class CursoTutorConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Integer id = new Integer(string);
        CursoTutorController controller = (CursoTutorController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "cursoTutor");
        return controller.getJpaController().find(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof CursoTutor) {
            CursoTutor o = (CursoTutor) object;
            return o.getIdCursoTutor() == null ? "" : o.getIdCursoTutor().toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: Model.CursoTutor");
        }
    }
    
}
