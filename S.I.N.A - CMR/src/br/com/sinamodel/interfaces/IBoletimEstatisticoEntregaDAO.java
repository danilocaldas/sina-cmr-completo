/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.BoletimEstatisticoEntrega;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public interface IBoletimEstatisticoEntregaDAO {
    
    int save(BoletimEstatisticoEntrega entrega );
    
    int update(BoletimEstatisticoEntrega entrega);
    
    int delete(Long id);
    
    List<BoletimEstatisticoEntrega> list(Date de, Date ate);
    
    List<BoletimEstatisticoEntrega> listPeriodo(String prestador, Date de, Date ate);

    List<BoletimEstatisticoEntrega> listPrestProcePeriodo(String prestador, String procedimento, Date de, Date ate);
    
}
