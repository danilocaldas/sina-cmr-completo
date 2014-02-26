/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.entidades.NaoPactuados;
import br.com.sinamodel.interfaces.INaoPactuadosDao;
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
public class NaoPactuadosDao implements INaoPactuadosDao {

    private static final String sqlInsert = "insert into nao_pactuado (PACIENTE, PRESTADOR, PROCEDIMENTO, MES, ANO,"
            + "MUNICIPIO, OBSERVACAO)"
            + "VALUES (?,?,?,?,?,?, ?)";
    private static final String sqlUpdate = "update nao_pactuado set PACIENTE = ? ,PRESTADOR = ?, "
            + "PROCEDIMENTO = ?, MES = ?, ANO = ?, MUNICIPIO = ?,"
            + "OBSERVACAO = ? where ID = ?";
    private static final String sqlDelete = "delete from nao_pactuado where ID = ? ";
    //CONSULTAS
    private static final String sqlFindPrestador = "select * from nao_pactuado where prestador like ? order by procedimento";
    private static final String sqlFindProcedimento = "select * from nao_pactuado where procedimento like ? order by prestador";
    private static final String sqlFindProMes = "select * from nao_pactuado where mes like ? AND ano like ? order by prestador, procedimento";
    private static final String sqlFindProMunicipio = "select * from nao_pactuado where municipio like ? order by prestador";

    @Override
    public int save(NaoPactuados np) {

        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            int index = 0;
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsert);
            pstm.setString(++index, np.getPaciente());
            pstm.setString(++index, np.getPrestador());
            pstm.setString(++index, np.getProcedimento());
            pstm.setString(++index, np.getMes());
            pstm.setInt(++index, np.getAno());
            pstm.setString(++index, np.getMunicipios());
            pstm.setString(++index, np.getObervacao());
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
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return result;
    }

    @Override
    public int update(NaoPactuados np) {

        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlUpdate);
            pstm.setString(1, np.getPaciente());
            pstm.setString(2, np.getPrestador());
            pstm.setString(3, np.getProcedimento());
            pstm.setString(4, np.getMes());
            pstm.setInt(5, np.getAno());
            pstm.setString(6, np.getMunicipios());
            pstm.setString(7, np.getObervacao());
            pstm.setLong(8, np.getId());
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
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return result;
    }

    @Override
    public List<NaoPactuados> listPrestador(String prestador) {

        List<NaoPactuados> naoPactuados = new ArrayList<NaoPactuados>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindPrestador);
            pstm.setString(1, prestador);
            rset = pstm.executeQuery();
            while (rset.next()) {
                NaoPactuados np = new NaoPactuados();
                np.setId(rset.getLong("id")); 
                np.setPaciente(rset.getString("paciente"));
                np.setPrestador(rset.getString("prestador"));
                np.setProcedimento(rset.getString("procedimento"));
                np.setMes(rset.getString("mes"));
                np.setAno(rset.getInt("ano"));
                np.setMunicipios(rset.getString("municipio"));
                np.setObervacao(rset.getString("observacao"));
                naoPactuados.add(np);
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
                CriaConexao.close(conn, pstm, rset);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return naoPactuados;
    }

    @Override
    public List<NaoPactuados> listProcedimento(String procedimento) {
        List<NaoPactuados> naoPactuados = new ArrayList<NaoPactuados>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindProcedimento);
            pstm.setString(1, procedimento);
            rset = pstm.executeQuery();
            while (rset.next()) {
                NaoPactuados np = new NaoPactuados();
                np.setId(rset.getLong("id")); 
                np.setPaciente(rset.getString("paciente"));
                np.setPrestador(rset.getString("prestador"));
                np.setProcedimento(rset.getString("procedimento"));
                np.setMes(rset.getString("mes"));
                np.setAno(rset.getInt("ano"));
                np.setMunicipios(rset.getString("municipio"));
                np.setObervacao(rset.getString("observacao"));

                naoPactuados.add(np);
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
                CriaConexao.close(conn, pstm, rset);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return naoPactuados;
    }

    @Override
    public List<NaoPactuados> listAnoMes(String mes, Integer ano) {
        List<NaoPactuados> naoPactuados = new ArrayList<NaoPactuados>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindProMes);
            pstm.setString(1, mes);
            pstm.setInt(2, ano);
            rset = pstm.executeQuery();
            while (rset.next()) {
                NaoPactuados np = new NaoPactuados();
                np.setId(rset.getLong("id")); 
                np.setPaciente(rset.getString("paciente"));
                np.setPrestador(rset.getString("prestador"));
                np.setProcedimento(rset.getString("procedimento"));
                np.setMes(rset.getString("mes"));
                np.setAno(rset.getInt("ano"));
                np.setMunicipios(rset.getString("municipio"));
                np.setObervacao(rset.getString("observacao"));
                naoPactuados.add(np);
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
                CriaConexao.close(conn, pstm, rset);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return naoPactuados;
    }

    @Override
    public List<NaoPactuados> listMunicipios(String municipios) {
        List<NaoPactuados> naoPactuados = new ArrayList<NaoPactuados>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlFindProMunicipio);
            pstm.setString(1, municipios);
            rset = pstm.executeQuery();
            while (rset.next()) {
                NaoPactuados np = new NaoPactuados();
                np.setId(rset.getLong("id"));
                np.setPaciente(rset.getString("paciente"));
                np.setPrestador(rset.getString("prestador"));
                np.setProcedimento(rset.getString("procedimento"));
                np.setMes(rset.getString("mes"));
                np.setAno(rset.getInt("ano"));
                np.setMunicipios(rset.getString("municipio"));
                np.setObervacao(rset.getString("observacao"));

                naoPactuados.add(np);
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
                CriaConexao.close(conn, pstm, rset);
            }
            ex.printStackTrace();
            MensageError.errorSql();
        }
        return naoPactuados;
    }
}
