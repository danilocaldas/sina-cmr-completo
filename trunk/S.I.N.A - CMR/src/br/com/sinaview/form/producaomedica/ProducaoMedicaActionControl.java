/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.producaomedica;


import br.com.sinacontrol.controller.FuncionarioController;
import br.com.sinacontrol.controller.PrestadorController;
import br.com.sinacontrol.controller.ProcedimentoController;
import br.com.sinacontrol.controller.ProducaoMedicaController;
import br.com.sinamodel.dao.CriaConexao;
import br.com.sinamodel.dao.ReportDao;
import br.com.sinamodel.entidades.Funcionario;
import br.com.sinamodel.entidades.Prestador;
import br.com.sinamodel.entidades.Procedimento;
import br.com.sinamodel.entidades.ProducaoMedica;
import br.com.sinaview.form.sistema.MenssageErrorForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author ritacosta
 */
public class ProducaoMedicaActionControl implements ActionListener {

    public FormProducaoMedica form;
    public List<Procedimento> listProdimento;
    public List<Prestador> listPrestador;
    public List<Funcionario> listFuncionario;
    public List<ProducaoMedica> listProducaoMedica;
    private Long idProducaoMedica;

    public ProducaoMedicaActionControl(FormProducaoMedica form) {
        this.form = form;
        addControleBtn();
        refreshComboPesquisaMedica();
        
        enableFilds(false);
    }

    private void addControleBtn() {
        form.getBtNovo().addActionListener(this);
        form.getBtAtualizar().addActionListener(this);
        form.getBtSalvar().addActionListener(this);
        form.getBtExcluir().addActionListener(this);
        form.getBtCancelar().addActionListener(this);
        form.getBtPesquisar().addActionListener(this);
        form.getBtGerarRelatorio().addActionListener(this);

    }

    public void refreshCombo() {
        listFuncionario = new FuncionarioController().findNomeMedico();
        listPrestador = new PrestadorController().findNome("%%");
        listProdimento = new ProcedimentoController().finfNome("%%");
        form.getComboFuncionario().removeAllItems();
        form.getComboPrestador().removeAllItems();
        form.getComboProcedimento().removeAllItems();

        if (listFuncionario != null) {
            for (int i = 0; i < listFuncionario.size(); i++) {
                form.getComboFuncionario().addItem(listFuncionario.get(i).getNome());
            }
        }
        if (listPrestador != null) {
            for (int i = 0; i < listPrestador.size(); i++) {
                form.getComboPrestador().addItem(listPrestador.get(i).getNome());
            }
        }
        if (listProdimento != null) {
            for (int i = 0; i < listProdimento.size(); i++) {
                form.getComboProcedimento().addItem(listProdimento.get(i).getNome());
            }
        }
    }

    private void refreshComboPesquisaMedica() {
        listFuncionario = new FuncionarioController().findNomeMedico();
        form.getComboPesquisaMedico().removeAllItems();

        if (listFuncionario != null) {
            for (int i = 0; i < listFuncionario.size(); i++) {

                form.getComboPesquisaMedico().addItem(listFuncionario.get(i).getNome());
            }
        }
    }

    private void mostraDadosTable() {
        if (listProducaoMedica != null) {
            form.getTbProducaoMedica().setModel(new ProducaoMedicaTableModel(listProducaoMedica));
            form.getTbProducaoMedica().setDefaultRenderer(Object.class, new ProducaoMedicaCellRenderer());
        }
    }

    private void refreshTable() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataDe = (java.util.Date) form.getTxtDataDe().getDate();
        java.util.Date dataAte = (java.util.Date) form.getTxtDataAte().getDate();
        listProducaoMedica = new ProducaoMedicaController().listarProducao(
                Date.valueOf(formato.format(dataDe)), Date.valueOf(formato.format(dataAte)));
        mostraDadosTable();
        somarQtdAnalisada();

    }

    private void refreshTableNomePeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataDe = (java.util.Date) form.getTxtDataDe().getDate();
        java.util.Date dataAte = (java.util.Date) form.getTxtDataAte().getDate();
        listProducaoMedica = new ProducaoMedicaController().listarProducaoFunMedico(
                "%" + form.getComboPesquisaMedico().getSelectedItem().toString() + "%",
                Date.valueOf(formato.format(dataDe)), Date.valueOf(formato.format(dataAte)));
        mostraDadosTable();
        somarQtdAnalisada();

    }

    private void enableFilds(boolean enabled) {
        form.getTxtDataEntrada().setEnabled(enabled);
        form.getComboPrestador().setEnabled(enabled);
        form.getComboProcedimento().setEnabled(enabled);
        form.getTxtQtdLaudos().setEnabled(enabled);
        form.getTxtDataAnalise().setEnabled(enabled);
        form.getComboFuncionario().setEnabled(enabled);
        form.getTxtEncaminhamento().setEnabled(enabled);
        form.getComboNucleos().setEnabled(enabled);
    }

    private void onCancelar() {
        limparCombo();
        form.getTxtDataAnalise().setDate(null);
        form.getTxtEncaminhamento().setDate(null);
        form.getTxtDataEntrada().setDate(null);
        form.getTxtQtdLaudos().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salvar")) {
            onSaveProducao();
        } else if (e.getActionCommand().equals("Novo")) {
            enableFilds(true);
           refreshCombo();
        } else if (e.getActionCommand().equals("Cancelar")) {
            onCancelar();
            enableFilds(false);
        } else if (e.getActionCommand().equals("Excluir")) {
            removerProducao();
        } else if (e.getActionCommand().equals("Atualizar")) {
            onAlterarProducao();
        } else if (e.getActionCommand().equals("PESQUISAR")) {
            if (verificarPreencherDatasPesquisa()) {
                if (form.getRadioMedico().isSelected()) {
                    refreshTableNomePeriodo();
                }
                if (form.getRadioPeriodo().isSelected()) {
                    refreshTable();
                }
            }
        } else if (e.getActionCommand().equals("Gerar Relatório")) {
            if (verificarPreencherDatasPesquisa()) {
                if (form.getRadioMedicoRelatorio().isSelected()) {
                    abrirRelPesquisarProMedicaNome();
                }
                if (form.getRadioPeriodoRelatorio().isSelected()) {
                    abrirRelPesquisarProdMedica();
                }
            }
        }
    }

    private boolean verificarPreencherDatasPesquisa() {
        if (form.getTxtDataDe().getDate() != null
                && form.getTxtDataAte().getDate() != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(form, "As datas são necessárias para pesquisa.",
                    "Validação", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean verificarPreencherDatas() {
        if (form.getTxtDataEntrada().getDate() != null && form.getTxtDataAnalise().getDate() != null
                && form.getTxtEncaminhamento().getDate() != null) {
            return true;
        } else {
            return false;
        }
    }

    private void onSaveProducao() {
        ProducaoMedica pMedica = new ProducaoMedica();
        if (verificarPreencherDatas()) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataEntrada = (java.util.Date) form.getTxtDataEntrada().getDate();
            java.util.Date dataAnalise = (java.util.Date) form.getTxtDataAnalise().getDate();
            java.util.Date dataEncaminhamento = (java.util.Date) form.getTxtEncaminhamento().getDate();
            pMedica.setEntradaCmr(Date.valueOf(formato.format(dataEntrada)));
            pMedica.setPrestador(form.getComboPrestador().getSelectedItem().toString());
            pMedica.setProcedimento(form.getComboProcedimento().getSelectedItem().toString());
            pMedica.setQuantidade(Integer.parseInt(form.getTxtQtdLaudos().getText()));
            pMedica.setAnalise(Date.valueOf(formato.format(dataAnalise)));
            pMedica.setFuncionario(form.getComboFuncionario().getSelectedItem().toString());
            pMedica.setEncaminhamento(Date.valueOf(formato.format(dataEncaminhamento)));
            pMedica.setNucleos(form.getComboNucleos().getSelectedItem().toString());
        } else {
            JOptionPane.showMessageDialog(form, "Todos os campos são obrigatórios!");
            return;
        }

        int result = 0;
        if (idProducaoMedica == null) {
            result = new ProducaoMedicaController().salvarProducaoMedica(pMedica);
        } else {
            pMedica.setId(idProducaoMedica);
            result = new ProducaoMedicaController().atualizarProducaoMedica(pMedica);
            idProducaoMedica = null;
        }
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Produção inserido com sucesso!");
            //enableFilds(false);
            //onCancelar();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    private void onAlterarProducao() {
        int indexRow = form.getTbProducaoMedica().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione a produção a ser alterada!");
            return;
        }
        enableFilds(true);
        refreshCombo();
        ProducaoMedica pMedica = new ProducaoMedicaTableModel(listProducaoMedica).get(indexRow);
        idProducaoMedica = pMedica.getId();
        form.getLabelID().setText(String.valueOf(pMedica.getId()));
        form.getTxtDataEntrada().setDate(pMedica.getEntradaCmr());
        form.getComboPrestador().setSelectedItem(pMedica.getPrestador());
        form.getComboProcedimento().setSelectedItem(pMedica.getProcedimento());
        form.getTxtQtdLaudos().setText(String.valueOf(pMedica.getQuantidade()));
        form.getTxtDataAnalise().setDate(pMedica.getAnalise());
        form.getComboFuncionario().setSelectedItem(pMedica.getFuncionario());
        form.getTxtEncaminhamento().setDate(pMedica.getEncaminhamento());
        form.getComboNucleos().setSelectedItem(pMedica.getNucleos());
        
    }

    private void removerProducao() {
        int indexRow = form.getTbProducaoMedica().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione a produção a ser removida!");
            return;
        }
        ProducaoMedica pMedica = new ProducaoMedicaTableModel(listProducaoMedica).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(form, "Confirmar a exclusão?", "Excluir Produção", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new ProducaoMedicaController().deletarProducao(pMedica.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Produção removida com sucesso!");
            refreshTableNomePeriodo();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    private void somarQtdAnalisada() {
        int total = 0;
        String valor = "";
        int linhas = form.getTbProducaoMedica().getRowCount();
        for (int i = 0; i < linhas; i++) {
            valor = String.valueOf(form.getTbProducaoMedica().getValueAt(i, 4));
            total = total + Integer.parseInt(valor);
        }
        form.getLabelTotal().setText("" + total);
    }

    private void limparCombo() {
        form.getComboFuncionario().removeAllItems();
        form.getComboPrestador().removeAllItems();
        form.getComboProcedimento().removeAllItems();
    }

    public void abrirRelPesquisarProdMedica() {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date dataInicio = (java.util.Date) form.getTxtDataDe().getDate();
        java.util.Date dataFim = (java.util.Date) form.getTxtDataAte().getDate();

        InputStream inputStream = getClass().getResourceAsStream("/relMainProdMedPeriodo.jasper");

        Map parametros = new HashMap();
        parametros.put("dataDe", Date.valueOf(formato.format(dataInicio)));
        parametros.put("dataAte", Date.valueOf(formato.format(dataFim)));

        try {
            ReportDao.openReport("Relatório Geral de Análise Médica", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    public void abrirRelPesquisarProMedicaNome() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicio = (java.util.Date) form.getTxtDataDe().getDate();
        java.util.Date dataFim = (java.util.Date) form.getTxtDataAte().getDate();

        InputStream inputStream = getClass().getResourceAsStream("/relMainProdMedPerioNome.jasper");

        Map parametros = new HashMap();
        parametros.put("dataDe", Date.valueOf(formato.format(dataInicio)));
        parametros.put("dataAte", Date.valueOf(formato.format(dataFim)));
        parametros.put("funcionario", form.getComboPesquisaMedico().getSelectedItem().toString());

        try {
            ReportDao.openReport("Relatório de Análise Médica", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }
}
