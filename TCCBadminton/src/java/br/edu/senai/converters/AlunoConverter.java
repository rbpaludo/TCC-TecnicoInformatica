package br.edu.senai.converters;


import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.model.Aluno;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("alunoConverter")
public class AlunoConverter implements Converter {

    AlunoDAO uDAO = new AlunoDAO();
    //converte uma string para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && !value.isEmpty() && !value.equals("--Selecione o aluno--")) {
            return uDAO.find((Integer.parseInt(value)));
        }
        return null;
    }

    //retorna a versão string do objeto
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        try {
            Aluno u = (Aluno) value;
            return u.getId().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
}
