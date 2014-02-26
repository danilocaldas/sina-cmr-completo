/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IProducaoMedicaDAO;
import br.com.sinamodel.entidades.ProducaoMedica;
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
public class ProducaoMedicaoDAO implements IProducaoMedicaDAO {

    public static final String sqlInsert = "insert into producao_medica "
            + "(data_entrada_cmr, prestador, procedimento, quantidade_laudos, "
            + "data_analise, funcionario, data_encaminhamento, nucleos) values (?,?,?,?,?,?,?,?)";
    public static final String sqlUpdate = "update producao_medica set data_entrada_cmr = ?, "
            + "prestador = ?, procedimento = ?, quantidade_laudos = ?, data_analise = ?"
            + ", funcionario = ?, data_encaminhamento = ?, nucleos = ?  where ID = ?";
    public static final String sqlDelete = "delete from producao_medica where ID = ? ";
    public static final String sqlList = "select * from producao_medica where data_analise BETWEEN ? AND ?";
    public static final String sqlListNomePeriodo = "SELECT * FROM PRODUCAO_MEDICA WHERE FUNCIONARIO LIKE ? "
            + "AND DATA_ANALISE BETWEEN ? AND ?";
    

    @Override
    public int save(ProducaoMedica pMedica) {
        //Connection conn = DBConnection.getConnection();
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setDate(++index, pMedica.getEntradaCmr());
            pstm.setString(++index, pMedica.getPrestador());
            pstm.setString(++index, pMedica.getProcedimento());
            pstm.setInt(++index, pMedica.getQuantidade());
            pstm.setDate(++index, pMedica.getAnalise());
            pstm.setString(++index, pMedica.getFuncionario());
            pstm.setDate(++index, pMedica.getEncaminhamento());
            pstm.setString(++index, pMedica.getNucleos());
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
    public int updade(ProducaoMedica pMedica) {
        //Connection conn = DBConnection.getConnection();
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            int index = 0;
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setDate(++index, pMedica.getEntradaCmr());
            pstm.setString(++index, pMedica.getPrestador());
            pstm.setString(++index, pMedica.getProcedimento());
            pstm.setInt(++index, pMedica.getQuantidade());
            pstm.setDate(++index, pMedica.getAnalise());
            pstm.setString(++index, pMedica.getFuncionario());
            pstm.setDate(++index, pMedica.getEncaminhamento());
            pstm.setString(++index, pMedica.getNucleos());
            pstm.setLong(++index, pMedica.getId());
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
    public int delete(Long id) {
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
                DBConnection.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return result;
    }

    @Override
    public List<ProducaoMedica> listar(Date dataDe, Date dataAte) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ProducaoMedica> pMedicas = new ArrayList();
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlList);
            pstm.setDate(1, dataDe);
            pstm.setDate(2, dataAte);
            rs = pstm.executeQuery();
            while(rs.next()){
                ProducaoMedica pMedica = new ProducaoMedica();
                pMedica.setId(rs.getLong("id"));
                pMedica.setEntradaCmr(rs.getDate("data_entrada_cmr"));
                pMedica.setPrestador(rs.getString("prestador"));
                pMedica.setProcedimento(rs.getString("procedimento"));
                pMedica.setQuantidade(rs.getInt("quantidade_laudos"));
                pMedica.setAnalise(rs.getDate("data_analise"));
                pMedica.setFuncionario(rs.getString("funcionario"));
                pMedica.setEncaminhamento(rs.getDate("data_encaminhamento"));
                pMedica.setNucleos(rs.getString("nucleos"));
                pMedicas.add(pMedica);
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
               // DBConnection.close(conn, pstm, rs);
                CriaConexao.close(conn, pstm, rs);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return pMedicas;
    }

    @Override
    public List<ProducaoMedica> listarProMedica(String nome, Date dataDe, Date dataAte) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<ProducaoMedica> pMedicas = new ArrayList();
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlListNomePeriodo);
            pstm.setString(1, nome);
            pstm.setDate(2, dataDe);
            pstm.setDate(3, dataAte);
            rs = pstm.executeQuery();
            while(rs.next()){
                ProducaoMedica pMedica = new ProducaoMedica();
                pMedica.setId(rs.getLong("id"));
                pMedica.setEntradaCmr(rs.getDate("data_entrada_cmr"));
                pMedica.setPrestador(rs.getString("prestador"));
                pMedica.setProcedimento(rs.getString("procedimento"));
                pMedica.setQuantidade(rs.getInt("quantidade_laudos"));
                pMedica.setAnalise(rs.getDate("data_analise"));
                pMedica.setFuncionario(rs.getString("funcionario"));
                pMedica.setEncaminhamento(rs.getDate("data_encaminhamento"));
                pMedica.setNucleos(rs.getString("nucleos"));
                pMedicas.add(pMedica);
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
        return pMedicas;
    }
}
