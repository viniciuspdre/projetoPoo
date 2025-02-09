package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entity.Vendas;
import view.Telas;

import java.io.IOException;

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
    private TableColumn<Vendas, String> colDesconto;
    @FXML
    private TableColumn<Vendas, String> colFormaPagamento;
    @FXML
    private TableColumn<Vendas, String> colVencimento;
    @FXML
    private TableColumn<Vendas, String> colValor;

    @FXML
    public void initialize() {
    }

    @FXML
    private void cadastrarVenda() {

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
            //scene.getStylesheets().add(getClass().getResource("C:\\Users\\Pedro Lira\\Documents\\projetos\\projetoPoo\\src\\style\\style.css").toExternalForm());
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
