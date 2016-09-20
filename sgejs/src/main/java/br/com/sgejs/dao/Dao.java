/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Tiagolgo
 * @param <T>
 */
public interface Dao<T> {

    boolean remove(T objeto) throws HibernateException;

    Serializable persiste(T o) throws HibernateException;

    void update(T o) throws HibernateException;

    List<T> listar() throws HibernateException;

    T retorna(long id) throws HibernateException;
}
