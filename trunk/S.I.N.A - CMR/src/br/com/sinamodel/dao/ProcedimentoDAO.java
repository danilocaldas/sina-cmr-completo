/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IProcedimentoDAO;
import br.com.sinamodel.entidades.Procedimento;
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
public class ProcedimentoDAO implements IProcedimentoDAO {

    private static final String sqlInsert = "insert into PROCEDIMENTO (CODIGO, NOME)"
            + "VALUES (?,?)";
    private static final String sqlUpdate = "update PROCEDIMENTO set CODIGO = ?, "
            + "NOME = ? where ID = ?";
    private static final String sqlDelete = "delete from PROCEDIMENTO where ID = ? ";
    private static final String sqlFindAll = "select * from PROCEDIMENTO order by nome";
    private static final String sqlFindNome = "select * from PROCEDIMENTO where nome like ? order by nome";

    @Override
    public int save(Procedimento procedimento) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(++index, procedimento.getCodigo());
            pstm.setString(++index, procedimento.getNome());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                MensageError.errorSql();
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
    public int update(Procedimento procedimento) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(++index, procedimento.getCodigo());
            pstm.setString(++index, procedimento.getNome());
            pstm.setLong(++index, procedimento.getId());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                MensageError.errorSql();
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
                ex1.printStackTrace();
                MensageError.errorSql();
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
    public List<Procedimento> findAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Procedimento> procedimentos = new ArrayList<Procedimento>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindAll);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Procedimento procedimento = new Procedimento();
                procedimento.setId(rs.getLong("id"));
                procedimento.setCodigo(rs.getString("codigo"));
                procedimento.setNome(rs.getString("nome"));
                procedimentos.add(procedimento);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                MensageError.errorSql();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return procedimentos;
    }

    @Override
    public List<Procedimento> findNome(String nome) {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Procedimento> procedimentos = new ArrayList<Procedimento>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindNome);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Procedimento procedimento = new  Procedimento();
                procedimento.setNome(rs.getString("nome"));
                procedimentos.add(procedimento);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                MensageError.errorSql();
            } finally {
                //DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return procedimentos;
    }
    
}
