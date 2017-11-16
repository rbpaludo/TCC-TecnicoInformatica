/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.Exercicio;
import br.edu.senai.model.Fundamentos;
import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Treinador;
import br.edu.senai.model.Treino;
import br.edu.senai.util.Conexao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class ExercicioDAO {

    Conexao conn = new Conexao();

    public void salvar(Exercicio e, List<Fundamentos> lF, String treinador) {
        TreinadorDAO tDAo = new TreinadorDAO();

        if (e.getId() == null) {
            e.setTreinador(tDAo.retornaTreinador(treinador));
            for (int i = 0; i < lF.size(); i++) {
                conn.alterar(lF.get(i));
            }
            conn.persist(e);
        } else {
            
            conn.alterar(e);
        }
    }
    public void adaptarExercicio(Exercicio e, List<Fundamentos> lF, String treinador) {
        TreinadorDAO tDAo = new TreinadorDAO();
        e.setPublico(false);
        e.setId(null);
            e.setTreinador(tDAo.retornaTreinador(treinador));
            for (int i = 0; i < lF.size(); i++) {
                conn.alterar(lF.get(i));
            }
            conn.persist(e);
     
    }
    

    public void excluir(Exercicio e) {
        conn.excluir(e);
    }

    public List<Exercicio> listar(String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);
        Query query = conn.getEm().createQuery("select a from tb_exercicio a where a.treinador.id = :parametro1 ORDER BY a.nome");
        query.setParameter("parametro1", treinadorOnline.getId());
        List<Exercicio> lExercicios = query.getResultList();
        conn.desconectar();
        return lExercicios;
    }

    public List<Exercicio> listarPublicos() {
        Query query = conn.getEm().createQuery("select a from tb_exercicio a where a.publico = true ORDER BY a.NumCompartilhamentos DESC, a.nome ASC");
        List<Exercicio> lExercicios = query.getResultList();
        conn.desconectar();
        return lExercicios;
      
    }

    public Fundamentos find(Integer id) {
        return conn.getEm().find(Fundamentos.class, id);
    }

    public Exercicio findExercicio(Integer id) {
        return conn.getEm().find(Exercicio.class, id);
    }

    public List<Exercicio> listar(Treino treino) {
        Query query = conn.getEm().createQuery("select a from tb_exercicio a where :id MEMBER OF a.lTreinos ORDER BY a.nome");
        query.setParameter("id", treino);
        List<Exercicio> lExercicios = query.getResultList();
        conn.desconectar();
        return lExercicios;
    }

    public List<Fundamentos> getFundamentoByNome(List<String> listaSelecionados) {
        List<Fundamentos> lFundamentosCertos = new ArrayList<>();

        Query query = conn.getEm().createQuery("select a from tb_fundamentos a");
        List<Fundamentos> lFundamentos = query.getResultList();
        for (int i = 0; i < lFundamentos.size(); i++) {
            for (int j = 0; j < listaSelecionados.size(); j++) {
                if (listaSelecionados.get(j).equals(lFundamentos.get(i).toString())) {
                    lFundamentosCertos.add(lFundamentos.get(i));
                }
            }
            /**
             * Query query1 = conn.getEm().createQuery("select a from
             * tb_fundamento where nome = ?"); query1.setParameter(1,
             * listaSelecionados.get(i));
             * lFundamentosCertos.add((Fundamentos)query1.getSingleResult());
             */
        }

        return lFundamentosCertos;

    }

}
