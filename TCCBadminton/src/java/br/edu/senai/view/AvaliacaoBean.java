/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.AvaliacaoDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.AvaliacaoAluno;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Treino;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.swing.JOptionPane;

/**
 *
 * @author RafaelBP
 */
@ManagedBean
@ViewScoped
public class AvaliacaoBean {

    private AvaliacaoAluno avaliacao = new AvaliacaoAluno();
    private AvaliacaoDAO avaDAO = new AvaliacaoDAO();
    private Aluno alunoSelecionado = new Aluno();
    private List<AvaliacaoAluno> lAva = new ArrayList<>();
    

    public AvaliacaoAluno getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(AvaliacaoAluno avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public void salvar(AvaliacaoAluno ava, AlunoBean aBean, TreinoBean tBean) {

        ava.setAluno(aBean.getAlunoSelecionado());
        
        ava.setExercicio(tBean.getExercicioSelecionado());

        avaDAO.salvar(ava);
        avaliacao = new AvaliacaoAluno();
        tBean.setExercicioSelecionado(null);
        tBean.setGrupoSelecionado(null);
        tBean.setTreinoSelecionado(null);
        aBean.setAlunoSelecionado(null);
        

    }

    public void excluir(AvaliacaoAluno ava) {
        avaDAO.excluir(ava);
    }

    public List<AvaliacaoAluno> listar() {

        lAva = avaDAO.listar();
        return lAva;
    }
}
