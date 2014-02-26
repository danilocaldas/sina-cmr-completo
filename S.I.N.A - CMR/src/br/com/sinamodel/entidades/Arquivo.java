/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.entidades;

/**
 *
 * @author Danilo Caldas
 */
public class Arquivo {
    private Long id;
    private Integer numero;
    private String ano;
    private String mes;
    private String cor;
    private String prestador;
    private String procedimento;
    

    public Arquivo() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer caixa) {
        this.numero = caixa;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
