/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgejs.controle;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;

/*
 * @author Tiago
 */
@Controller
public class IndexController {

    @Path("/")
    public void index() {
    }

    @Path("/arquivo")
    public void arquivo() {
    }

    @Path("/servidores")
    public void servidores() {
    }
}
