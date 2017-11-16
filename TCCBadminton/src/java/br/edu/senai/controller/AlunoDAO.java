/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.Aluno;
import br.edu.senai.model.Exercicio;
import br.edu.senai.model.GrupoAlunos;
import br.edu.senai.model.Treinador;
import br.edu.senai.util.Conexao;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.persistence.Query;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Aluno
 */
@ViewScoped
public class AlunoDAO {

    Conexao conn = new Conexao();
    private PieChartModel PCMdesempenho = new PieChartModel();

    public void salvar(Aluno a) {
        if (a.getId() == null) {
            conn.persist(a.getPessoa());
            conn.persist(a);
        } else {
            
            conn.alterar(a.getPessoa());
        }
    }

    public void excluir(Aluno a) {
        conn.excluirAvalias(a);
        conn.excluir(a);
        conn.excluir(a.getPessoa());

    }

    public List<Aluno> listar(String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);
        Query query = conn.getEm().createQuery("select a from tb_aluno a where a.treinador.id = :id ORDER BY a.pessoa.usuario");
        query.setParameter("id", treinadorOnline.getId());
        List<Aluno> lAlunos = query.getResultList();

        return lAlunos;
    }

    public List<Aluno> listar(GrupoAlunos grupoSelecionado) {
        Query query = conn.getEm().createQuery("select a from tb_aluno a where :id MEMBER OF a.lGrupos ORDER BY a.pessoa.usuario");
        query.setParameter("id", grupoSelecionado);
        List<Aluno> lAlunos = query.getResultList();
        conn.desconectar();
        return lAlunos;
    }

    public List<Aluno> listarUsuarios() {
        Query query = conn.getEm().createQuery("select a from tb_aluno a ORDER BY a.pessoa.usuario");
        List<Aluno> lAlunos = query.getResultList();
        return lAlunos;
    }

    public List<Aluno> listarAlunos(String treinador) {
        TreinadorDAO tDAO = new TreinadorDAO();
        Treinador treinadorOnline = tDAO.retornaTreinador(treinador);
        Query query = conn.getEm().createQuery("select a from tb_aluno a where a.treinador.id = :id ORDER BY a.pessoa.usuario");
        query.setParameter("id", treinadorOnline.getId());
        List<Aluno> lAlunos = query.getResultList();
        return lAlunos;
    }

    public Aluno find(Integer id) {
        return conn.getEm().find(Aluno.class, id);
    }

    public PieChartModel genialidadeDesemp(Aluno alunoSelecionado) {
        PCMdesempenho = new PieChartModel();
        Query query = conn.getEm().createQuery("select avg(a.qAcertos), avg(a.qErros) from tb_avaliacao a, tb_aluno b where a.aluno.id = b.id and b.id = :id");
        query.setParameter("id", alunoSelecionado.getId());
        if (query.getResultList() != null) {
            List<Object[]> lObjects = query.getResultList();
            List<Double> lMediaDesemp = new ArrayList<>();
            for (Object[] lista : lObjects) {
                lMediaDesemp.add((Double) lista[0]);
                lMediaDesemp.add((Double) lista[1]);
            }

            Double soma = 0.0;
            List<Double> lPorcentDesemp = new ArrayList<>();

            for (Double num : lMediaDesemp) {
                soma += num;
            }

            for (Double num : lMediaDesemp) {
                lPorcentDesemp.add((num * 100) / soma);
            }

            Double acertos = lPorcentDesemp.get(0);
            Double erros = lPorcentDesemp.get(1);

            PCMdesempenho.set("Acertos", acertos);
            PCMdesempenho.set("Erros", erros);
            return PCMdesempenho;
        }
        return PCMdesempenho;
    }

    public PieChartModel retornaPCMDesempenho(Aluno aluno, Exercicio exercicio) {
        PCMdesempenho = new PieChartModel();
        if (exercicio == null) {
            Query query = conn.getEm().createQuery("select avg(a.qAcertos), avg(a.qErros) from tb_avaliacao a, tb_aluno b where a.aluno.id = b.id and b.id = :id");
            query.setParameter("id", aluno.getId());
            List<Object[]> lObjects2 = query.getResultList();
            List<Double> lMediaDesemp2 = new ArrayList<>();
            for (Object[] lista : lObjects2) {
                lMediaDesemp2.add((Double) lista[0]);
                lMediaDesemp2.add((Double) lista[1]);
            }

            if (lMediaDesemp2.get(0) != null) {

                List<Object[]> lObjects = query.getResultList();
                List<Double> lMediaDesemp = new ArrayList<>();
                for (Object[] lista : lObjects) {
                    lMediaDesemp.add((Double) lista[0]);
                    lMediaDesemp.add((Double) lista[1]);
                }

                Double soma = 0.0;
                List<Double> lPorcentDesemp = new ArrayList<>();

                for (Double num : lMediaDesemp) {
                    soma += num;
                }

                for (Double num : lMediaDesemp) {
                    lPorcentDesemp.add((num * 100) / soma);
                }

                Double acertos = lPorcentDesemp.get(0);
                Double erros = lPorcentDesemp.get(1);

                PCMdesempenho.set("Acertos", acertos);
                PCMdesempenho.set("Erros", erros);
                return PCMdesempenho;
            } else {
                PCMdesempenho.set("Nenhum Resultado Encontrado", 1);
                return PCMdesempenho;
            }
        } else {

            // Query query = conn.getEm().createQuery("select avg(a.qAcertos), avg(a.qErros) from tb_avaliacao a, tb_aluno b, tb_exercicio e where a.aluno.id = b.id and b.id = :id and e.id = :exe and e.id = a.exercicio.id");
            Query query = conn.getEm().createQuery("select avg(a.qAcertos), avg(a.qErros) from tb_avaliacao a where a.exercicio.id = :exe and a.aluno.id = :id");

            query.setParameter("id", aluno.getId());
            query.setParameter("exe", exercicio.getId());
            List<Object[]> lObjects2 = query.getResultList();
            List<Double> lMediaDesemp2 = new ArrayList<>();
            for (Object[] lista : lObjects2) {
                lMediaDesemp2.add((Double) lista[0]);
                lMediaDesemp2.add((Double) lista[1]);
            }

            if (lMediaDesemp2.get(0) != null) {

                List<Object[]> lObjects = query.getResultList();

                List<Double> lMediaDesemp = new ArrayList<>();
                for (Object[] lista : lObjects) {
                    lMediaDesemp.add((Double) lista[0]);
                    lMediaDesemp.add((Double) lista[1]);
                }

                Double soma = 0.0;
                List<Double> lPorcentDesemp = new ArrayList<>();

                for (Double num : lMediaDesemp) {
                    soma += num;
                }

                for (Double num : lMediaDesemp) {
                    lPorcentDesemp.add((num * 100) / soma);
                }

                Double acertos = lPorcentDesemp.get(0);
                Double erros = lPorcentDesemp.get(1);

                PCMdesempenho.set("Acertos", acertos);
                PCMdesempenho.set("Erros", erros);
                return PCMdesempenho;

            } else {
                PCMdesempenho.set("Nenhum Resultado Encontrado", 1);
                return PCMdesempenho;
            }
        }
    }

    public PieChartModel getPCMdesempenho() {
        return PCMdesempenho;
    }

    public void setPCMdesempenho(PieChartModel PCMdesempenho) {
        this.PCMdesempenho = PCMdesempenho;
    }
}
