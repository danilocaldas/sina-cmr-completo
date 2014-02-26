/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.sistema;

import javax.swing.JOptionPane;

/**
 *
 * @author ritacosta
 */
public class MenssageErrorForm {

    /**
     * Mensagens de controle do arquivo
     */
    public static void msgVerificarNumeroArquivo() {
        JOptionPane.showMessageDialog(null, "Numero invalidado!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }

    public static void msgExclusaoArquivo() {
        JOptionPane.showMessageDialog(null, "Caixa excluida com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msgInserirArquivo() {
        JOptionPane.showMessageDialog(null, "Caixa inserida com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void msgBuscarNumeroCaixa() {
        JOptionPane.showMessageDialog(null, "Procure pelo ultimo numero da caixa referente ao ano.",
                "Atenção", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void msgErroValidaçãoConsultaCaixa(){
        JOptionPane.showMessageDialog(null, "Informe o numero da caixa!","Informação", JOptionPane.ERROR_MESSAGE);
    }

    //fim
    
    /**
     * Mensagens globais
     */
    
    public static void msgSelecaoModoPesquisa(){
        JOptionPane.showMessageDialog(null, "Selecione uma forma de pesquisa!", "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public static void msgErroTenteNovamente() {
        JOptionPane.showMessageDialog(null, "Tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void msgCamposObrigatorios() {
        JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "Erro de validação", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void msgErrorRelatorioNãoEncontrado(){
        JOptionPane.showMessageDialog(null, "Erro no carregamento do relatório."
                + "\n Contate o administrador do sistema", "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    //fim
    
    
    /**
     * Mensagens de boletim estatistico
     */
    
    public static void msgInserirBoletim(){
        JOptionPane.showMessageDialog(null, "Boletim estatístico inserido com sucesso!","Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void msgExcluirBoletim(){
        JOptionPane.showMessageDialog(null, "Boletim estatístico removido com sucesso!","Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void msgSelecaoAlterarBoletim(){
        JOptionPane.showMessageDialog(null, "Selecione o Boletim estatístico a ser alterado!","Erro de seleção", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void msgSelecaoExcluirBoletim(){
        JOptionPane.showMessageDialog(null, "Selecione o boletim estatístico a ser removida!", "Erro de seleção", JOptionPane.ERROR_MESSAGE);
    }
    //fim
    
    
    
}
