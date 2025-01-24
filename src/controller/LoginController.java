package controller;

import dao.UsuarioDAO;
import view.TelaPrincipal;
import view.LoginCadastro;

import javax.swing.*;

public class LoginController {
    private LoginCadastro loginCadastro;

    public LoginController(LoginCadastro loginCadastro) {
        this.loginCadastro = loginCadastro;
    }
    public void botaoEnterLogin(java.awt.event.ActionEvent evt) {
        try {
            String Login = loginCadastro.getJfUsernameLogin().getText();
            String Senha = new String(loginCadastro.getJfPasswordLogin().getPassword());

            if (Login.isEmpty() || Senha.isEmpty() ) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean auteticacao = UsuarioDAO.autenticacaoUsuario(Login, Senha);

            if (auteticacao) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                TelaPrincipal objPrincipal = new TelaPrincipal();
                objPrincipal.setVisible(true);

                loginCadastro.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception erro) {
            System.err.println("Erro ao processar login: " + erro.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao realizar login. Tente novamente. ", "Erro" , JOptionPane.ERROR_MESSAGE);
        }
    }
}

