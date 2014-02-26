/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.arquivo;


import br.com.sinamodel.dao.CriaConexao;
import br.com.sinamodel.dao.ReportDao;
import br.com.sinaview.form.sistema.MenssageErrorForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author ritacosta
 */
public class ConsultaArquivoActionControl implements ActionListener {

    public ConsultaCaixa form;

    public ConsultaArquivoActionControl(ConsultaCaixa form) {
        this.form = form;
        addBtnToForm();
    }
    
    
    private void addBtnToForm(){
        form.getjButtonOK().addActionListener(this);
        form.getjButtonSair().addActionListener(this);
    }

    public void abrirRelPesquisaCaixaAno() throws Exception {

        InputStream inputStream = getClass().getResourceAsStream("/relPesquisaCaixaAno.jasper");

        Map parametros = new HashMap();
        parametros.put("ano", form.getjYearChooserAno().getYear());


        try {
            ReportDao.openReport("Relatório Anual Arquivo", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    public void abrirRelPesquisaCaixaAnoMes() throws Exception {

        InputStream inputStream = getClass().getResourceAsStream("/relPesquisaCaixaAnoMes.jasper");

        Map parametros = new HashMap();
        parametros.put("ano", form.getjYearChooserAno().getYear());
        parametros.put("mes", form.getjComboBoxMes().getSelectedItem().toString());

        try {
            ReportDao.openReport("Relatório Mensal Arquivo", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    public void abrirRelPesquisaFrenteCaixa() throws Exception {
        /*
         * Obtendo o arquivo do relatorio
         * note que estamos utilizando um InputStrem para obter o arquivo
         * que esta dentro do nosso projeto. fazendo isso
         * não teremos problemas quando nosso projeto for empacotado em um .jar
         * 
         * note que o caminho do .jasper inicia com /, ou seja, 
         * a raiz da localização das classes compiladas no nosso projeto
         * 
         * 
         */
        InputStream inputStream = getClass().getResourceAsStream("/relFrenteCaixa.jasper");

        Map parametros = new HashMap();
        parametros.put("ano", form.getjYearChooserAno().getYear());
        parametros.put("mes", form.getjComboBoxMes().getSelectedItem().toString());

        try {
            ReportDao.openReport("Folha de Rosto", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    public void abrirRelPesquisaCaixaNumero() throws Exception {
        /*
         * Obtendo o arquivo do relatorio
         * note que estamos utilizando um InputStrem para obter o arquivo
         * que esta dentro do nosso projeto. fazendo isso
         * não teremos problemas quando nosso projeto for empacotado em um .jar
         * 
         * note que o caminho do .jasper inicia com /, ou seja, 
         * a raiz da localização das classes compiladas no nosso projeto
         * 
         * 
         */
        InputStream inputStream = getClass().getResourceAsStream("/relPesquisaCaixa.jasper");

        Map parametros = new HashMap();
        parametros.put("ano", form.getjYearChooserAno().getYear());
        parametros.put("mes", form.getjComboBoxMes().getSelectedItem().toString());
        parametros.put("numero", Integer.parseInt(String.valueOf(form.getjFormattedTextFieldCaixa().getValue())));

        try {
            ReportDao.openReport("Relatório Anual", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Efetuar Pesquisa")) {
            if (form.getjRadioButtonPesquisarAno().isSelected()) {
                try {
                    abrirRelPesquisaCaixaAno();
                } catch (Exception ex) {
                    Logger.getLogger(ConsultaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (form.getjRadioButtonPesquisarAnoMEs().isSelected()) {
                try {
                    abrirRelPesquisaCaixaAnoMes();
                } catch (Exception ex) {
                    Logger.getLogger(ConsultaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (form.getjRadioButtonContCaixa().isSelected()) {
                if (form.getjFormattedTextFieldCaixa().getValue() != null) {
                    try {
                        abrirRelPesquisaCaixaNumero();
                    } catch (Exception ex) {
                        Logger.getLogger(ConsultaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    MenssageErrorForm.msgErroValidaçãoConsultaCaixa();
                }
            } else if (form.getjRadioButtonFolhaRosto().isSelected()) {
                try {
                    abrirRelPesquisaFrenteCaixa();
                } catch (Exception ex) {
                    Logger.getLogger(ConsultaCaixa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                MenssageErrorForm.msgSelecaoModoPesquisa();
            }
        }else if(e.getActionCommand().equals("Finalizar")){
            form.dispose();
        }
    }
}
