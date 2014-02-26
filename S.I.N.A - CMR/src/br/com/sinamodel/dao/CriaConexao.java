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
public class CriaConexao {
    static String DRIVER_HSQLDB = "org.hsqldb.jdbcDriver";
    static String USER = "SA";
    static String PASSWORD_H2 = "12345";

    public static Connection getConexao() throws SQLException {

        System.out.println("Conectando ao banco!");
        try {
            String path = System.getProperty("user.dir") + "\\bd\\sna";
            Class.forName(DRIVER_HSQLDB);
            return DriverManager.getConnection("jdbc:hsqldb:file:"+path, USER, PASSWORD_H2);
        } catch (ClassNotFoundException ex) {
            MensageError.errorDriver();
            ex.printStackTrace();
        } catch (SQLException ex) {
            MensageError.errorConexaoBd();
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
            MensageError.errorfecharConexaoBd();
        }
        System.out.println("Desconectando ao banco!");

    }
    
//    public static void main(String[]args){
//        try {
//            CriaConexao.createTableUsuarios();
//            CriaConexao.insertUser();
//        } catch (SQLException ex) {
//            Logger.getLogger(CriaConexao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }

    public static void insertUser() throws SQLException {
        Connection connection = getConexao();
        PreparedStatement stmt = null;
        String sql = "insert into usuarios (login, senha, role_user) values('root','root','ATIVO')";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Usuário Inserido!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, stmt, null);
        }
    }

    public static void createTableProcedimento() throws SQLException {
        Connection connection = getConexao();
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

    public static void createTablePrestador() throws SQLException {
        Connection connection = getConexao();
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

    public static void createTableFuncionario() throws SQLException {
        Connection connection = getConexao();
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

    public static void createTableProducao() throws SQLException {
        Connection connection = getConexao();
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

    public static void createTableProducaoMedica() throws SQLException {
        Connection connection = getConexao();
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

    public static void createTableUsuarios() throws SQLException {
        Connection connection = getConexao();
        PreparedStatement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                + "  ID bigint(20) NOT NULL AUTO_INCREMENT,\n"
                + "  login VARCHAR(50) NOT NULL,\n"
                + "  senha VARCHAR(50) NOT NULL,\n"
                + "  role_user VARCHAR(50) NOT NULL,\n"
                + "PRIMARY KEY (ID)\n"
                + ");";
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
