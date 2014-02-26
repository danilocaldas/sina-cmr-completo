/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sinamodel.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Danilo
 */
public class CriaTabelas {
    
    public static void main(String[] args){
        createTableNaoPactuados();
    }
    
    public static void createTableUsuarios() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE CACHED TABLE IF NOT EXISTS usuarios (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  login VARCHAR(50) NOT NULL,\n"
                + "  senha VARCHAR(50) NOT NULL,\n"
                + "  role_user VARCHAR(10) NOT NULL)";           
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table Usuários Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CriaConexao.close(connection, stmt, null);
        }
    }
    
    public static void createTableProcedimento() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS procedimento (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  codigo VARCHAR(50) NULL,\n"
                + "  nome VARCHAR(50) NOT NULL)";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table Procedimento Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
    
    public static void createTablePrestador() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS prestador (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  cnes VARCHAR(50) NULL,\n"
                + "  nome VARCHAR(50) NOT NULL)";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table Prestador Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
    
    public static void createTableFuncionario() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS funcionario (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  nome VARCHAR(50) NOT NULL,\n"
                + "  sobrenome VARCHAR(50) NULL,\n"
                + "  cargo VARCHAR(50) NOT NULL)";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table funcionario Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
    
    public static void createTableProducaoMedica() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS producao_medica (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  data_entrada_cmr DATE NOT NULL,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  quantidade_laudos INT NOT NULL,\n"
                + "  data_analise DATE NOT NULL,\n"
                + "  funcionario VARCHAR(50) NOT NULL,\n"
                + "  data_encaminhamento DATE NOT NULL,\n"
                + "  nucleos VARCHAR(10) NOT NULL);";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table produção médica Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
    
     public static void createTableProducaoDigitadores() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS producao (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  funcionario VARCHAR(50) NOT NULL,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  data_entrada DATE NOT NULL,\n"
                + "  data_digitacao DATE NOT NULL,\n"
                + "  quantidade INT NOT NULL);";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table produção Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
    
     public static void createTableProducaoCaixa() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS caixa (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  numero VARCHAR(50) NOT NULL,\n"
                + "  ano VARCHAR(50) NOT NULL,\n"
                + "  mes VARCHAR(50) NOT NULL,\n"
                + "  cor VARCHAR(50) NOT NULL,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL);";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table caixa Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
     
     public static void createTableBoletimRecebimento() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS boletimrecebimento (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  diaUtil DATE NOT NULL,\n"
                + "  qtd INT NOT NULL);";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table Boletim Recebimento Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
     
     public static void createTableBoletimEntrega() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "CREATE TEXT TABLE IF NOT EXISTS boletimentrega (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  diaUtil DATE NOT NULL,\n"
                + "  qtd INT NOT NULL);";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Create Table Boletim Entrega Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
     
     public static void createTableNaoPactuados() {
        Connection connection = null;
        PreparedStatement stmt = null;
        
        String sqlDROP = "DROP TABLE NAO_PACTUADO"; 
        String sqlCreate = "CREATE CACHED TABLE IF NOT EXISTS nao_pactuado (\n"
                + "  id INT PRIMARY KEY IDENTITY,\n"
                + "  paciente VARCHAR(255) NOT NULL,\n"
                + "  prestador VARCHAR(50) NOT NULL,\n"
                + "  procedimento VARCHAR(50) NOT NULL,\n"
                + "  mes VARCHAR(50) NOT NULL,\n"
                + "  ano INT NOT NULL,\n"
                + "  municipio VARCHAR(50) NOT NULL,\n"
                + "  observacao VARCHAR(100) NULL);";
                
                
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sqlCreate);
            stmt.execute();
            System.out.println("Create Table Não Pactuados Ok!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             CriaConexao.close(connection, stmt, null);
        }
    }
     
     public static void insereUsuario() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "insert into usuarios (login, senha, role_user) values ('admin','admin','ADMIN')";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Usuario inserido!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CriaConexao.close(connection, stmt, null);
        }
    }
     
     public static void dropNaoPactuado() {
        Connection connection = null;
        PreparedStatement stmt = null;
        String sql = "drop table nao_patuado";
        try {
            connection = CriaConexao.getConexao();
            stmt = connection.prepareStatement(sql);
            stmt.execute();
            System.out.println("Não Pactuado excluido!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CriaConexao.close(connection, stmt, null);
        }
    }
     
//     public static void main(String [] args){
//         dropUsuario();
//         createTableUsuarios();
//         insereUsuario();
//     }
   
}
