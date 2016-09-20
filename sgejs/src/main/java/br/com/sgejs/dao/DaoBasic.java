/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tiagolgo
 * @param <T>
 */
public class DaoBasic<T> implements Dao<T> {

    protected  Class classe;
    protected  Session sessao;

    @Override
    public boolean remove(T o) throws HibernateException {
        Transaction tr = null;
        try {
            tr = sessao.beginTransaction();
            sessao.delete(o);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

    @Override
    public Serializable persiste(T o) throws HibernateException {
        Transaction tr = null;
        try {
            tr = sessao.beginTransaction();
            sessao.saveOrUpdate(o);
            tr.commit();
            return sessao.getIdentifier(o);
        } catch (Exception e) {
            System.out.println("Causa: " + e.getCause());
            e.printStackTrace();
            tr.rollback();
            return null;
        }
    }

    @Override
    public List<T> listar() throws HibernateException {
        Criteria c = sessao.createCriteria(classe);
        List list = c.list();
        sessao.flush();
        return list;
    }

    @Override
    public T retorna(long id) throws HibernateException {
        Criteria c = sessao.createCriteria(classe);
        return (T) c.add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void update(T o) throws HibernateException {
        sessao.update(o);
        sessao.beginTransaction().commit();
    }

    public void fechaSesao() {
        if (sessao.isOpen()) {
            sessao.close();
        }
    }

    public Serializable persiste2(T o) throws HibernateException {
        Transaction tr = null;
        try {
            tr = sessao.beginTransaction();
            sessao.persist(o);
            tr.commit();
            return sessao.getIdentifier(o);
        } catch (Exception e) {
            System.out.println("Causa: " + e.getCause());
            e.printStackTrace();
            tr.rollback();
            return null;
        }
    }
}
