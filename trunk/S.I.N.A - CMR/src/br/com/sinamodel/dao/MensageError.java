/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import javax.swing.JOptionPane;

/**
 *
 * @author ritacosta
 */
public class MensageError {
    
    public static void errorSql(){
        JOptionPane.showMessageDialog(null, "Erro na execução do sql.\n Contate o administrador.");
    }
    
    public static void errorDriver(){
        JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o driver de conexão.\n"
                    + "Contate o administrador do sistema.");
    }
    
    public static void errorConexaoBd(){
        JOptionPane.showMessageDialog(null, "Não foi possivel conectar a base de dados ou\n"
                    + "há outro sistema aberto.Contate o administrador do sistema.");
    }
    
    public static void errorfecharConexaoBd(){
        JOptionPane.showMessageDialog(null, "Não foi possivel fechar conexão com a base de dados."
                    + "\n.Contate o administrador do sistema.");
    }
}
