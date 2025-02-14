package controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dao.ProdutoDAO;
import dao.ProdutosVendasDAO;
import dao.VendasDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import model.Usuario;
import model.Vendas;
import view.Telas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private Label totalValorVenda;

    @FXML
    private TextField campoFiltrar;

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
    private Label labelUsuario;
    @FXML
    private AnchorPane painelUsuario;

    private Usuario usuarioLogado;

    @FXML
    private Button btVoltar;

    @FXML
    private void voltarTelaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
            Parent root = loader.load();
            TelaPrincipalController controller = loader.getController();
            controller.setUsuario(usuarioLogado);

            Stage telaGerenciamento = new Stage();
            telaGerenciamento.setTitle("Tela Principal");
            telaGerenciamento.setScene(new Scene(root));
            telaGerenciamento.setResizable(false);
            telaGerenciamento.show();

            Stage stage = ( Stage ) btVoltar.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            System.out.println("Erro ao voltar");
        }
    }

    @FXML
    public void initialize() {
        configurarTabela();
        listarVendas();
        btGerarFatura.setDisable(true);
        btConcluirVenda.setDisable(true);
        btCancelarVenda.setDisable(true);
        btModificarVenda.setDisable(true);
        campoFiltrar.textProperty().addListener((observable, oldValue, newValue) -> pesquisarVendasCPF());
        mostrarValorTotalVendas();
        configurarEventoSelecao();
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        labelUsuario.setText(usuario.getNome());
        painelUsuario.setLeftAnchor(labelUsuario, 0.0);
        painelUsuario.setRightAnchor(labelUsuario, 0.0);
        labelUsuario.setAlignment(Pos.CENTER);
    }

    private void configurarEventoSelecao() {
        tabelaVendas.getSelectionModel().selectedItemProperty().addListener((_, _, vendaSelecionada) -> {
            if (vendaSelecionada != null ) {
                boolean concluida = vendaSelecionada.getEstado_venda().equalsIgnoreCase("Concluída");
                boolean em_andamento = vendaSelecionada.getEstado_venda().equalsIgnoreCase("Em andamento");

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
        mostrarValorTotalVendas();
    }

    @FXML
    private void pesquisarVendasCPF() {
        String filtro = campoFiltrar.getText().trim();

        if (filtro.isEmpty()) {
            listarVendas(); // Recarrega todas as vendas caso o campo esteja vazio
            return;
        }

        List<Vendas> listaFiltrada = VendasDAO.listarVendas()
                .stream()
                .filter(v -> v.getCPFCliente().contains(filtro))
                .toList(); // Filtra vendas que contenham o CPF digitado

        tabelaVendas.getItems().setAll(listaFiltrada);
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
            controller.setUsuario(usuarioLogado);

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

    @FXML
    private void mostrarValorTotalVendas() {
        Double valorTotal = 0.0;

        for (Vendas venda : tabelaVendas.getItems()) {
            if (venda.getEstado_venda().equals("concluída")) {
                valorTotal += venda.getValor();
            }
        }

        totalValorVenda.setText(String.format("R$ %.2f", valorTotal));
    }

    @FXML
    private void gerarFatura() {
        Document faturaPDF = new Document();
        Vendas venda = tabelaVendas.getSelectionModel().getSelectedItem();
        String caminhoFaturas = "C:\\Users\\Pedro Lira\\Documents\\projetos\\projetoPoo\\src\\faturas\\faturaVendaId" + String.valueOf(venda.getIdVenda())+".pdf";

        try {
            PdfWriter.getInstance(faturaPDF, new FileOutputStream(caminhoFaturas));

            faturaPDF.open();
            gerarCabecalhoFatura(venda, faturaPDF);
            gerarCorpoFatura(venda, faturaPDF);
            gerarRodapeFatura(venda, faturaPDF);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        faturaPDF.close();
        gerarAlertas(Alert.AlertType.CONFIRMATION, "A fatura dessa venda foi gerada com sucesso, obtenha ela no sistema de arquivos do computador.", "Fatura gerada com sucesso!");
    }

    private void gerarCabecalhoFatura(Vendas venda, Document document) {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        paragrafoTitulo.add(new Chunk(
                "Fatura da Venda",
                new Font(Font.HELVETICA, 24)
                ));
        document.add(paragrafoTitulo);
        document.add(new Paragraph("\n"));

        Paragraph paragrafoData = new Paragraph();
        paragrafoData.setAlignment(Element.ALIGN_CENTER);
        paragrafoData.add(new Chunk(venda.getData_venda() + " - " + venda.getHorario()));
        document.add(paragrafoData);
        document.add(new Paragraph("\n"));

        Paragraph paragrafoCNPJLoja = new Paragraph();
        paragrafoCNPJLoja.setAlignment(Element.ALIGN_CENTER);
        paragrafoCNPJLoja.add(new Chunk("CNPJ: " + venda.getCnpj(), new Font(Font.BOLD, 12)));
        document.add(paragrafoCNPJLoja);
        document.add(new Paragraph("\n"));

        Paragraph paragrafoCPFCliente = new Paragraph();
        paragrafoCPFCliente.setAlignment(Element.ALIGN_CENTER);
        String cpf = venda.getCPFCliente();
        paragrafoCPFCliente.add(new Chunk(
                "Cliente: " + String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9)),
                new Font(Font.BOLD, 12)
        ));
        document.add(paragrafoCPFCliente);
        Paragraph paragrafoSessao = new Paragraph();
        paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
        paragrafoSessao.add(new Chunk("____________________________________________________________________________", new Font(Font.BOLD)));
        document.add(paragrafoSessao);
        document.add(new Paragraph("\n"));
    }

    private void gerarCorpoFatura(Vendas venda, Document document) {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_LEFT);
        paragrafoTitulo.add(new Chunk("Itens Vendidos", new Font(Font.HELVETICA, 14, Font.BOLD)));
        document.add(paragrafoTitulo);

        List<ProdutosVendas> produtosVendas = ProdutosVendasDAO.listarProdutoVendasId(venda.getIdVenda());
        PdfPTable tabelaProdutos = new PdfPTable(3);
        tabelaProdutos.setWidthPercentage(100);
        tabelaProdutos.setSpacingBefore(10);
        tabelaProdutos.setSpacingAfter(10);

        PdfPCell celulaCodProduto = new PdfPCell(new Phrase("Produto", new Font(Font.HELVETICA, 12, Font.BOLD)));
        PdfPCell celulaQtdProduto = new PdfPCell(new Phrase("Quantidade", new Font(Font.HELVETICA, 12, Font.BOLD)));
        PdfPCell celulaValorProduto = new PdfPCell(new Phrase("Valor", new Font(Font.HELVETICA, 12, Font.BOLD)));
        celulaQtdProduto.setHorizontalAlignment(Element.ALIGN_CENTER);  celulaQtdProduto.setVerticalAlignment(Element.ALIGN_MIDDLE);    celulaValorProduto.setHorizontalAlignment(Element.ALIGN_CENTER);
        celulaValorProduto.setVerticalAlignment(Element.ALIGN_MIDDLE);  celulaCodProduto.setHorizontalAlignment(Element.ALIGN_CENTER);  celulaCodProduto.setVerticalAlignment(Element.ALIGN_MIDDLE);

        tabelaProdutos.addCell(celulaCodProduto); tabelaProdutos.addCell(celulaQtdProduto); tabelaProdutos.addCell(celulaValorProduto);

        for (ProdutosVendas p : produtosVendas) {
            Produto produto = ProdutoDAO.listarUnicoProduto(p.getCod_produto());

            tabelaProdutos.addCell(new PdfPCell(new Phrase(produto.getNome()))).setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaProdutos.addCell(new PdfPCell(new Phrase(String.valueOf(p.getQuantidade())))).setHorizontalAlignment(Element.ALIGN_CENTER);
            tabelaProdutos.addCell(new PdfPCell((new Phrase(String.format("R$ %.2f", p.getPreco_produto()))))).setHorizontalAlignment(Element.ALIGN_CENTER);
        }

        document.add(tabelaProdutos);

        Paragraph paragrafoSessao = new Paragraph();
        paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
        paragrafoSessao.add(new Chunk("____________________________________________________________________________", new Font(Font.BOLD)));
        document.add(paragrafoSessao);
        document.add(new Paragraph("\n"));
    }

    private void gerarRodapeFatura(Vendas venda, Document document) {
        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_LEFT);
        paragrafoTitulo.add(new Chunk("Forma de pagamento: " + venda.getForma_pagamento(), new Font(Font.HELVETICA, 12, Font.BOLD)));
        document.add(paragrafoTitulo);
        document.add(new Paragraph("\n"));

        if (venda.getForma_pagamento().equals("Boleto")) {
            Paragraph paragrafoDataVencimento = new Paragraph();
            paragrafoDataVencimento.setAlignment(Element.ALIGN_LEFT);
            paragrafoDataVencimento.add(new Chunk("Data de vencimento: "+ venda.getData_vencimento(), new Font(Font.HELVETICA, 12, Font.BOLD)));
            document.add(paragrafoDataVencimento);
        }

        Paragraph paragrafoValor = new Paragraph();
        paragrafoValor.setAlignment(Element.ALIGN_RIGHT);
        paragrafoValor.add(new Chunk("Valor Total: " + String.format("R$ %.2f", venda.getValor()),  new Font(Font.HELVETICA, 12, Font.BOLD)));
        document.add(paragrafoValor);
        document.add(new Paragraph("\n"));

        try {
            Image imagem = Image.getInstance("C:\\Users\\Pedro Lira\\Documents\\projetos\\projetoPoo\\src\\icon\\loja-online.png");
            imagem.scaleToFit(150, 150); // Ajusta para 150x150 pixels
            imagem.setAlignment(Element.ALIGN_CENTER);
            document.add(imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
