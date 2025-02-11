package controller;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import dao.ProdutosVendasDAO;
import dao.VendasDAO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CadastroVendaController {

    @FXML
    private Label dataHorario;

    @FXML
    private ComboBox<String> catalogoClientes;

    @FXML
    private Button addProduto;

    @FXML
    private TextField adicionarDesconto;

    @FXML
    private Label valorCompra;

    @FXML
    private ComboBox<String> formaPagamento;

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
    private TableView<Carrinho> tabelaCarrinho;

    @FXML
    private TableColumn<Carrinho, String> colunaCodCarrinho;

    @FXML
    private TableColumn<Carrinho, String> colunaNomeCarrinho;

    @FXML
    private TableColumn<Carrinho, String> colunaValor;

    @FXML
    private TableColumn<Carrinho, String> colunaQtd;

    @FXML
    private Button subProduto;

    private int index = 0;
    double valorTotal = 0;

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
        adicionarFormasPagamento();
        adicionandoClientesCombo();
        adicionarDesconto.setDisable(true);
    }

    private void gerarAlertas(Alert.AlertType tipo, String conteudo, String titulo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
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
        colunaEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));

        colunaCodCarrinho.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaNomeCarrinho.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

    }


    @FXML
    private void adicionarProdutosTabela() {
        List<Produto> produtos = ProdutoDAO.listarProdutos();
        tabelaProdutos.getItems().setAll(produtos);
    }

    @FXML
    private void addProdutoCarrinho() {
        Produto produto = tabelaProdutos.getSelectionModel().getSelectedItem();
        Carrinho carrinho = new Carrinho();
        carrinho.setCodigo(produto.getCodigo());
        carrinho.setNome(produto.getNome());
        int qtdCampoTexto = quantidadeProduto.getText().trim().isEmpty() ? 1 : Integer.parseInt(quantidadeProduto.getText().trim());
        carrinho.setQuantidade(String.valueOf(qtdCampoTexto));
        carrinho.setValor(String.valueOf(produto.getPreco() * qtdCampoTexto));

        if (!quantidadeProduto.getText().trim().isEmpty()) {

            try {
                if (Integer.parseInt(quantidadeProduto.getText()) > produto.getEstoque()) {
                    gerarAlertas(Alert.AlertType.WARNING, "Você inseriu uma quantidade de produtos que não temos em estoque", "Estoque insuficiente");
                    return;
                }
            } catch (NumberFormatException e) {
                gerarAlertas(Alert.AlertType.WARNING, "A quantidade deve ser um número inteiro válido.", "Valor inválido");
                return;
            }
        }

        if (quantidadeProduto.getText().matches("\\d*") || quantidadeProduto.getText().trim().isEmpty()) {
            for (Carrinho carrinho1 : tabelaCarrinho.getItems()) {
                if (produto.getCodigo().equals(carrinho1.getCodigo())) {
                    carrinho1.setQuantidade(String.valueOf(Integer.parseInt(carrinho1.getQuantidade()) + qtdCampoTexto));
                    int estoque = quantidadeProduto.getText().trim().isEmpty() ? produto.getEstoque() - 1 : produto.getEstoque() - Integer.parseInt(quantidadeProduto.getText().trim());
                    produto.setEstoque(estoque);
                    carrinho1.setValor(String.valueOf(produto.getPreco() * Integer.parseInt(carrinho1.getQuantidade())));
                    somarProdutosCarrinho();
                    tabelaCarrinho.refresh();
                    tabelaProdutos.refresh();
                    return;
                }
            }
            tabelaCarrinho.getItems().add(carrinho);
            int estoque = quantidadeProduto.getText().trim().isEmpty() ? produto.getEstoque() - 1 : produto.getEstoque() - Integer.parseInt(quantidadeProduto.getText().trim());
            produto.setEstoque(estoque);

            produto.setQuantidadeNoCarrinho(qtdCampoTexto);
            tabelaProdutos.refresh();
            somarProdutosCarrinho();
            index++;
            return;
        }
        gerarAlertas(Alert.AlertType.WARNING, "Você deve inserir um valor numérico válido no campo quantidade", "Valor inválido");
    }

    @FXML
    private void somarProdutosCarrinho() {
        valorTotal = 0;
        for (Carrinho carrinho1 : tabelaCarrinho.getItems()) {
            valorTotal += Double.parseDouble(carrinho1.getValor());
        }
        valorCompra.setText(String.format("%.2f", valorTotal));
    }

    @FXML
    private void adicionarFormasPagamento() {
        List<String> listaFormaPagamento = List.of("Cartão de crédito", "Cartão de débito", "Pix", "Boleto", "Dinheiro");
        formaPagamento.setItems(FXCollections.observableArrayList(listaFormaPagamento));
    }

    @FXML
    private void removerProdutoCarrinho() {
        Carrinho produto = tabelaCarrinho.getSelectionModel().getSelectedItem();
        if (produto != null) {
            tabelaCarrinho.getItems().remove(produto);
            valorTotal -= Double.parseDouble(colunaValor.getCellData(produto).replace(",", "."));
            valorCompra.setText(String.format("%.2f", valorTotal));
            index--;

            String cod = produto.getCodigo();
            for (Produto produto1 : tabelaProdutos.getItems()) {
                if (produto1.getCodigo().equals(cod)) {
                    produto1.setEstoque(Integer.parseInt(produto.getQuantidade()) + produto1.getEstoque());
                    break;
                }
            }
            tabelaProdutos.refresh();
        }
    }

    @FXML
    private void adicionarDescontoVenda(Usuario usuario) {
        if (usuario.getTipoUsuario().equals("admin")) {
            adicionarDesconto.setDisable(false);
            String valorDesconto = adicionarDesconto.getText().trim().replace("\\D", "");
            valorTotal *= Double.parseDouble(valorDesconto)/100;
        }
    }

    @FXML
    private void adicionandoClientesCombo() {
        List<String> clientesCPF = ClienteDAO.buscarCPFCliente();
        if (clientesCPF.isEmpty()) {
            System.out.print("A lista de clientes está vazia");
            return;
        }

        catalogoClientes.setItems(FXCollections.observableArrayList(clientesCPF));
    }

    private void enviarDadosVenda(Vendas venda) { // talvez precisarei colocar usuario pois tenho q pegar qm fez a venda
        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        venda.setData_venda(data.format(formatterSaida));
        venda.setCnpj("23.456.789/0001-95");
        venda.setForma_pagamento(formaPagamento.getSelectionModel().getSelectedItem());
        venda.setValor((float)valorTotal);
        venda.setHorario(hora.format(formatterHora));
        venda.setEstado_venda("Em andamento");
        venda.setCPFCliente(catalogoClientes.getValue());
        venda.setForma_pagamento(formaPagamento.getValue());
        if (formaPagamento.getSelectionModel().getSelectedItem().equals("Boleto")) {
            venda.setData_vencimento(LocalDate.now().plusDays(5).format(formatterSaida));
        } else {venda.setData_vencimento(venda.getData_venda());}
    }

    private void enviarDadosProdutoVenda(int idVenda) {
        ProdutosVendas produtosVendas = new ProdutosVendas();
        for (Carrinho carrinho : tabelaCarrinho.getItems()) {
            produtosVendas.setId_venda(idVenda);
            produtosVendas.setCod_produto(carrinho.getCodigo());
            produtosVendas.setPreco_produto(Float.parseFloat(carrinho.getValor()));
            produtosVendas.setQuantidade(Integer.parseInt(carrinho.getQuantidade()));
            ProdutosVendasDAO.cadastrarProdutoVendas(produtosVendas);
        }
    }

    private boolean checaCamposVazios() {
        if (catalogoClientes.getSelectionModel().isEmpty() || formaPagamento.getSelectionModel().isEmpty()) {
            return false;
        } return true;
    }

    @FXML
    private void concluirVenda() { // ainda falta atualizar estoque no banco de dados
        if (!checaCamposVazios()) {
            gerarAlertas(Alert.AlertType.WARNING, "Selecione um cliente e uma forma de pagamento para continuar.", "Preencha os campos necessário.");
            return;
        }

        Vendas venda = new Vendas();
        enviarDadosVenda(venda);
        int idVenda = VendasDAO.CadastrarVenda(venda);

        if (idVenda != -1) {
            enviarDadosProdutoVenda(idVenda);
        } else {    gerarAlertas(Alert.AlertType.WARNING, "Erro ao cadastrar a sua venda", "Erro ao cadastrar a sua venda."); }

        gerarAlertas(Alert.AlertType.INFORMATION,"Sua venda foi cadastrada no sistema com sucesso! Verifique se já pode concluir ela.", "Venda cadastrada com sucesso!");
        Stage stage = (Stage) btConcluir.getScene().getWindow();
        stage.close();
    }

}