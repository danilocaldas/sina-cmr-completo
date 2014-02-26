/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.BoletimEstatisticoRecebimento;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public interface IBoletimEstatisticoRecimentoDAO {
    
    int save(BoletimEstatisticoRecebimento be);
    int update(BoletimEstatisticoRecebimento be);
    int delete(Long id);
    List<BoletimEstatisticoRecebimento> list(Date de, Date ate);
    List<BoletimEstatisticoRecebimento> listPeriodo(String prestador, Date de, Date ate);
    List<BoletimEstatisticoRecebimento> listPrestProcePeriodo(String prestador, String procedimento, Date de, Date ate);
    
    
}
