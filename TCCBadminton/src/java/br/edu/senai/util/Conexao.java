/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.util;

import br.edu.senai.model.Aluno;
import br.edu.senai.model.AvaliacaoAluno;
import com.sun.deploy.uitoolkit.impl.fx.ui.MixedCodeInSwing;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author RafaelBP
 */
public class Conexao {

    private static EntityManagerFactory factory;
    private EntityManager em;
    private boolean conectou = false;

    public Conexao() {
        conectar();
    }

    public boolean conectar() {
        try {
            factory = Persistence.createEntityManagerFactory("TCCBadmintonPU");
            em = factory.createEntityManager();
            conectou = true;
        } catch (Exception e1) {
            System.out.println("Não foi possível conectar ao banco de dados\n" + e1.getMessage());
        }
        return conectou;
    }

    public void desconectar() {
        try {
            em.close();
            factory.close();
        } catch (Exception e) {
            System.out.println("Não foi possível se desconectar do banco de dados " + e.getMessage());
        }
    }

    public void persist(Object o) {
        if (conectar()) {
            try {
                em.getTransaction().begin();
                em.persist(o);
                em.getTransaction().commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                em.getTransaction().rollback();
            } finally {
                desconectar();
            }
        }
    }

    public void alterar(Object o) {
        if (conectar()) {
            try {
                em.getTransaction().begin();
                em.merge(o);
                em.getTransaction().commit();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                em.getTransaction().rollback();
            } finally {
                desconectar();
            }
        }
    }

    public void excluir(Object o) {
        if (conectar()) {
            try {
                em.getTransaction().begin();
                em.remove(em.merge(o));
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            } finally {
                desconectar();
            }
        }
    }

    public void excluirAvalias(Aluno a) {
        if (conectar()) {
            try {
                em.getTransaction().begin();
                Query query1 = getEm().createQuery("select a from tb_avaliacao a where a.aluno.id = :id");
                query1.setParameter("id", a.getId());
                List<AvaliacaoAluno> lAvaliacoes = query1.getResultList();
                for (int i = 0; i < lAvaliacoes.size(); i++) {
                    em.remove(em.merge(lAvaliacoes.get(i)));
                }

                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            } finally {
                desconectar();
            }
        }
    }

    public EntityManager getEm() {
        if (!em.isOpen()) {
            conectar();
        }
        return em;
    }
}
