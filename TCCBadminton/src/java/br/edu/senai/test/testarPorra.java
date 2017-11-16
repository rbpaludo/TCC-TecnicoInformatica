/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.test;

import br.edu.senai.controller.AlunoDAO;
import br.edu.senai.controller.PessoaDAO;
import br.edu.senai.model.Aluno;
import br.edu.senai.model.Pessoa;
import br.edu.senai.util.Conexao;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Aluno
 */
public class testarPorra {

    public static void main(String[] args) {

      Conexao conn = new Conexao();
      conn.conectar();
      conn.desconectar();
        
        
        
        
    }

}
