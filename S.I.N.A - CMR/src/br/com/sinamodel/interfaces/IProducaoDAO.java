/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.Producao;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface IProducaoDAO {
    
    int save(Producao producao);
    int update(Producao producao);
    int excluir(Long id);
    List<Producao> findAll();
    List<Producao> findProPeriodo(Date dataInicial, Date dataFinal);
    List<Producao> findProFuncionario(String funcionario);
    List<Producao> findProFuncioPeriodo(String funcionario, Date dataInicial, Date dataFinal);
    List<Producao> findProPrestador(String prestador);
    
    
}
