package br.com.sgejs.modelo.rh.servidor.documentos;

import br.com.sgejs.modelo.rh.servidor.enums.Uf;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity(name = "titulo")
public class Titulo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long numero;
    private int zona, secao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date emissao;
    private String pais;
    @Enumerated(EnumType.STRING)
    private Uf uf;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf estado) {
        this.uf = estado;
    }

    @Override
    public String toString() {
        return "Titulo{" + "id=" + id + ", numero=" + numero + ", zona=" + zona + ", secao=" + secao + ", emissao=" + emissao + ", pais=" + pais + ", uf=" + uf + '}';
    }

}
