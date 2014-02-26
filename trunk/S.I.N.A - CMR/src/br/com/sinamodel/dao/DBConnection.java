/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Danilo
 */
public class DBConnection {

    private static final String URL_MYSQL = "jdbc:mysql://localhost/sna_teste";
    private static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
//    private static final String PASS = "12345";
    private static final String PASS = "root";

    public static Connection getConnection() {
        System.out.println("Conectando ao banco!");
        try {
            Class.forName(DRIVER_CLASS_MYSQL);
            return DriverManager.getConnection(URL_MYSQL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public static void close(Connection conn, PreparedStatement pstm, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void createTableProcedimento() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS procedimento (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  codigo VARCHAR(50) NOT NULL,\n"
                + "  nome VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Procedimento Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }

    public static void createTablePrestador() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS prestador (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  cnes VARCHAR(50) NOT NULL,\n"
                + "  nome VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table Prestador Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }

    public static void createTableFuncionario() {
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS funcionario (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  nome VARCHAR(50) NOT NULL,\n"
                + "  sobrenome VARCHAR(50) NOT NULL,\n"
                + "  cargo VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Funcionário Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
    
    public static void createTableProducao(){
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS producao (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  funcionario VARCHAR(50) NOT NULL,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  data VARCHAR(50) NOT NULL,\n"
                + "  data_entrada VARCHAR(50) NOT NULL,\n"
                + "  data_digitacao VARCHAR(50) NOT NULL,\n"
                + "  quantidade VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Produção Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
    
    public static void createTableProducaoMedica(){
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS producao_medica (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  data_entrada_cmr DATE NOT NULL,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  quantidade_laudos int NOT NULL,\n"
                + "  data_analise DATE NOT NULL,\n"
                + "  funcionario VARCHAR(50) NOT NULL,\n"
                + "  data_encaminhamento DATE NOT NULL,\n"
                + "  nucleos VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables ProduçãoMedica Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
    
     public static void createTableUsuarios(){
        Connection connection = getConnection();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  login VARCHAR(50) NOT NULL,\n"
                + "  senha VARCHAR(50) NOT NULL,\n"
                + "  role_user VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ")";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Tables Usuarios Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }
}
