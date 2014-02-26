/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.usuario;


import br.com.sinamodel.entidades.Usuarios;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ritacosta
 */
public class UsuarioTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_LOGIN = 1;
    private static final int COL_SENHA = 2;
    private static final int COL_STATUS = 3;
    private List<Usuarios> valores;

    public UsuarioTableModel(List<Usuarios> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuarios usuario = valores.get(rowIndex);
        if (columnIndex == COL_ID) {
            return usuario.getId();
        } else if (columnIndex == COL_LOGIN) {
            return usuario.getLogin();
        } else if (columnIndex == COL_SENHA) {
            return usuario.getSenha();
        } else if (columnIndex == COL_STATUS) {
            return usuario.getRole_user();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String colunas = "";
        switch (column) {
            case COL_ID:
                colunas = "Id";
                break;
            case COL_LOGIN:
                colunas = "Login";
                break;
            case COL_SENHA:
                colunas = "Senha";
                break;
            case COL_STATUS:
                colunas = "Status";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida!");
        }
        return colunas;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Long.class;
        } else if (columnIndex == COL_LOGIN) {
            return String.class;
        } else if (columnIndex == COL_SENHA) {
            return String.class;
        }else if (columnIndex == COL_STATUS) {
            return String.class;
        }
        return null;
    }

    public Usuarios get(int row) {
        return valores.get(row);
    }
}
