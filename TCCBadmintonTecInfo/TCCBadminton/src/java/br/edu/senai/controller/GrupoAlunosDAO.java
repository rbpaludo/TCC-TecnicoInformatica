/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.Aluno;
import br.edu.senai.model.Fundamentos;
import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Treinador;
import br.edu.senai.model.Treino;
import br.edu.senai.util.Conexao;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class GrupoAlunosDAO {

    Conexao conn = new Conexao();
    AlunoDAO aDAO = new AlunoDAO();

//    public void salvarAdicionar(GrupoAlunos gA, List<Aluno> alunosAntesAtualizar) {
//        if (gA.getId() == null) {
//            conn.persist(gA);
//            for (Aluno alu : gA.getlAlunos()) {
//                alu.getlGrupos().add(gA);
//                aDAO.salvar(alu);
//            }
//
//        } else {
//            for (int i = 0; i < alunosAntesAtualizar.size(); i++) {
//                alunosAntesAtualizar.get(i).getlGrupos().remove(gA);
//            }
//            for (int i = 0; i < gA.getlAlunos().size(); i++) {
//                Aluno novoAluno = gA.getlAlunos().get(i);
//                novoAluno.getlGrupos().add(gA);
//                conn.alterar(novoAluno);
//            }
//            conn.alterar(gA);
//        }
//
//        //} else {
//        //  conn.alterar(gA);
//        //}
//    }
    public void salvarAdicionar(GrupoAlunos gA, String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);
        gA.setTreinador(treinadorOnline);
        conn.persist(gA);
        for (Aluno alu : gA.getlAlunos()) {
            alu.getlGrupos().add(gA);
            aDAO.salvar(alu);
        }

        //} else {
        //  conn.alterar(gA);
        //}
    }

    public void salvarAlterar(GrupoAlunos gA, List<Aluno> alunosAntesAtualizar) {
        // Eu tenho a nova target, antiga target, grupo de alunos dahora

        //objetivo é atualizar lista de dentro de cada aluno retirado e adicionado
        //depois eu tenho que colocar os novos
        //OBJETOS RETIRADOS:eu quero ver os itens que estavam na antiga e não estão mais na nova, antiga - nova
        //se retirar os que sairam da antiga temos os que ficaram
        // ESQUECE --- OBJETOS ADICIONADOS: eu quero as que estão na nova e não estavam na antiga, nova - antiga
        //só retirar os resltados da primeira, e adicionar os da segunda, atualizar o grupo de boeiras
        //primeiramente atualizar o grupo
        conn.alterar(gA);

        List<Aluno> listaAntiga = alunosAntesAtualizar;
        List<Aluno> listaAntiga2 = alunosAntesAtualizar;
        List<Aluno> listaNova = gA.getlAlunos();
        // Achando os itens que sairam 
        for (int i = 0; i < listaNova.size(); i++) {
            listaAntiga.remove(listaNova.get(i));
        }
        //atualizando os que sairam
        for (int i = 0; i < listaAntiga.size(); i++) {
            listaAntiga.get(i).getlGrupos().remove(gA);
            conn.alterar(listaAntiga.get(i));
        }
        //retirar do novo os que não precisam ser atualizados        
        for (int i = 0; i < listaAntiga2.size(); i++) {
            listaNova.remove(listaAntiga2.get(i));
        }
        // atualizar os adicionados    
        for (int i = 0; i < listaNova.size(); i++) {
            listaNova.get(i).getlGrupos().add(gA);
            conn.alterar(listaNova.get(i));
        }
        // APARTIR DE AGORA TODOS OS ALUNOS ESTAO ATUALIZADOS

    }

    public void excluir(GrupoAlunos gA) {
        conn.excluir(gA);
    }

    public List<GrupoAlunos> listar(Treino treinoSelecionado) {
        Query query = conn.getEm().createQuery("select a from tb_grupoAlunos a where :id MEMBER OF a.lTreinos ORDER BY a.nome");
        query.setParameter("id", treinoSelecionado);
        List<GrupoAlunos> lGrupos = query.getResultList();
        conn.desconectar();
        return lGrupos;
    }

    public List<GrupoAlunos> listar(String treinador) {
        Treinador treinadorOnline = new Treinador();
        TreinadorDAO tDAO = new TreinadorDAO();
        treinadorOnline = tDAO.retornaTreinador(treinador);
        Query query = conn.getEm().createQuery("select a from tb_grupoAlunos a where a.treinador.id = :parametro ORDER BY a.nome");
        query.setParameter("parametro", treinadorOnline.getId());
        List<GrupoAlunos> lGrupos = query.getResultList();

        return lGrupos;
    }

//    public Usuario retornaUser(String parametro) {
//        Query q = conn.getEm().createQuery("select u from tb_usuario u where u.login = :parametro");
//        q.setParameter("parametro", parametro);
//        Usuario u = (Usuario) q.getSingleResult();
//        System.out.println("Retorna ---!---" + u.toString());
//        conn.desconectar();
//        return u;
//    }
    public List<GrupoAlunos> listarDoTreinador(String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);

        Query query = conn.getEm().createQuery("select a from tb_grupoAlunos a where a.treinador.id = :parametro ORDER BY a.nome");
        query.setParameter("parametro", treinadorOnline.getId());
        List<GrupoAlunos> lGrupos = query.getResultList();

        return lGrupos;
    }

    public GrupoAlunos find(Integer id) {
        return conn.getEm().find(GrupoAlunos.class, id);
    }
}
