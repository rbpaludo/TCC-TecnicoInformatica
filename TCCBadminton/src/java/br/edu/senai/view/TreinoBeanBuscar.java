/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.controller.ExercicioDAO;
import br.edu.senai.controller.GrupoAlunosDAO;
import br.edu.senai.controller.TreinoDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Treino;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Aluno
 */
@ManagedBean
@ViewScoped
public class TreinoBeanBuscar {

    private Treino t = new Treino();
    private Treino treinoSelecionado = new Treino();
    private Exercicio exercicioSelecionado = new Exercicio();
    private GrupoAlunos grupoSelecionado = new GrupoAlunos();
    private TreinoDAO tDAO = new TreinoDAO();
    private ExercicioDAO eDAO = new ExercicioDAO();
    private GrupoAlunosDAO gDAO = new GrupoAlunosDAO();
    private AlunoDAO aDAO = new AlunoDAO();
    private List<Treino> lTreinos = new ArrayList<>();
    private List<Exercicio> lExercs = new ArrayList<>();
    private List<GrupoAlunos> lGrupos = new ArrayList<>();
    private List<Aluno> lAlunos = new ArrayList<>();
    
    private boolean mostraDesempenho = false;

    private String teste;

    private Boolean renderizarExercicio = false;

    public void alterouExercicio() {
        if (mostraDesempenho == false) {
            mostraDesempenho = true;
        }else{
            mostraDesempenho = false;
        }
    }

    public void passa(){
    
    }
    public void alterouTreino() {
        getListaExerciciosAva();
        getListaGruposAva();
        renderizarExercicio = true;
    }

    public void alterouGrupo() {
        getListaAlunosAva();
    }

    public Treino getT() {
        return t;
    }

    public void setT(Treino t) {
        this.t = t;
    }

    public Treino getTreinoSelecionado() {
        return treinoSelecionado;
    }

    public void setTreinoSelecionado(Treino treinoSelecionado) {
        this.treinoSelecionado = treinoSelecionado;
    }

    public TreinoDAO gettDAO() {
        return tDAO;
    }

    public void settDAO(TreinoDAO tDAO) {
        this.tDAO = tDAO;
    }

    public List<Treino> getlTreinos() {
        return lTreinos;
    }

    public void setlTreinos(List<Treino> lTreinos) {
        this.lTreinos = lTreinos;
    }

    public GrupoAlunos getGrupoSelecionado() {
        return grupoSelecionado;
    }

    public void setGrupoSelecionado(GrupoAlunos grupoSelecionado) {
        this.grupoSelecionado = grupoSelecionado;
    }

    public List<Exercicio> getlExercs() {
        return lExercs;
    }

    public void setlExercs(List<Exercicio> lExercs) {
        this.lExercs = lExercs;
    }

    public List<GrupoAlunos> getlGrupos() {
        return lGrupos;
    }

    public void setlGrupos(List<GrupoAlunos> lGrupos) {
        this.lGrupos = lGrupos;
    }

    public List<Aluno> getlAlunos() {
        return lAlunos;
    }

    public void setlAlunos(List<Aluno> lAlunos) {
        this.lAlunos = lAlunos;
    }

    public List<Treino> getListaTreinosAva() {
        lTreinos = tDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());
        return lTreinos;
    }

    public List<GrupoAlunos> getListaGruposAva() {
        if (treinoSelecionado != null) {
            lGrupos = gDAO.listar(treinoSelecionado);
            return lGrupos;
        }
        return null;
    }

    public List<Exercicio> getListaExerciciosAva() {
        if (treinoSelecionado != null) {
            lExercs = eDAO.listar(treinoSelecionado);
            return lExercs;
        }
        return null;
    }

    public List<Aluno> getListaAlunosAva() {
        if (grupoSelecionado != null) {
            lAlunos = aDAO.listar(grupoSelecionado);
            return lAlunos;
        }
        return null;
    }

    public Exercicio getExercicioSelecionado() {
        return exercicioSelecionado;
    }

    public void setExercicioSelecionado(Exercicio exercicioSelecionado) {
        this.exercicioSelecionado = exercicioSelecionado;
    }


    public boolean isMostraDesempenho() {
        return mostraDesempenho;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
}
