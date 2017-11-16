package br.edu.senai.converters;


import br.edu.senai.controller.ExercicioDAO;
import br.edu.senai.model.Exercicio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("exercicioConverter")
public class ExercicioConverter implements Converter {

    ExercicioDAO uDAO = new ExercicioDAO();
    //converte uma string para um objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && !value.isEmpty() && !value.equals("--Selecione o exercicio--")) {
            return uDAO.findExercicio((Integer.parseInt(value)));
        }
        return null;
    }

    //retorna a versão string do objeto
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        try {
            Exercicio u = (Exercicio) value;
            return u.getId().toString();
        } catch (Exception e) {
            return null;
        }
    }
    
}
