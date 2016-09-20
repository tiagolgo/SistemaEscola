    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.database;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Tiago Luiz Gomes
 */
@ApplicationScoped
public class ConexaoBanco {

    private SessionFactory factory = null;

    @Produces
    @ApplicationScoped
    public SessionFactory ConexaoBanco(){
        if (this.factory==null) {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            this.factory = configuration.buildSessionFactory(serviceRegistry);
        }
        return this.factory;
    }

    public void fechaConexao(@Disposes SessionFactory factory){
        factory.close();
    }
}
