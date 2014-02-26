/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;

import br.com.sinamodel.dao.UsuariosDAO;
import br.com.sinamodel.entidades.Usuarios;
import br.com.sinamodel.interfaces.IUsuariosDAO;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class UsuarioFacade {
    
    private IUsuariosDAO dao;

    public UsuarioFacade() {
        this.dao = new UsuariosDAO();
    }
    
    public int save(Usuarios usuarios){
        return dao.save(usuarios);
    }
    
    public int update(Usuarios usuarios){
        return dao.update(usuarios);
    }
    
    public int delete(Long id){
        return dao.delete(id);
    }
    
    public List<Usuarios> listar(){
        return dao.listar();
    }
}
