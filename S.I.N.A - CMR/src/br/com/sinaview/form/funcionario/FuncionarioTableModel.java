/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.funcionario;


import br.com.sinamodel.entidades.Funcionario;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class FuncionarioTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_NOME = 1;
    private static final int COL_SOBRENOME = 2;
    private static final int COL_CARGO = 3;
    public List<Funcionario> valores;

    public FuncionarioTableModel(List<Funcionario> valores) {
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
        Funcionario funcionario = valores.get(rowIndex);
        if (columnIndex == COL_ID) {
            return funcionario.getId();
        } else if (columnIndex == COL_NOME) {
            return funcionario.getNome();
        } else if (columnIndex == COL_SOBRENOME) {
            return funcionario.getSobrenome();
        } else if (columnIndex == COL_CARGO) {
            return funcionario.getCargo();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String colunas = "";
        switch (column) {
            case COL_ID:
                colunas = "ID";
                break;
            case COL_NOME:
                colunas = "NOME";
                break;
            case COL_SOBRENOME:
                colunas = "SOBRENOME";
                break;
            case COL_CARGO:
                colunas = "CARGO";
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
        } else if (columnIndex == COL_NOME) {
            return String.class;
        } else if (columnIndex == COL_SOBRENOME) {
            return String.class;
        }else if (columnIndex == COL_CARGO) {
            return String.class;
        }
        return null;
    }

    public Funcionario get(int row) {
        return valores.get(row);
    }
}
