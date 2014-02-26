/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;

import br.com.sinamodel.dao.NaoPactuadosDao;
import br.com.sinamodel.entidades.NaoPactuados;
import br.com.sinamodel.interfaces.INaoPactuadosDao;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class NaoPactuadoFacade {

    public INaoPactuadosDao dao;

    public NaoPactuadoFacade() {
        this.dao = new NaoPactuadosDao();
    }

    public int save(NaoPactuados np) {
        return dao.save(np);
    }

    public int update(NaoPactuados np) {
        return dao.update(np);
    }

    public int delete(Long id) {
        return dao.delete(id);
    }

    public List<NaoPactuados> listPrestador(String prestador) {
        return dao.listPrestador(prestador);
    }
    
    public List<NaoPactuados> listProcedimento(String procedimento) {
        return dao.listProcedimento(procedimento);
    }
    
    public List<NaoPactuados> listAnoMes(String mes,Integer ano) {
        return dao.listAnoMes(mes, ano);
    }
    
    public List<NaoPactuados> listMunicipios(String municipios) {
        return dao.listMunicipios(municipios);
    }
}
