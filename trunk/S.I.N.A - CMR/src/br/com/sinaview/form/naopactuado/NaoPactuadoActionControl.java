/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.naopactuado;

import br.com.sinacontrol.controller.NaoPactuadoController;
import br.com.sinacontrol.controller.PrestadorController;
import br.com.sinacontrol.controller.ProcedimentoController;
import br.com.sinamodel.dao.CriaConexao;
import br.com.sinamodel.dao.ReportDao;
import br.com.sinamodel.entidades.NaoPactuados;
import br.com.sinamodel.entidades.Prestador;
import br.com.sinamodel.entidades.Procedimento;
import br.com.sinaview.form.sistema.MenssageErrorForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
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
public class NaoPactuadoActionControl implements ActionListener {

    private List<Prestador> prestadores;
    private List<Procedimento> procedimentos;
    private List<NaoPactuados> naoPactuados;
    public FormNaoPactuados form;
    private Long idNaoPactuados;

    public NaoPactuadoActionControl(FormNaoPactuados form) {
        this.form = form;
        addBtnToForm();
        refreshComboPesquisas();
        enableFilds(false);

    }

    private void addBtnToForm() {
        form.getBtNovo().addActionListener(this);
        form.getBtCancelar().addActionListener(this);
        form.getBtExcluir().addActionListener(this);
        form.getBtNovo().addActionListener(this);
        form.getBtAtualizar().addActionListener(this);
        form.getBtSalvar().addActionListener(this);
        form.getBtnImprimir().addActionListener(this);
        form.getBtnPesquisar().addActionListener(this);
    }

    private void refreshCombo() {
        prestadores = new PrestadorController().findNome("%%");
        procedimentos = new ProcedimentoController().finfNome("%%");

        form.getComboPrestador().removeAllItems();
        form.getComboProcedimento().removeAllItems();

        if (prestadores != null) {
            for (int i = 0; i < prestadores.size(); i++) {
                form.getComboPrestador().addItem(prestadores.get(i).getNome());
            }
        }
        if (prestadores != null) {
            for (int i = 0; i < procedimentos.size(); i++) {
                form.getComboProcedimento().addItem(procedimentos.get(i).getNome());
            }
        }
    }

    private void refreshComboPesquisas() {
        prestadores = new PrestadorController().findNome("%%");
        procedimentos = new ProcedimentoController().finfNome("%%");

        form.getComboPrestadorPesquisa().removeAllItems();
        form.getComboProcedimentoPesquisa().removeAllItems();

        if (prestadores != null) {
            for (int i = 0; i < prestadores.size(); i++) {
                form.getComboPrestadorPesquisa().addItem(prestadores.get(i).getNome());
            }
        }
        if (prestadores != null) {
            for (int i = 0; i < procedimentos.size(); i++) {
                form.getComboProcedimentoPesquisa().addItem(procedimentos.get(i).getNome());
            }
        }
    }

    private void mostraDadosTable() {
        if (naoPactuados != null) {
            form.getTbNaoPactuados().setModel(new NaoPactuadoTableModel(naoPactuados));
            form.getTbNaoPactuados().setDefaultRenderer(Object.class, new NaoPactuadoCellRenderer());

        }
    }

    private void refreshTablePrestador() {
        naoPactuados = new NaoPactuadoController().listPrestador(
                "%" + form.getComboPrestadorPesquisa().getSelectedItem().toString() + "%");
        mostraDadosTable();
    }

    private void refreshTableProcedimento() {
        naoPactuados = new NaoPactuadoController().listProcedimento(
                "%" + form.getComboProcedimentoPesquisa().getSelectedItem().toString() + "%");
        mostraDadosTable();
    }

    private void refreshTableMes() {


        naoPactuados = new NaoPactuadoController().listAnoMes(
                "%" + String.valueOf(form.getComboPesquisaMes().getSelectedItem().toString()) + "%",
                Integer.valueOf(form.getTxtAnoPesquisa().getText()));
        mostraDadosTable();
    }

    private void refreshTableMunicipio() {
        naoPactuados = new NaoPactuadoController().listMunicipios(
                "%" + form.getComboMunicipioPesquisa().getSelectedItem().toString() + "%");
        mostraDadosTable();
    }

    private void enableFilds(boolean enabled) {
        form.getComboPrestador().setEnabled(enabled);
        form.getComboProcedimento().setEnabled(enabled);
        form.getComboMes().setEnabled(enabled);
        form.getComboMunicipio().setEnabled(enabled);
        form.getTxtObservacao().setEnabled(enabled);
        form.getTxtPaciente().setEnabled(enabled);

    }

    private void onCancelar() {
        limparCombo();
        form.getTxtObservacao().setText("");
        form.getLabelID().setText("");
        form.getTxtPaciente().setText("");
        form.getTxtAno().setText("");
    }

    private void limparCombo() {
        form.getComboPrestador().removeAllItems();
        form.getComboProcedimento().removeAllItems();
    }

    private void onSaveNP() {
        NaoPactuados np = new NaoPactuados();
        np.setPaciente(form.getTxtPaciente().getText().toUpperCase());
        np.setPrestador(form.getComboPrestador().getSelectedItem().toString().toUpperCase());
        np.setProcedimento(form.getComboProcedimento().getSelectedItem().toString().toUpperCase());
        np.setMes(form.getComboMes().getSelectedItem().toString());
        np.setAno(Integer.parseInt(form.getTxtAno().getText()));
        np.setMunicipios(form.getComboMunicipio().getSelectedItem().toString());
        np.setObervacao(form.getTxtObservacao().getText().toUpperCase());

        int result = 0;
        if (idNaoPactuados == null) {
            result = new NaoPactuadoController().save(np);
        } else {
            np.setId(idNaoPactuados);
            result = new NaoPactuadoController().update(np);
            idNaoPactuados = null;
        }
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Registro inserido com sucesso!");
            this.form.getTxtPaciente().setText("");

        } else {
            JOptionPane.showMessageDialog(form, "Tente novamente!");
        }
    }

    private void onAlterarNP() {
        int indexRow = form.getTbNaoPactuados().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione um registro a ser alterado!");
            return;
        }
        enableFilds(true);
        refreshCombo();
        NaoPactuados np = new NaoPactuadoTableModel(naoPactuados).get(indexRow);
        idNaoPactuados = np.getId();
        form.getLabelID().setText(String.valueOf(np.getId()));
        form.getTxtPaciente().setText(np.getPaciente());
        form.getComboPrestador().setSelectedItem(np.getPrestador());
        form.getComboProcedimento().setSelectedItem(np.getProcedimento());
        form.getComboMes().setSelectedItem(np.getMes());
        form.getTxtAno().setText(String.valueOf(np.getAno()));
        form.getComboMunicipio().setSelectedItem(np.getMunicipios());
        form.getTxtObservacao().setText(np.getObervacao());

    }

    private void removerNP() {
        int indexRow = form.getTbNaoPactuados().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione um registro a ser removido!");
            return;
        }
        NaoPactuados np = new NaoPactuadoTableModel(naoPactuados).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(form, "Confirmar a exclusão?", "Excluir Registro", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new NaoPactuadoController().delete(np.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Registro removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(form, "Tente novamente!");
        }
    }

    public boolean validarCamposAno() {
        if (form.getTxtAnoPesquisa().getText() != "") {
            return true;
        }
        return false;
    }

    public void abrirRelNaoPactuado() {
        //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        //java.util.Date dataInicio = (java.util.Date) form.getTxtDataDe().getDate();
        //java.util.Date dataFim = (java.util.Date) form.getTxtDataAte().getDate();

        InputStream inputStream = getClass().getResourceAsStream("/relMainProdMedPerioNome.jasper");

        int ano = Integer.parseInt(form.getTxtAnoPesquisa().getText());
        String mes = form.getComboPesquisaMes().getSelectedItem().toString();

        Map parametros = new HashMap();
        parametros.put("ano", ano);
        parametros.put("mes", mes);

        try {
            ReportDao.openReport("Relatório de procedimentos realizados de Munícipios Não-Pactuados", inputStream, parametros,
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
        if (e.getActionCommand().equals("Salvar")) {
            onSaveNP();
            form.getTxtPaciente().requestFocus();
        } else if (e.getActionCommand().equals("Novo")) {
            enableFilds(true);
            refreshCombo();
        } else if (e.getActionCommand().equals("Cancelar")) {
            onCancelar();
            enableFilds(false);
        } else if (e.getActionCommand().equals("Excluir")) {
            removerNP();
        } else if (e.getActionCommand().equals("Atualizar")) {
            onAlterarNP();
        } else if (e.getActionCommand().equals("Pesquisar")) {

            if (form.getRadioPrestador().isSelected()) {
                refreshTablePrestador();
            }
            if (form.getRadioProcedimento().isSelected()) {
                refreshTableProcedimento();
            }

            if (form.getRadioMunicipio().isSelected()) {
                refreshTableMunicipio();
            }
            if (form.getRadioMes().isSelected()) {
                refreshTableMes();
            }

        } else if (e.getActionCommand().equals("Imprimir")) {

            if (form.getRadioPrestador().isSelected()) {
            }
            if (form.getRadioProcedimento().isSelected()) {
            }

            if (form.getRadioMunicipio().isSelected()) {
            }
            if (form.getRadioMes().isSelected()) {
                if(validarCamposAno()){
                    abrirRelNaoPactuado();
                }
            }
        }
    }
}
