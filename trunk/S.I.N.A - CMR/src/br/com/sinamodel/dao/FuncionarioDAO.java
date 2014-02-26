/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IFuncionarioDAO;

import br.com.sinamodel.entidades.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo
 */
public class FuncionarioDAO implements IFuncionarioDAO {

    private static final String sqlInsert = "insert into FUNCIONARIO (NOME, SOBRENOME, CARGO)"
            + "VALUES (?,?,?)";
    private static final String sqlUpdate = "update FUNCIONARIO set NOME = ?, "
            + "SOBRENOME = ?, CARGO = ? where ID = ?";
    private static final String sqlDelete = "delete from FUNCIONARIO where ID = ? ";
    private static final String sqlFindAll = "select * from FUNCIONARIO order by nome";
    private static final String sqlFindNome = "select * from FUNCIONARIO where nome like ? order by nome";
    private static final String sqlFindFunMedico = "select nome from funcionario where cargo = 'MÉDICO' order by nome";
    private static final String sqlFindFunDigitador = "select nome from funcionario where cargo = 'DIGITADOR' order by nome";

    @Override
    public int save(Funcionario funcionario) {
        //Connection conn = DBConnection.getConnection();
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(++index, funcionario.getNome());
            pstm.setString(++index, funcionario.getSobrenome());
            pstm.setString(++index, funcionario.getCargo());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();

                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, null);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return result;
    }

    @Override
    public int update(Funcionario funcionario) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(++index, funcionario.getNome());
            pstm.setString(++index, funcionario.getSobrenome());
            pstm.setString(++index, funcionario.getCargo());
            pstm.setLong(++index, funcionario.getId());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();

                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, null);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return result;
    }

    @Override
    public int remove(Long id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlDelete);
            pstm.setLong(1, id);
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, null);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return result;
    }

    @Override
    public List<Funcionario> findAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindAll);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getLong("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSobrenome(rs.getString("sobrenome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();

                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            MensageError.errorSql();
            ex.printStackTrace();
        }
        return funcionarios;
    }

    @Override
    public List<Funcionario> findNome(String nome) {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindNome);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return funcionarios;
    }

    @Override
    public List<Funcionario> findNomeMedico() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindFunMedico);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();

                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return funcionarios;
    }

    @Override
    public List<Funcionario> findNomeDigitador() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindFunDigitador);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString("nome"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                MensageError.errorSql();
                ex1.printStackTrace();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return funcionarios;
    }
}
