/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.prestador;


import br.com.sinamodel.entidades.Prestador;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class PrestadorTableModel extends AbstractTableModel{
    private static final int COL_ID = 0;
    private static final int COL_CNES = 1;
    private static final int COL_NOME = 2;
    private List<Prestador> valores;

    public PrestadorTableModel(List<Prestador> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prestador prestador = valores.get(rowIndex);
        if (columnIndex == COL_ID) {
            return prestador.getId();
        } else if (columnIndex == COL_CNES) {
            return prestador.getCnes();
        } else if (columnIndex == COL_NOME) {
            return prestador.getNome();
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
            case COL_CNES:
                colunas = "CNES";
                break;
            case COL_NOME:
                colunas = "NOME";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida!");
        }
        return colunas;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == COL_ID){
            return Long.class;
        }else if(columnIndex == COL_CNES){
            return String.class;
        }else if(columnIndex == COL_NOME){
            return String.class;
        }
        return null; 
    }
    
     public Prestador get(int row){
        return valores.get(row);
    }
}
