package controller;

import dao.ProdutoDAO;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
import model.entity.Produto;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable {

    @FXML
    private Button Adicionar_Produto;

    @FXML
    private ComboBox<String> Adicionar_Produto_Categorias;

    @FXML
    private TextArea Adicionar_Produto_Descricao;

    @FXML
    private TextField Adicionar_Produto_Estoque;

    @FXML
    private TextField Adicionar_Produto_Fornecedor;

    @FXML
    private TextField Adicionar_Produto_Marca;

    @FXML
    private TextField Adicionar_Produto_Nome;

    @FXML
    private TextField Adicionar_Produto_Preco;

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
    private Button Importar_Adicionar_Produto;

    @FXML
    private Button Importar_Atualizar_Produto;

    @FXML
    private TextField Input_Remover_Produto;

    @FXML
    private Button Limpar_Adicionar_Produto;

    @FXML
    private Button Limpar_Atualizar_Produto;

    @FXML
    private Button Limpar_Remover_Produto;

    @FXML
    private Button Pesquisar_Adicionar_Produto;

    @FXML
    private ImageView Pesquisar_Atualizar_Produto;

    @FXML
    private TextField Pesquisar_Remover_Produto;

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
    private TableColumn<Produto, String> colunaFornecedor;

    @FXML
    private TableColumn<Produto, String> colunaFoto;

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
    private TextField input_Atualizar_Fornecedor;

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
    private ObservableList<Produto> DadosTabelaProduto = FXCollections.observableArrayList();

    private FilteredList<Produto> filteredList = new FilteredList<>(DadosTabelaProduto, p -> true);

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
            DadosTabelaProduto.removeIf(produto -> produto.getCodigo().equals(codigo));
            Tabela_Produto_Remover.refresh(); // Atualiza a tabela para refletir a exclusão

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
        /*if (produtos != null && !produtos.isEmpty()) {
            for (Produto produto : produtos) {
                DadosTabelaProduto.add(produto); // Adiciona cada produto à lista observável
            }
        } else {
            System.out.println("Lista de produtos está vazia ou nula.");
        }
        Tabela_Produto_Remover.setItems(DadosTabelaProduto);*/
        DadosTabelaProduto.clear(); // Limpa os dados antes de carregar
        DadosTabelaProduto.addAll(produtos); // Adiciona todos os produtos à lista
    }

    // Metodo para configurar as colunas da TableView
    private void configurarColunasTabela() {
        colunaNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colunaPreco.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPreco()).asObject());
        colunaEstoque.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque()).asObject());
        colunaVendidos.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVendidos()).asObject());
        colunaCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colunaMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        colunaDescricao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        colunaLoja.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCnpj_loja()));
    }

    public void eventos(){
        // Adicionando o evento de pressionar Enter no TextField
        Input_Remover_Produto.setOnAction(event -> {
            btn_Remover_Produto();
        });
    }

    public void filtroPesquisa(){
        // Criação da lista filtrada
        filteredList = new FilteredList<>(DadosTabelaProduto, p -> true); // Inicialmente, sem filtro

        // Adiciona um listener ao TextField de pesquisa para filtrar os produtos
        Pesquisar_Remover_Produto.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(produto -> {
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
        Tabela_Produto_Remover.setItems(filteredList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtos = ProdutoDAO.listarProdutos();
        Total_Produtos.setText(String.valueOf(produtos.size()));

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Tabela_Produto_Remover.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Captura os dados da linha selecionada
                Produto produtoSelecionado = newValue;
                Input_Remover_Produto.setText(produtoSelecionado.getCodigo());
            }
        });

        configurarColunasTabela(); // Configura as colunas da TableView
        filtroPesquisa(); // Filtro da textfield Pesquisa
        carregarListaCategorias(); // Carrega a lista de categorias da combobox
        carregarDadosTabelaProduto(); // Carrega os dados para a tabela
        eventos(); // Adiciona eventos de teclado
    }

}
