/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.BoletimEstatiscoEntregaFacade;
import br.com.sinamodel.entidades.BoletimEstatisticoEntrega;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatisticoEntregaController {
    
    private BoletimEstatiscoEntregaFacade facade;

    public BoletimEstatisticoEntregaController() {
        this.facade = new BoletimEstatiscoEntregaFacade();
    }
    
    public int save(BoletimEstatisticoEntrega entrega){
        return facade.save(entrega);
    }
    
    public int update(BoletimEstatisticoEntrega entrega){
        return facade.update(entrega);
    }
    
    public int delete(Long id){
        return facade.delete(id);
    }
    
    public List<BoletimEstatisticoEntrega> list(Date de, Date ate){
        return facade.list(de, ate);
    }
    
    public List<BoletimEstatisticoEntrega> list(String prestador, Date de, Date ate){
        return facade.listPeriodo(prestador, de, ate);
    }
    
    public List<BoletimEstatisticoEntrega> list(String prestador, String procedimento, Date de, Date ate){
        return facade.listPrestProcePeriodo(prestador, procedimento, de, ate);
    }
}
