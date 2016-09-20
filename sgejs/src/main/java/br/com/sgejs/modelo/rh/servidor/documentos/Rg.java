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

@Entity(name = "identidade")
public class Rg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date emissao;
    private String pais, emissor;
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

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Rg{" + "id=" + id + ", numero=" + numero + ", emissao=" + emissao + ", pais=" + pais + ", emissor=" + emissor + ", uf=" + uf + '}';
    }

}
