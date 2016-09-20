/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.modelo.rh.servidor;


import br.com.sgejs.modelo.rh.servidor.enums.EstadoCivil;
import br.com.sgejs.modelo.rh.servidor.enums.Dependencia;
import br.com.sgejs.modelo.rh.servidor.enums.SituacaoFinanceira;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Tiago
 */
@Entity(name = "dependente")
public class Dependente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private long rg;
    @Enumerated(EnumType.ORDINAL)
    private Dependencia dependencia;
    @Enumerated(EnumType.ORDINAL)
    private EstadoCivil estadoCivil;
    @Enumerated(EnumType.ORDINAL)
    private SituacaoFinanceira situacaoFinanceira;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public SituacaoFinanceira getSituacaoFinanceira() {
        return situacaoFinanceira;
    }

    public void setSituacaoFinanceira(SituacaoFinanceira situacaoFinanceira) {
        this.situacaoFinanceira = situacaoFinanceira;
    }

    @Override
    public String toString() {
        return "Dependente{" + "id=" + id + ", nome=" + nome + ", rg=" + rg + ", dependencia=" + dependencia + ", estadoCivil=" + estadoCivil + ", situacaoFinanceira=" + situacaoFinanceira + '}';
    }

}
