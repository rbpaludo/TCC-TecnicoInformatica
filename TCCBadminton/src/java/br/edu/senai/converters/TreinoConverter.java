package br.edu.senai.converters;

import br.edu.senai.controller.TreinoDAO;
import br.edu.senai.model.Treino;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("treinoConverter")
public class TreinoConverter implements Converter {

    TreinoDAO tDAO = new TreinoDAO();
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && !value.isEmpty() && !value.equals("--Selecione o treino--")){
            return tDAO.find((Integer.parseInt(value)));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        try {
            Treino u = (Treino) value;
            return u.getId().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
}
