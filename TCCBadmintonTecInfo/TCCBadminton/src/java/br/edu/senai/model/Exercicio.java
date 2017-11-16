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
import javax.persistence.ManyToOne;

/**
 *
 * @author Aluno
 */
@Entity(name = "tb_exercicio")
public class Exercicio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercicio")
    private Integer id;
    @Column(name = "nome_exercicio")
    private String nome;
    @Column(name = "desc_exercicio")
    private String descricao;
    @Column(name = "publico")
    private boolean publico = false;
    @ManyToOne
    @JoinColumn(name = "id_treinador")
    private Treinador treinador;
    @Column(name = "NumCompartilhamentos")
    private int NumCompartilhamentos = 0;
    @ManyToMany
    @JoinTable(name = "exercicio_fundamentos", joinColumns = @JoinColumn(name = "id_exercicio"), inverseJoinColumns = @JoinColumn(name = "id_fundamentos"))
    private List<Fundamentos> fundamentos;
    @Column(name = "url_exercicio")
    private String url;
    @Column(name = "aon_exercicio")
    private Boolean adaptado;
    @Column(name = "adapt_exercicio")
    private String adaptacao;
    @Column(name = "nMinAt_exercicio")
    private Integer numMinAtletas;
    @Column(name = "funcao_exercicio")
    private String funcao;
    @ManyToMany(mappedBy = "lExercicios")
    private List<Treino> lTreinos;

    public boolean isPublico() {
        return publico;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }

    
    
    public void setPublico(boolean publico) {
        
        this.publico = publico;
        
    }

    public int getNumCompartilhamentos() {
        return NumCompartilhamentos;
    }

    public void setNumCompartilhamentos(int NumCompartilhamentos) {
        this.NumCompartilhamentos = NumCompartilhamentos;
    }

    
    public List<Fundamentos> getFundamentos() {
        return fundamentos;
    }

    public void setFundamentos(List<Fundamentos> fundamentos) {
        this.fundamentos = fundamentos;
    }

    public List<Treino> getlTreinos() {
        return lTreinos;
    }

    public void setlTreinos(List<Treino> lTreinos) {
        this.lTreinos = lTreinos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isAdaptado() {
        return adaptado;
    }

    public void setAdaptado(Boolean adaptado) {
        this.adaptado = adaptado;
    }

    public String getAdaptacao() {
        return adaptacao;
    }

    public void setAdaptacao(String adaptacao) {
        this.adaptacao = adaptacao;
    }

    public Integer getNumMinAtletas() {
        return numMinAtletas;
    }

    public void setNumMinAtletas(Integer numMinAtletas) {
        this.numMinAtletas = numMinAtletas;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Exercicio other = (Exercicio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    

   


    public Exercicio(String nome) {
        this.nome = nome;
    }

    public Exercicio() {
    }

}
