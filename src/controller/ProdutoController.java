package controller;

import dao.ProdutoDAO;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produto;
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
import model.Usuario;

import java.io.*;

import java.awt.*;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.List;

public class ProdutoController extends Component implements Initializable {

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
    private ImageView Image_Adicionar_Produto;

    @FXML
    private ImageView Image_Atualizar_Produto;

    @FXML
    private TextField Input_Remover_Produto;

    @FXML
    private TextField Pesquisar_Atualizar_Produto;

    @FXML
    private ImageView Imagem_Produto_Adicionar;

    @FXML
    private TextField Pesquisar_Remover_Produto;

    @FXML
    private TextField Pesquisar_Adicionar_Produto;

    @FXML
    private Label Alerta_Estoque;

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
    private TableView<Produto> Tabela_Produto_Atualizar;

    @FXML
    private TableColumn<Produto, String> colunaCategoriaAtualizar;

    @FXML
    private TableColumn<Produto, String> colunaCodigoAtualizar;

    @FXML
    private TableColumn<Produto, String> colunaDescricaoAtualizar;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoqueAtualizar;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoqueMinimoAtualizar;

    @FXML
    private TableColumn<Produto, String> colunaLojaAtualizar;

    @FXML
    private TableColumn<Produto, String> colunaMarcaAtualizar;

    @FXML
    private TableColumn<Produto, String> colunaNomeAtualizar;

    @FXML
    private TableColumn<Produto, Double> colunaPrecoAtualizar;

    @FXML
    private TableColumn<Produto, Integer> colunaVendidosAtualizar;

    @FXML
    private TableView<Produto> Table_AlertaEstoque;

    @FXML
    private TableColumn<Produto, String> colunaCategoriaAlerta;

    @FXML
    private TableColumn<Produto, String> colunaCodigoAlerta;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoqueAlerta;

    @FXML
    private TableColumn<Produto, Integer> colunaEstoqueMinimoAlerta;

    @FXML
    private TableColumn<Produto, String> colunaMarcaAlerta;

    @FXML
    private TableColumn<Produto, String> colunaNomeAlerta;

    @FXML
    private TableColumn<Produto, Double> colunaPrecoAlerta;

    @FXML
    private TableColumn<Produto, Integer> colunaVendidosAlerta;

    @FXML
    private Label Total_Produtos;

    @FXML
    private Label Vendas_Mes;

    @FXML
    private ComboBox<String> input_Atualizar_Categoria;

    @FXML
    private TextField input_Atualizar_EstoqueMinimo;

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

    @FXML private TextField input_Atualizar_Vendidos;
    @FXML private Button btnFechar;
    @FXML private Button btnMinimizer;
    @FXML private AnchorPane Container_Adicionar_Produto;
    @FXML private AnchorPane Container_Atualizar_Produto;
    @FXML private AnchorPane Container_Estatisticas_Produto;
    @FXML private AnchorPane Container_Remover_Produto;
    @FXML private AnchorPane Container_AlertaEstoque;
    @FXML private AnchorPane Container_Produto;
    @FXML private TextField Pesquisar_AlertaEstoque;
    @FXML private Label Alerta_Nome;
    @FXML private Label Alerta_Codigo_Produto;
    @FXML private Label Alerta_Marca;
    @FXML private Label Alerta_Estoque_Label;
    @FXML private Label Alerta_EstoqueMinimo;
    @FXML private Label Alerta_Vendidos;
    @FXML private Label Alerta_Categoria;
    @FXML private Label Alerta_Descricao;
    @FXML private Label Alerta_Loja;
    @FXML private Label Alerta_Preco;
    @FXML private ImageView Image_Alerta_Produto;
    @FXML private AnchorPane Container_Home;
    @FXML private GridPane Catalogo;
    @FXML private TextField Pesquisar_Home;
    @FXML private ScrollPane Scroll_Catalogo;
    @FXML private Label nome_Produto;
    @FXML private ImageView view;
    @FXML private Label preco_Produto;
    @FXML private Label descricao_Produto;
    @FXML private Label parcelamento_Produto;
    @FXML private Label estoque_Produto;
    @FXML private Label entrega_Produtos;
    @FXML private Label codigo_Produto;
    @FXML private Label marca_Produtos;
    @FXML private AnchorPane Container_Pagamentos;
    @FXML private Label labelUsuario;
    @FXML private AnchorPane painelUsuario;

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

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        labelUsuario.setText(usuario.getNome());
        painelUsuario.setLeftAnchor(labelUsuario, 0.0);
        painelUsuario.setRightAnchor(labelUsuario, 0.0);
        labelUsuario.setAlignment(Pos.CENTER);
    }

    private List<Produto> produtos; // Definindo produtos como um atributo da classe
    private List<Produto> alertaEstoque;
    private List<String> categorias = new ArrayList<String>();
    private Produto catalogo_Produto;

    private ObservableList<String> categoriasObservableList;
    private ObservableList<Produto> DadosTabelaProdutoRemover = FXCollections.observableArrayList();
    private ObservableList<Produto> DadosTabelaProdutoAdicionar = FXCollections.observableArrayList();
    private ObservableList<Produto> DadosTabelaProdutoAtualizar = FXCollections.observableArrayList();
    private ObservableList<Produto> DadosTabelaProdutoAlerta = FXCollections.observableArrayList();
    private ObservableList<Produto> listaProdutos = FXCollections.observableArrayList();

    private FilteredList<Produto> filteredListRemover = new FilteredList<>(DadosTabelaProdutoRemover, p -> true);
    private FilteredList<Produto> filteredListAdicionar = new FilteredList<>(DadosTabelaProdutoAdicionar, p -> true);
    private FilteredList<Produto> filteredListAtualizar = new FilteredList<>(DadosTabelaProdutoAtualizar, p -> true);
    private FilteredList<Produto> filteredListAlerta = new FilteredList<>(DadosTabelaProdutoAlerta, p -> true);
    private FilteredList<Produto> filteredListCatalogo = new FilteredList<>(listaProdutos, p -> true);;

    private byte[] imagemBytes;

    @FXML
    public void Adicionar_Produto() {
        Container_Adicionar_Produto.setVisible(true);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(false);
        Container_AlertaEstoque.setVisible(false);
        Container_Produto.setVisible(false);
        Container_Home.setVisible(false);
        Container_Pagamentos.setVisible(false);
    }

    @FXML
    public void Atualizar_Produto() {
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(true);
        Container_Remover_Produto.setVisible(false);
        Container_AlertaEstoque.setVisible(false);
        Container_Home.setVisible(false);
        Container_Produto.setVisible(false);
        Container_Pagamentos.setVisible(false);
    }

    @FXML
    public void Estatisticas_Produto() {
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(true);
        Container_AlertaEstoque.setVisible(false);
        Container_Home.setVisible(false);
        Container_Produto.setVisible(false);
        Container_Pagamentos.setVisible(false);
    }

    @FXML
    public void Remover_Produto() {
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(true);
        Container_Estatisticas_Produto.setVisible(false);
        Container_AlertaEstoque.setVisible(false);
        Container_Home.setVisible(false);
        Container_Produto.setVisible(false);
        Container_Pagamentos.setVisible(false);
    }
    @FXML
     public void Estoque_Alerta(){
        Container_AlertaEstoque.setVisible(true);
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(false);
        Container_Home.setVisible(false);
        Container_Produto.setVisible(false);
        Container_Pagamentos.setVisible(false);
    }

    @FXML public void Home(){
        Container_Home.setVisible(true);
        Container_AlertaEstoque.setVisible(false);
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(false);
        Container_Produto.setVisible(false);
        Container_Pagamentos.setVisible(false);
    }

    @FXML public void Comprar(){}

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
    public byte[] Importar_Atualizar_Produto() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Carregar Imagem");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                Image_Atualizar_Produto.setImage(new Image(file.toURI().toString()));
                imagemBytes = Files.readAllBytes(file.toPath());
                return imagemBytes;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @FXML
    void btn_meio_Pagamento() {
        Container_Pagamentos.setVisible(true);
        Container_Home.setVisible(false);
        Container_AlertaEstoque.setVisible(false);
        Container_Adicionar_Produto.setVisible(false);
        Container_Atualizar_Produto.setVisible(false);
        Container_Remover_Produto.setVisible(false);
        Container_Estatisticas_Produto.setVisible(false);
        Container_Produto.setVisible(true);
    }

    @FXML
    private void close() {
        // Obter a janela atual
        Container_Pagamentos.setVisible(false);
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
    private void Limpar_Atualizar_Produto() {
        Pesquisar_Atualizar_Produto.setText(null);
        input_Atualizar_Nome.setText(null);
        input_Atualizar_Marca.setText(null);
        input_Atualizar_Preco.setText(null);
        input_Atualizar_Estoque.setText(null);
        input_Atualizar_EstoqueMinimo.setText(null);
        input_Atualizar_Vendidos.setText(null);
        input_Atualizar_Categoria.setValue(null);
        input_Atualizar_Loja.setText(null);
        input_Atualizar_Descricao.setText(null);
        input_Atualizar_Codigo_Produto.setText(null);
        Image_Atualizar_Produto.setImage(new Image(new File("src/icon/image2.png").toURI().toString()));
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
        int vendidos = gerarNumeroVendas();
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
            atualizarDados();

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
    private void btn_Atualizar_Produto(){
        // Obtém o código do produto a ser atualizado
        String codigo = input_Atualizar_Codigo_Produto.getText();
        System.out.println(codigo);
        // Obtém os valores dos outros campos
        String nome = input_Atualizar_Nome.getText();
        String precoTexto = input_Atualizar_Preco.getText();
        String marca = input_Atualizar_Marca.getText();
        String estoqueTexto = input_Atualizar_Estoque.getText();
        String estoqueMinimoTexto = input_Atualizar_EstoqueMinimo.getText();
        String categoria = input_Atualizar_Categoria.getValue();
        String descricao = input_Atualizar_Descricao.getText();
        String vendidosTexto = input_Atualizar_Vendidos.getText();
        String loja = input_Atualizar_Loja.getText();
        byte[] fotoBytes = imagemBytes;

        // Validação de campos obrigatórios
        if (codigo.trim().isEmpty()) {
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
            int estoqueMinimo = Integer.parseInt(estoqueMinimoTexto);
            int vendidos = Integer.parseInt(vendidosTexto);

            // Criação do objeto Produto com os dados atualizados
            Produto produtoAtualizado = new Produto(codigo, nome, preco, estoque, estoqueMinimo, vendidos, categoria, marca, descricao, fotoBytes, loja);

            // Chama o metodo do DAO para atualizar o produto
            ProdutoDAO produtoDAO = new ProdutoDAO();
            boolean sucesso = produtoDAO.atualizarProduto(produtoAtualizado);

            if (sucesso) {
                // Feedback para o usuário
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sucesso");
                alert.setHeaderText(null);
                alert.setContentText("Produto atualizado com sucesso! Código: " + codigo);
                alert.showAndWait();
            } else {
                // Caso o produto não seja encontrado
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Produto não encontrado para o código fornecido.");
                alert.showAndWait();
            }

            // Limpar os campos após a atualização
            Limpar_Atualizar_Produto();
            atualizarDados();

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
            alert.setContentText("Erro ao atualizar o produto: " + e.getMessage());
            System.out.println(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void btn_Remover_Produto() throws SQLException {
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
            DadosTabelaProdutoAtualizar.removeIf(produto -> produto.getCodigo().equals(codigo));
            Tabela_Produto_Remover.refresh(); // Atualiza a tabela para refletir a exclusão
            Tabela_Produto_Adicionar.refresh();
            Tabela_Produto_Atualizar.refresh();

            Limpar_Remover_Produto();
            atualizarDados();

        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao tentar remover o produto, pois o mesmo já foi vendido pelo menos uma vez");
            alert.showAndWait();
            System.out.println("Erro ao tentar remover o produto: " + e.getMessage());
            e.printStackTrace(); // Para depuração (pode ser substituído por um logger)
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao tentar remover o produto.");
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

    private int gerarNumeroVendas(){
        Random gerador = new Random();
        return gerador.nextInt(151);
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
        DadosTabelaProdutoAtualizar.clear();
        DadosTabelaProdutoAlerta.clear();
        listaProdutos.clear();

        // Adiciona todos os produtos à lista
        DadosTabelaProdutoRemover.addAll(produtos);
        DadosTabelaProdutoAdicionar.addAll(produtos);
        DadosTabelaProdutoAtualizar.addAll(produtos);
        DadosTabelaProdutoAlerta.addAll(alertaEstoque);
        listaProdutos.addAll(ProdutoDAO.listarProdutos());
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

        //Configuração Tabela Pagina de remover produto
        colunaNomeAtualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaCodigoAtualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colunaPrecoAtualizar.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPreco()).asObject());
        colunaEstoqueAtualizar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque()).asObject());
        colunaEstoqueMinimoAtualizar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque_minimo()).asObject());
        colunaVendidosAtualizar.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVendidos()).asObject());
        colunaCategoriaAtualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colunaMarcaAtualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        colunaDescricaoAtualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricao()));
        colunaLojaAtualizar.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCnpj_loja()));

        //Configuração Tabela Pagina de alerta produto
        colunaNomeAlerta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaCodigoAlerta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colunaPrecoAlerta.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPreco()).asObject());
        colunaEstoqueAlerta.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque()).asObject());
        colunaEstoqueMinimoAlerta.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEstoque_minimo()).asObject());
        colunaVendidosAlerta.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getVendidos()).asObject());
        colunaCategoriaAlerta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategoria()));
        colunaMarcaAlerta.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
    }

    public void eventos() throws SQLException{
        // Adicionando o evento de pressionar Enter no TextField
            Input_Remover_Produto.setOnAction(event -> {
                try {
                    btn_Remover_Produto();
                } catch (SQLIntegrityConstraintViolationException e) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Erro");
                    alerta.setContentText("Erro ao remover produto pois ele já foi vendido pelo menos uma vez");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Tabela_Produto_Remover.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Limpar_Remover_Produto();
                // Captura os dados da linha selecionada
                Produto produtoSelecionado_Remover = newValue;
                Input_Remover_Produto.setText(produtoSelecionado_Remover.getCodigo());
            }
        });

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Tabela_Produto_Adicionar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Captura os dados da linha selecionada
                Limpar_Adicionar_Produto();
                Produto produtoSelecionado_Adicionar = newValue;
                Adicionar_Produto_Nome.setText(produtoSelecionado_Adicionar.getNome());
                Adicionar_Produto_Preco.setText(String.valueOf(produtoSelecionado_Adicionar.getPreco()));
                Adicionar_Produto_Marca.setText(produtoSelecionado_Adicionar.getMarca());
                Adicionar_Produto_Estoque.setText(String.valueOf(produtoSelecionado_Adicionar.getEstoque()));
                Adicionar_Produto_Estoque_Minimo.setText(String.valueOf(produtoSelecionado_Adicionar.getEstoque_minimo()));
                Adicionar_Produto_Categorias.setValue(produtoSelecionado_Adicionar.getCategoria());
                Adicionar_Produto_Descricao.setText(produtoSelecionado_Adicionar.getDescricao());
                //Pegar Imagem
                if (produtoSelecionado_Adicionar.getFoto() != null) {
                    Image image = new Image(new ByteArrayInputStream(produtoSelecionado_Adicionar.getFoto()));
                    Imagem_Produto_Adicionar.setImage(image);
                }
            }
        });

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Tabela_Produto_Atualizar.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Captura os dados da linha selecionada
                Limpar_Atualizar_Produto();
                Produto produtoSelecionado_Atualizar = newValue;
                input_Atualizar_Nome.setText(produtoSelecionado_Atualizar.getNome());
                input_Atualizar_Preco.setText(String.valueOf(produtoSelecionado_Atualizar.getPreco()));
                input_Atualizar_Marca.setText(produtoSelecionado_Atualizar.getMarca());
                input_Atualizar_Estoque.setText(String.valueOf(produtoSelecionado_Atualizar.getEstoque()));
                input_Atualizar_EstoqueMinimo.setText(String.valueOf(produtoSelecionado_Atualizar.getEstoque_minimo()));
                input_Atualizar_Vendidos.setText(String.valueOf(produtoSelecionado_Atualizar.getVendidos()));
                input_Atualizar_Categoria.setValue(produtoSelecionado_Atualizar.getCategoria());
                input_Atualizar_Descricao.setText(produtoSelecionado_Atualizar.getDescricao());
                input_Atualizar_Loja.setText(produtoSelecionado_Atualizar.getCnpj_loja());
                input_Atualizar_Codigo_Produto.setText(produtoSelecionado_Atualizar.getCodigo());
                //Pegar Imagem
                if (produtoSelecionado_Atualizar.getFoto() != null) {
                    Image image = new Image(new ByteArrayInputStream(produtoSelecionado_Atualizar.getFoto()));
                    Image_Atualizar_Produto.setImage(image);
                }
            }
        });

        // Ao clicar em uma linha da tabela tranfere o código dela para o textfield
        Table_AlertaEstoque.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Captura os dados da linha selecionada
                Produto produtoSelecionado_Alerta = newValue;
                Alerta_Nome.setText("Nome: " + produtoSelecionado_Alerta.getNome());
                Alerta_Preco.setText("Preço: " + String.valueOf(produtoSelecionado_Alerta.getPreco()));
                Alerta_Marca.setText("Marca: " + produtoSelecionado_Alerta.getMarca());
                Alerta_Estoque_Label.setText("Estoque: " + String.valueOf(produtoSelecionado_Alerta.getEstoque()));
                Alerta_EstoqueMinimo.setText("Estoque Minímo: " + String.valueOf(produtoSelecionado_Alerta.getEstoque_minimo()));
                Alerta_Vendidos.setText("Vendidos: " + String.valueOf(produtoSelecionado_Alerta.getVendidos()));
                Alerta_Categoria.setText("Categoria: " + produtoSelecionado_Alerta.getCategoria());
                Alerta_Descricao.setText("Descrição: " + produtoSelecionado_Alerta.getDescricao());
                Alerta_Loja.setText("CNPJ Loja: " + produtoSelecionado_Alerta.getCnpj_loja());
                Alerta_Codigo_Produto.setText("Código: " + produtoSelecionado_Alerta.getCodigo());
                //Pegar Imagem
                if (produtoSelecionado_Alerta.getFoto() != null) {
                    Image image = new Image(new ByteArrayInputStream(produtoSelecionado_Alerta.getFoto()));
                    Image_Alerta_Produto.setImage(image);
                }
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

        Adicionar_Produto_Estoque_Minimo.setTextFormatter(new TextFormatter<>(change -> {
            String caracteresPermitidos = "0123456789";
            if (change.getText().matches("[" + caracteresPermitidos + "]*")) {
                return change; // Aceita a mudança
            }
            return null; // Rejeita a mudança
        }));

        input_Atualizar_Preco.setTextFormatter(new TextFormatter<>(change -> {
            String caracteresPermitidos = "0123456789.";
            if (change.getText().matches("[" + caracteresPermitidos + "]*")) {
                return change; // Aceita a mudança
            }
            return null; // Rejeita a mudança
        }));

        input_Atualizar_Vendidos.setTextFormatter(new TextFormatter<>(change -> {
            String caracteresPermitidos = "0123456789";
            if (change.getText().matches("[" + caracteresPermitidos + "]*")) {
                return change; // Aceita a mudança
            }
            return null; // Rejeita a mudança
        }));

        input_Atualizar_Estoque.setTextFormatter(new TextFormatter<>(change -> {
            String caracteresPermitidos = "0123456789";
            if (change.getText().matches("[" + caracteresPermitidos + "]*")) {
                return change; // Aceita a mudança
            }
            return null; // Rejeita a mudança
        }));

        input_Atualizar_EstoqueMinimo.setTextFormatter(new TextFormatter<>(change -> {
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

    public void filtroPesquisaAtualizar(){
        // Criação da lista filtrada
        filteredListAtualizar = new FilteredList<>(DadosTabelaProdutoAtualizar, p -> true); // Inicialmente, sem filtro

        // Adiciona um listener ao TextField de pesquisa para filtrar os produtos
        Pesquisar_Atualizar_Produto.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListAtualizar.setPredicate(produto -> {
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
        Tabela_Produto_Atualizar.setItems(filteredListAtualizar);
    }

    public void filtroPesquisaAlerta(){
        // Criação da lista filtrada
        filteredListAlerta = new FilteredList<>(DadosTabelaProdutoAlerta, p -> true); // Inicialmente, sem filtro

        // Adiciona um listener ao TextField de pesquisa para filtrar os produtos
        Pesquisar_AlertaEstoque.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListAlerta.setPredicate(produto -> {
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
        Table_AlertaEstoque.setItems(filteredListAlerta);
    }

    public void filtroPesquisaProduto() {
        Pesquisar_Home.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListCatalogo.setPredicate(produto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostra todos os produtos se o campo estiver vazio
                }

                String lowerCaseFilter = newValue.toLowerCase();

                return produto.getNome().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCodigo().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCategoria().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getMarca().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getDescricao().toLowerCase().contains(lowerCaseFilter) ||
                        produto.getCnpj_loja().toLowerCase().contains(lowerCaseFilter);
            });

            criarCatalogo(); // Atualiza o catálogo com os produtos filtrados
        });
    }

    public void atualizarDados(){
        produtos = ProdutoDAO.listarProdutos();
        Total_Produtos.setText(String.valueOf(produtos.size()));

        alertaEstoque = ProdutoDAO.listarAlertaEstoque();
        Alerta_Estoque.setText(String.valueOf(alertaEstoque.size()));

        carregarListaCategorias(); // Carrega a lista de categorias da combobox
        carregarDadosTabelaProduto(); // Carrega os dados para a tabela
        filtroPesquisaRemover(); // Filtro da textfield Pesquisa
        filtroPesquisaAdicionar(); // Filtro da textfield Pesquisa
        filtroPesquisaAtualizar(); // FIltro da textfileld Pesquisa
        filtroPesquisaAlerta(); // FIltro da textfileld Pesquisa
        filtroPesquisaProduto();
        criarCatalogo();
    }

    public void criarCatalogo(){
        Scroll_Catalogo.setFitToWidth(true);  // Faz com que a largura do conteúdo se ajuste ao ScrollPane
        Scroll_Catalogo.setFitToHeight(false); // Permite rolagem vertical

        Catalogo.getChildren().clear(); // Limpa o GridPane antes de adicionar novos produtos

        // Ajusta o GridPane para expandir corretamente dentro do ScrollPane
        Catalogo.setPrefWidth(Region.USE_COMPUTED_SIZE);
        Catalogo.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Catalogo.setHgap(10);  // Espaço horizontal entre os cards
        Catalogo.setVgap(10);  // Espaço vertical entre os cards
        Catalogo.setStyle("-fx-padding: 10;");

        // Limpar colunas e linhas do GridPane
        Catalogo.getColumnConstraints().clear();
        Catalogo.getRowConstraints().clear();

        produtos = ProdutoDAO.listarProdutos();
        int numCols = 3;  // Defina o número de colunas desejado (por exemplo, 3)
        int row = 0;
        int col = 0;

        for (Produto produto : filteredListCatalogo) {
            // Adiciona os cards no GridPane com a lógica de posicionamento em linhas e colunas
            VBox card = criarCardProduto(produto);
            // Ajuste do tamanho do card para ocupar toda a célula
            card.setMaxWidth(Double.MAX_VALUE);
            Catalogo.add(card, col, row);

            col++;
            if(col > numCols){
                col = 0;
                row++;
            }
        }
    }

    VBox criarCardProduto(Produto produto) {
        VBox card = new VBox();
        card.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 10; -fx-background-color: #f9f9f9; -fx-background-radius: 10; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 6,0,0,0);");
        card.setAlignment(Pos.CENTER);

        ImageView imageView;

        if (produto.getFoto() != null) {
            Image image = new Image(new ByteArrayInputStream(produto.getFoto()));
            imageView = new ImageView(image);
        } else{
            imageView = new ImageView(new Image("icon/image.png"));
        }

        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true); // Mantém a proporção da imagem

        Label nomeLabel = new Label(produto.getNome());
        nomeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label precoLabel = new Label("R$ " + String.format("%.2f", produto.getPreco()));
        precoLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #1f67b9; -fx-font-weight: bold;");

        Label descricaoLabel = new Label(produto.getDescricao());
        descricaoLabel.setWrapText(true);
        descricaoLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #000;");

        card.getChildren().addAll(imageView,nomeLabel, precoLabel, descricaoLabel);

        // Evento de clique para retornar o produto selecionado
        card.setOnMouseClicked(event -> {
            catalogo_Produto = produto;
            Container_Produto.setVisible(true);
            nome_Produto.setText(catalogo_Produto.getNome());
            nome_Produto.setWrapText(true);
            descricao_Produto.setText(catalogo_Produto.getDescricao());
            descricao_Produto.setWrapText(true);
            preco_Produto.setText("R$ " + String.format("%.2f", catalogo_Produto.getPreco()));
            double precoProduto = catalogo_Produto.getPreco();
            double valorMinParcela = 25.0;
            int maxParcelas = 12;
            int parcelasPossiveis = (int) Math.min(maxParcelas, precoProduto / valorMinParcela);
            parcelamento_Produto.setText("O produto pode ser parcelado em até " + parcelasPossiveis + "x de R$ " + String.format("%.2f", (precoProduto / parcelasPossiveis)));
            estoque_Produto.setText("Estoque disponível: " + catalogo_Produto.getEstoque());
            entrega_Produtos.setText(entrega());
            codigo_Produto.setText(catalogo_Produto.getCodigo());
            marca_Produtos.setText("Vendido por " + catalogo_Produto.getMarca());
            if (catalogo_Produto.getFoto() != null) {
                Image image = new Image(new ByteArrayInputStream(catalogo_Produto.getFoto()));
                view.setImage(image);
            } else{
                view.setImage(new Image("icon/image.png"));
            }
        });

        card.setOnMouseEntered(event -> {
            // Remove seleção de todos os outros cards
            Catalogo.getChildren().forEach(node -> node.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 10; -fx-background-color: #f9f9f9; -fx-background-radius: 10; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 6,0,0,0);"));
            // Destaca o card selecionado
            card.setStyle("-fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 10; -fx-background-color: linear-gradient(to bottom right, #f9f9f9, #5090de); -fx-background-radius: 10; -fx-cursor: hand; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 6,0,0,0);");
        });

        return card;
    }

    public String entrega(){
        Random random = new Random();

        // Lista de dias da semana úteis
        String[] diasSemana = {"segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira"};

        // Gerar dois dias aleatórios diferentes
        int indiceDia1 = random.nextInt(diasSemana.length);
        int indiceDia2;
        do {
            indiceDia2 = random.nextInt(diasSemana.length);
        } while (indiceDia1 == indiceDia2); // Garante que os dias sejam diferentes

        // Ordena os dias para exibir em ordem cronológica
        int menorDia = Math.min(indiceDia1, indiceDia2);
        int maiorDia = Math.max(indiceDia1, indiceDia2);

        // Gerar preços aleatórios dentro do intervalo
        double precoMin = 30.00; // Valor mínimo possível
        double precoMax = 100.00; // Valor máximo possível
        double preco1 = precoMin + (precoMax - precoMin) * random.nextDouble();
        double preco2 = precoMin + (precoMax - precoMin) * random.nextDouble();

        // Garante que os preços sejam exibidos em ordem crescente
        double menorPreco = Math.min(preco1, preco2);
        double maiorPreco = Math.max(preco1, preco2);

        // Criar mensagem formatada
        String mensagem = String.format("Chegará entre %s e %s por R$ %.2f",
                diasSemana[menorDia], diasSemana[maiorDia], menorPreco);


        // Exibir a mensagem
        return mensagem;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Container_Home.setVisible(true);
        atualizarDados();
        configurarColunasTabela(); // Configura as colunas da TableView
        try {
            eventos(); // Adiciona eventos de teclado
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}