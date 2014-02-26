/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinamodel.dao;

import br.com.sinamodel.interfaces.IUsuariosDAO;
import br.com.sinamodel.entidades.Usuarios;
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
public class UsuariosDAO implements IUsuariosDAO {

    private static final String sqlInsertUser = "insert into usuarios (login, senha, role_user) values(?,?,?)";
    private static final String sqlUpdateUser = "update usuarios set login = ?, senha =?, role_user = ? where id = ?";
    private static final String sqlDeleteUser = "delete from usuarios where id = ?";
    private static final String sqlSelectUser = "select * from usuarios order by login";
    
    @Override
    public int save(Usuarios usuarios) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            int index = 0;
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlInsertUser);
            pstm.setString(++index, usuarios.getLogin());
            pstm.setString(++index, usuarios.getSenha());
            pstm.setString(++index, usuarios.getRole_user());
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
        public int update(Usuarios usuarios) {
        Connection conn = null;
        PreparedStatement pstm = null;
        int result = 0;
        try {
            int index = 0;
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlUpdateUser);
            pstm.setString(++index, usuarios.getLogin());
            pstm.setString(++index, usuarios.getSenha());
            pstm.setString(++index, usuarios.getRole_user());
            pstm.setLong(++index, usuarios.getId());
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
            pstm = conn.prepareStatement(sqlDeleteUser);
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
        public List<Usuarios> listar() {
        Connection conn = null;
        PreparedStatement pstm = null;
        List<Usuarios> usuarios = new ArrayList<Usuarios>();
        ResultSet rs = null;
        try {
            conn = CriaConexao.getConexao();
            pstm = conn.prepareStatement(sqlSelectUser);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Usuarios user = new  Usuarios();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setSenha(rs.getString("senha"));
                user.setRole_user(rs.getString("role_user"));
                usuarios.add(user);
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
        return usuarios;
    }
    
    
    
}
