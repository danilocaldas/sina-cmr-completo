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
public class ProducaoMedica {
    
    private long id;
    private String funcionario;
    private String procedimento;
    private String prestador;
    private String nucleos;
    private int quantidade;
    private Date entradaCmr;
    private Date analise;
    private Date encaminhamento;

    public ProducaoMedica() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public String getNucleos() {
        return nucleos;
    }

    public void setNucleos(String nucleos) {
        this.nucleos = nucleos;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getEntradaCmr() {
        return entradaCmr;
    }

    public void setEntradaCmr(Date entradaCmr) {
        this.entradaCmr = entradaCmr;
    }

    public Date getAnalise() {
        return analise;
    }

    public void setAnalise(Date analise) {
        this.analise = analise;
    }

    public Date getEncaminhamento() {
        return encaminhamento;
    }

    public void setEncaminhamento(Date encaminhamento) {
        this.encaminhamento = encaminhamento;
    }
    
    
    
}
