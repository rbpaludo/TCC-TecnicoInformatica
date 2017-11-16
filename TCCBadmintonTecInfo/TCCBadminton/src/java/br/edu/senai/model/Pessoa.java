/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.senai.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author RafaelBP
 */
@Entity(name = "tb_pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Integer id;
    @Column(name = "nome_pessoa")
    private String nome;
    @Column(name = "end_pessoa")
    private String endereco;
    @Column(name = "nasc_pessoa")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nascimento;
    @Column(name = "CPF_pessoa")
    private String CPF;
    @Column(name = "email_pessoa")
    private String email;
    @Column(name = "usu_pessoa")
    private String usuario;
    @Column(name = "senha_pessoa")
    private String senha;
    @Transient
    private String testSenha;
    @Column(name = "AlunoTreinador")
    private String alunoTreinador;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAlunoTreinador() {
        return alunoTreinador;
    }

    public void setAlunoTreinador(String alunoTreinador) {
        this.alunoTreinador = alunoTreinador;
    }

    public String getTestSenha() {
        return testSenha;
    }

    public void setTestSenha(String testSenha) {
        this.testSenha = testSenha;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
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
        final Pessoa other = (Pessoa) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
