/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Treinador;
import br.edu.senai.model.Treino;
import br.edu.senai.util.Conexao;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Aluno
 */
public class TreinoDAO {

    Conexao conn = new Conexao();
    ExercicioDAO exeDAO = new ExercicioDAO();
    GrupoAlunosDAO grupoDAO = new GrupoAlunosDAO();

    public void salvar(Treino tr, List<Exercicio> lE, List<GrupoAlunos> lG, String treinador, List<Exercicio> exerciciosAntesAtualizar, List<GrupoAlunos> gruposAntesAtualizar) {
        TreinadorDAO treDAO = new TreinadorDAO();
        Treinador treinadorOnline = treDAO.retornaTreinador(treinador);
        tr.setTreinador(treinadorOnline);

        if (tr.getId() == null) {

            tr.setlGrupos(lG);
            tr.setlExercicios(lE);
            conn.persist(tr);

            for (int i = 0; i < lG.size(); i++) {
                lG.get(i).getlTreinos().add(tr);
                grupoDAO.conn.alterar(lG.get(i));
            }

            for (int i = 0; i < lE.size(); i++) {
                lE.get(i).getlTreinos().add(tr);
                exeDAO.conn.alterar(lE.get(i));
            }

        } else {

            tr.setlExercicios(lE);
            tr.setlGrupos(lG);

            conn.alterar(tr);

            List<Exercicio> listaEAntiga = exerciciosAntesAtualizar;
            List<GrupoAlunos> listaGAntiga = gruposAntesAtualizar;
            List<Exercicio> listaEAntiga2 = exerciciosAntesAtualizar;
            List<GrupoAlunos> listaGAntiga2 = gruposAntesAtualizar;
            List<Exercicio> listaENova = tr.getlExercicios();
            List<GrupoAlunos> listaGNova = tr.getlGrupos();

//            //Achando os itens que sairam
//            for (int i = 0; i < listaGNova.size(); i++) {
//                listaGAntiga.remove(listaGNova.get(i));
//            }
//
//            //atualizando os que sairam
//            for (int i = 0; i < listaGAntiga.size(); i++) {
//                listaGAntiga.get(i).getlTreinos().remove(tr);
//            }
//            //retirar do novo os que não precisam ser atualiados
//            for (int i = 0; i < listaGAntiga2.size(); i++) {
//                listaGNova.remove(listaGAntiga2.get(i));
//            }
//            //atualizar os adicionados
//
//            for (int i = 0; i < listaGNova.size(); i++) {
//                listaGNova.get(i).getlTreinos().add(tr);
//                conn.alterar(listaGNova.get(i));
//            }
            //A PARTIR DE AGORA TODOS OS GRUPOS E EXERCICIOS ESTAO ATUALIZADOS
        }

    }

    public List<Treino> listarDoTreinador(String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);

        Query query = conn.getEm().createQuery("select a from tb_treino a where a.treinador.id = :parametro");
        query.setParameter("parametro", treinadorOnline.getId());
        List<Treino> lTreinos = query.getResultList();

        return lTreinos;
    }

    public List<Treino> listarDoTreinadorDoDia(String treinador, Date data) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);

        Query query = conn.getEm().createQuery("select a from tb_treino a where a.treinador.id = :parametro1 and a.data = :parametro2 ORDER BY A.horario");
        query.setParameter("parametro1", treinadorOnline.getId());
        query.setParameter("parametro2", data);
        List<Treino> lTreinos = query.getResultList();

        return lTreinos;
    }

    public void excluir(Treino tr) {
        conn.excluir(tr);
    }

    public List<Treino> listar() {
        Query query = conn.getEm().createQuery("select a from tb_treino a");
        List<Treino> lTreinos = query.getResultList();

        return lTreinos;
    }

    public List<Treino> listar(String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);
        Query query = conn.getEm().createQuery("select a from tb_treino a where a.treinador.id = :id");
        query.setParameter("id", treinadorOnline.getId());
        List<Treino> lTreinos = query.getResultList();

        return lTreinos;
    }

    public Treino find(Integer id) {
        return conn.getEm().find(Treino.class, id);
    }
}
