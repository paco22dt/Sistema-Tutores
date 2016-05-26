/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.jsf;

import Model.NotaCurso;
import Model.NotaCursoPK;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import View.NotaCursoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Francisco
 */
public class NotaCursoConverter implements Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        NotaCursoPK id = getId(string);
        NotaCursoController controller = (NotaCursoController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "notaCurso");
        return controller.getJpaController().find(id);
    }

    NotaCursoPK getId(String string) {
        NotaCursoPK id = new NotaCursoPK();
        String[] params = new String[2];
        int p = 0;
        int grabStart = 0;
        String delim = "#";
        String escape = "~";
        Pattern pattern = Pattern.compile(escape + "*" + delim);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            String found = matcher.group();
            if (found.length() % 2 == 1) {
                params[p] = string.substring(grabStart, matcher.start());
                p++;
                grabStart = matcher.end();
            }
        }
        if (p != params.length - 1) {
            throw new IllegalArgumentException("string " + string + " is not in expected format. expected 2 ids delimited by " + delim);
        }
        params[p] = string.substring(grabStart);
        for (int i = 0; i < params.length; i++) {
            params[i] = params[i].replace(escape + delim, delim);
            params[i] = params[i].replace(escape + escape, escape);
        }
        id.setCURSOTEidcurso(Integer.parseInt(params[0]));
        id.setTUTORcarnet(Integer.parseInt(params[1]));
        return id;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof NotaCurso) {
            NotaCurso o = (NotaCurso) object;
            NotaCursoPK id = o.getNotaCursoPK();
            if (id == null) {
                return "";
            }
            String delim = "#";
            String escape = "~";
            String CURSOTEidcurso = String.valueOf(id.getCURSOTEidcurso());
            CURSOTEidcurso = CURSOTEidcurso.replace(escape, escape + escape);
            CURSOTEidcurso = CURSOTEidcurso.replace(delim, escape + delim);
            String TUTORcarnet = String.valueOf(id.getTUTORcarnet());
            TUTORcarnet = TUTORcarnet.replace(escape, escape + escape);
            TUTORcarnet = TUTORcarnet.replace(delim, escape + delim);
            return CURSOTEidcurso + delim + TUTORcarnet;
            // TODO: no setter methods were found in your primary key class
            //    Model.NotaCursoPK
            // and therefore getAsString() method could not be pre-generated.
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: Model.NotaCurso");
        }
    }
    
}
