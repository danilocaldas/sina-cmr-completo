/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;


import br.com.sinamodel.dao.ProducaoMedicaoDAO;
import br.com.sinamodel.entidades.ProducaoMedica;
import br.com.sinamodel.interfaces.IProducaoMedicaDAO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaFacade {
    
    private IProducaoMedicaDAO dao;

    public ProducaoMedicaFacade() {
        this.dao = new ProducaoMedicaoDAO();
    }
    
     public int save(ProducaoMedica pMedica){
        return dao.save(pMedica);
    }
    
    public int update(ProducaoMedica pMedica){
        return dao.updade(pMedica);
    }
    
    public int remove(Long id){
        return dao.delete(id);
    }
    public List<ProducaoMedica> findAll(Date dataDe, Date dataAte){
        return dao.listar(dataDe, dataAte);
    }
    public List<ProducaoMedica> findAllPeriodo(String nome, Date dataDe, Date dataAte){
        return dao.listarProMedica(nome, dataDe, dataAte);
    }
}
