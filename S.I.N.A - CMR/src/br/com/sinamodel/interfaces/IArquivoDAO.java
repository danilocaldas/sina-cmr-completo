/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.Arquivo;

/**
 *
 * @author ritacosta
 */
public interface IArquivoDAO {
    
    int save (Arquivo arquivo);
    int delete(Arquivo arquivo);
    
}
