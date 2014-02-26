/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IPrestadorDAO;
import br.com.sinamodel.entidades.Prestador;
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
public class PrestadorDAO implements IPrestadorDAO{
    
    private static final String sqlInsert = "insert into PRESTADOR (CNES, NOME)"
            + "VALUES (?,?)";
    private static final String sqlUpdate = "update PRESTADOR set CNES = ?, "
            + "NOME = ? where ID = ?";
    private static final String sqlDelete = "delete from PRESTADOR where ID = ? ";
    private static final String sqlFindAll = "select * from PRESTADOR order by nome";
    private static final String sqlFindNome = "select * from PRESTADOR where nome like ? order by nome";

    @Override
    public int save(Prestador prestador) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(++index, prestador.getCnes());
            pstm.setString(++index, prestador.getNome());
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
    public int update(Prestador prestador) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(++index, prestador.getCnes());
            pstm.setString(++index, prestador.getNome());
            pstm.setLong(++index, prestador.getId());
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
        return result;  //To change body of generated methods, choose Tools | Templates.
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
        return result;  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestador> findAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Prestador> prestadores = new ArrayList<Prestador>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindAll);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Prestador prestador = new  Prestador();
                prestador.setId(rs.getLong("id"));
                prestador.setCnes(rs.getString("cnes"));
                prestador.setNome(rs.getString("nome"));
                prestadores.add(prestador);
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
        return prestadores;
    }

    @Override
    public List<Prestador> findNome(String nome) {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Prestador> prestadores = new ArrayList<Prestador>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindNome);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Prestador prestador = new  Prestador();
                prestador.setNome(rs.getString("nome"));
                prestadores.add(prestador);
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
        return prestadores;
    }
   
}
