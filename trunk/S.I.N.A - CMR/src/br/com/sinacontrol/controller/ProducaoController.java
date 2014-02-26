/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.controller;


import br.com.sinacontrol.facade.ProducaoFacade;
import br.com.sinamodel.entidades.Producao;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProducaoController {

    private ProducaoFacade facade;

    public ProducaoController() {
        this.facade = new ProducaoFacade();
    }

    public int salvarProducao(Producao producao) {
        return facade.save(producao);
    }

    public int atualizarProducao(Producao producao) {
        return facade.update(producao);
    }

    public int deletarProducao(Long id) {
        return facade.remove(id);
    }

    public List<Producao> listarProducao() {
        return facade.findAll();
    }

    public List<Producao> listarProPeriodo(Date dataInicial, Date dataFinal) {
        return facade.findProPeriodo(dataInicial, dataFinal);
    }

    public List<Producao> listarProFuncionario(String funcionario) {
        return facade.findProFuncionario(funcionario);
    }
    
     public List<Producao> listarProFunPeriodo(String funcionario, Date dataInicial, Date dataFinal) {
        return facade.findProFuncioPeriodo(funcionario, dataInicial, dataFinal);
    }
    
    public List<Producao> listarProPrestador(String prestador) {
        return facade.findProPrestador(prestador);
    }
}
