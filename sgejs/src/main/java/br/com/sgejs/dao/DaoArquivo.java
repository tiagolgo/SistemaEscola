/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import br.com.sgejs.modelo.arquivoMorto.Arquivo;
import br.com.sgejs.modelo.arquivoMorto.Curso;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

/**
 *
 * @author Tiago
 */
public class DaoArquivo extends DaoBasic<Arquivo> {

    @Inject
    public DaoArquivo(Session s) {
        sessao = s;
        classe = Arquivo.class;
    }

    public List getAnos() throws HibernateException {
        Criteria cri = sessao.createCriteria(classe);
        cri.setProjection(Projections.distinct(Projections.property("ano")));
        cri.addOrder(Order.asc("ano"));
        return cri.list();
    }

    public List getCursosDoAno(int ano) throws HibernateException {
        Criteria cri = sessao.createCriteria(classe);
        cri.add(Restrictions.eq("ano", ano));
        cri.setProjection(Projections.distinct(Projections.property("curso")));
        return cri.list();
    }

    public List getTipoDeArquivoDoAno(int ano, Curso curso) throws HibernateException {
        Criteria cri = sessao.createCriteria(classe);
        cri.add(Restrictions.eq("ano", ano)).add(Restrictions.eq("curso", curso));
        cri.setProjection(Projections.distinct(Projections.property("tipo")));
        return cri.list();
    }

    public List<Arquivo> getPropriedadesArquivo() throws HibernateException {
        Criteria cri = sessao.createCriteria(classe, "p");
        cri.addOrder(Order.asc("ano")).setProjection(
                Projections.projectionList()
                .add(Projections.property("p.id").as("id"))
                .add(Projections.property("p.ano").as("ano"))
                .add(Projections.property("p.curso").as("curso"))
                .add(Projections.property("p.tipo").as("tipo"))
                .add(Projections.property("p.caixa").as("caixa"))
                .add(Projections.property("p.criacao").as("criacao"))
        ).setResultTransformer(new AliasToBeanResultTransformer(Arquivo.class));
        return cri.list();
    }

    public boolean verificarSeExiste(Arquivo arq) throws HibernateException {
        Example exemplo = Example.create(arq);
        int size = sessao.createCriteria(classe).add(exemplo).list().size();
        return size > 0;
    }

    public List filtrar(Arquivo arq) throws HibernateException {

        Criteria cri = sessao.createCriteria(classe, "p");
        /*  Example exemplo = Example.create(arq);
        cri.add(exemplo);*/

        if (arq.getAno() > 0) {
            cri.add(Restrictions.eq("ano", arq.getAno()));
        }
        if (arq.getTipo() != null) {
            cri.add(Restrictions.eq("tipo", arq.getTipo()));
        }
        if (arq.getCurso() != null) {
            cri.add(Restrictions.eq("curso", arq.getCurso()));
        }

        cri.setProjection(
                Projections.projectionList()
                .add(Projections.property("p.id").as("id"))
                .add(Projections.property("p.ano").as("ano"))
                .add(Projections.property("p.curso").as("curso"))
                .add(Projections.property("p.tipo").as("tipo"))
                .add(Projections.property("p.caixa").as("caixa"))
                .add(Projections.property("p.criacao").as("criacao"))
        ).setResultTransformer(new AliasToBeanResultTransformer(Arquivo.class));

        return cri.list();
    }

    public List<Arquivo> getArquivosDoAluno(long id) throws HibernateException {
        Criteria criArquivo = sessao.createCriteria(classe, "p");
        criArquivo.setProjection(
                Projections.projectionList()
                .add(Projections.property("p.id").as("id"))
                .add(Projections.property("p.ano").as("ano"))
                .add(Projections.property("p.curso").as("curso"))
                .add(Projections.property("p.tipo").as("tipo"))
                .add(Projections.property("p.caixa").as("caixa"))
                .add(Projections.property("p.criacao").as("criacao"))
        ).setResultTransformer(new AliasToBeanResultTransformer(Arquivo.class));
        criArquivo.createCriteria("alunos").add(Restrictions.eq("id", id));
        return criArquivo.list();
    }

    public void atualizar(Arquivo arquivo) throws HibernateException {
        SQLQuery query = sessao.createSQLQuery("update arquivo set ano=:ano, caixa=:caixa, curso=:curso, tipo=:tipo where id=:id");
        query.setInteger("ano", arquivo.getAno()).setString("caixa", arquivo.getCaixa()).setInteger("curso", arquivo.getCurso().ordinal()).setInteger("tipo", arquivo.getTipo().ordinal()).setLong("id", arquivo.getId());
        query.executeUpdate();
    }

    public void deletar(long id) throws HibernateException {
        Arquivo arquivo = this.retorna(id);
        arquivo.getAlunos().clear();
        this.remove(arquivo);
        sessao.createSQLQuery("delete from aluno where id not in(select a.aluno_id from arquivo_alunos a)").executeUpdate();

    }
}
