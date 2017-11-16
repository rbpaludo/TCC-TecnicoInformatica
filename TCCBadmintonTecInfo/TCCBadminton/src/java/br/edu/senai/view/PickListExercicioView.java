/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.ExercicioDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;
import org.primefaces.model.DualListModel;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Gustavo
 */
@ManagedBean
@ViewScoped
public class PickListExercicioView {

    public List<Exercicio> getExerciciosSelecionados() {

        List<Exercicio> exerciciosSelecionados = new ArrayList<>();
        List<Exercicio> listaTarget = getExercicios().getTarget();

        for (int i = 0; i < listaTarget.size(); i++) {
            exerciciosSelecionados.add(listaTarget.get(i));
        }

        return exerciciosSelecionados;
    }

    public void atualizarTarget(List<Exercicio> exerciciosExistentes) {
        this.exercicioTarget = exerciciosExistentes;
        exercicios.setTarget(exercicioTarget);
        Exercicio exeT = new Exercicio();
        Exercicio exeS = new Exercicio();
        Boolean existe = false;
        for (int i = 0; i < exercicioTarget.size(); i++) {
            exeT = exercicioTarget.get(i);
            for (int j = 0; j < exerciciosSource.size(); j++) {
                exeS = exerciciosSource.get(j);
                if (exeS.getId() == exeT.getId()) {
                    existe = exerciciosSource.contains(exeS);
                    exerciciosSource.remove(j);
                    existe = exerciciosSource.contains(exeS);
                }
            }
        }
        exercicios.setSource(exerciciosSource);
//        
//        this.exercicioTarget = exerciciosExistentes;
//        exercicios.setTarget(exercicioTarget);
//        for (int i = 0; i < exercicioTarget.size(); i++) {
//            exerciciosSource.remove(exercicioTarget.get(i));
//        }
//        exercicios.setSource(exerciciosSource);
    }

    private DualListModel<Exercicio> exercicios;
    List<Exercicio> exerciciosSource = new ArrayList<>();
    List<Exercicio> exercicioTarget = new ArrayList<>();

    public void carregarExercicios(String treinador) {
        ExercicioDAO eDAO = new ExercicioDAO();

    }

    @PostConstruct
    public void init() {
        exercicioTarget = new ArrayList<>();
        ExercicioDAO eDAO = new ExercicioDAO();
        exerciciosSource = eDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());
        exercicios = new DualListModel<Exercicio>(exerciciosSource, exercicioTarget);

    }

    public DualListModel<Exercicio> getExercicios() {
        return exercicios;
    }

    public void setExercicios(DualListModel<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

}
