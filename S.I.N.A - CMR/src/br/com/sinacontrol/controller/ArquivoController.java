/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.ArquivoFacade;
import br.com.sinamodel.entidades.Arquivo;

/**
 *
 * @author ritacosta
 */
public class ArquivoController {
    
    private ArquivoFacade facade;

    public ArquivoController() {
        this.facade = new ArquivoFacade();
    }
    
    public int salvarArquivo(Arquivo arquivo){
        return facade.save(arquivo);
    }
    
    
    public int excluirArquivo(Arquivo arquivo){
        return facade.delete(arquivo);
    }
            
}
