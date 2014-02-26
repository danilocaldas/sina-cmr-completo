/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IArquivoDAO;
import br.com.sinamodel.entidades.Arquivo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ritacosta
 */
public class ArquivoDAO implements IArquivoDAO {

    private static final String cadastraArquivo = "INSERT INTO CAIXA (NUMERO, "
            + "ANO, MES, COR, PRESTADOR, PROCEDIMENTO) "
            + "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String excluirCaixa = "DELETE "
            + "FROM CAIXA WHERE NUMERO LIKE ? AND ANO LIKE ?";

    @Override
    public int save(Arquivo arquivo) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(cadastraArquivo);
            pstm.setInt(1, arquivo.getNumero());
            pstm.setString(2, arquivo.getAno());
            pstm.setString(3, arquivo.getMes());
            pstm.setString(4, arquivo.getCor());
            pstm.setString(5, arquivo.getPrestador());
            pstm.setString(6, arquivo.getProcedimento());
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
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
        }
        return result;
    }

    @Override
    public int delete(Arquivo arquivo) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(excluirCaixa);
            pstm.setInt(1, arquivo.getNumero());
            pstm.setString(2, arquivo.getAno());
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
                CriaConexao.close(conn, pstm, null);
            }
            ex.printStackTrace();
            MensageError.errorSql(); 
        }
        return result;
    }
}
