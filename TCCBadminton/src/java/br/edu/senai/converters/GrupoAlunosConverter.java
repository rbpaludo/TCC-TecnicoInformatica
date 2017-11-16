package br.edu.senai.converters;


import br.edu.senai.controller.GrupoAlunosDAO;
import br.edu.senai.model.GrupoAlunos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("grupoAlunosConverter")
public class GrupoAlunosConverter implements Converter {

    GrupoAlunosDAO uDAO = new GrupoAlunosDAO();
    //converte uma string para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && !value.isEmpty() && !value.equals("--Selecione o grupo--")) {
            return uDAO.find((Integer.parseInt(value)));
        }
        return null;
    }

    //retorna a versão string do objeto
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        try {
            GrupoAlunos u = (GrupoAlunos) value;
            return u.getId().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
}
