/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.AvaliacaoAluno;
import br.edu.senai.util.Conexao;
import java.util.List;
import javax.persistence.Query;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author RafaelBP
 */
public class AvaliacaoDAO {

    Conexao conn = new Conexao();

    public void salvar(AvaliacaoAluno a) {
        if (a.getId_avaliacao() == null && a.getAluno().getId() != null) {
            
            conn.persist(a);
        } else {
            conn.alterar(a);
        }
    }
    
    public void excluir(AvaliacaoAluno a){
        conn.excluir(a);
    }
    
    public List<AvaliacaoAluno> listar(){
        Query query1 = conn.getEm().createQuery("select a from tb_avaliacao a");
        List<AvaliacaoAluno> lAvaliacoes = query1.getResultList();
        return lAvaliacoes;
    }
    
    public AvaliacaoAluno find(Integer id){
        return conn.getEm().find(AvaliacaoAluno.class, id);
    }
    
    public PieChartModel getPieChartModels(){
        Query query = conn.getEm().createQuery("select avg(a.qAcertos), avg(a.qErros) from tb_avaliacao a, tb_aluno b where a.id_avaliacao = b.id and b.id = :id");
        query.setParameter("id", conn);
        return null;
    }
}
