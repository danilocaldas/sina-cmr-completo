/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.saidalaudos;


import br.com.sinamodel.entidades.BoletimEstatisticoEntrega;
import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ritacosta
 */
public class BoletimEstatisticoEntradaTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_PRESTADOR = 1;
    private static final int COL_DIA = 2;
    private static final int COL_SERVICO = 3;
    private static final int COL_QTD = 4;
    private List<BoletimEstatisticoEntrega> valores;

    public BoletimEstatisticoEntradaTableModel(List<BoletimEstatisticoEntrega> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BoletimEstatisticoEntrega boletim = valores.get(rowIndex);
        if (columnIndex == COL_ID) {
            return boletim.getId();
        } else if (columnIndex == COL_PRESTADOR) {
            return boletim.getPrestador();
        } else if (columnIndex == COL_DIA) {
            return boletim.getDiaUtil();
        } else if (columnIndex == COL_SERVICO) {
            return boletim.getProcedimento();
        } else if (columnIndex == COL_QTD) {
            return boletim.getQtd();
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
            case COL_PRESTADOR:
                colunas = "PRESTADOR";
                break;
            case COL_DIA:
                colunas = "DIAUTIL";
                break;
            case COL_SERVICO:
                colunas = "PROCEDIMENTO";
                break;
            case COL_QTD:
                colunas = "QTD.";
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
        } else if (columnIndex == COL_PRESTADOR) {
            return String.class;
        } else if (columnIndex == COL_DIA) {
            return Date.class;
        } else if (columnIndex == COL_SERVICO) {
            return String.class;
        } else if (columnIndex == COL_QTD) {
            return Integer.class;
        }
        return null;
    }

    public BoletimEstatisticoEntrega get(int row) {
        return valores.get(row);
    }
}
