/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import br.com.sgejs.modelo.arquivoMorto.Aluno;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

/**
 *
 * @author Tiago
 */
public class DaoAluno extends DaoBasic<Aluno> {

    @Inject
    public DaoAluno(Session s) {
        sessao = s;
        classe = Aluno.class;
    }

    public List<Aluno> buscaNomePorTrecho(String trecho) throws HibernateException {
        Criteria cri = sessao.createCriteria(classe);
        return cri.add(Restrictions.like("nome", trecho, MatchMode.START)).list();
    }

    public Aluno buscaAlunoPorNome(String nome) throws HibernateException {
        Criteria cri = sessao.createCriteria(classe);
        return (Aluno) cri.add(Restrictions.eq("nome", nome)).uniqueResult();
    }

    public int salvarAluno(int arquivo, Aluno aluno) throws HibernateException {
        int id = (int) persiste(aluno);
        SQLQuery query = sessao.createSQLQuery("insert into arquivo_alunos(arquivo_id,aluno_id) values(:arquivo,:aluno)");
        return query.setInteger("arquivo", arquivo).setInteger("aluno", id).executeUpdate();
    }

    public void removerAluno(int arquivo, Aluno aluno) {
        try {
            sessao.createSQLQuery("delete from arquivo_alunos where arquivo_id=:id1 and aluno_id=:id2").setInteger("id1", arquivo).setInteger("id2", aluno.getId()).executeUpdate();
            sessao.delete(aluno);
            sessao.beginTransaction().commit();
        } catch (HibernateException e) {
        }
    }

    public List listar(int id_arquivo) throws HibernateException {
        SQLQuery query = sessao.createSQLQuery("select * from aluno where id in(select aluno_id from arquivo_alunos where arquivo_id=:id)");
        return query.setResultTransformer(new AliasToBeanResultTransformer(classe)).setInteger("id", id_arquivo).list();
    }

    public void editar(Aluno aluno) throws HibernateException {
        sessao.createSQLQuery("update aluno set nome=:n where id=:id").setString("n", aluno.getNome()).setInteger("id", aluno.getId()).executeUpdate();
    }
}
