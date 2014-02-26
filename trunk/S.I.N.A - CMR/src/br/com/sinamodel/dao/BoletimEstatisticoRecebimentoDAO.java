/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IBoletimEstatisticoRecimentoDAO;
import br.com.sinamodel.entidades.BoletimEstatisticoRecebimento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatisticoRecebimentoDAO implements IBoletimEstatisticoRecimentoDAO {

    private static final String sqlInsert = "insert into boletimrecebimento (prestador, procedimento, diaUtil, qtd)"
            + "values (?,?,?,?)";
    private static final String sqlUpdate = "update boletimrecebimento set prestador = ?, procedimento = ?, diaUtil = ?, qtd = ?"
            + "where id = ?";
    private static final String sqldelete = "delete from boletimrecebimento where id = ?";
    private static final String sqList = "select * from boletimrecebimento where diaUtil between ? and ?";
    private static final String sqListPrestadorPeriodo = "select * from boletimrecebimento where prestador like ? and diaUtil between ? and ?";
    private static final String sqListPrestadorProcedimentoPeriodo = "select * from boletimrecebimento where prestador like ? and procedimento like ? and diaUtil between ? and ?";

    @Override
    public int save(BoletimEstatisticoRecebimento be) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(1, be.getPrestador());
            pstm.setString(2, be.getProcedimento());
            pstm.setDate(3, be.getDiaUtil());
            pstm.setInt(4, be.getQtd());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                
                e.printStackTrace();
                MensageError.errorSql(); 
            } finally {
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
          
        }
        return result;
    }

    @Override
    public int update(BoletimEstatisticoRecebimento be) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(1, be.getPrestador());
            pstm.setString(2, be.getProcedimento());
            pstm.setDate(3, be.getDiaUtil());
            pstm.setInt(4, be.getQtd());
            pstm.setLong(5, be.getId());
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                MensageError.errorSql(); 
                e.printStackTrace();
            } finally {
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
        }
        return result;
    }

    @Override
    public int delete(Long id) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqldelete);
            pstm.setLong(1, id);
            result = pstm.executeUpdate();
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
               MensageError.errorSql(); 
                e.printStackTrace();
            } finally {
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
           MensageError.errorSql(); 
        }
        return result;
    }

    @Override
    public List<BoletimEstatisticoRecebimento> list(Date de, Date ate) {
        List<BoletimEstatisticoRecebimento> boEstatico = new ArrayList<BoletimEstatisticoRecebimento>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqList);
            pstm.setDate(1, de);
            pstm.setDate(2, ate);
            rs = pstm.executeQuery();
            while (rs.next()) {
                BoletimEstatisticoRecebimento bo = new BoletimEstatisticoRecebimento(
                        rs.getLong("id"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"),
                        rs.getDate("diaUtil"),
                        rs.getInt("qtd"));
                boEstatico.add(bo);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                MensageError.errorSql(); 
                e.printStackTrace();
            } finally {
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
        }
        return boEstatico;
    }

    @Override
    public List<BoletimEstatisticoRecebimento> listPeriodo(String prestador, Date de, Date ate) {
        List<BoletimEstatisticoRecebimento> boletim = new ArrayList<BoletimEstatisticoRecebimento>();
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstn = conn.prepareStatement(sqListPrestadorPeriodo);
            pstn.setString(1, prestador);
            pstn.setDate(2, de);
            pstn.setDate(3, ate);
            rs = pstn.executeQuery();
            while (rs.next()) {
                BoletimEstatisticoRecebimento bo = new BoletimEstatisticoRecebimento(
                        rs.getLong("id"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"),
                        rs.getDate("diaUtil"),
                        rs.getInt("qtd"));
                boletim.add(bo);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                MensageError.errorSql(); 
                e.printStackTrace();
            } finally {
                CriaConexao.close(conn, pstn, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
        }
        return boletim;
    }

    @Override
    public List<BoletimEstatisticoRecebimento> listPrestProcePeriodo(String prestador, String procedimento, Date de, Date ate) {
        List<BoletimEstatisticoRecebimento> boletim = new ArrayList<BoletimEstatisticoRecebimento>();
        Connection conn = null;
        PreparedStatement pstn = null;
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstn = conn.prepareStatement(sqListPrestadorProcedimentoPeriodo);
            pstn.setString(1, prestador);
            pstn.setString(2, procedimento);
            pstn.setDate(3, de);
            pstn.setDate(4, ate);
            rs = pstn.executeQuery();
            while (rs.next()) {
                BoletimEstatisticoRecebimento bo = new BoletimEstatisticoRecebimento(
                        rs.getLong("id"),
                        rs.getString("prestador"),
                        rs.getString("procedimento"),
                        rs.getDate("diaUtil"),
                        rs.getInt("qtd"));
                boletim.add(bo);
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                MensageError.errorSql(); 
                e.printStackTrace();
            } finally {
                CriaConexao.close(conn, pstn, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
        }
        return boletim;
    }
}
