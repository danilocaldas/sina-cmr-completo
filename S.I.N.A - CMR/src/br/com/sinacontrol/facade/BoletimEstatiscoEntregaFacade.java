/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;


import br.com.sinamodel.dao.BoletimEstatisticoEntregaDAO;
import br.com.sinamodel.entidades.BoletimEstatisticoEntrega;
import br.com.sinamodel.interfaces.IBoletimEstatisticoEntregaDAO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatiscoEntregaFacade {
    
    
    private IBoletimEstatisticoEntregaDAO dao;

    public BoletimEstatiscoEntregaFacade() {
        this.dao = new BoletimEstatisticoEntregaDAO();
    }
    
    public int save(BoletimEstatisticoEntrega entrega){
        return dao.save(entrega);
    }
    
    public int update(BoletimEstatisticoEntrega entrega){
        return dao.update(entrega);
    }
    
    public int delete(Long id){
        return dao.delete(id);
    }
    
    public List<BoletimEstatisticoEntrega> list(Date de, Date ate){
        return dao.list(de, ate);
    }
    
    public List<BoletimEstatisticoEntrega> listPeriodo(String prestador, Date de, Date ate){
        return dao.listPeriodo(prestador, de, ate);
    }
    
    public List<BoletimEstatisticoEntrega> listPrestProcePeriodo(String prestador, String procedimento, Date de, Date ate){
        return dao.listPrestProcePeriodo(prestador, procedimento, de, ate);
    }
    
}
