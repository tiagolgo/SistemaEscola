/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.dao;

import br.com.sgejs.modelo.rh.servidor.Graduacao;
import javax.inject.Inject;
import org.hibernate.Session;

/**
 *
 * @author Tiago
 */
public class DaoGraduacao extends DaoBasic<Graduacao>{

    @Inject
    public DaoGraduacao(Session s) {
        classe=Graduacao.class;
        sessao=s;
    }
    
}
