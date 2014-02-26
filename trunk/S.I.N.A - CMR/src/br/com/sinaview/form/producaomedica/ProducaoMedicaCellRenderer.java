/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.producaomedica;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaCellRenderer extends DefaultTableCellRenderer {

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
        table.getColumnModel().getColumn(0).setMaxWidth(40);//0
        table.getColumnModel().getColumn(1).setMaxWidth(60);//1
        table.getColumnModel().getColumn(2).setMaxWidth(180);//2
        table.getColumnModel().getColumn(3).setMaxWidth(180);//3
        table.getColumnModel().getColumn(4).setMaxWidth(70);//4
        table.getColumnModel().getColumn(5).setMaxWidth(60);//5
        table.getColumnModel().getColumn(6).setMaxWidth(180);//6
        table.getColumnModel().getColumn(7).setMaxWidth(80);//7
        table.getColumnModel().getColumn(8).setMaxWidth(100);//8
        return this;
    }
}
