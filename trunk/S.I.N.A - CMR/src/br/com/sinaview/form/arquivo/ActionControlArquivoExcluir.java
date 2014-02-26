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
import javax.swing.JOptionPane;

/**
 *
 * @author ritacosta
 */
public class ActionControlArquivoExcluir implements ActionListener {

    public ExcluirCaixa form;

    public ActionControlArquivoExcluir(ExcluirCaixa form) {
        this.form = form;
        addBtnToForm();

    }

    private void addBtnToForm() {
        form.getjButtonCancelar().addActionListener(this);
        form.getjButtonExluirCaixa().addActionListener(this);

    }

    private boolean verificarNumero() {

        if (form.getjFormattedTextFieldCaixaExlcuir().getText().isEmpty()) {
            MenssageErrorForm.msgVerificarNumeroArquivo();
            return false;
        } else {
            return true;
        }
    }

    public void excluirArquivo() {
        if (verificarNumero()) {
            Object[] options = {"Sim", "Não"};
            int i = JOptionPane.showOptionDialog(null, "Tem certeza que deseja excluir está caixa?",
                    "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (i == JOptionPane.YES_OPTION) {

                Arquivo arq = new Arquivo();
                ArquivoController cad = new ArquivoController();
                arq.setAno(String.valueOf(form.getjYearChooserAnoExlcuirCaixa().getYear()));
                arq.setNumero(Integer.parseInt(form.getjFormattedTextFieldCaixaExlcuir().getText()));
                cad.excluirArquivo(arq);
                MenssageErrorForm.msgExclusaoArquivo();
                form.getjFormattedTextFieldCaixaExlcuir().setText("");

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Excluir")) {
            excluirArquivo();
        } else if (e.getActionCommand().equals("Cancelar")) {
            form.dispose();
        }
    }
}
