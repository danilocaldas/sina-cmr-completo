/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.naopactuado;

import br.com.sinamodel.entidades.NaoPactuados;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Danilo
 */
public class NaoPactuadoTableModel extends AbstractTableModel {

    private static final int COL_PACIENTE = 0;
    private static final int COL_PRESTADOR = 1;
    private static final int COL_PROCEDIMENTO = 2;
    private static final int COL_MES = 3;
    private static final int COL_ANO = 4;
    private static final int COL_MUNICIPIO = 5;
    private static final int COL_OBSERVACAO = 6;
    public List<NaoPactuados> valores;

    public NaoPactuadoTableModel(List<NaoPactuados> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NaoPactuados np = valores.get(rowIndex);
//        if (columnIndex == COL_ID) {
//            return np.getId();
//        } else 
        if (columnIndex == COL_PACIENTE) {
            return np.getPaciente();
        } else if (columnIndex == COL_PRESTADOR) {
            return np.getPrestador();
        } else if (columnIndex == COL_PROCEDIMENTO) {
            return np.getProcedimento();
        } else if (columnIndex == COL_MES) {
            return np.getMes();
        } else if (columnIndex == COL_ANO) {
            return np.getAno();
        } else if (columnIndex == COL_MUNICIPIO) {
            return np.getMunicipios();
        } else if (columnIndex == COL_OBSERVACAO) {
            return np.getObervacao();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String colunas = "";
        switch (column) {
            case COL_PACIENTE:
                colunas = "PACIENTE";
                break;
            case COL_PRESTADOR:
                colunas = "PRESTADOR";
                break;
            case COL_PROCEDIMENTO:
                colunas = "PROCEDIMENTO";
                break;
            case COL_MES:
                colunas = "MÊS";
                break;
            case COL_ANO:
                colunas = "ANO";
                break;
            case COL_MUNICIPIO:
                colunas = "MUNICIPIO";
                break;
            case COL_OBSERVACAO:
                colunas = "OBERVAÇÃO";
                break;
            default:
                throw new IllegalArgumentException("Coluna Inválida!");
        }
        return colunas;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
//        if (columnIndex == COL_ID) {
//            return Long.class;
//        } else 

        if (columnIndex == COL_PACIENTE) {
            return String.class;
        } else if (columnIndex == COL_PRESTADOR) {
            return String.class;
        } else if (columnIndex == COL_PROCEDIMENTO) {
            return String.class;
        } else if (columnIndex == COL_MES) {
            return String.class;
        }  else if (columnIndex == COL_ANO) {
            return Integer.class;
        } else if (columnIndex == COL_MUNICIPIO) {
            return String.class;
        } else if (columnIndex == COL_OBSERVACAO) {
            return String.class;
        }

        return null;
    }

    public NaoPactuados get(int row) {
        return valores.get(row);
    }
}
