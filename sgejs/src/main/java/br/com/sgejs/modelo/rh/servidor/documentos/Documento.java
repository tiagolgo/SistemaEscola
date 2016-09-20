/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.modelo.rh.servidor.documentos;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Tiagolgo
 */
@Entity(name = "documento")
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Rg identidade;
    @OneToOne(cascade = CascadeType.ALL)
    private Pis pis;
    @OneToOne(cascade = CascadeType.ALL)
    private Titulo titulo;
    @OneToOne(cascade = CascadeType.ALL)
    private Reservista reservista;
    @OneToOne(cascade = CascadeType.ALL)
    private Ctps ctps;
    @OneToOne(cascade = CascadeType.ALL)
    private Cnh cnh;

    private long cpf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rg getIdentidade() {
        return identidade;
    }

    public void setIdentidade(Rg identidade) {
        this.identidade = identidade;
    }

    public Pis getPis() {
        return pis;
    }

    public void setPis(Pis pis) {
        this.pis = pis;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

    public Reservista getReservista() {
        return reservista;
    }

    public void setReservista(Reservista reservista) {
        this.reservista = reservista;
    }

    public Ctps getCtps() {
        return ctps;
    }

    public void setCtps(Ctps ctps) {
        this.ctps = ctps;
    }

    public Cnh getCnh() {
        return cnh;
    }

    public void setCnh(Cnh cnh) {
        this.cnh = cnh;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Documento{" + "id=" + id + ", identidade=" + identidade + ", pis=" + pis + ", titulo=" + titulo + ", reservista=" + reservista + ", ctps=" + ctps + ", cnh=" + cnh + ", cpf=" + cpf + '}';
    }
    
}
