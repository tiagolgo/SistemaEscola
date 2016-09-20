/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.modelo.arquivoMorto;

/**
 *
 * @author Tiago
 */
public enum Curso {
    Fundamental_Anos_Iniciais, Fundamental_Anos_Finais, Ensino_Médio, Supletivo, Correção_de_Fluxo, Técnico_em_Contabilidade, Educação_Geral;

    @Override
    public String toString() {
        return this.name().replaceAll("_", " ");
    }

}
