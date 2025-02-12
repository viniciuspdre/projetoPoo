package controller;

import dao.ProdutoDAO;
import dao.ProdutosVendasDAO;
import dao.VendasDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Produto;
import model.ProdutosVendas;
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
        configurarEventoSelecao();
    }

    private void configurarEventoSelecao() {
        tabelaVendas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                boolean concluida = newSelection.getEstado_venda().equalsIgnoreCase("Concluída");
                boolean em_andamento = newSelection.getEstado_venda().equalsIgnoreCase("Em andamento");

                // Desativa ou ativa os botões com base no estado da venda
                btGerarFatura.setDisable(em_andamento);

                btConcluirVenda.setDisable(concluida);
                btCancelarVenda.setDisable(concluida);
                btModificarVenda.setDisable(concluida);
            }
        });
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

    @FXML
    private void cancelarVenda() {
        Vendas venda = tabelaVendas.getSelectionModel().getSelectedItem();

        int idVenda = venda.getIdVenda();
        List<ProdutosVendas> produtosParaVoltarEstoque = ProdutosVendasDAO.listarProdutoVendasId(idVenda);

        for (ProdutosVendas p : produtosParaVoltarEstoque) {
            Produto produto = ProdutoDAO.listarUnicoProduto(p.getCod_produto());
            ProdutoDAO.atualizarEstoque(p.getCod_produto(), produto.getEstoque() + p.getQuantidade());
        }

        ProdutosVendasDAO.deletarProdutoVendas(idVenda);
        VendasDAO.deletarVenda(idVenda);
        listarVendas();
    }

    private FXMLLoader loadFrame(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String pageFxml = "/view/" + fxml + ".fxml";
        fxmlLoader.setLocation(Telas.class.getResource(pageFxml));
        Parent root = fxmlLoader.load();
        return fxmlLoader;
    }

    private void abrirPopup(String fxml, String tituloTela, boolean isModificacao, Vendas venda) {
        try {
            FXMLLoader loader = loadFrame(fxml);
            Parent root = loader.getRoot(); // Obtém o root carregado

            CadastroVendaController controller = loader.getController();
            controller.setModificacao(isModificacao, venda);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); // Bloqueia a janela principal enquanto o popup está aberto
            stage.setTitle(tituloTela);
            stage.setResizable(false);
            stage.setOnHidden(event -> listarVendas());
            stage.showAndWait();// Aguarda o usuário fechar o popup antes de continuar
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void gerarAlertas(Alert.AlertType tipo, String conteudo, String titulo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

    @FXML
    private void abrirCadastrarVenda() {
        abrirPopup("CadastroVenda", "Cadastrar Venda", false, null);
    }

    @FXML
    private void abrirModificarVenda() {
        Vendas venda = tabelaVendas.getSelectionModel().getSelectedItem();
        if (venda != null) {
            abrirPopup("CadastroVenda", "Modificar Venda", true, venda);
        } else {  gerarAlertas(Alert.AlertType.WARNING, "Selecione uma venda.", "Não é possível modificar uma venda sem antes selecioná-la");  }
    }
}
