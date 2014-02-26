/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.entidades;

/**
 *
 * @author ritacosta
 */
public class Usuarios {
    private long id;
    private String login;
    private String senha;
    private String role_user;

    public Usuarios() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }
    
}
