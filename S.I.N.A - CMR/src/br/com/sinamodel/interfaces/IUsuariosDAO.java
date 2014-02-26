/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.Usuarios;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface IUsuariosDAO {
    
    int save(Usuarios usuarios);
    int update(Usuarios usuarios);
    int delete(Long id);
    List<Usuarios> listar();
}
