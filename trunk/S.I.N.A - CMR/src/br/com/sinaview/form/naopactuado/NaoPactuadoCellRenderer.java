/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.sinaview.form.naopactuado;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Danilo
 */
public class NaoPactuadoCellRenderer extends DefaultTableCellRenderer{

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
        
//        table.getColumnModel().getColumn(0).setMaxWidth(40);
//        table.getColumnModel().getColumn(1).setMaxWidth(80);
//        table.getColumnModel().getColumn(2).setMaxWidth(80);
//        table.getColumnModel().getColumn(3).setMaxWidth(80);
//        table.getColumnModel().getColumn(4).setMaxWidth(80);
//        table.getColumnModel().getColumn(5).setMaxWidth(80);
//        table.getColumnModel().getColumn(6).setMaxWidth(80);
        return this;
    }
    
    
}
