/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Pessoa;
import br.edu.senai.model.Treinador;
import br.edu.senai.util.Conexao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Aluno
 */
public class TreinadorDAO {

    Conexao conn = new Conexao();

    public void salvar(Treinador t) {
        if (t.getId() == null) {
            conn.persist(t);
        } else {
            conn.alterar(t);
        }
    }

    public void excluir(Treinador t) {
        conn.excluir(t);
    }

    public List<Treinador> listar() {
        Query query = conn.getEm().createQuery("select a from tb_treinador a");
        List<Treinador> lTreinadores = query.getResultList();

        return lTreinadores;
    }

    public Treinador retornaTreinador(String treinador){
        Query q = conn.getEm().createQuery("select u from tb_treinador u where u.pessoa.usuario = :parametro");
        q.setParameter("parametro", treinador);
        Treinador t = (Treinador) q.getSingleResult();
        conn.desconectar();
        return t;
    }
    
    public Treinador find(Integer id) {
        return conn.getEm().find(Treinador.class, id);
    }
    
}
