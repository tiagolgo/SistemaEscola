/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.modelo.arquivoMorto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Tiago
 */
@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int ano;
    private String caixa;
    @Enumerated(EnumType.ORDINAL)
    private TipoArquivo tipo;
    @Enumerated(EnumType.ORDINAL)
    private Curso curso;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date criacao;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "arquivo_alunos", joinColumns = {
                @JoinColumn(name = "arquivo_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "aluno_id")
            }
    )
    private List<Aluno> alunos = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public TipoArquivo getTipo() {
        return tipo;
    }

    public void setTipo(TipoArquivo tipo) {
        this.tipo = tipo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public void setAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    @Override
    public String toString() {
        return "Arquivo{" + "id=" + id + ", ano=" + ano + ", caixa=" + caixa + ", tipo=" + tipo + ", curso=" + curso + ", alunos=" + alunos + '}';
    }

}
