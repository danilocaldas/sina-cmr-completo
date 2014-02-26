/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.producaomedica;


import br.com.sinamodel.entidades.ProducaoMedica;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaTableModel extends AbstractTableModel {

    private static final int COL_ID = 0;
    private static final int COL_ENTRADA_CMR = 1;
    private static final int COL_PRESTADOR = 2;
    private static final int COL_PROCEDIMENTO = 3;
    private static final int COL_QUANTIDADE_LAUDOS = 4;
    private static final int COL_DATA_ANALISE = 5;
    private static final int COL_FUNCIONARIO = 6;
    private static final int COL_DATA_ENCAMINHAMENTO = 7;
    private static final int COL_NUCLEOS = 8;
    private List<ProducaoMedica> valores;

    public ProducaoMedicaTableModel(List<ProducaoMedica> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProducaoMedica pMedica = valores.get(rowIndex);
        //SimpleDateFormat formatdata = new SimpleDateFormat("dd/MM/yyyy");
        
        
        if (columnIndex == COL_ID) {
            return pMedica.getId();
        } else if (columnIndex == COL_ENTRADA_CMR) {
            return pMedica.getEntradaCmr();
        } else if (columnIndex == COL_PRESTADOR) {
            return pMedica.getPrestador();
        } else if (columnIndex == COL_PROCEDIMENTO) {
            return pMedica.getProcedimento();
        } else if (columnIndex == COL_QUANTIDADE_LAUDOS) {
            return pMedica.getQuantidade();
        } else if (columnIndex == COL_DATA_ANALISE) {
            return pMedica.getAnalise();
        } else if (columnIndex == COL_FUNCIONARIO) {
            return pMedica.getFuncionario();
        } else if (columnIndex == COL_DATA_ENCAMINHAMENTO) {
            return pMedica.getEncaminhamento();
        } else if (columnIndex == COL_NUCLEOS) {
            return pMedica.getNucleos();
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
            case COL_ENTRADA_CMR:
                colunas = "Entrada";
                break;
            case COL_PRESTADOR:
                colunas = "Prestador";
                break;
            case COL_PROCEDIMENTO:
                colunas = "Procedimento";
                break;
            case COL_QUANTIDADE_LAUDOS:
                colunas = "Qtd. Laudos";
                break;
            case COL_DATA_ANALISE:
                colunas = "Analisado";
                break;
            case COL_FUNCIONARIO:
                colunas = "Funcionário";
                break;
            case COL_DATA_ENCAMINHAMENTO:
                colunas = "Encaminhado";
                break;
            case COL_NUCLEOS:
                colunas = "Núcleos";
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
        } else if (columnIndex == COL_ENTRADA_CMR) {
            return Date.class;
        } else if (columnIndex == COL_PRESTADOR) {
            return String.class;
        } else if (columnIndex == COL_PROCEDIMENTO) {
            return String.class;
        } else if (columnIndex == COL_QUANTIDADE_LAUDOS) {
            return Integer.class;
        } else if (columnIndex == COL_DATA_ANALISE) {
            return Date.class;
        } else if (columnIndex == COL_FUNCIONARIO) {
            return String.class;
        } else if (columnIndex == COL_DATA_ENCAMINHAMENTO) {
            return Date.class;
        } else if (columnIndex == COL_NUCLEOS) {
            return String.class;
        }
        return null;
    }

    public ProducaoMedica get(int row) {
        return valores.get(row);
    }
}
