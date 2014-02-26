/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.funcionario;


import br.com.sinacontrol.controller.FuncionarioController;
import br.com.sinamodel.entidades.Funcionario;
import br.com.sinaview.form.sistema.MenssageErrorForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Danilo
 */
public class FuncionarioActionControl implements ActionListener {

    private FormFuncionario frm;
    private List<Funcionario> listFuncionarios;
    private Long idFuncionario;

    public FuncionarioActionControl(FormFuncionario frm) {
        this.frm = frm;
        addButoesToForm();
        refreshTable();
        enableFields(false);
        frm.getTxtId().setEnabled(false);
    }

    private void addButoesToForm() {
        frm.getBtNovo().addActionListener(this);
        frm.getBtAtualizar().addActionListener(this);
        frm.getBtSalvar().addActionListener(this);
        frm.getBtExcluir().addActionListener(this);
        frm.getBtCancelar().addActionListener(this);

    }

    private void onCancelar() {
        enableFields(false);
        frm.getTxtId().setText("");
        frm.getTxtNome().setText("");
        frm.getTxtSobrenome().setText("");

    }

    private void onSaveFuncionario() {
        Funcionario funcionario = new Funcionario();
        if (frm.getTxtNome().getText().length() > 0
                && frm.getTxtSobrenome().getText().length() > 0) {
            funcionario.setCargo(frm.getComboCargo().getSelectedItem().toString());
            funcionario.setNome(frm.getTxtNome().getText().toUpperCase());
            funcionario.setSobrenome(frm.getTxtSobrenome().getText().toUpperCase());
        } else {
            MenssageErrorForm.msgCamposObrigatorios();
            return;
        }

        int result = 0;

        if (idFuncionario == null) {
            result = new FuncionarioController().addFuncionario(funcionario);
        } else {
            funcionario.setId(idFuncionario);
            result = new FuncionarioController().updateFuncionario(funcionario);
            idFuncionario = null;
        }
        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Funcionário inserido com sucesso!");
            enableFields(false);
            onCancelar();
            refreshTable();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    private void onAlterarFuncionario() {
        int indexRow = frm.getTbFuncionario().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm,
                    "Selecione o funcionário a ser alterado!");
            return;
        }
        Funcionario funcionario = new FuncionarioTableModel(
                listFuncionarios).get(indexRow);
        idFuncionario = funcionario.getId();
        frm.getTxtId().setText(String.valueOf(funcionario.getId()));
        frm.getTxtNome().setText(funcionario.getNome());
        frm.getTxtSobrenome().setText(funcionario.getSobrenome());
        frm.getComboCargo().setSelectedItem(funcionario.getCargo());
        enableFields(true);
    }

    private void removerFuncionario() {
        int indexRow = frm.getTbFuncionario().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm,
                    "Selecione o funcionário a ser removido!");
            return;
        }
        Funcionario funcionario = new FuncionarioTableModel(
                listFuncionarios).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(frm,
                "Confirmar a exclusão?", "Excluir Funcionário",
                JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new FuncionarioController().excluirFuncionario(
                funcionario.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Funcionário removido com sucesso!");
            refreshTable();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salvar")) {
            onSaveFuncionario();
        } else if (e.getActionCommand().equals("Novo")) {
            enableFields(true);
        } else if (e.getActionCommand().equals("Cancelar")) {
            onCancelar();
        } else if (e.getActionCommand().equals("Excluir")) {
            removerFuncionario();
        } else if (e.getActionCommand().equals("Atualizar")) {
            onAlterarFuncionario();
        }

    }

    private void refreshTable() {
        listFuncionarios = new FuncionarioController().finfFuncionario();
        if (listFuncionarios != null) {
            frm.getTbFuncionario().setModel(new FuncionarioTableModel(
                    listFuncionarios));
            frm.getTbFuncionario().setDefaultRenderer(
                    Object.class, new FuncionarioCellRenderer());
        }
    }

    private void enableFields(boolean enable) {
        frm.getComboCargo().setEnabled(enable);
        frm.getTxtNome().setEnabled(enable);
        frm.getTxtSobrenome().setEnabled(enable);

    }
}
