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
import br.com.sgejs.dao.DaoServidor;
import br.com.sgejs.modelo.rh.servidor.Servidor;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Tiago
 */
@Controller
public class RhController {

    @Inject
    Result result;
    @Inject
    DaoServidor dao;

    @Consumes("application/json")
    @Post
    public void salvar(Servidor servidor) {
        System.out.println("salvar");
        this.dao.persiste(servidor);
        System.out.println(servidor);
        this.result.nothing();
    }

    @Consumes("application/json")
    @Post
    public void remover(long id) {
        Servidor servidor = this.dao.retorna(id);
        this.dao.remove(servidor);
        this.result.nothing();
    }

    @Get
    public void listar(Servidor servidor) {
        List<Servidor> lista = this.dao.listPropriedades();
        this.result.use(Results.json()).from(lista, "lista").include("contato").serialize();
    }

    @Consumes("application/json")
    @Post
    public void setFiliacao(Servidor servidor) {
        System.out.println("*** JSON ***");
        System.out.println(servidor);
        this.result.nothing();
    }

    @Consumes("application/json")
    @Post
    public void verificarNome(String nome) {
        System.out.println("nome: " + nome);
        boolean retorno = this.dao.seExiste(nome.toLowerCase());
        this.result.use(Results.json()).from(retorno, "existe").serialize();
    }

    @Consumes("application/json")
    @Post
    public void visualizar(long id) {
        Servidor retorno = this.dao.retorna(id);
        this.result.use(Results.json()).from(retorno, "servidor").recursive().serialize();
    }

}
