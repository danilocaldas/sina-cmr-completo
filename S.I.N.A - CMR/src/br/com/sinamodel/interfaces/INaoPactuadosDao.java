/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.interfaces;


import br.com.sinamodel.entidades.NaoPactuados;
import java.util.List;

/**
 *
 * @author Danilo
 */
public interface INaoPactuadosDao {

    int save(NaoPactuados np);

    int update(NaoPactuados np);

    int delete(Long id);

    List<NaoPactuados> listPrestador(String prestador);

    List<NaoPactuados> listProcedimento(String procedimento);

    List<NaoPactuados> listAnoMes(String mes, Integer ano);

    List<NaoPactuados> listMunicipios(String municipios);

}
