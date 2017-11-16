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
public class TreinoBean {

    TreinoDAO tDAO = new TreinoDAO();
    private List<GrupoAlunos> gruposAntesAtualizar = new ArrayList<>();
    private List<Exercicio> exerciciosAntesAtualizar = new ArrayList<>();
    private Treino t = new Treino();
    private Treino treinoSelecionado = new Treino();
    Exercicio exercicioSelecionado = new Exercicio();
    private GrupoAlunos grupoSelecionado = new GrupoAlunos();

    private ExercicioDAO eDAO = new ExercicioDAO();
    private GrupoAlunosDAO gDAO = new GrupoAlunosDAO();
    private AlunoDAO aDAO = new AlunoDAO();
    private List<Treino> lTreinos = new ArrayList<>();
    private List<Exercicio> lExercs = new ArrayList<>();
    private List<GrupoAlunos> lGrupos = new ArrayList<>();
    private List<Aluno> lAlunos = new ArrayList<>();
    private List<Treino> filtroTreino;

    public List<Treino> getFiltroTreino() {
        return filtroTreino;
    }

    public void setFiltroTreino(List<Treino> filtroTreino) {
        this.filtroTreino = filtroTreino;
    }

    public void naoExisteExercicio(AlunoBean aBean) {
        if (exercicioSelecionado == null) {

            aBean.setExibeTabelations(false);
        } else {
            if (aBean.getAlunoSelecionado() != null) {
                aBean.setExibeTabelations(true);

            }
        }

    }

    private boolean mostraDesempenho = false;

    private String teste;

    private Boolean renderizarExercicio = false;

    public Treino getT() {
        return t;
    }

    public String passaTreino() {
        return "cadastroTreino";
    }

    public void setT(Treino t) {
        this.t = t;
    }

    public TreinoDAO gettDAO() {
        return tDAO;
    }

    public void setTreino(Treino treino, PickListGrupoView pickGrupo, PickListExercicioView pickExercicio) {
        t = treino;

        pickExercicio.atualizarTarget(t.getlExercicios());
        pickGrupo.atualizarTarget(t.getlGrupos());
        gruposAntesAtualizar = t.getlGrupos();
        exerciciosAntesAtualizar = t.getlExercicios();
    }

    public void excluir(Treino treino, PickListGrupoView pickGrupo, PickListExercicioView pickExercicio) {
        tDAO.excluir(treino);
        pickGrupo.init();
        pickExercicio.init();

    }

    public void settDAO(TreinoDAO tDAO) {
        this.tDAO = tDAO;
    }

    public List<Treino> listarTreinosDoTreinador(String treinador) {
        return tDAO.listarDoTreinador(treinador);
    }

    public List<Treino> listarTreinosDoTreinadorDoDia(String treinador, CalendarView calendario) {

        return tDAO.listarDoTreinadorDoDia(treinador, calendario.getDate1());
    }

    public void salvar(PickListGrupoView pickGrupo, PickListExercicioView pickExercicio, List<Exercicio> exerc, List<GrupoAlunos> grupss, String treinador) {

        tDAO.salvar(t, exerc, grupss, treinador, exerciciosAntesAtualizar, gruposAntesAtualizar);

        this.t = new Treino();
        pickExercicio.init();
        pickGrupo.init();

    }

    public void alterouExercicio() {
        if (mostraDesempenho == false) {
            mostraDesempenho = true;
        } else {
            mostraDesempenho = false;
        }
    }

    public void alterouTreino() {
        getListaExerciciosAva();
        getListaGruposAva();
        renderizarExercicio = true;
    }

    public void alterouGrupo() {
        getListaAlunosAva();
    }

    public Treino getTreinoSelecionado() {
        return treinoSelecionado;
    }

    public void setTreinoSelecionado(Treino treinoSelecionado) {
        this.treinoSelecionado = treinoSelecionado;
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

  //  private String teste = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("");
}
