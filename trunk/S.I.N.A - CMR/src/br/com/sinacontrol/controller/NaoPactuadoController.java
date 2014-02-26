/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.NaoPactuadoFacade;
import br.com.sinamodel.entidades.NaoPactuados;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class NaoPactuadoController {

    public  NaoPactuadoFacade facade;

    public NaoPactuadoController() {
        this.facade = new NaoPactuadoFacade();
    }

    public int save(NaoPactuados np) {
        return facade.save(np);
    }

    public int update(NaoPactuados np) {
        return facade.update(np);
    }

    public int delete(Long id) {
        return facade.delete(id);
    }

    public List<NaoPactuados> listPrestador(String prestador) {
        return facade.listPrestador(prestador);
    }
    public List<NaoPactuados> listProcedimento(String procedimento) {
        return facade.listProcedimento(procedimento);
    }
    public List<NaoPactuados> listAnoMes(String mes, Integer ano) {
        return facade.listAnoMes(mes, ano);
    }
    public List<NaoPactuados> listMunicipios(String municipios) {
        return facade.listMunicipios(municipios);
    }
    
    
}
