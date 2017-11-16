/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.controller.PessoaDAO;
import br.edu.senai.controller.TreinadorDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.Pessoa;
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

public class AlunoBean {

    private Aluno aluno = new Aluno();
    private AlunoDAO alunoDAO = new AlunoDAO();
    private Pessoa pessoa = new Pessoa();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private Aluno alunoSelecionado = new Aluno();
    private boolean exibeDesempenho;
    private TreinoBean tBean = new TreinoBean();

    private boolean exibeTabelations = false;

    private List<Exercicio> filtroAluno;

    public List<Exercicio> getFiltroAluno() {
        return filtroAluno;
    }

    public void setFiltroAluno(List<Exercicio> filtroAluno) {
        this.filtroAluno = filtroAluno;
    }

    public void salvar(String treinadorNome) {
        pessoa.setAlunoTreinador("ROLE_ALUNO");
        TreinadorDAO tDAO = new TreinadorDAO();
        aluno.setTreinador(tDAO.retornaTreinador(treinadorNome));
        aluno.setPessoa(pessoa);
        
        alunoDAO.salvar(aluno);
        aluno = new Aluno();
        pessoa = new Pessoa();
    }

    public void alterouAluno(TreinoBean tBean) {
        if (alunoSelecionado != null && tBean.exercicioSelecionado != null) {
            exibeTabelations = true;
            
            tBean.alterouExercicio();
        } else {
            exibeTabelations = false;
        }
    }

    public void excluirAluno(Aluno aluno) {
        this.aluno = new Aluno();
        alunoDAO.excluir(aluno);
    }

    public List<Aluno> listar() {
        return alunoDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;

    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        this.pessoa = aluno.getPessoa();
    }

    public boolean isExibeDesempenho() {
        return exibeDesempenho;
    }

    public void setExibeDesempenho(boolean exibeDesempenho) {
        this.exibeDesempenho = exibeDesempenho;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isExibeTabelations() {
        return exibeTabelations;
    }

    public boolean renderizarTabelations() {
        return exibeTabelations;
    }

    public void setExibeTabelations(boolean exibeTabelations) {
        this.exibeTabelations = exibeTabelations;
    }

}
