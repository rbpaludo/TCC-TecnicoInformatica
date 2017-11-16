/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.senai.view;

import br.edu.senai.controller.LoginDAO;
import br.edu.senai.controller.PessoaDAO;
import br.edu.senai.model.Pessoa;
import java.util.List;
import javax.faces.bean.ManagedBean;



/**
 *
 * @author Aluno
 */
@ManagedBean
public class LoginBean {
    Pessoa pessoa = new Pessoa();
    PessoaDAO pessoaDAO = new PessoaDAO();
    private List<Pessoa> listaPessoas;
    LoginDAO loginDAO = new LoginDAO();
    
    public String login(){
        String url;
        listaPessoas = pessoaDAO.listar();
        url = loginDAO.checaLogin(listaPessoas, pessoa);
        return url;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaDAO getPessoaDAO() {
        return pessoaDAO;
    }

    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public LoginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
    
    
    
}
