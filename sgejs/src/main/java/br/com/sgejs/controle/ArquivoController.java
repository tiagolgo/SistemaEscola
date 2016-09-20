/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.controle;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.sgejs.dao.DaoArquivo;
import br.com.sgejs.modelo.arquivoMorto.Arquivo;
import br.com.sgejs.modelo.arquivoMorto.Curso;
import br.com.sgejs.modelo.arquivoMorto.TipoArquivo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.HibernateException;

/*
 * @author Tiago
 */
@Controller
public class ArquivoController {

    @Inject
    private Result result;
    @Inject
    private DaoArquivo dao;

    @Consumes("application/json")
    @Post
    public void salvarArquivo(Arquivo arquivo) {
        arquivo.setCriacao(new Date());
        this.dao.persiste(arquivo);
        this.result.nothing();
    }

    @Consumes("application/json")
    @Post
    public void removerArquivo(long id) {
        this.dao.deletar(id);
        this.result.nothing();
    }

    @Consumes("application/json")
    @Post
    public void verificarDuplicidade(Arquivo arquivo) {
        System.out.println(arquivo);
        arquivo.setCaixa(null);
        boolean existe = this.dao.verificarSeExiste(arquivo);
        System.out.println("existe? "+existe);
        this.result.use(Results.json()).from(existe, "existe").serialize();
    }

    @Get
    public void getCursoTipo() {
        List values = new ArrayList();
        values.add(TipoArquivo.values());
        values.add(Curso.values());
        this.result.use(Results.json()).from(values, "dados").serialize();
    }

    @Get
    public void listarArquivos() {
        List<Arquivo> retorno = this.dao.getPropriedadesArquivo();
        this.result.use(Results.json()).from(retorno, "arquivo").serialize();
    }

    @Get
    public void getArquivo(int id) {
        Arquivo arquivo = this.dao.retorna(id);
        this.result.use(Results.json()).from(arquivo, "arquivo").include("alunos").serialize();
    }

    @Consumes("application/json")
    @Post
    public void getArquivoQueAlunoEsta(long id) {
        List<Arquivo> retorno = this.dao.getArquivosDoAluno(id);
        this.result.use(Results.json()).from(retorno, "arquivos").serialize();
    }

    @Consumes("application/json")
    @Post
    public void filtrar(Arquivo arq) {
        System.out.println("buscar "+arq);
        List<Arquivo> retorno = this.dao.filtrar(arq);
        this.result.use(Results.json()).from(retorno, "arquivos").serialize();
    }

}
