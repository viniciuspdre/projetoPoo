package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaPrincipalController {
        @FXML
        private Label tituloTela;

        public void initialize() {
            tituloTela.setText("Bem-vindo à Tela Principal!");
        }
    }

