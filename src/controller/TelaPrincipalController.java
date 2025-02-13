package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaPrincipalController {

    @FXML
    ImageView iconCliente;
    @FXML
    ImageView iconProduto;
    @FXML
    ImageView iconVenda;

        public void initialize() {
            Image icon = new Image("/icon/cliente.png");
            iconCliente.setImage(icon);
            icon = new Image("/icon/produto.png");
            iconProduto.setImage(icon);
            icon = new Image("/icon/venda.png");
            iconVenda.setImage(icon);
        }
    }

