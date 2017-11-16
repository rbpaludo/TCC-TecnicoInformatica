/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Aluno
 */
@Entity(name = "tb_grupoAlunos")
public class GrupoAlunos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")  
    private Integer id;
    @Column(name = "nome_grupoAlunos")
    private String nome;
    @ManyToMany
    @JoinTable(name = "grupo_aluno",
            joinColumns =
                    @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = 
                    @JoinColumn(name = "id_aluno"))
    private List<Aluno> lAlunos;
    @ManyToMany
    @JoinTable(name = "grupo_treino",
            joinColumns =
                    @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = 
                    @JoinColumn(name = "id_treino"))
    private List<Treino> lTreinos;
    
    @OneToOne
    @JoinColumn(name = "id_treinador")
    private Treinador treinador;

    public List<Treino> getlTreinos() {
        return lTreinos;
    }

   

    public void setlTreinos(List<Treino> lTreinos) {
        this.lTreinos = lTreinos;
    }

    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getlAlunos() {
        return lAlunos;
    }

    public void setlAlunos(List<Aluno> lAlunos) {
        this.lAlunos = lAlunos;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final GrupoAlunos other = (GrupoAlunos) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
