package controller;

import dao.VendasDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Vendas;
import view.Telas;

import java.io.IOException;
import java.util.List;

public class GerenciamentoVendasController {

    @FXML
    private Button btCancelarVenda;

    @FXML
    private Button btGerarVenda;

    @FXML
    private Button btModificarVenda;

    @FXML
    private AnchorPane painelListaVendas;

    @FXML
    private ScrollPane painelVendas;

    @FXML
    private Button btConcluirVenda;

    @FXML
    private Button btGerarFatura;

    @FXML
    private ImageView imageUser;

    @FXML
    private TableView<Vendas> tabelaVendas;

    @FXML
    private TableColumn<Vendas, String> colLoja;
    @FXML
    private TableColumn<Vendas, String> colCliente;
    @FXML
    private TableColumn<Vendas, String> colDataVenda;
    @FXML
    private TableColumn<Vendas, String> colHora;
    @FXML
    private TableColumn<Vendas, String> colFormaPagamento;
    @FXML
    private TableColumn<Vendas, String> colVencimento;
    @FXML
    private TableColumn<Vendas, String> colValor;
    @FXML
    private TableColumn<Vendas, String> colEstadoVenda;
    @FXML
    private TableColumn<Vendas, String> colIDVenda;

    @FXML
    public void initialize() {
        configurarTabela();
        listarVendas();
//        Vendas venda = tabelaVendas.getSelectionModel().getSelectedItem();
//        if (venda.getEstado_venda().equals("Concluída") && venda != null) {
//            btConcluirVenda.setDisable(true);
//            btCancelarVenda.setDisable(true);
//            btModificarVenda.setDisable(true);
//        }
    }

    @FXML
    private void configurarTabela() {
        colLoja.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("CPFCliente"));
        colDataVenda.setCellValueFactory(new PropertyValueFactory<>("data_venda"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("horario"));
        colFormaPagamento.setCellValueFactory(new PropertyValueFactory<>("forma_pagamento"));
        colVencimento.setCellValueFactory(new PropertyValueFactory<>("data_vencimento"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colEstadoVenda.setCellValueFactory(new PropertyValueFactory<>("estado_venda"));
        colIDVenda.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
    }

    private void listarVendas() {
        List<Vendas> listaVendas = VendasDAO.listarVendas();
        tabelaVendas.getItems().setAll(listaVendas);
    }

    @FXML
    private void concluirVenda() {
        Vendas venda = tabelaVendas.getSelectionModel().getSelectedItem();
        VendasDAO.concluirVenda(venda.getIdVenda(), "Concluída");
        listarVendas();
    }

    private FXMLLoader loadFrame(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String pageFxml = "/view/" + fxml + ".fxml";
        fxmlLoader.setLocation(Telas.class.getResource(pageFxml));
        Parent root = fxmlLoader.load();
        return fxmlLoader;
    }

    private void abrirPopup(String fxml, String tituloTela) {
        try {
            FXMLLoader loader = loadFrame(fxml);
            Parent root = loader.getRoot(); // Obtém o root carregado

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Bloqueia a janela principal enquanto o popup está aberto
            stage.setTitle(tituloTela);
            stage.setResizable(false);
            stage.showAndWait(); // Aguarda o usuário fechar o popup antes de continuar
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirCadastrarVenda() {
        abrirPopup("CadastroVenda", "Cadastrar Venda");
    }


}
