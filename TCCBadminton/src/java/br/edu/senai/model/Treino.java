/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;

/**
 *
 * @author Aluno
 */
@Entity(name = "tb_treino")
public class Treino implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_treino")
    private Integer id;
    @Column(name = "nome_treino")
    private String nome;
    @ManyToMany(mappedBy = "lTreinos")
    private List<GrupoAlunos> lGrupos;
    @ManyToMany
    @JoinTable(name = "treino_exercicio", joinColumns = @JoinColumn(name = "id_treino"), inverseJoinColumns = @JoinColumn(name = "id_exercicio"))
    private List<Exercicio> lExercicios;

    @Column(name = "data_treino")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;

    @Column(name = "horario_treino")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date horario;

    @ManyToOne
    @JoinColumn(name = "id_treinador")
    private Treinador treinador;

    public List<GrupoAlunos> getlGrupos() {
        return lGrupos;
    }

    public void setlGrupos(List<GrupoAlunos> lGrupos) {
        this.lGrupos = lGrupos;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
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

    public List<Exercicio> getlExercicios() {
        return lExercicios;
    }

    public void setlExercicios(List<Exercicio> lExercicios) {
        this.lExercicios = lExercicios;
    }

    public Date getData() {
        return data;
    }

    public Date getHorario() {
        return horario;
    }

    public String getHorarioString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(getHorario())+" Hrs";             
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Treino other = (Treino) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
