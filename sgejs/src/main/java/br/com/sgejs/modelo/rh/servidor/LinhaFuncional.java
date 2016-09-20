package br.com.sgejs.modelo.rh.servidor;

import br.com.sgejs.modelo.rh.servidor.enums.Vinculo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity(name = "lf")
public class LinhaFuncional implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int codigo;
    @Enumerated(EnumType.STRING)
    private Vinculo vinculo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPosse;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public Date getDataPosse() {
        return dataPosse;
    }

    public void setDataPosse(Date dataPosse) {
        this.dataPosse = dataPosse;
    }

    @Override
    public String toString() {
        return "LinhaFuncional{" + "id=" + id + ", codigo=" + codigo + ", vinculo=" + vinculo + ", dataPosse=" + dataPosse + '}';
    }

}
