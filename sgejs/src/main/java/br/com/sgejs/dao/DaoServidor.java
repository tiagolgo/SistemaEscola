/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import br.com.sgejs.modelo.rh.servidor.Graduacao;
import br.com.sgejs.modelo.rh.servidor.Servidor;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

/**
 *
 * @author Tiagolgo
 */
public class DaoServidor extends DaoBasic<Servidor> {

    @Inject
    public DaoServidor(Session s) {
        classe = Servidor.class;
        sessao = s;
    }

    public List<Servidor> listPropriedades() {
        Criteria cri = sessao.createCriteria(classe, "s");
        cri.addOrder(Order.asc("nome")).setProjection(
                Projections.projectionList()
                .add(Projections.property("s.id").as("id"))
                .add(Projections.property("s.nome").as("nome"))
                .add(Projections.property("s.sexo").as("sexo"))
                .add(Projections.property("s.contato").as("contato"))
        ).setResultTransformer(new AliasToBeanResultTransformer(Servidor.class));
        return cri.list();
    }

    public Servidor getEscolaridade(Long id) {
        Criteria cri = sessao.createCriteria(classe, "s");
        cri.add(Restrictions.eq("s.id", id));
        cri.setProjection(
                Projections.projectionList()
                .add(Projections.property("s.nome").as("nome"))
                .add(Projections.property("s.instrucao").as("instrucao"))
                .add(Projections.property("s.posGraduacao").as("posGraduacao")))
                .setResultTransformer(new AliasToBeanResultTransformer(Servidor.class));
        Servidor u = (Servidor) cri.uniqueResult();

        SQLQuery q1 = sessao.createSQLQuery("select * from graduacao where servidor_id=" + id).addEntity(Graduacao.class);
        List list = q1.list();
        System.out.println("Graduacoes: " + list);
        u.setGraduacoes(list);
        return u;
    }

    public Servidor getInformacoesBasicas(long id) {
        Criteria cri = sessao.createCriteria(classe, "s");
        cri.add(Restrictions.eq("s.id", id));
        cri.setProjection(
                Projections.projectionList()
                .add(Projections.property("s.nome").as("nome"))
                .add(Projections.property("s.nascimento").as("nascimento"))
                .add(Projections.property("s.cidadeNascimento").as("cidadeNascimento"))
                .add(Projections.property("s.ufNascimento").as("ufNascimento"))
                .add(Projections.property("s.sexo").as("sexo"))
                .add(Projections.property("s.raca").as("raca"))
                .add(Projections.property("s.grupoSanguineo").as("grupoSanguineo"))
                .add(Projections.property("s.estadoCivil").as("estadoCivil"))
                .add(Projections.property("s.filiacao").as("filiacao")))
                .setResultTransformer(new AliasToBeanResultTransformer(Servidor.class)
                );
        return (Servidor) cri.uniqueResult();
    }

    public Servidor getDocumentos(long id) throws HibernateException {
        Criteria cri = sessao.createCriteria(classe, "s");
        cri.add(Restrictions.eq("s.id", id));
        cri.setProjection(
                Projections.projectionList()
                .add(Projections.property("s.nome").as("nome"))
                .add(Projections.property("s.documento").as("documento"))
        ).setResultTransformer(new AliasToBeanResultTransformer(classe));
        return (Servidor) cri.uniqueResult();
    }

    public boolean seExiste(String nome) throws HibernateException {
        SQLQuery query = sessao.createSQLQuery("select id from servidor where nome = :n");
        query.setString("n", nome);
        if (query.list().size() > 0) {
            return true;
        }
        return false;
    }
}
