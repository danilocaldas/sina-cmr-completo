/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sinaview.form.usuario;


import br.com.sinacontrol.controller.UsuariosController;
import br.com.sinamodel.entidades.Usuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ritacosta
 */
public class UsuarioActionControl implements ActionListener{
    
    public CadastroUsuarios form;
    private Long idUsuario;
    List<Usuarios> listUsuarios;
    public UsuarioActionControl(CadastroUsuarios form) {
        this.form = form;
        addBtn();
        refreshTable();
    }
    
    private void addBtn(){
        form.getBtAtualizar().addActionListener(this);
        form.getBtSalvar().addActionListener(this);
        form.getBtExcluir().addActionListener(this);
        form.getBtSair().addActionListener(this);
        form.getComboStatus().addActionListener(this);
        
    }
    
    private void refreshTable() {
        listUsuarios = new UsuariosController().listar();
        if (listUsuarios != null) {
            form.getTbUsuarios().setModel(new UsuarioTableModel(listUsuarios));
            form.getTbUsuarios().setDefaultRenderer(Object.class, new UsuarioCellRenderer());
        }
    }

    private void onSaveUsuario() {
        Usuarios usuarios = new Usuarios();
        if (form.getTxtLogin().getText().length() > 0 && form.getTxtSenha().getText().length() > 0) {
            usuarios.setRole_user(form.getComboStatus().getSelectedItem().toString());
            usuarios.setLogin(form.getTxtLogin().getText().trim());
            usuarios.setSenha(form.getTxtSenha().getText().trim());
        } else {
            JOptionPane.showMessageDialog(form, "Todos os campos são obrigatórios!");
            return;
        }

        int result = 0;

        if (idUsuario == null) {
            result = new UsuariosController().save(usuarios);
        } else {
            usuarios.setId(idUsuario);
            result = new UsuariosController().update(usuarios);
            idUsuario = null;
        }
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Usuário inserido com sucesso!");
            //enableFields(false);
            //onCancelar();
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(form, "Tente novamente!");
        }
    }
    
    private void onAlterarUsuario() {
        int indexRow = form.getTbUsuarios().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione o usuário a ser alterado!");
            return;
        }
        Usuarios usuario = new UsuarioTableModel(listUsuarios).get(indexRow);
        idUsuario= usuario.getId();
        form.getLabelId().setText(String.valueOf(usuario.getId()));
        form.getTxtLogin().setText(usuario.getLogin());
        form.getTxtSenha().setText(usuario.getSenha());
        form.getComboStatus().setSelectedItem(usuario.getRole_user());
        //enableFields(true);
    }
    
    private void removerUsuario() {
        int indexRow = form.getTbUsuarios().getSelectedRow();
        if (indexRow == -1) {
            JOptionPane.showMessageDialog(form, "Selecione o usuário a ser removido!");
            return;
        }
        Usuarios usuario = new UsuarioTableModel(listUsuarios).get(indexRow);
        int confirm = JOptionPane.showConfirmDialog(form, "Confirmar a exclusão?", "Excluir Usuário", JOptionPane.YES_NO_OPTION);
        if (confirm != 0) {
            return;
        }
        int result = new UsuariosController().delete(usuario.getId());
        if (result == 1) {
            JOptionPane.showMessageDialog(form, "Usuário removido com sucesso!");
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(form, "Tente novamente!");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Salvar")){
            onSaveUsuario();
        }else if (e.getActionCommand().equals("Atualizar")){
            onAlterarUsuario();
        }else if (e.getActionCommand().equals("Excluir")){
            removerUsuario();
        }else if (e.getActionCommand().equals("Sair")){
            form.dispose();
        }
    }
}
