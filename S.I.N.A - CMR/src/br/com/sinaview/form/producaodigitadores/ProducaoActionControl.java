/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.producaodigitadores;

import br.com.sinacontrol.controller.FuncionarioController;
import br.com.sinacontrol.controller.PrestadorController;
import br.com.sinacontrol.controller.ProcedimentoController;
import br.com.sinacontrol.controller.ProducaoController;
import br.com.sinamodel.dao.CriaConexao;
import br.com.sinamodel.dao.ReportDao;
import br.com.sinamodel.entidades.Funcionario;
import br.com.sinamodel.entidades.Prestador;
import br.com.sinamodel.entidades.Procedimento;
import br.com.sinamodel.entidades.Producao;
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
 * @author Danilo
 */
public class ProducaoActionControl implements ActionListener {

    public FormProducao frm;
    public List<Producao> listProducao;
    public List<Procedimento> listProdimento;
    public List<Prestador> listPrestador;
    public List<Funcionario> listFuncionario;
    private Long idProducao;

    public ProducaoActionControl(FormProducao frm) {
        this.frm = frm;
        addButoesToForm();
        //refreshTable();
        refreshCombo();
        enableFilds(false);
        frm.getTxtId().setEnabled(false);
    }

    private void refreshCombo() {
        listFuncionario = new FuncionarioController().findNomeDigitador();
        listPrestador = new PrestadorController().findNome("%%");
        listProdimento = new ProcedimentoController().finfNome("%%");
        frm.getComboFuncionario().removeAllItems();
        frm.getComboPrestador().removeAllItems();
        frm.getComboProcedimento().removeAllItems();

        frm.getComboPesquisaFuncionario().removeAllItems();
        frm.getComboPesquisaPrestador().removeAllItems();

        if (listFuncionario != null) {
            for (int i = 0; i < listFuncionario.size(); i++) {
                frm.getComboFuncionario().addItem(listFuncionario.get(i).getNome());
                frm.getComboPesquisaFuncionario().addItem(listFuncionario.get(i).getNome());
            }
        }
        if (listPrestador != null) {
            for (int i = 0; i < listPrestador.size(); i++) {
                frm.getComboPrestador().addItem(listPrestador.get(i).getNome());
                frm.getComboPesquisaPrestador().addItem(listPrestador.get(i).getNome());
            }
        }
        if (listProdimento != null) {
            for (int i = 0; i < listProdimento.size(); i++) {
                frm.getComboProcedimento().addItem(listProdimento.get(i).getNome());
            }
        }
    }

    private void mostraDadosTable() {
        if (listProducao != null) {
            frm.getTbProducao().setModel(new ProducaoTableModel(listProducao));
            frm.getTbProducao().setDefaultRenderer(Object.class, new ProducaoCellRenderer());
        }
    }

    private void refreshTable() {
        listProducao = new ProducaoController().listarProducao();
        mostraDadosTable();
        somarQtdAnalisada();
    }

    private void refreshTableFuncionario() {
        listProducao = new ProducaoController().listarProFuncionario("%" + frm.getComboPesquisaFuncionario().
                getSelectedItem().toString() + "%");
        mostraDadosTable();
        somarQtdAnalisada();
    }

    private void refreshTablePrestador() {
        listProducao = new ProducaoController().listarProPrestador("%" + frm.getComboPesquisaPrestador().
                getSelectedItem().toString() + "%");
        mostraDadosTable();
        somarQtdAnalisada();
    }

    private void refreshTablePeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicial = (java.util.Date) frm.getTxtDataDe().getDate();
        java.util.Date dataFinal = (java.util.Date) frm.getTxtDataAte().getDate();
        listProducao = new ProducaoController().listarProPeriodo(Date.valueOf(formato.format(dataInicial)),
                Date.valueOf(formato.format(dataFinal)));
        mostraDadosTable();
        somarQtdAnalisada();
    }

    private void refreshTableFuncionarioPeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataInicial = (java.util.Date) frm.getTxtDataDe().getDate();
        java.util.Date dataFinal = (java.util.Date) frm.getTxtDataAte().getDate();
        listProducao = new ProducaoController().listarProFunPeriodo(
                "%" + frm.getComboPesquisaFuncionario().getSelectedItem().toString() + "%",
                Date.valueOf(formato.format(dataInicial)),
                Date.valueOf(formato.format(dataFinal)));
        mostraDadosTable();
        somarQtdAnalisada();
    }

    private void enableFilds(boolean enabled) {
        frm.getComboFuncionario().setEnabled(enabled);
        frm.getComboPrestador().setEnabled(enabled);
        frm.getComboProcedimento().setEnabled(enabled);
        frm.getTxtDataEntrada().setEnabled(enabled);
        frm.getTxtDataDigitacao().setEnabled(enabled);
        frm.getTxtQuantidade().setEnabled(enabled);


    }

    private void onCancelar() {
        frm.getTxtDataDigitacao().setDate(null);
        frm.getTxtDataEntrada().setDate(null);
        frm.getTxtQuantidade().setText("");
        frm.getTxtId().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Salvar")) {
            onSaveProducao();
        } else if (e.getActionCommand().equals("Novo")) {
            enableFilds(true);
        } else if (e.getActionCommand().equals("Cancelar")) {
            onCancelar();
            enableFilds(false);
        } else if (e.getActionCommand().equals("Excluir")) {
            removerProducao();
        } else if (e.getActionCommand().equals("Atualizar")) {
            onAlterarProducao();
        } else if (e.getActionCommand().equals("Pesquisar")) {
            pesquisarProducaoGeral();
            pesquisarProdPorProfissional();
            pesquisarProdPorPrestador();
            pesquisarProdPorPeriodo();
            pesquisarProdPorPeriodoProfissional();
        } else if (e.getActionCommand().equals("Imprimir")) {
            abrirRelPesquisarProdDigiPerio();
            abrirRelPesquisarProdDigiPerioNome();
        }
    }

    private boolean verificarPreencherDatas() {
        if (frm.getTxtDataDigitacao().getDate() != null && frm.getTxtDataEntrada().getDate() != null && frm.getTxtQuantidade().getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void onSaveProducao() {
        Producao producao = new Producao();
        if (verificarPreencherDatas()) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataEntrada = (java.util.Date) frm.getTxtDataEntrada().getDate();
            java.util.Date dataDigitacao = (java.util.Date) frm.getTxtDataDigitacao().getDate();
            producao.setDataEntrada(Date.valueOf(formato.format(dataEntrada)));
            producao.setDataDigitacao(Date.valueOf(formato.format(dataDigitacao)));
            producao.setFuncionario(frm.getComboFuncionario().getSelectedItem().toString());
            producao.setPrestador(frm.getComboPrestador().getSelectedItem().toString());
            producao.setProcedimento(frm.getComboProcedimento().getSelectedItem().toString());

            producao.setQuantidade(frm.getTxtQuantidade().getText());

        } else {
            JOptionPane.showMessageDialog(frm, "Todos os campos são obrigatórios!");
            return;
        }

        int result = 0;
        if (idProducao == null) {
            result = new ProducaoController().salvarProducao(producao);
        } else {
            producao.setId(idProducao);
            result = new ProducaoController().atualizarProducao(producao);
            idProducao = null;
        }

        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Produção inserido com sucesso!");
            enableFilds(false);
            onCancelar();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frm, "Tente novamente!");
        }
    }

    private void onAlterarProducao() {
        int indexRow = frm.getTbProducao().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm, "Selecione a produção a ser alterada!");
            return;
        }
        Producao producao = new ProducaoTableModel(listProducao).get(indexRow);

        idProducao = producao.getId();
        frm.getTxtId().setText(String.valueOf(producao.getId()));
        frm.getComboFuncionario().setSelectedItem(producao.getFuncionario());
        frm.getComboPrestador().setSelectedItem(producao.getPrestador());
        frm.getComboProcedimento().setSelectedItem(producao.getProcedimento());

        frm.getTxtDataEntrada().setDate(producao.getDataEntrada());
        frm.getTxtDataDigitacao().setDate(producao.getDataDigitacao());
        frm.getTxtQuantidade().setText(producao.getQuantidade());
        enableFilds(true);

    }

    private void removerProducao() {
        int indexRow = frm.getTbProducao().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(frm, "Selecione a produção a ser removida!");
            return;
        }
        Producao producao = new ProducaoTableModel(listProducao).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(frm, "Confirmar a exclusão?", "Excluir Produção", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new ProducaoController().deletarProducao(producao.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(frm, "Produção removida com sucesso!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(frm, "Tente novamente!");
        }
    }

    private void addButoesToForm() {
        frm.getBtNovo().addActionListener(this);
        frm.getBtAtualizar().addActionListener(this);
        frm.getBtSalvar().addActionListener(this);
        frm.getBtExcluir().addActionListener(this);
        frm.getBtCancelar().addActionListener(this);
        frm.getBtPesquisar().addActionListener(this);
        frm.getRadioGeral().addActionListener(this);
        frm.getRadioFuncionario().addActionListener(this);
        frm.getRadioFuncionarioPeriodo().addActionListener(this);
        frm.getRadioPeriodo().addActionListener(this);
        frm.getRadioPrestador().addActionListener(this);
        frm.getBtnImprimir().addActionListener(this);
    }

    private boolean validarCamposDataPesquisa() {
        if (frm.getTxtDataDe().getDate() != null && frm.getTxtDataAte().getDate() != null) {
            return true;
        }
        JOptionPane.showMessageDialog(frm, "É necessário o preechimento do período desejado!", "Pesquisa", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }

    public void pesquisarProducaoGeral() {
        if (frm.getRadioGeral().isSelected()) {
            //limparTabela(producoes);
            refreshTable();
        }
    }

    public void pesquisarProdPorPeriodo() {
        if (frm.getRadioPeriodo().isSelected() && validarCamposDataPesquisa()) {
            //limparTabela(producoes);
            refreshTablePeriodo();
        }
    }

    public void pesquisarProdPorProfissional() {
        if (frm.getRadioFuncionario().isSelected()) {
            //limparTabela(producoes);
            refreshTableFuncionario();
        }
    }

    public void pesquisarProdPorPeriodoProfissional() {
        if (frm.getRadioFuncionarioPeriodo().isSelected() && validarCamposDataPesquisa()) {
            //limparTabela(producoes);
            refreshTableFuncionarioPeriodo();
        }
    }

    public void pesquisarProdPorPrestador() {
        if (frm.getRadioPrestador().isSelected()) {
            //limparTabela(producoes);
            refreshTablePrestador();
        }
    }

    private void somarQtdAnalisada() {
        int total = 0;
        String valor = "";
        int linhas = frm.getTbProducao().getRowCount();
        for (int i = 0; i < linhas; i++) {
            valor = String.valueOf(frm.getTbProducao().getValueAt(i, 6));
            total = total + Integer.parseInt(valor);
        }
        frm.getLabelTotal().setText("" + total);
    }

    public void abrirRelPesquisarProdDigiPerio() {
        if (frm.getRadioPeriodo().isSelected()) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date dataInicio = (java.util.Date) frm.getTxtDataDe().getDate();
            java.util.Date dataFim = (java.util.Date) frm.getTxtDataAte().getDate();

            InputStream inputStream = getClass().getResourceAsStream("/relMainProDigi.jasper");

            Map parametros = new HashMap();
            parametros.put("dataDe", Date.valueOf(formato.format(dataInicio)));
            parametros.put("dataAte", Date.valueOf(formato.format(dataFim)));

            try {
                ReportDao.openReport("Relatório Geral de Digitação - Período", inputStream, parametros,
                        CriaConexao.getConexao());
            } catch (SQLException exc) {
                exc.printStackTrace();
            } catch (JRException exc) {
                exc.printStackTrace();
                MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
            }
        }
    }

    public void abrirRelPesquisarProdDigiPerioNome() {

        if (frm.getRadioFuncionarioPeriodo().isSelected()) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date dataInicio = (java.util.Date) frm.getTxtDataDe().getDate();
            java.util.Date dataFim = (java.util.Date) frm.getTxtDataAte().getDate();

            InputStream inputStream = getClass().getResourceAsStream("/relProdDigiFunc.jasper");

            Map parametros = new HashMap();
            parametros.put("dataDe", Date.valueOf(formato.format(dataInicio)));
            parametros.put("dataAte", Date.valueOf(formato.format(dataFim)));
            parametros.put("funcionario", frm.getComboPesquisaFuncionario().getSelectedItem().toString());

            try {
                ReportDao.openReport("Relatório Geral de Digitação - Período|Funcionário", inputStream, parametros,
                        CriaConexao.getConexao());
            } catch (SQLException exc) {
                exc.printStackTrace();
            } catch (JRException exc) {
                exc.printStackTrace();
                MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
            }
        }
    }
}
