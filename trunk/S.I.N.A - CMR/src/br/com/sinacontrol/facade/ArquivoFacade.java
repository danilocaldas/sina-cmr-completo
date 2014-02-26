/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;


import br.com.sinamodel.dao.ArquivoDAO;
import br.com.sinamodel.entidades.Arquivo;
import br.com.sinamodel.interfaces.IArquivoDAO;

/**
 *
 * @author ritacosta
 */
public class ArquivoFacade {

    private IArquivoDAO dao;

    public ArquivoFacade() {
        this.dao = new ArquivoDAO();
    }

    public int save(Arquivo arquivo) {
        return dao.save(arquivo);
    }

    public int delete(Arquivo arquivo) {
        return dao.delete(arquivo);
    }
}
