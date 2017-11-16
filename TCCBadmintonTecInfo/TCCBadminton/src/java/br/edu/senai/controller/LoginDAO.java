/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.controller;

import br.edu.senai.model.Pessoa;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class LoginDAO {

    public String checaLogin(List<Pessoa> listaPessoas, Pessoa pessoa) {
        boolean passou = false;
        String mensagem = "login.xhtml";
        int x = (listaPessoas.size() - 1);
        int xQuePassou = 0;
        //xQuePassou é a posição em que o login foi achado, guardado para lá em baixo ver se a pessoa
        //é aluno ou treinador
     

        if (true == passou) {
            if (listaPessoas.get(xQuePassou).getAlunoTreinador().equals("treinador")) {
                mensagem = "CadastroDeTreinador.xhtml";
            } else {
                mensagem = "CadastroDeAlunos.xhtml";
            }

        }
        return mensagem;
    }

}
