package controller;

import dao.ProdutoDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.entity.Cliente;
import model.entity.FormaPagamento;
import model.entity.Produto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CadastroVendaController {
    private static int numeroId = 1;
    private double layoutXProduto = 14;
    private double layoutYProduto = 55;
    private double layoutXQuantidade = 198;

    @FXML
    private Label dataHorario;

    @FXML
    private ComboBox<Cliente> catalogoClientes;

    @FXML
    private ComboBox<Produto> catalogoProduto1;

    @FXML
    private Button maisProduto;

    @FXML
    private TextField adicionarDesconto;

    @FXML
    private Label valorCompra;

    @FXML
    private ComboBox<FormaPagamento> formaPagamento;

    @FXML
    private Button btConcluir;

    @FXML
    private Button btCancelar;

    @FXML
    private AnchorPane todosProdutos;

    @FXML
    private TextField quantidadeProduto;

    @FXML
    public void initialize() {
        mudarDataHorario();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> mudarDataHorario())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void mudarDataHorario() {
        LocalDateTime dataHora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");

        dataHorario.setText(dataHora.format(formatter));
    }

    @FXML
    private void fecharTela() {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void adicionarNovoProduto() {
        numeroId++;
        layoutYProduto += 50;

        ComboBox<Produto> novoCatalogoProduto = new ComboBox<>();
        novoCatalogoProduto.getItems().addAll(catalogoProduto1.getItems());
        novoCatalogoProduto.setPromptText("Adicione um produto");
        novoCatalogoProduto.getStyleClass().add("choice-box");
        novoCatalogoProduto.setPrefHeight(36);
        novoCatalogoProduto.setPrefWidth(179);
        novoCatalogoProduto.setId("catalogoProduto" + numeroId);

        novoCatalogoProduto.setLayoutX(layoutXProduto);
        novoCatalogoProduto.setLayoutY(layoutYProduto);

        todosProdutos.getChildren().add(novoCatalogoProduto);

        TextField novaQuantidadeProduto = new TextField();
        novaQuantidadeProduto.setPromptText("Qtd");
        novaQuantidadeProduto.setLayoutX(layoutXQuantidade);
        novaQuantidadeProduto.setLayoutY(layoutYProduto);
        novaQuantidadeProduto.setPrefHeight(36);
        novaQuantidadeProduto.setPrefWidth(40);
        novaQuantidadeProduto.setId("quantidadeProduto" + numeroId);
        novaQuantidadeProduto.getStyleClass().add("text-field");
        novaQuantidadeProduto.setStyle("-fx-font-family: 'Avignon Pro Demi'");

        todosProdutos.getChildren().add(novaQuantidadeProduto);

    }

    @FXML
    private void cadastrarProdutos() {
        List<ComboBox<Produto>> listaCatalogoProdutos = new ArrayList<>();
        listaCatalogoProdutos.add(catalogoProduto1);

    }

    @FXML
    private void adicionarProdutosCatalogo() {
        List<Produto> produtos = new ArrayList<>();
        produtos = ProdutoDAO.listarProdutos();

        catalogoProduto1.getItems().clear();

        for (Produto produto1 : produtos) {
            catalogoProduto1.getItems().add(produto1);
        }
    }
}
