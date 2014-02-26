/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.ProducaoMedicaFacade;
import br.com.sinamodel.entidades.ProducaoMedica;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaController {
    
    private ProducaoMedicaFacade facade;
    
    public ProducaoMedicaController() {
        this.facade = new ProducaoMedicaFacade();
    }
    
    public int salvarProducaoMedica(ProducaoMedica pMedica) {
        return facade.save(pMedica);
    }

    public int atualizarProducaoMedica(ProducaoMedica pMedica) {
        return facade.update(pMedica);
    }

    public int deletarProducao(Long id) {
        return facade.remove(id);
    }

    public List<ProducaoMedica> listarProducao(Date dataDe, Date dataAte) {
        return facade.findAll(dataDe, dataAte);
    }
    
    public List<ProducaoMedica> listarProducaoFunMedico(String nome, Date dataDe, Date dataAte) {
        return facade.findAllPeriodo(nome, dataDe, dataAte);
    }
}
