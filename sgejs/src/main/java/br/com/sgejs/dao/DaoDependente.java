/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import br.com.sgejs.modelo.rh.servidor.Dependente;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Tiagolgo
 */
public class DaoDependente extends DaoBasic<Dependente> {

    @Inject
    public DaoDependente(Session s) {
        sessao = s;
        classe = Dependente.class;
    }

    public List<Dependente> listDependentes(long id) {
        SQLQuery q = sessao.createSQLQuery("select * from Dependente d where d.id in (select sd.aluno_id from servidor_dependente sd where sd.servidor_id=:id)").addEntity(classe);
        q.setParameter("id", id);
        return q.list();
    }

    public boolean deleterDependente(Dependente d) {
        try {
            SQLQuery q1 = sessao.createSQLQuery("delete from servidor_dependente where aluno_id=:id");
            q1.setParameter("id", d.getId());
            q1.executeUpdate();
            return remove(d);
        } catch (HibernateException e) {
            System.out.println("causa: " + e.getCause());
            return false;
        }
    }

    public boolean insereDependente(long idServidor, Dependente d) {
        try {
            long id = (long) persiste(d);     
            SQLQuery query = sessao.createSQLQuery("insert into servidor_dependente values(" + idServidor + "," + id + ")");
            query.executeUpdate();
            return true;
        } catch (HibernateException e) {
            System.out.println("Causa: " + e.getCause());
            e.printStackTrace();
            return false;
        }
    }
}
