package controller;

import dao.ProdutoDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableColumn<Produto, String> colunaEstoque;

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
    private Button subProduto;

    private int index = 0;
    private double valorTotal = 0;

    @FXML
    public void initialize() {
        mudarDataHorario();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> mudarDataHorario())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        configurarTabelaCombo();
        adicionarProdutosTabela();
        adicionarFormasPagamento();
        adicionarDesconto.setDisable(true);
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
    private void configurarTabelaCombo() {
        colunaCod.setCellValueFactory(new PropertyValueFactory<>("codigo")); // aqui ele basicamente adapta as celulas da coluna codigo a funcionar para pegar o codigo
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("R$ %.2f", cellData.getValue().getPreco())));
        colunaEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));

        colunaCodCarrinho.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNomeCarrinho.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaQtd.setCellValueFactory(cellData -> {
            String quantidade = quantidadeProduto.getText().trim();
            return new SimpleStringProperty(quantidade.isEmpty() ? "1" : quantidade);
        });
        colunaValor.setCellValueFactory(cellData -> {
            Produto produto = cellData.getValue();
            double valorTotal = produto.getPreco() * Integer.parseInt(colunaQtd.getCellData(produto));
            return new SimpleStringProperty(String.format("%.2f", valorTotal));
        });

        formaPagamento.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(FormaPagamento formaPagamento, boolean vazio) {
                super.updateItem(formaPagamento, vazio);
                setText((vazio || formaPagamento == null) ? null : formaPagamento.getPagamento());
            }
        });

        formaPagamento.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(FormaPagamento formaPagamento, boolean vazio) {
                super.updateItem(formaPagamento, vazio);
                setText((vazio || formaPagamento == null) ? null : formaPagamento.getPagamento());
            }
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

        if (quantidadeProduto.getText().matches("\\d*") || quantidadeProduto.getText().isEmpty()) {
            if (produto != null) {
                tabelaCarrinho.getItems().add(produto);
            }
            somarProdutosCarrinho();
            return;
        }
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Valor inválido");
        alerta.setHeaderText(null);
        alerta.setContentText("Você deve inserir um valor numérico no campo quantidade.");
        alerta.showAndWait();
    }

    @FXML
    private void somarProdutosCarrinho() {
        ObservableList<Produto> produto = tabelaCarrinho.getItems();
        double valor = Double.parseDouble(colunaValor.getCellData(produto.get(index)).replace(",", "."));
        valorTotal += valor;
        System.out.println(colunaValor.getCellData(produto.get(index)).replace(",", "."));
        valorCompra.setText(String.format("%.2f", valorTotal));
        index++;
    }

    @FXML
    private void adicionarFormasPagamento() {
        formaPagamento.setItems(FXCollections.observableArrayList(FormaPagamento.values()));
    }

    @FXML
    private void AdicionarDescontoCompra() {
        if (formaPagamento.getSelectionModel().getSelectedItem() != null) {
            adicionarDesconto.setDisable(false);
            double valorDesconto = Double.parseDouble(adicionarDesconto.getText().trim());
            valorTotal = valorDesconto * valorDesconto/100;
        }
    }
}
