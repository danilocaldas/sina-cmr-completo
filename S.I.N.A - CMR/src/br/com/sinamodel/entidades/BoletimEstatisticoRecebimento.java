/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.entidades;

import java.sql.Date;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatisticoRecebimento {

    private Long id;
    private String prestador;
    private String procedimento;
    private Date diaUtil;
    private Integer qtd;

    public BoletimEstatisticoRecebimento() {
    }

    public BoletimEstatisticoRecebimento(Long id, String prestador, String procedimento, Date diaUtil, Integer qtd) {
        this.id = id;
        this.prestador = prestador;
        this.procedimento = procedimento;
        this.diaUtil = diaUtil;
        this.qtd = qtd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public Date getDiaUtil() {
        return diaUtil;
    }

    public void setDiaUtil(Date diaUtil) {
        this.diaUtil = diaUtil;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }
}
