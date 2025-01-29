package controller;

import dao.ProdutoDAO;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.ByteStringConverter;
import model.entity.Produto;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.*;

import java.awt.*;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoController extends Component implements Initializable {

    @FXML
    private Button Adicionar_Produto;

    @FXML
    private ComboBox<String> Adicionar_Produto_Categorias;

    @FXML
    private TextArea Adicionar_Produto_Descricao;

    @FXML
    private TextField Adicionar_Produto_Estoque;

    @FXML
    private TextField Adicionar_Produto_Marca;

    @FXML
    private TextField Adicionar_Produto_Nome;

    @FXML
    private TextField Adicionar_Produto_Preco;

    @FXML
    private TextField Adicionar_Produto_Estoque_Minimo;

    @FXML
    private Button Atualizar_Produto;

    @FXML
    private Button Encerrar_Sessao;

    @FXML
    private Button Estatisticas_Produto;

    @FXML
    private BarChart<?, ?> Grafico_Venda_Produtos;

    @FXML
    private Button Home;

    @FXML
    private ImageView Image_Adicionar_Produto;

    @FXML
    private ImageView Image_Atualizar_Produto;

    @FXML
    private TextField Input_Remover_Produto;

    @FXML
    private Button Limpar_Atualizar_Produto;

    @FXML
    private Button Limpar_Remover_Produto;

    @FXML
    private ImageView Pesquisar_Atualizar_Produto;

    @FXML
    private ImageView Imagem_Produto_Adicionar;

    @FXML
    private TextField Pesquisar_Remover_Produto;

    @FXML
    private TextField Pesquisar_Adicionar_Produto;

    @FXML
    private Button Remover_Produto;

    @FXML
    private Label Sem_Estoque;

    @FXML
    private TableView<Produto> Tabela_Produto_Remover;

    @FXML
    private TableColumn<Produto, String> colunaCategoria;

    @FXML
    private TableColumn<Produto, String> colunaCodigo;

    @FXML
    private TableColumn<Produto, String> colunaDescricao;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoque;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoque_Minimo;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoque_Minimo_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaLoja;

    @FXML
    private TableColumn<Produto, String> colunaMarca;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Double> colunaPreco;

    @FXML
    private TableColumn<Produto, Integer> colunaVendidos;

    @FXML
    private TableView<Produto> Tabela_Produto_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaCategoria_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaCodigo_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaDescricao_Adicionar;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoque_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaLoja_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaMarca_Adicionar;

    @FXML
    private TableColumn<Produto, String> colunaNome_Adicionar;

    @FXML
    private TableColumn<Produto, Double> colunaPreco_Adicionar;

    @FXML
    private TableColumn<Produto, Integer> colunaVendidos_Adicionar;

    @FXML
    private Label Total_Produtos;

    @FXML
    private Label Vendas_Mes;

    @FXML
    private Button btn_Adicionar_Produto;

    @FXML
    private Button btn_Atualizar_Produto;

    @FXML
    private ComboBox<String> input_Atualizar_Categoria;

    @FXML
    private TextField input_Atualizar_Codigo;

    @FXML
    private TextField input_Atualizar_Codigo_Produto;

    @FXML
    private TextArea input_Atualizar_Descricao;

    @FXML
    private TextField input_Atualizar_Estoque;

    @FXML
    private TextField input_Atualizar_Loja;

    @FXML
    private TextField input_Atualizar_Marca;

    @FXML
    private TextField input_Atualizar_Nome;

    @FXML
    private TextField input_Atualizar_Preco;

    @FXML
    private TextField input_Atualizar_Vendidos;

    @FXML
    private Button btnFechar;

    @FXML
    private Button btnMinimizer;

    @FXML
    private AnchorPane Container_Adicionar_Produto;

    @FXML
    private AnchorPane Container_Atualizar_Produto;

    @FXML
    private AnchorPane Container_Estatisticas_Produto;

    @FXML
    private AnchorPane Container_Remover_Produto;

    private List<Produto> produtos; // Definindo produtos como um atributo da classe
    private List<String> categorias = new ArrayList<String>();

    private ObservableList<String> categoriasObservableList;
    private ObservableList<Produto> DadosTabelaProdutoRemover = FXCollections.observableArrayList();
    private ObservableList<Produto> DadosTabelaProdutoAdicionar = FXCollections.observableArrayList();

    private FilteredList<Produto> filteredListRemover = new FilteredList<>(DadosTabelaProdutoRemover, p -> true);
    private FilteredList<Produto> filteredListAdicionar = new FilteredList<>(DadosTabelaProdutoAdicionar, p -> true);

  private byte[] imagemBytes;

    @FXML
    void Adicionar_Produto() {
        Container_Adicionar_Produto.setVisible(true);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(false);
    }

    @FXML
    void Atualizar_Produto() {
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(true);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(false);
    }

    @FXML
    void Estatisticas_Produto() {
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(true);
    }

    @FXML
    void Remover_Produto() {
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(true);
        Container_Estatisticas_Produto.setVisible(false);
    }

    @FXML
    public byte[] Importar_Foto_Adicionar_Produto() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Carregar Imagem");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                Imagem_Produto_Adicionar.setImage(new Image(file.toURI().toString()));
                imagemBytes = Files.readAllBytes(file.toPath());
                return imagemBytes;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @FXML
    private void close() {
        // Obter a janela atual
        Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close(); // Fecha a janela
    }

    @FXML
    private void minimizer() {
        // Obter a janela atual
        Stage stage = (Stage) btnMinimizer.getScene().getWindow();
        stage.setIconified(true);  // Minimiza a janela
    }

    @FXML
    private void Limpar_Remover_Produto() {
        Input_Remover_Produto.setText(null);
        Pesquisar_Remover_Produto.setText(null);
    }

    @FXML
    private void Limpar_Adicionar_Produto() {
        Pesquisar_Adicionar_Produto.setText(null);
        Adicionar_Produto_Nome.setText(null);
        Adicionar_Produto_Preco.setText(null);
        Adicionar_Produto_Marca.setText(null);
        Adicionar_Produto_Estoque.setText(null);
        Adicionar_Produto_Categorias.setValue(null);
        Adicionar_Produto_Descricao.setText(null);
        Adicionar_Produto_Estoque_Minimo.setText(null);
        Imagem_Produto_Adicionar.setImage(new Image(new File("src/icon/image2.png").toURI().toString()));

    }
    @FXML
    private void btn_Adicionar_Produto() throws FileNotFoundException {
        String codigo = gerarCodigoProduto();// Gera automaticamente o código do produto
        // Obtém os demais valores dos campos
        String nome = Adicionar_Produto_Nome.getText();
        String precoTexto = Adicionar_Produto_Preco.getText();
        String marca = Adicionar_Produto_Marca.getText();
        String estoqueTexto = Adicionar_Produto_Estoque.getText();
        String estoque_minimoTexto = Adicionar_Produto_Estoque_Minimo.getText();
        String categoria = Adicionar_Produto_Categorias.getSelectionModel().getSelectedItem();
        String descricao = Adicionar_Produto_Descricao.getText();
        String cnpj_loja = "23.456.789/0001-95";
        int vendidos = 0;
        byte[] fotoBytes = imagemBytes;

        // Validação de campos obrigatórios
        if (nome.trim().isEmpty() || precoTexto.trim().isEmpty() || estoqueTexto.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Obrigatórios");
            alert.setHeaderText(null);
            alert.setContentText("Preencha todos os campos obrigatórios.");
            alert.showAndWait();
            return;
        }

        try {
            // Conversão de valores
            double preco = Double.parseDouble(precoTexto);
            int estoque = Integer.parseInt(estoqueTexto);
            int estoque_minimo = Integer.parseInt(estoque_minimoTexto);

            // Criação do objeto produto
            Produto novoProduto = new Produto(codigo, nome, preco, estoque, estoque_minimo, vendidos, categoria, marca, descricao, fotoBytes, cnpj_loja);

            // Salvando o produto no banco (DAO)
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.cadastrarProduto(novoProduto);

            // Feedback para o usuário
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Produto cadastrado com sucesso! Código: " + codigo);
            alert.showAndWait();

            // Limpar os campos após o cadastro
            Limpar_Adicionar_Produto();

            produtos = ProdutoDAO.listarProdutos();
            carregarDadosTabelaProduto();
            carregarListaCategorias();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Preço e estoque devem ser valores numéricos.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao cadastrar o produto: " + e.getMessage());
            alert.showAndWait();
        }
    }



    @FXML
    private void btn_Remover_Produto(){
        // Obtém o código digitado no campo de texto
        String codigo = Input_Remover_Produto.getText();

        // Verifica se o código não está vazio
        if (codigo == null || codigo.trim().isEmpty()) {
            // Usando um Alert para mostrar a mensagem de erro ao usuário
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira um código válido.");
            alert.showAndWait();
            return;
        }

        try {
            // Chama o metodo para excluir o produto
            ProdutoDAO produtoDAO = new ProdutoDAO(); // Instância do DAO
            produtoDAO.excluirProduto(codigo);
            // Feedback para o usuário
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Produto com código " + codigo + " foi excluído com sucesso!");
            alert.showAndWait();

            // Atualiza a tabela após a remoção do produto
            DadosTabelaProdutoRemover.removeIf(produto -> produto.getCodigo().equals(codigo));
            DadosTabelaProdutoAdicionar.removeIf(produto -> produto.getCodigo().equals(codigo));
            Tabela_Produto_Remover.refresh(); // Atualiza a tabela para refletir a exclusão
            Tabela_Produto_Adicionar.refresh();

            // Atualiza quantidade de produto na janela de estatísticas
            produtos = ProdutoDAO.listarProdutos();
            Total_Produtos.setText(String.valueOf(produtos.size()));

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao tentar remover o produto");
            alert.showAndWait();
            System.out.println("Erro ao tentar remover o produto: " + e.getMessage());
            e.printStackTrace(); // Para depuração (pode ser substituído por um logger)
        }
    }

    // Metodo para gerar automaticamente o próximo código do produto
    private String gerarCodigoProduto() {
        // Prefixo fixo
        String prefixo = "CPV";

        // Ano atual
        String ano = String.valueOf(java.time.Year.now());

        // Obtém o último produto cadastrado no banco
        ProdutoDAO produtoDAO = new ProdutoDAO();
        String ultimoCodigo = produtoDAO.obterUltimoCodigoProduto(); // Metodo no DAO

        // Calcula o próximo número sequencial
        int numeroSequencial = 1; // Inicia no 1 se não houver código anterior
        if (ultimoCodigo != null && ultimoCodigo.startsWith(prefixo)) {
            String sequencialStr = ultimoCodigo.substring(9); // Obtém os últimos 5 dígitos
            numeroSequencial = Integer.parseInt(sequencialStr) + 1;
        }

        // Formata o número sequencial com 5 dígitos
        String sequencialFormatado = String.format("%05d", numeroSequencial);

        // Retorna o código completo
        return String.format("%s-%s-%s", prefixo, ano, sequencialFormatado);
    }


    private void carregarListaCategorias(){
        // Limpando a lista de categorias para evitar duplicações
        categorias.clear();

        // Adicionando categorias a partir dos produtos
        for (Produto produto : produtos) {
            String categoria = produto.getCategoria();
            if (categoria != null && !categorias.contains(categoria)) {
                categorias.add(categoria);
            }
        }

        // Ordenando as categorias em ordem alfabética
        Collections.sort(categorias);

        categoriasObservableList = FXCollections.observableArrayList(categorias);
        Adicionar_Produto_Categorias.setItems(categoriasObservableList);
        input_Atualizar_Categoria.setItems(categoriasObservableList);
    }

    private void carregarDadosTabelaProduto() {
        // Limpa os dados antes de carregar
        DadosTabelaProdutoRemover.clear();
        DadosTabelaProdutoAdicionar.clear();

        // Adiciona todos os produtos à lista
        DadosTabelaProdutoRemover.addAll(produtos);
        DadosTabelaProdutoAdicionar.addAll(produtos);
    }

    // Metodo para configurar as colunas da TableView
    private void configurarColunasTabela() {
        //Configuração Tabela Pagina de remover produto
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colunaPreco.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPreco()).asObject());
        colunaEstoque.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque()).asObject());
        colunaEstoque_Minimo.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque_minimo()).asObject());
        colunaVendidos.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVendidos()).asObject());
        colunaCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colunaMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        colunaLoja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCnpj_loja()));

        //Configuração Tabela Pagina de adicionar produto
        colunaNome_Adicionar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaCodigo_Adicionar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colunaPreco_Adicionar.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPreco()).asObject());
        colunaEstoque_Adicionar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque()).asObject());
        colunaEstoque_Minimo_Adicionar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque_minimo()).asObject());
        colunaVendidos_Adicionar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVendidos()).asObject());
        colunaCategoria_Adicionar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colunaMarca_Adicionar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        colunaDescricao_Adicionar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        colunaLoja_Adicionar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCnpj_loja()));
    }

    public void eventos(){
        // Adicionando o evento de pressionar Enter no TextField
        Input_Remover_Produto.setOnAction(event -> {
            btn_Remover_Produto();
        });

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Tabela_Produto_Remover.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Captura os dados da linha selecionada
                Produto produtoSelecionado_Remover = newValue;
                Input_Remover_Produto.setText(produtoSelecionado_Remover.getCodigo());
            }
        });

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Tabela_Produto_Adicionar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Captura os dados da linha selecionada
                Produto produtoSelecionado_Adicionar = newValue;
                Adicionar_Produto_Nome.setText(produtoSelecionado_Adicionar.getNome());
                Adicionar_Produto_Preco.setText(String.valueOf(produtoSelecionado_Adicionar.getPreco()));
                Adicionar_Produto_Marca.setText(produtoSelecionado_Adicionar.getMarca());
                Adicionar_Produto_Estoque.setText(String.valueOf(produtoSelecionado_Adicionar.getEstoque()));
                Adicionar_Produto_Estoque_Minimo.setText(String.valueOf(produtoSelecionado_Adicionar.getEstoque_minimo()));
                Adicionar_Produto_Categorias.setValue(produtoSelecionado_Adicionar.getCategoria());
                Adicionar_Produto_Descricao.setText(produtoSelecionado_Adicionar.getDescricao());
                //Pegar Imagem
                Image image = new Image(new ByteArrayInputStream(produtoSelecionado_Adicionar.getFoto()));
                Imagem_Produto_Adicionar.setImage(image);
            }
        });

        Adicionar_Produto_Preco.setTextFormatter(new TextFormatter<>(change -> {
            String caracteresPermitidos = "0123456789.";
            if (change.getText().matches("[" + caracteresPermitidos + "]*")) {
                return change; // Aceita a mudança
            }
            return null; // Rejeita a mudança
        }));

        Adicionar_Produto_Estoque.setTextFormatter(new TextFormatter<>(change -> {
            String caracteresPermitidos = "0123456789";
            if (change.getText().matches("[" + caracteresPermitidos + "]*")) {
                return change; // Aceita a mudança
            }
            return null; // Rejeita a mudança
        }));
    }

    public void filtroPesquisaRemover(){
        // Criação da lista filtrada
        filteredListRemover = new FilteredList<>(DadosTabelaProdutoRemover, p -> true); // Inicialmente, sem filtro

        // Adiciona um listener ao TextField de pesquisa para filtrar os produtos
        Pesquisar_Remover_Produto.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListRemover.setPredicate(produto -> {
                // Se o texto de pesquisa estiver vazio, mostra todos os produtos
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Converte o texto de pesquisa para minúsculas
                String lowerCaseFilter = newValue.toLowerCase();

                // Filtra os produtos com base no nome ou no código
                return produto.getNome().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCodigo().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCategoria().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getMarca().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getDescricao().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCnpj_loja().toLowerCase().contains(lowerCaseFilter);
            });
        });

        // Configura a TableView para usar o filtro
        Tabela_Produto_Remover.setItems(filteredListRemover);
    }

    public void filtroPesquisaAdicionar(){
        // Criação da lista filtrada
        filteredListAdicionar = new FilteredList<>(DadosTabelaProdutoAdicionar, p -> true); // Inicialmente, sem filtro

        // Adiciona um listener ao TextField de pesquisa para filtrar os produtos
        Pesquisar_Adicionar_Produto.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListAdicionar.setPredicate(produto -> {
                // Se o texto de pesquisa estiver vazio, mostra todos os produtos
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Converte o texto de pesquisa para minúsculas
                String lowerCaseFilter = newValue.toLowerCase();

                // Filtra os produtos com base no nome ou no código
                return produto.getNome().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCodigo().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCategoria().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getMarca().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getDescricao().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCnpj_loja().toLowerCase().contains(lowerCaseFilter);
            });
        });

        // Configura a TableView para usar o filtro
        Tabela_Produto_Adicionar.setItems(filteredListAdicionar);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtos = ProdutoDAO.listarProdutos();
        Total_Produtos.setText(String.valueOf(produtos.size()));

        configurarColunasTabela(); // Configura as colunas da TableView
        filtroPesquisaRemover(); // Filtro da textfield Pesquisa
        filtroPesquisaAdicionar(); // Filtro da textfield Pesquisa
        carregarListaCategorias(); // Carrega a lista de categorias da combobox
        carregarDadosTabelaProduto(); // Carrega os dados para a tabela
        eventos(); // Adiciona eventos de teclado
    }

}
