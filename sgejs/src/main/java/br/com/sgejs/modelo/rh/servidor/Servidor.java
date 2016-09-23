package br.com.sgejs.modelo.rh.servidor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import br.com.sgejs.modelo.rh.servidor.documentos.Documento;
import br.com.sgejs.modelo.rh.servidor.enums.EstadoCivil;
import br.com.sgejs.modelo.rh.servidor.enums.Instrucao;
import br.com.sgejs.modelo.rh.servidor.enums.PosGraduacao;
import br.com.sgejs.modelo.rh.servidor.enums.Raca;
import br.com.sgejs.modelo.rh.servidor.enums.Sexo;
import br.com.sgejs.modelo.rh.servidor.enums.Uf;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "servidor")
public class Servidor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date nascimento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Raca raca;
   
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Uf ufNascimento;
   
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private EstadoCivil estadoCivil;
    
    @Enumerated(EnumType.ORDINAL)
    private Instrucao instrucao;
    
    @Enumerated(EnumType.ORDINAL)
    private PosGraduacao posGraduacao;
   
    @Column(nullable = false)
    private String nome, cidadeNascimento, grupoSanguineo;
       
    @OneToMany(cascade = CascadeType.ALL)
    private List<Funcao> funcoes = new ArrayList<>();
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<Dependente> dependentes = new ArrayList<>();
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<Graduacao> graduacoes = new ArrayList<>();
   
    @OneToOne(cascade = CascadeType.ALL)
    private Documento documento;
   
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Contato contato;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Filiacao filiacao;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ContaCorrente cc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Uf getUfNascimento() {
        return ufNascimento;
    }

    public void setUfNascimento(Uf ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Instrucao getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(Instrucao instrucao) {
        this.instrucao = instrucao;
    }

    public PosGraduacao getPosGraduacao() {
        return posGraduacao;
    }

    public void setPosGraduacao(PosGraduacao posGraduacao) {
        this.posGraduacao = posGraduacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public List<Funcao> getFuncoes() {
        return funcoes;
    }

    public void setFuncoes(List<Funcao> funcoes) {
        this.funcoes = funcoes;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    public List<Graduacao> getGraduacoes() {
        return graduacoes;
    }

    public void setGraduacoes(List<Graduacao> graduacoes) {
        this.graduacoes = graduacoes;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Filiacao getFiliacao() {
        return filiacao;
    }

    public void setFiliacao(Filiacao filiacao) {
        this.filiacao = filiacao;
    }

    public ContaCorrente getCc() {
        return cc;
    }

    public void setCc(ContaCorrente cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Servidor{" + "id=" + id + ", nascimento=" + nascimento + ", sexo=" + sexo + ", raca=" + raca + ", ufNascimento=" + ufNascimento + ", estadoCivil=" + estadoCivil + ", instrucao=" + instrucao + ", posGraduacao=" + posGraduacao + ", nome=" + nome + ", cidadeNascimento=" + cidadeNascimento + ", grupoSanguineo=" + grupoSanguineo + ", linhaFuncional=" + funcoes + ", dependentes=" + dependentes + ", graduacoes=" + graduacoes + ", documento=" + documento + ", endereco=" + endereco + ", contato=" + contato + ", filiacao=" + filiacao + ", contaCorrente=" + cc + '}';
    }
}
