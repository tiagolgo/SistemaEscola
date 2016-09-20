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
import br.com.sgejs.dao.DaoAluno;
import br.com.sgejs.modelo.arquivoMorto.Aluno;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Tiago
 */
@Controller
public class AlunoController {

    @Inject
    private Result result;
    @Inject
    private DaoAluno dao;

    @Consumes("application/json")
    @Post
    public void salvarAluno(int arquivo, Aluno aluno) {
        System.out.println("arquivo:" + arquivo);
        System.out.println("aluno:" + aluno);
        if (aluno.getId() > 0) {//editar
            this.dao.editar(aluno);
        } else { //salvar
            Aluno ret = this.dao.buscaAlunoPorNome(aluno.getNome());
            if (ret != null) {
                aluno = ret;
            }
            this.dao.salvarAluno(arquivo, aluno);
        }
        this.result.nothing();
    }

    @Consumes("application/json")
    @Post
    public void removerAluno(int arquivo, Aluno aluno) {
        this.dao.removerAluno(arquivo, aluno);
        this.result.nothing();
    }

    @Get
    public void listarAlunos(int arquivo) {
        List retorno = this.dao.listar(arquivo);
        this.result.use(Results.json()).from(retorno, "alunos").serialize();
    }

    @Consumes("application/json")
    @Post
    public void autoComplete(String nome) {
        List<Aluno> retorno = this.dao.buscaNomePorTrecho(nome);
        System.out.println("autocomplete "+retorno);
        this.result.use(Results.json()).from(retorno, "nomes").serialize();
    }
}
