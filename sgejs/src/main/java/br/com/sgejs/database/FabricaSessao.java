/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.database;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Tiago Luiz Gomes
 */
public class FabricaSessao {

    @Produces
    @RequestScoped
    public Session criaSessao(SessionFactory factory) {System.out.println("Abrir sessao*******");return factory.openSession();}
    public void fechaSessao(@Disposes Session session) {if (session.isOpen()) { session.close();System.out.println("Fechar sessao*******");}}
}
