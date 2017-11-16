/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author RafaelBP
 */
@Entity(name = "tb_avaliacao")
public class AvaliacaoAluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer id_avaliacao;
   
    @Column(name = "qAcertos_avaliacao")
    private int qAcertos;
    @Column(name = "qErros_avaliacao")
    private int qErros;
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    @ManyToOne
    @JoinColumn(name = "id_exercicio")
    private Exercicio exercicio;

    
    
    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }
    

    
    
    public Integer getId_avaliacao() {
        return id_avaliacao;
    }

    public void setId_avaliacao(Integer id_avaliacao) {
        this.id_avaliacao = id_avaliacao;
    }

    public int getqAcertos() {
        return qAcertos;
    }

    public void setqAcertos(int qAcertos) {
        this.qAcertos = qAcertos;
    }

    public int getqErros() {
        return qErros;
    }

    public void setqErros(int qErros) {
        this.qErros = qErros;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id_avaliacao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AvaliacaoAluno other = (AvaliacaoAluno) obj;
        if (!Objects.equals(this.id_avaliacao, other.id_avaliacao)) {
            return false;
        }
        return true;
    }
}
