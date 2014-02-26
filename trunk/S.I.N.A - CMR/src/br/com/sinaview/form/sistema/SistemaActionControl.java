/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.sistema;

import br.com.sinaview.form.acesso.Acesso;
import br.com.sinaview.form.arquivo.CadastroArquivo;
import br.com.sinaview.form.arquivo.ConsultaCaixa;
import br.com.sinaview.form.arquivo.ExcluirCaixa;
import br.com.sinaview.form.entradalaudos.FomrBoletimRecebimentoEstatistico;
import br.com.sinaview.form.funcionario.FormFuncionario;
import br.com.sinaview.form.naopactuado.FormNaoPactuados;
import br.com.sinaview.form.prestador.FormPrestador;
import br.com.sinaview.form.procedimento.FormProcedimemto;
import br.com.sinaview.form.producaodigitadores.FormProducao;
import br.com.sinaview.form.producaomedica.FormProducaoMedica;
import br.com.sinaview.form.saidalaudos.FomrBoletimEstatisticoEntrega;
import br.com.sinaview.form.usuario.CadastroUsuarios;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ritacosta
 */
public class SistemaActionControl implements ActionListener {

    private FormSistema form;
    private FormProducaoMedica formMedica;
    private FormPrestador formSisPres;
    private FormProcedimemto formSisProc;
    private FormFuncionario formSisFunc;
    private FormProducao formSisFormProdu;
    private CadastroUsuarios formUsuarios;
    private CadastroArquivo formCadArquivo;
    private ConsultaCaixa formConsArquivo;
    private ExcluirCaixa formExcArquivo;
    private FomrBoletimRecebimentoEstatistico formBOER;
    private FomrBoletimEstatisticoEntrega formBOEE;
    private FormNaoPactuados formNP;

    public SistemaActionControl(FormSistema form) {
        this.form = form;
        addControlBtMenusForm();

    }

    private void addControlBtMenusForm() {
        this.form.getMenuProducaoMedica().addActionListener(this);
        this.form.getMenuPrestadores().addActionListener(this);
        this.form.getMenuProcedimentos().addActionListener(this);
        this.form.getMenuFuncionarios().addActionListener(this);
        this.form.getMenuProdDigitadores().addActionListener(this);
        this.form.getMenuTrocarUsuario().addActionListener(this);
        this.form.getMenuSair().addActionListener(this);
        this.form.getMenuUser().addActionListener(this);
        this.form.getMenuCadastroArquivo().addActionListener(this);
        this.form.getMenuConsultarArquivo().addActionListener(this);
        this.form.getMenuExcluirArquivo().addActionListener(this);
        this.form.getMenuBOER().addActionListener(this);
        this.form.getMenuBOEE().addActionListener(this);
        this.form.getMenuRelNP().addActionListener(this);
    }

    private void abrirProducaoMedica() {

        if (formMedica == null) {
            formMedica = new FormProducaoMedica();
            form.getjDesktopPaneSistema().add(formMedica);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formMedica.getWidth()) / 2;
            int y = (content.getHeight() - formMedica.getHeight()) / 2;
            formMedica.setLocation(x, y);
            formMedica.setVisible(true);
        } else {
            if (formMedica.isVisible()) {
                formMedica.pack();
            } else {
                form.getjDesktopPaneSistema().add(formMedica);
                formMedica.setVisible(true);
            }

        }

    }

    private void abrirPrestadores() {
        if (formSisPres == null) {
            formSisPres = new FormPrestador();
            form.getjDesktopPaneSistema().add(formSisPres);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formSisPres.getWidth()) / 2;
            int y = (content.getHeight() - formSisPres.getHeight()) / 2;
            formSisPres.setLocation(x, y);
            formSisPres.setVisible(true);
        } else {
            if (formSisPres.isVisible()) {
                formSisPres.pack();
            } else {
                form.getjDesktopPaneSistema().add(formSisPres);
                formSisPres.setVisible(true);
            }
        }
    }

    private void abrirProcedimentos() {
        if (formSisProc == null) {
            formSisProc = new FormProcedimemto();
            form.getjDesktopPaneSistema().add(formSisProc);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formSisProc.getWidth()) / 2;
            int y = (content.getHeight() - formSisProc.getHeight()) / 2;
            formSisProc.setLocation(x, y);
            formSisProc.setVisible(true);
        } else {
            if (formSisProc.isVisible()) {
                formSisProc.pack();
            } else {
                form.getjDesktopPaneSistema().add(formSisProc);
                formSisProc.setVisible(true);
            }
        }
    }

    private void abrirFuncionarios() {
        if (formSisFunc == null) {
            formSisFunc = new FormFuncionario();
            form.getjDesktopPaneSistema().add(formSisFunc);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formSisFunc.getWidth()) / 2;
            int y = (content.getHeight() - formSisFunc.getHeight()) / 2;
            formSisFunc.setLocation(x, y);
            formSisFunc.setVisible(true);
        } else {
            if (formSisFunc.isVisible()) {
                formSisFunc.pack();
            } else {
                form.getjDesktopPaneSistema().add(formSisFunc);
                formSisFunc.setVisible(true);
            }
        }
    }

    private void abrirProdDigitadores() {
        if (formSisFormProdu == null) {
            formSisFormProdu = new FormProducao();
            form.getjDesktopPaneSistema().add(formSisFormProdu);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formSisFormProdu.getWidth()) / 2;
            int y = (content.getHeight() - formSisFormProdu.getHeight()) / 2;
            formSisFormProdu.setLocation(x, y);
            formSisFormProdu.setVisible(true);
        } else {
            if (formSisFormProdu.isVisible()) {
                formSisFormProdu.pack();
            } else {
                form.getjDesktopPaneSistema().add(formSisFormProdu);
                formSisFormProdu.setVisible(true);
            }
        }
    }

    private void abrirUsuarios() {
        if (formUsuarios == null) {
            formUsuarios = new CadastroUsuarios();
            form.getjDesktopPaneSistema().add(formUsuarios);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formUsuarios.getWidth()) / 2;
            int y = (content.getHeight() - formUsuarios.getHeight()) / 2;
            formUsuarios.setLocation(x, y);
            formUsuarios.setVisible(true);
        } else {
            if (formUsuarios.isVisible()) {
                formUsuarios.pack();
            } else {
                form.getjDesktopPaneSistema().add(formUsuarios);
                formUsuarios.setVisible(true);
            }
        }
    }

    private void abrirCadastroArquivo() {
        if (formCadArquivo == null) {
            formCadArquivo = new CadastroArquivo();
            form.getjDesktopPaneSistema().add(formCadArquivo);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formCadArquivo.getWidth()) / 2;
            int y = (content.getHeight() - formCadArquivo.getHeight()) / 2;
            formCadArquivo.setLocation(x, y);
            formCadArquivo.setVisible(true);
        } else {
            if (formCadArquivo.isVisible()) {
                formCadArquivo.pack();
            } else {
                form.getjDesktopPaneSistema().add(formCadArquivo);
                formCadArquivo.setVisible(true);
            }
        }
    }

    private void abrirConsultaArquivo() {
        if (formConsArquivo == null) {
            formConsArquivo = new ConsultaCaixa();
            form.getjDesktopPaneSistema().add(formConsArquivo);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formConsArquivo.getWidth()) / 2;
            int y = (content.getHeight() - formConsArquivo.getHeight()) / 2;
            formConsArquivo.setLocation(x, y);
            formConsArquivo.setVisible(true);
        } else {
            if (formConsArquivo.isVisible()) {
                formCadArquivo.pack();
            } else {
                form.getjDesktopPaneSistema().add(formConsArquivo);
                formConsArquivo.setVisible(true);
            }
        }
    }

    private void abrirExcluirArquivo() {
        if (formExcArquivo == null) {
            formExcArquivo = new ExcluirCaixa();
            form.getjDesktopPaneSistema().add(formExcArquivo);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formExcArquivo.getWidth()) / 2;
            int y = (content.getHeight() - formExcArquivo.getHeight()) / 2;
            formExcArquivo.setLocation(x, y);
            formExcArquivo.setVisible(true);
        } else {
            if (formExcArquivo.isVisible()) {
                formExcArquivo.pack();
            } else {
                form.getjDesktopPaneSistema().add(formExcArquivo);
                formExcArquivo.setVisible(true);
            }
        }
    }

    private void abrirBOER() {
        if (formBOER == null) {
            formBOER = new FomrBoletimRecebimentoEstatistico();
            form.getjDesktopPaneSistema().add(formBOER);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formBOER.getWidth()) / 2;
            int y = (content.getHeight() - formBOER.getHeight()) / 2;
            formBOER.setLocation(x, y);
            formBOER.setVisible(true);
        } else {
            if (formBOER.isVisible()) {
                formBOER.pack();
            } else {
                form.getjDesktopPaneSistema().add(formBOER);
                formBOER.setVisible(true);
            }
        }
    }

    private void abrirBOEE() {
            if (formBOEE == null) {
            formBOEE = new FomrBoletimEstatisticoEntrega();
            form.getjDesktopPaneSistema().add(formBOEE);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formBOEE.getWidth()) / 2;
            int y = (content.getHeight() - formBOEE.getHeight()) / 2;
           formBOEE.setLocation(x, y);
            formBOEE.setVisible(true);
        } else {
            if (formBOEE.isVisible()) {
                formBOEE.pack();
            } else {
                form.getjDesktopPaneSistema().add(formBOEE);
                formBOEE.setVisible(true);
            }
        } 
    }

    private void abrirRNP() {
        if (formNP == null) {
            formNP = new FormNaoPactuados();
            form.getjDesktopPaneSistema().add(formNP);
            Component content = form.getContentPane();
            int x = (content.getWidth() - formNP.getWidth()) / 2;
            int y = (content.getHeight() - formNP.getHeight()) / 2;
           formNP.setLocation(x, y);
            formNP.setVisible(true);
        } else {
            if (formNP.isVisible()) {
                formNP.pack();
            } else {
                form.getjDesktopPaneSistema().add(formNP);
                formNP.setVisible(true);
            }
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Produção Médica")) {

            abrirProducaoMedica();
        } else if (e.getActionCommand().equals("Prestadores")) {
            abrirPrestadores();
        } else if (e.getActionCommand().equals("Procedimentos")) {
            abrirProcedimentos();
        } else if (e.getActionCommand().equals("Funcionários")) {
            abrirFuncionarios();
        } else if (e.getActionCommand().equals("Produção digitadores")) {
            abrirProdDigitadores();
        } else if (e.getActionCommand().equals("Usuários")) {
            abrirUsuarios();
        } else if (e.getActionCommand().equals("Trocar usuário")) {
            form.dispose();
            Acesso a = new Acesso();
            a.setVisible(true);
        } else if (e.getActionCommand().equals("Sair")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("Cadastro")) {
            abrirCadastroArquivo();
        } else if (e.getActionCommand().equals("Consultar")) {
            abrirConsultaArquivo();
        } else if (e.getActionCommand().equals("Excluir")) {
            abrirExcluirArquivo();
        } else if (e.getActionCommand().equals("Boletim estatistico de recebimento")) {
            abrirBOER();
        } else if (e.getActionCommand().equals("Boletim estatistico de entrega")) {
            abrirBOEE();
        } else if (e.getActionCommand().equals("Relatório Não-Pactuados")) {
            abrirRNP();
        }

    }
}
