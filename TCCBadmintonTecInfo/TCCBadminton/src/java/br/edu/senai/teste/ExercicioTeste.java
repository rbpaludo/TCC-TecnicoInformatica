/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.senai.teste;

import br.edu.senai.model.Treinador;
import br.edu.senai.util.Conexao;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class ExercicioTeste {
    public static void main(String[] args) {
     Treinador treinador = new Treinador();   
    
    Conexao conn = new Conexao();
    
    Query q = conn.getEm().createQuery("select u from tb_treinador u where u.pessoa.usuario = :parametro");
        q.setParameter("parametro", "gustavogaludo");
        Treinador t = (Treinador) q.getSingleResult();
        conn.desconectar();
        JOptionPane.showMessageDialog(null, t.getId());
    }
}
