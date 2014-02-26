/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.entidades;

/**
 *
 * @author Danilo
 */
public class Prestador {
    
    private Long id;
    private String cnes;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Prestador{" + "id=" + id + ", cnes=" + cnes + ", nome=" + nome + '}';
    }
   
}
