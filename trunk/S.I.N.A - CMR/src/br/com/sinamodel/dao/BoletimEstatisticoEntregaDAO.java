/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IBoletimEstatisticoEntregaDAO;
import br.com.sinamodel.entidades.BoletimEstatisticoEntrega;
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
public class BoletimEstatisticoEntregaDAO implements IBoletimEstatisticoEntregaDAO {

    private static final String sqlInsert = "insert into boletimentrega (prestador, procedimento, diaUtil, qtd)"
            + "values (?,?,?,?)";
    private static final String sqlUpdate = "update boletimentrega set prestador = ?, procedimento = ?, diaUtil = ?, qtd = ?"
            + "where id = ?";
    private static final String sqldelete = "delete from boletimentrega where id = ?";
    private static final String sqList = "select * from boletimentrega where diaUtil between ? and ?";
    private static final String sqListPrestadorPeriodo = "select * from boletimentrega where prestador like ? and diaUtil between ? and ?";
    private static final String sqListPrestadorProcedimentoPeriodo = "select * from boletimentrega where prestador like ? and procedimento like ? and diaUtil between ? and ?";

    @Override
    public int save(BoletimEstatisticoEntrega entrega) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(1, entrega.getPrestador());
            pstm.setString(2, entrega.getProcedimento());
            pstm.setDate(3, entrega.getDiaUtil());
            pstm.setInt(4, entrega.getQtd());
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
    public int update(BoletimEstatisticoEntrega entrega) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(1, entrega.getPrestador());
            pstm.setString(2, entrega.getProcedimento());
            pstm.setDate(3, entrega.getDiaUtil());
            pstm.setInt(4, entrega.getQtd());
            pstm.setLong(5, entrega.getId());
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
    public List<BoletimEstatisticoEntrega> list(Date de, Date ate) {
        List<BoletimEstatisticoEntrega> boEstatico = new ArrayList<BoletimEstatisticoEntrega>();
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
                BoletimEstatisticoEntrega bo = new BoletimEstatisticoEntrega();
                bo.setId(rs.getLong("id"));
                bo.setPrestador(rs.getString("prestador"));
                bo.setProcedimento(rs.getString("procedimento"));
                bo.setDiaUtil(rs.getDate("diaUtil"));
                bo.setQtd(rs.getInt("qtd"));

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
    public List<BoletimEstatisticoEntrega> listPeriodo(String prestador, Date de, Date ate) {
        List<BoletimEstatisticoEntrega> boletim = new ArrayList<BoletimEstatisticoEntrega>();
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
                BoletimEstatisticoEntrega bo = new BoletimEstatisticoEntrega();
                bo.setId(rs.getLong("id"));
                bo.setPrestador(rs.getString("prestador"));
                bo.setProcedimento(rs.getString("procedimento"));
                bo.setDiaUtil(rs.getDate("diaUtil"));
                bo.setQtd(rs.getInt("qtd"));
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
    public List<BoletimEstatisticoEntrega> listPrestProcePeriodo(String prestador, String procedimento, Date de, Date ate) {
        List<BoletimEstatisticoEntrega> boletim = new ArrayList<BoletimEstatisticoEntrega>();
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
                BoletimEstatisticoEntrega bo = new BoletimEstatisticoEntrega();
                bo.setId(rs.getLong("id"));
                bo.setPrestador(rs.getString("prestador"));
                bo.setProcedimento(rs.getString("procedimento"));
                bo.setDiaUtil(rs.getDate("diaUtil"));
                bo.setQtd(rs.getInt("qtd"));
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
