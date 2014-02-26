/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinacontrol.facade;

import br.com.sinamodel.dao.ProducaoDAO;
import br.com.sinamodel.entidades.Producao;
import br.com.sinamodel.interfaces.IProducaoDAO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class ProducaoFacade {
    
    private IProducaoDAO dao;

    public ProducaoFacade() {
        this.dao = new ProducaoDAO();
    }
    
    public int save(Producao producao){
        return dao.save(producao);
    }
    
    public int update(Producao producao){
        return dao.update(producao);
    }
    
    public int remove(Long id){
        return dao.excluir(id);
    }
    public List<Producao> findAll(){
        return dao.findAll();
    }
    
    public List<Producao> findProPeriodo(Date dataInicial, Date dataFinal){
        return dao.findProPeriodo(dataInicial, dataFinal);
    }
    
    public List<Producao> findProFuncionario(String funcionario){
        return  dao.findProFuncionario(funcionario);
    }
    
    public List<Producao> findProFuncioPeriodo(String funcionario, Date dataInicial, Date dataFinal){
        return dao.findProFuncioPeriodo(funcionario, dataInicial, dataFinal);
    }
    
    public List<Producao> findProPrestador(String prestador){
        return dao.findProPrestador(prestador);
    }
}
