/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.ExercicioDAO;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.Fundamentos;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author RafaelBP
 */
@ManagedBean
@ViewScoped
public class ExercicioBean {

    private Exercicio exercicio = new Exercicio();
    private ExercicioDAO eDAO = new ExercicioDAO();
    private List<String> lFundamentos = new ArrayList<String>();
    private List<String> fundSelecionados = new ArrayList<String>();
    private List<Fundamentos> lFundamentosCertos = new ArrayList<>();
    private String adaptadoString = "";
    private Exercicio[] filtroExercicio;
    private List<Exercicio> filtroExercicio2;
    private Exercicio antes = new Exercicio();

    public List<Exercicio> getFiltroExercicio2() {
        return filtroExercicio2;
    }

    public void setFiltroExercicio2(List<Exercicio> filtroExercicio2) {
        this.filtroExercicio2 = filtroExercicio2;
    }

    public Exercicio[] getFiltroExercicio() {
        return filtroExercicio;
    }

    public void setFiltroExercicio(Exercicio[] filtroExercicio) {
        //  Exercicio provisorio = new Exercicio();
        //   for (int i = 0; i < 10; i++) {
        //       provisorio = filtroExercicio.get(i);
        //        this.filtroExercicio[i] = exercicio;
        //    }
        this.filtroExercicio = filtroExercicio;
    }

    public String getAdaptadoString() {
        if (exercicio.isAdaptado() != null) {
            if (exercicio.isAdaptado() == true) {
                this.adaptadoString = "true";
            } else {
                this.adaptadoString = "false";
            }
        } else {
            adaptadoString = "";
        }
        return this.adaptadoString;
    }

    public void setAdaptadoString(String adaptadoString) {
        this.adaptadoString = adaptadoString;
        if (adaptadoString.equals("true")) {
            getExercicio().setAdaptado(true);
        } else if (adaptadoString.equals("false")) {
            getExercicio().setAdaptado(false);
        }
    }

    public List<String> getFundSelecionados() {
        if (exercicio.getFundamentos() == null) {
            return fundSelecionados;
        } else {
            fundSelecionados = new ArrayList<String>();
            List<Fundamentos> listaOutcomeGay = new ArrayList<>();
            listaOutcomeGay = exercicio.getFundamentos();
            for (int i = 0; i < listaOutcomeGay.size(); i++) {
                fundSelecionados.add(listaOutcomeGay.get(i).getNome());
            }
        }
        return fundSelecionados;
    }

    public void setFundSelecionados(List<String> fundSelecionados) {
        this.fundSelecionados = fundSelecionados;
    }

    public List<String> getlFundamentos() {
        return lFundamentos;
    }

    public Exercicio getAntes() {

        return antes;
    }

    public void setAntes(Exercicio antes) {

        this.antes = antes;
    }

    public void setlFundamentos(List<String> lFundamentos) {
        this.lFundamentos = lFundamentos;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;

    }

    public void setExercicioAdquiri(Exercicio exercicio) {
        exercicio.setPublico(false);
        this.antes = exercicio;
        this.exercicio = exercicio;
        this.exercicio.setPublico(false);

    }

    public void salvarExercicioFund(List<String> lSelecionados, String treinador) {
        lFundamentosCertos = (eDAO.getFundamentoByNome(lSelecionados));
        exercicio.setFundamentos(lFundamentosCertos);
        if (!exercicio.isAdaptado()) {
            exercicio.setAdaptacao("");
        }
        eDAO.salvar(exercicio, lFundamentosCertos, treinador);
        exercicio = new Exercicio();
        fundSelecionados = new ArrayList<>();
    }

    public void adquirirExercicioFund(List<String> lSelecionados, String treinador) {

        antes.setNumCompartilhamentos(antes.getNumCompartilhamentos() + 1);
        antes.setPublico(true);
        eDAO.salvar(antes, antes.getFundamentos(), treinador);

        exercicio.setNumCompartilhamentos(0);
        lFundamentosCertos = (eDAO.getFundamentoByNome(lSelecionados));
        exercicio.setFundamentos(lFundamentosCertos);
        exercicio.setId(null);
        exercicio.setPublico(false);
        eDAO.adaptarExercicio(exercicio, lFundamentosCertos, treinador);

        exercicio = new Exercicio();
        antes = new Exercicio();
        fundSelecionados = new ArrayList<>();
    }

    public void excluir(Exercicio e) {
        eDAO.excluir(e);
        exercicio = new Exercicio();
    }

    public List<Exercicio> listarExercicios(String treinador) {
        return eDAO.listar(treinador);
    }

    public List<Exercicio> listarExerciciosPublicos() {
        return eDAO.listarPublicos();
    }

    @PostConstruct
    public void init() {
        lFundamentos = new ArrayList<String>();
        lFundamentos.add("Smash");
        lFundamentos.add("Drop");
        lFundamentos.add("Clear");
        lFundamentos.add("Clip");
        lFundamentos.add("Curta");
        lFundamentos.add("Lob");
        lFundamentos.add("Net kill");
        lFundamentos.add("Kill");
        lFundamentos.add("Saque lateral");
        lFundamentos.add("Saque frontal");
    }

    public String mandaPaginaProvisoria() {
        return "/treinador/index.xhtml";
    }
}
