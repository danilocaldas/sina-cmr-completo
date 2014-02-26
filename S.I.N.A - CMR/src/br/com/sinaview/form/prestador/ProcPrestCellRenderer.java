/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.prestador;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ritacosta
 */
public class ProcPrestCellRenderer extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if(row % 2 == 0){
            setBackground(Color.YELLOW);
        }else{
            setBackground(null);
        }
        
        if(isSelected){
            setBackground(Color.GREEN);
        }
        
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(500);
        
        
        return this;
    }
    
    
}
