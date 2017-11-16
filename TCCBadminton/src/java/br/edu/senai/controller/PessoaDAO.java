/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.Pessoa;
import br.edu.senai.model.Treino;
import br.edu.senai.util.Conexao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author RafaelBP
 */
public class PessoaDAO {

    Conexao conn = new Conexao();

    public Pessoa find(Integer id) {
        return conn.getEm().find(Pessoa.class, id);
    }

    public void salvar(Pessoa p) {
        if (p.getId() == null) {
            conn.persist(p);
        } else {
            conn.alterar(p);
        }
    }

    public void excluir(Pessoa p) {
        conn.excluir(p);
    }

    public List<Pessoa> listar() {
        Query query = conn.getEm().createQuery("select a from tb_pessoa a");
        List<Pessoa> lPessoas = query.getResultList();

        return lPessoas;
    }

    public List<Pessoa> ChecaUsuario(Pessoa pessoa) {
        Query query = conn.getEm().createQuery("select a from tb_pessoa a where a.usuario = :usuario");
        query.setParameter("usuario", pessoa.getUsuario());
        List<Pessoa> lPessoas = query.getResultList();

        return lPessoas;
    }

}
