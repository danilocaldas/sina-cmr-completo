/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;

import br.com.sinamodel.dao.BoletimEstatisticoRecebimentoDAO;
import br.com.sinamodel.entidades.BoletimEstatisticoRecebimento;
import br.com.sinamodel.interfaces.IBoletimEstatisticoRecimentoDAO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatisticoRecebimentoFacade {
    
    private IBoletimEstatisticoRecimentoDAO dao;

    public BoletimEstatisticoRecebimentoFacade() {
        this.dao = new BoletimEstatisticoRecebimentoDAO();
    }
    
    public int save(BoletimEstatisticoRecebimento be){
        return dao.save(be);
    }
    
    public int update(BoletimEstatisticoRecebimento be){
        return dao.update(be);
    }
    
    public int delete(Long id){
        return dao.delete(id);
    }
    
    public List<BoletimEstatisticoRecebimento> list(Date de, Date ate){
        return dao.list(de, ate);
    }
    
    public List<BoletimEstatisticoRecebimento> listPrestadorPeriodo(String prestador, Date de, Date ate){
        return dao.listPeriodo(prestador, de, ate);
    }
    
    public List<BoletimEstatisticoRecebimento> listPrestadorProcePeriodo(String prestador, String procedimento, Date de, Date ate){
        return dao.listPrestProcePeriodo(prestador, procedimento, de, ate);
    }
    
}
