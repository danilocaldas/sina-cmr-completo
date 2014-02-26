/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.BoletimEstatisticoRecebimentoFacade;
import br.com.sinamodel.entidades.BoletimEstatisticoRecebimento;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatisticoRecebimentoController {

    private BoletimEstatisticoRecebimentoFacade facade;

    public BoletimEstatisticoRecebimentoController() {
        this.facade = new BoletimEstatisticoRecebimentoFacade();
    }

    public int save(BoletimEstatisticoRecebimento be) {
        return facade.save(be);
    }

    public int update(BoletimEstatisticoRecebimento be) {
        return facade.update(be);
    }

    public int delete(Long id) {
        return facade.delete(id);
    }

    public List<BoletimEstatisticoRecebimento> list(Date de, Date ate) {
        return facade.list(de, ate);
    }
   
    public List<BoletimEstatisticoRecebimento> listPrestadorPeriodo(String prestador, Date de, Date ate){
        return facade.listPrestadorPeriodo(prestador, de, ate);
    }
    
    public List<BoletimEstatisticoRecebimento> listPrestadorProcePeriodo(String prestador, String procedimento, Date de, Date ate){
        return facade.listPrestadorProcePeriodo(prestador, procedimento, de, ate);
    }
}
