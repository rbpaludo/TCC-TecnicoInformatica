/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.view;

import br.edu.senai.controller.PessoaDAO;
import br.edu.senai.controller.TreinadorDAO;
import br.edu.senai.model.Pessoa;
import br.edu.senai.model.Treinador;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Aluno
 */
@ManagedBean
public class TreinadorBean {

    private Treinador treinador = new Treinador();
    private Pessoa pessoa = new Pessoa();
    private TreinadorDAO treinadorDAO = new TreinadorDAO();
    private PessoaDAO pessoaDAO = new PessoaDAO();

    public String salvar() {
        //ver se login está certo
        if (pessoa.getSenha().equals(pessoa.getTestSenha())) {
            if (pessoaDAO.ChecaUsuario(pessoa).isEmpty()) {
                pessoa.setAlunoTreinador("ROLE_TREINADOR");
                treinador.setPessoa(pessoa);
                pessoaDAO.salvar(pessoa);
                treinadorDAO.salvar(treinador);
                return "login";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuário Já Existente"));
                return null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("As senhas não são iguais"));
            return null;
        }

    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
