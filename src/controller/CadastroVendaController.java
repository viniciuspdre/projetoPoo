package controller;

import dao.ProdutoDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button addProduto;

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
    private TableView<Produto> tabelaProdutos;

    @FXML
    private TableColumn<Produto, String> colunaCod;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, String> colunaPreco;

    @FXML
    private TableView<Produto> tabelaCarrinho;

    @FXML
    private TableColumn<Produto, String> colunaCodCarrinho;

    @FXML
    private TableColumn<Produto, String> colunaNomeCarrinho;

    @FXML
    private TableColumn<Produto, String> colunaValor;

    @FXML
    private TableColumn<Produto, String> colunaQtd;

    @FXML
    public void initialize() {
        mudarDataHorario();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> mudarDataHorario())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        configurarTabela();
        adicionarProdutosTabela();
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
    private void configurarTabela() {
        colunaCod.setCellValueFactory(new PropertyValueFactory<>("codigo")); // aqui ele basicamente adapta as celulas da coluna codigo a funcionar para pegar o codigo
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("R$ %.2f", cellData.getValue().getPreco())));

        colunaCodCarrinho.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNomeCarrinho.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaValor.setCellValueFactory(cellData -> {
            Produto produto = cellData.getValue();
            int quantidade = Integer.parseInt(quantidadeProduto.getText());
            double valorTotal = produto.getPreco() * quantidade;

            return new SimpleStringProperty(String.format("%.2f", valorTotal));
        });
        colunaQtd.setCellValueFactory(cellData -> {
            String quantidade = quantidadeProduto.getText().trim();
            return new SimpleStringProperty(quantidade.isEmpty() ? "1" : quantidade);
        });
    }


    @FXML
    private void adicionarProdutosTabela() {
        List<Produto> produtos = ProdutoDAO.listarProdutos();
        tabelaProdutos.getItems().setAll(produtos);
    }

    @FXML
    private void addProdutoCarrinho() {
        Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();

        if (produto != null) {
            tabelaCarrinho.getItems().add(produto);
        }
    }
}
