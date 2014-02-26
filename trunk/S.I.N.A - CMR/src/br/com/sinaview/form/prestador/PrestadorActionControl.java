/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.prestador;


import br.com.sinacontrol.controller.PrestadorController;
import br.com.sinamodel.entidades.Prestador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class PrestadorActionControl implements ActionListener{
    
    private FormPrestador frm;
    private List<Prestador>listPrestador;
    private Long idPrestador;

    public PrestadorActionControl(FormPrestador frm) {
        this.frm = frm;
        addButoesToForm();
        refreshTable();
        enableFields(false);
        frm.getTxtId().setEnabled(false);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Salvar")){
            onSavePrestador();
        }else if(e.getActionCommand().equals("Novo")){
            enableFields(true);
        }else if(e.getActionCommand().equals("Cancelar")){
            onCancelar();
        }else if(e.getActionCommand().equals("Excluir")){
            removerPrestador();
        }else if(e.getActionCommand().equals("Atualizar")){
            onAlterarPrestador();
        }
    }    
    private void addButoesToForm(){
        frm.getBtNovo().addActionListener(this);
        frm.getBtAtualizar().addActionListener(this);
        frm.getBtSalvar().addActionListener(this);
        frm.getBtExcluir().addActionListener(this);
        frm.getBtCancelar().addActionListener(this);
        
    }
    private void onCancelar() {
        enableFields(false);
        frm.getTxtId().setText("");
        frm.getTxtCnes().setText("");
        frm.getTxtNome().setText("");
    }

    private void onSavePrestador() {
        Prestador prestador = new Prestador();
        if (frm.getTxtCnes().getText().length() > 0
                && frm.getTxtNome().getText().length() > 0 ) {
            prestador.setCnes(frm.getTxtCnes().getText().toUpperCase());
            prestador.setNome(frm.getTxtNome().getText().toUpperCase());
        } else {
            JOptionPane.showMessageDialog(frm, "Todos os campos são obrigatórios!");
            return;
        }

        int result = 0;
        
        if (idPrestador == null) {
            result = new PrestadorController().addPrestador(prestador);
        } else {
            prestador.setId(idPrestador);
            result = new PrestadorController().updatePrestador(prestador);
            idPrestador = null;
        }
        if(result == 1){
            JOptionPane.showMessageDialog(frm, "Prestador inserido com sucesso!");
            enableFields(false);
            onCancelar();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frm, "Tente novamente!");
        }
    }
    
    private void onAlterarPrestador() {
        int indexRow = frm.getTbPrestador().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm, "Selecione um prestador a ser alterado!");
            return;
        }
        Prestador prestador = new PrestadorTableModel(listPrestador).get(indexRow);
        idPrestador = prestador.getId();
        frm.getTxtId().setText(String.valueOf(prestador.getId()));
        frm.getTxtCnes().setText(prestador.getCnes());
        frm.getTxtNome().setText(prestador.getNome());
        enableFields(true);
    }

    private void removerPrestador() {
        int indexRow = frm.getTbPrestador().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm, "Selecione um prestador a ser removido!");
            return;
        }
        Prestador prestador = new PrestadorTableModel(listPrestador).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(frm, "Confirmar a exclusão?", "Excluir Prestador", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new PrestadorController().excluirPrestador(prestador.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Prestador removido com sucesso!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frm, "Tente novamente!");
        }
    }

    private void refreshTable() {
        listPrestador = new PrestadorController().finfPrestador();
        if(listPrestador != null){
            frm.getTbPrestador().setModel(new PrestadorTableModel(listPrestador));
            frm.getTbPrestador().setDefaultRenderer(Object.class, new ProcPrestCellRenderer());
        }
    }

    private void enableFields(boolean enable) {
        frm.getTxtCnes().setEnabled(enable);
        frm.getTxtNome().setEnabled(enable);
    }
    
}
