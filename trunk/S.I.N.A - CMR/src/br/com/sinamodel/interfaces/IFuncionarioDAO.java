/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.Funcionario;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface IFuncionarioDAO {
    
    int save(Funcionario funcionario);
    int update(Funcionario funcionario);
    int remove(Long id);
    List<Funcionario> findAll();
    List<Funcionario> findNome(String nome);
    List<Funcionario> findNomeMedico();
    List<Funcionario> findNomeDigitador();
   
}
