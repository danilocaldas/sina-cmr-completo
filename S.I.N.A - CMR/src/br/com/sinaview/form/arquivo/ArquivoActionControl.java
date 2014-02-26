/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.arquivo;

import br.com.sinacontrol.controller.ArquivoController;
import br.com.sinamodel.entidades.Arquivo;
import br.com.sinaview.form.sistema.MenssageErrorForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ritacosta
 */
public class ArquivoActionControl implements ActionListener {

    public CadastroArquivo form;
    private Integer idArquivo;

    public ArquivoActionControl(CadastroArquivo form) {
        this.form = form;
        addBtnToForm();
        habilitarForm(false);
        iniciarCompenentes();

    }

    private void addBtnToForm() {
        form.getjButtonNovo().addActionListener(this);
        form.getjButtonCancelar().addActionListener(this);
        form.getjButtonExcluirPrestador().addActionListener(this);
        form.getjButtonExcluirProcedimento().addActionListener(this);
        form.getjButtonFechar().addActionListener(this);
        form.getjButtonSalvar().addActionListener(this);
    }

    private void habilitarForm(boolean enabled) {
        form.getjYearChooserCadastroAno().setEnabled(enabled);
        form.getjComboBoxMes().setEnabled(enabled);
        form.getjComboBoxCor().setEnabled(enabled);
        form.getjFormattedTextFieldCaixa().setEnabled(enabled);
        form.getjButtonSalvar().setEnabled(enabled);
        form.getjButtonCancelar().setEnabled(enabled);
        form.getjButtonExcluirPrestador().setEnabled(enabled);
        form.getjButtonExcluirProcedimento().setEnabled(enabled);
        form.getjListPrestador().setEnabled(enabled);
        form.getjListProcedimento().setEnabled(enabled);
    }

    private void limparPrestador() {
        form.getjTextAreaPrestador().setText("");
    }

    private void limparProcedimento() {
        form.getjTextAreaProcedimento().setText("");
    }

    public void limparForm() {
        form.getjFormattedTextFieldCaixa().setText("");

    }

    private boolean NumeroCaixa() {
        try {
            if (Integer.parseInt(String.valueOf(
                    form.getjFormattedTextFieldCaixa().getValue())) > 0) {
                return true;
            }
        } catch (NumberFormatException ex) {
            MenssageErrorForm.msgVerificarNumeroArquivo();

        }
        return false;
    }

    private void cadastrarCaixa() {
        Arquivo arquivo = new Arquivo();
        if (NumeroCaixa()) {
            arquivo.setNumero(Integer.parseInt(form.getjFormattedTextFieldCaixa().getText()));
            arquivo.setAno(String.valueOf(form.getjYearChooserCadastroAno().getYear()));
            arquivo.setMes(form.getjComboBoxMes().getSelectedItem().toString().toUpperCase());
            arquivo.setCor(form.getjComboBoxCor().getSelectedItem().toString().toUpperCase());
            arquivo.setPrestador(form.getjTextAreaPrestador().getText().toUpperCase());
            arquivo.setProcedimento(form.getjTextAreaProcedimento().getText().toUpperCase());
            form.getjFormattedTextFieldCaixa().setValue(Integer.parseInt(String.valueOf(form.getjFormattedTextFieldCaixa().getValue())) + 1);
        } else {
            MenssageErrorForm.msgCamposObrigatorios();
            return;
        }

        int result = 0;

        if (idArquivo == null) {
            result = new ArquivoController().salvarArquivo(arquivo);
        }
        if (result == 1) {
            MenssageErrorForm.msgInserirArquivo();
            habilitarForm(false);
            limparPrestador();
            limparProcedimento();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    private void iniciarCompenentes() {
        form.getjListProcedimento().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListProcedimentoMouseClicked(evt);
            }
        });

        form.getjListPrestador().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListPrestadorMouseClicked(evt);
            }
        });
    }

    private void jListProcedimentoMouseClicked(java.awt.event.MouseEvent evt) {
        StringBuffer buf = new StringBuffer();
        Object[] selectedValues = form.getjListProcedimento().getSelectedValues();
        for (int i = 0; i < selectedValues.length; i++) {
            buf.append(selectedValues[i].toString()).append(",\n");
        }
        form.getjTextAreaProcedimento().append(buf.toString());
    }

    private void jListPrestadorMouseClicked(java.awt.event.MouseEvent evt) {
        StringBuffer buf = new StringBuffer();
        Object[] selectedValues = form.getjListPrestador().getSelectedValues();
        for (int i = 0; i < selectedValues.length; i++) {
            buf.append(selectedValues[i].toString()).append(",\n");
        }
        form.getjTextAreaPrestador().append(buf.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Novo")) {
            MenssageErrorForm.msgBuscarNumeroCaixa();
            habilitarForm(true);
        } else if (e.getActionCommand().equals("Fechar")) {
            habilitarForm(false);
            limparPrestador();
            limparProcedimento();
            limparForm();
            form.dispose();
        } else if (e.getActionCommand().equals("Salvar")) {
            cadastrarCaixa();
        } else if (e.getActionCommand().equals("Cancelar")) {
            habilitarForm(false);
            limparPrestador();
            limparProcedimento();
        } else if (e.getActionCommand().equals("Limpar Prestadores")) {
            limparPrestador();
        } else if (e.getActionCommand().equals("Limpar Procedimentos")) {
            limparProcedimento();
        }
    }
}
