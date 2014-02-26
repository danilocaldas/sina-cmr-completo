/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.saidalaudos;

import br.com.sinacontrol.controller.BoletimEstatisticoEntregaController;
import br.com.sinacontrol.controller.PrestadorController;
import br.com.sinacontrol.controller.ProcedimentoController;
import br.com.sinamodel.dao.CriaConexao;
import br.com.sinamodel.dao.ReportDao;
import br.com.sinamodel.entidades.BoletimEstatisticoEntrega;
import br.com.sinamodel.entidades.Prestador;
import br.com.sinamodel.entidades.Procedimento;
import br.com.sinaview.form.entradalaudos.BoletimEstatisticoCellRenderer;
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
public class FormBoletimEstatisticoEntradaActionControl implements ActionListener {

    public FomrBoletimEstatisticoEntrega form;
    private Long idBoletim;
    private List<BoletimEstatisticoEntrega> listBoletim;
    private List<Prestador> prestadores;
    private List<Procedimento> procedimentos;

    public FormBoletimEstatisticoEntradaActionControl(FomrBoletimEstatisticoEntrega form) {
        this.form = form;
        addBtnToForm();
        refreshCombo();
        enableFilds(false);
        refreshComboPesquisas();
    }

    private void addBtnToForm() {
        form.getBtNovo().addActionListener(this);
        form.getBtAtualizar().addActionListener(this);
        form.getBtExcluir().addActionListener(this);
        form.getBtCancelar().addActionListener(this);
        form.getBtSalvar().addActionListener(this);
        form.getBtnPesquisar().addActionListener(this);
        form.getBtnImprimir().addActionListener(this);
    }

    private void enableFilds(boolean enabled) {
        form.getComboDia().setEnabled(enabled);
        form.getComoPrestador().setEnabled(enabled);
        form.getComoProcedimento().setEnabled(enabled);
        form.getComboQtd().setEnabled(enabled);
    }

    private void onCancelar() {
        form.getComboQtd().setText(null);
        form.getLabelID().setText(null);
        enableFilds(false);
    }

    private boolean verificarPreencherCampos() {
        if (form.getComboDia().getDate() == null && form.getComboQtd().getText().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    private void onSaveProducao() {
        BoletimEstatisticoEntrega boletim = new BoletimEstatisticoEntrega();
        if (verificarPreencherCampos()) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date dataEntrada = (java.util.Date) form.getComboDia().getDate();

            boletim.setPrestador(form.getComoPrestador().getSelectedItem().toString().toUpperCase());

            boletim.setProcedimento(form.getComoProcedimento().getSelectedItem().toString().toUpperCase());

            boletim.setDiaUtil(Date.valueOf(formato.format(dataEntrada)));

            boletim.setQtd(Integer.parseInt(form.getComboQtd().getText()));
        } else {
            MenssageErrorForm.msgCamposObrigatorios();
            return;
        }

        int result = 0;
        if (idBoletim == null) {
            result = new BoletimEstatisticoEntregaController().save(boletim);
        } else {
            boletim.setId(idBoletim);
            result = new BoletimEstatisticoEntregaController().update(boletim);
            idBoletim = null;
            refreshTableGeral();
        }
        if (result == 1) {
            MenssageErrorForm.msgInserirBoletim();
            onCancelar();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    private void onAlterarProducao() {
        int indexRow = form.getTbBoletimEstatitiscoRecebimento().getSelectedRow();
        if (indexRow == -1) {
            MenssageErrorForm.msgSelecaoAlterarBoletim();
            return;
        }
        refreshCombo();
        enableFilds(true);
        BoletimEstatisticoEntrega boletim = new BoletimEstatisticoEntradaTableModel(listBoletim).get(indexRow);
        idBoletim = boletim.getId();
        form.getLabelID().setText(String.valueOf(boletim.getId()));
        form.getComboDia().setDate(boletim.getDiaUtil());
        form.getComoPrestador().setSelectedItem(boletim.getPrestador());
        form.getComoProcedimento().setSelectedItem(boletim.getProcedimento());
        form.getComboQtd().setText(String.valueOf(boletim.getQtd()));


    }

    private void removerProducao() {
        int indexRow = form.getTbBoletimEstatitiscoRecebimento().getSelectedRow();
        if (indexRow == -1) {
            MenssageErrorForm.msgSelecaoExcluirBoletim();
            return;
        }
        BoletimEstatisticoEntrega boletim = new BoletimEstatisticoEntradaTableModel(listBoletim).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(form, "Confirmar a exclusão?", "Excluir Boletim", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new BoletimEstatisticoEntregaController().delete(boletim.getId());
        if (result == 1) {
            MenssageErrorForm.msgExcluirBoletim();
            refreshTableGeral();
        } else {
            MenssageErrorForm.msgErroTenteNovamente();
        }
    }

    /*
     * Formas de pesquisas
     */
    private void mostraDadosTable() {
        if (listBoletim != null) {
            form.getTbBoletimEstatitiscoRecebimento().setModel(new BoletimEstatisticoEntradaTableModel(listBoletim));
            form.getTbBoletimEstatitiscoRecebimento().setDefaultRenderer(Object.class, new BoletimEstatisticoCellRenderer());
            somarQtdBoletim();
        }
    }

    private void refreshTableGeral() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date de = (java.util.Date) form.getComboPesquisaDe().getDate();
        java.util.Date ate = (java.util.Date) form.getComboPesquisaAte().getDate();
        listBoletim = new BoletimEstatisticoEntregaController().list(Date.valueOf(formato.format(de)),
                Date.valueOf(formato.format(ate)));
        mostraDadosTable();
    }

    private void refreshTablePrestadorPeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date de = (java.util.Date) form.getComboPesquisaDe().getDate();
        java.util.Date ate = (java.util.Date) form.getComboPesquisaAte().getDate();
        listBoletim = new BoletimEstatisticoEntregaController().list(
                "%" + form.getComboPesquisaPrestador().getSelectedItem().toString() + "%",
                Date.valueOf(formato.format(de)),
                Date.valueOf(formato.format(ate)));

        mostraDadosTable();
    }

    private void refreshTablePrestadorProcePeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date de = (java.util.Date) form.getComboPesquisaDe().getDate();
        java.util.Date ate = (java.util.Date) form.getComboPesquisaAte().getDate();
        listBoletim = new BoletimEstatisticoEntregaController().list(
                "%" + form.getComboPesquisaPrestador().getSelectedItem().toString() + "%",
                "%" + form.getComboPesquisaProcedimento().getSelectedItem().toString() + "%",
                Date.valueOf(formato.format(de)),
                Date.valueOf(formato.format(ate)));

        mostraDadosTable();
    }

    public void pesquisarBoletimGeral() {
        if (form.getRadioGeral().isSelected()
                && form.getComboPesquisaDe().getDate() != null
                && form.getComboPesquisaAte().getDate() != null) {
            refreshTableGeral();
        }
    }

    public void pesquisarBoletimPrestadorPeriodo() {
        if (form.getRadioPrestadorPeriodo().isSelected()
                && form.getComboPesquisaDe().getDate() != null
                && form.getComboPesquisaAte().getDate() != null) {
            refreshTablePrestadorPeriodo();
        }
    }

    public void pesquisarBoletimPrestadorProcPeriodo() {
        if (form.getRadioPrestProcePerio().isSelected()
                && form.getComboPesquisaDe().getDate() != null
                && form.getComboPesquisaAte().getDate() != null) {
            refreshTablePrestadorProcePeriodo();
        }
    }

    //fim
    private void somarQtdBoletim() {
        int total = 0;
        String valor = "";
        int linhas = form.getTbBoletimEstatitiscoRecebimento().getRowCount();
        for (int i = 0; i < linhas; i++) {
            valor = String.valueOf(form.getTbBoletimEstatitiscoRecebimento().getValueAt(i, 4));
            total = total + Integer.parseInt(valor);
        }
        form.getLabelTotal().setText("Quantidade Total é: " + total);
    }

    private void refreshCombo() {

        prestadores = new PrestadorController().findNome("%%");
        procedimentos = new ProcedimentoController().finfNome("%%");
        form.getComoPrestador().removeAllItems();
        form.getComoProcedimento().removeAllItems();
        if (prestadores != null) {
            for (int i = 0; i < prestadores.size(); i++) {
                form.getComoPrestador().addItem(prestadores.get(i).getNome());
            }
        }
        if (procedimentos != null) {
            for (int i = 0; i < procedimentos.size(); i++) {
                form.getComoProcedimento().addItem(procedimentos.get(i).getNome());
            }
        }
    }

    private void refreshComboPesquisas() {
        prestadores = new PrestadorController().findNome("%%");
        form.getComboPesquisaPrestador().removeAllItems();
        procedimentos = new ProcedimentoController().finfNome("%%");
        form.getComboPesquisaProcedimento().removeAllItems();

        if (prestadores != null) {
            for (int i = 0; i < prestadores.size(); i++) {
                form.getComboPesquisaPrestador().addItem(prestadores.get(i).getNome());
            }
        }
        if (procedimentos != null) {
            for (int i = 0; i < procedimentos.size(); i++) {
                form.getComboPesquisaProcedimento().addItem(procedimentos.get(i).getNome());
            }
        }

    }

    public void abrirRelPesquisarBoletimGeral() {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date de = (java.util.Date) form.getComboPesquisaDe().getDate();
        java.util.Date ate = (java.util.Date) form.getComboPesquisaAte().getDate();

        InputStream inputStream = getClass().getResourceAsStream("/relMainBoletimEntregaGeral.jasper");

        Map parametros = new HashMap();
        parametros.put("de", Date.valueOf(formato.format(de)));
        parametros.put("ate", Date.valueOf(formato.format(ate)));
        try {
            ReportDao.openReport("Boletim Estatístico de entrega de laudos - GERAL", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    public void abrirRelPesquisarBoletimPrestadorNome() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date de = (java.util.Date) form.getComboPesquisaDe().getDate();
        java.util.Date ate = (java.util.Date) form.getComboPesquisaAte().getDate();

        InputStream inputStream = getClass().getResourceAsStream("/relMainBoletimEntregaPrestadorPeriodo.jasper");

        Map parametros = new HashMap();
        parametros.put("de", Date.valueOf(formato.format(de)));
        parametros.put("ate", Date.valueOf(formato.format(ate)));
        parametros.put("prestador", form.getComboPesquisaPrestador().getSelectedItem().toString());

        try {
            ReportDao.openReport("Boletim Estatístico de entrega de laudos - MENSAL", inputStream, parametros,
                    CriaConexao.getConexao());
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (JRException exc) {
            exc.printStackTrace();
            MenssageErrorForm.msgErrorRelatorioNãoEncontrado();
        }
    }

    public void abrirRelPesquisarBoletimPresProcPeriodo() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date de = (java.util.Date) form.getComboPesquisaDe().getDate();
        java.util.Date ate = (java.util.Date) form.getComboPesquisaAte().getDate();

        InputStream inputStream = getClass().getResourceAsStream("/relMainBoletimEntregaPresProcPerio.jasper");

        Map parametros = new HashMap();
        parametros.put("de", Date.valueOf(formato.format(de)));
        parametros.put("ate", Date.valueOf(formato.format(ate)));
        parametros.put("prestador", form.getComboPesquisaPrestador().getSelectedItem().toString());
        parametros.put("procedimento", form.getComboPesquisaProcedimento().getSelectedItem().toString());

        try {
            ReportDao.openReport("Boletim Estatístico de entrega de laudos - Prestador|Procedimento|Período", inputStream, parametros,
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
        if (e.getActionCommand().equals("Novo")) {
            enableFilds(true);
        } else if (e.getActionCommand().equals("Atualizar")) {
            onAlterarProducao();
        } else if (e.getActionCommand().equals("Salvar")) {
            onSaveProducao();
        } else if (e.getActionCommand().equals("Excluir")) {
            removerProducao();
        } else if (e.getActionCommand().equals("Cancelar")) {
            onCancelar();
        } else if (e.getActionCommand().equals("Pesquisar")) {
            pesquisarBoletimGeral();
            pesquisarBoletimPrestadorPeriodo();
            pesquisarBoletimPrestadorProcPeriodo();
        } else if (e.getActionCommand().equals("Imprimir")) {
            if (form.getRadioPrestadorPeriodo().isSelected()) {
                abrirRelPesquisarBoletimPrestadorNome();
            } else if (form.getRadioGeral().isSelected()) {
                abrirRelPesquisarBoletimGeral();
            } else if (form.getRadioPrestProcePerio().isSelected()) {
                abrirRelPesquisarBoletimPresProcPeriodo();
            }
        }
    }
}
