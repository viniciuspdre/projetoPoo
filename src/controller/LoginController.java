package controller;

import dao.UsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class LoginController {

    @FXML
    private TextField campoUsuarioLogin;
    @FXML
    private PasswordField campoSenhaLogin;
    @FXML
    private Button botaoLogin;

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void botaoEnterLogin(ActionEvent event) {
        try {
            String login = campoUsuarioLogin.getText();
            String senha = campoSenhaLogin.getText();

            if (login.isBlank() || senha.isBlank()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos Obrigatórios", "Preencha todos os campos.");
                return;
            }

            boolean autenticacao = UsuarioDAO.autenticacaoUsuario(login, senha);
            System.out.println("Autenticação " + autenticacao);

            if (autenticacao) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Login realizado com sucesso!");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
                Parent root = loader.load();

                Stage TelaPrincipal = new Stage();
                TelaPrincipal.setScene(new Scene(root));
                TelaPrincipal.setTitle("Tela Principal");
                TelaPrincipal.show();
                Stage stageAtual = (Stage) botaoLogin.getScene().getWindow();
                stageAtual.close();

                return;
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Login ou senha incorretos!");
                System.out.println("Erro ao autenticar");
            }

        } catch (Exception erro) {
            System.err.println("Erro ao processar login: " + erro.getMessage());
            erro.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao realizar login. Tente novamente.");
        }
    }
}


