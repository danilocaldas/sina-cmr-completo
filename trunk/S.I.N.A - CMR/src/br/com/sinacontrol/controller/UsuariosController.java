/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.UsuarioFacade;
import br.com.sinamodel.entidades.Usuarios;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class UsuariosController {

    private UsuarioFacade facade;

    public UsuariosController() {
        this.facade = new UsuarioFacade();
    }

    public int save(Usuarios usuarios) {
        return facade.save(usuarios);
    }

    public int update(Usuarios usuarios) {
        return facade.update(usuarios);
    }

    public int delete(Long id) {
        return facade.delete(id);
    }
    
    public List<Usuarios> listar(){
        return facade.listar();
    }
}
