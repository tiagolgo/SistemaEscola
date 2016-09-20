package br.com.sgejs.modelo.rh.servidor.documentos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity(name = "pis")
public class Pis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inclusao;
    private String banco;

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

    public Date getInclusao() {
        return inclusao;
    }

    public void setInclusao(Date inclusao) {
        this.inclusao = inclusao;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Pis{" + "id=" + id + ", numero=" + numero + ", inclusao=" + inclusao + ", banco=" + banco + '}';
    }

}
