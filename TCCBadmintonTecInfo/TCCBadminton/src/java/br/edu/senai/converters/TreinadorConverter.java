package br.edu.senai.converters;


import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.controller.TreinadorDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.Treinador;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("treinadorConverter")
public class TreinadorConverter implements Converter {

    TreinadorDAO uDAO = new TreinadorDAO();
    //converte uma string para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && !value.isEmpty()) {
            return uDAO.find((Integer.parseInt(value)));
        }
        return null;
    }

    //retorna a versão string do objeto
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        try {
            Treinador u = (Treinador) value;
            return u.getId().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
}
