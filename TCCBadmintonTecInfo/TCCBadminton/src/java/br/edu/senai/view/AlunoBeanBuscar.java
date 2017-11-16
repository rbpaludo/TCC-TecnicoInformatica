/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.controller.PessoaDAO;
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
public class AlunoBeanBuscar {

    private Aluno aluno = new Aluno();
    private AlunoDAO alunoDAO = new AlunoDAO();
    private Pessoa pessoa = new Pessoa();
    private PessoaDAO pessoaDAO = new PessoaDAO();
    private Aluno alunoSelecionado = new Aluno();
    private Exercicio exercicioSelecionado = new Exercicio();
    private boolean exibeDesempenho;

    private boolean exibeTabelations = false;
    private boolean mostraGraficos = false;
    
  

   
            
            
    public boolean isMostraGraficos() {

        return mostraGraficos;
    }

    public void setMostraGraficos(boolean mostraGraficos) {
        this.mostraGraficos = mostraGraficos;
 
    }

    public String salvar() {
        pessoa.setAlunoTreinador("ROLE_ALUNO");
        aluno.setPessoa(pessoa);
        pessoaDAO.salvar(pessoa);
        alunoDAO.salvar(aluno);
        return "login";
    }

    
    public void alterouAluno(TreinoBean tBean) {
        if (alunoSelecionado != null) {
            exibeTabelations = true;
            tBean.alterouExercicio();
            mostraGraficos = true;
        } else {
            exibeTabelations = false;
        }
    }

    public List<Aluno> listar() {
        return alunoDAO.listar(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Aluno getAlunoSelecionado() {
        return alunoSelecionado;

    }

    public Exercicio getExercicioSelecionado() {
        return exercicioSelecionado;
    }

    public void setExercicioSelecionado(Exercicio exercicioSelecionado) {
        this.exercicioSelecionado = exercicioSelecionado;
    }

    public void setAlunoSelecionado(Aluno alunoSelecionado) {
        this.alunoSelecionado = alunoSelecionado;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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
