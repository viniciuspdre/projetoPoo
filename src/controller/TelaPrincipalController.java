package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Usuario;

import java.io.IOException;

public class TelaPrincipalController {

    @FXML
    ImageView iconCliente;
    @FXML
    ImageView iconProduto;
    @FXML
    ImageView iconVenda;
    @FXML
    private Label labelUsuario;
    @FXML
    private AnchorPane painelUsuario;
    @FXML
    private Button btEncerrarSessao;
    @FXML
    private Button btEntrarVenda;
    @FXML
    private Button btEntrarProduto;
    @FXML
    private Button btEntrarCliente;

    private Usuario usuarioLogado;

    public void initialize() {
        Image icon = new Image("/icon/cliente.png");
        iconCliente.setImage(icon);
        icon = new Image("/icon/produto.png");
        iconProduto.setImage(icon);
        icon = new Image("/icon/venda.png");
        iconVenda.setImage(icon);
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        labelUsuario.setText(usuario.getNome());
        painelUsuario.setLeftAnchor(labelUsuario, 0.0);
        painelUsuario.setRightAnchor(labelUsuario, 0.0);
        labelUsuario.setAlignment(Pos.CENTER);
    }

    @FXML
    private void encerrarSessao() {
        Stage stage = (Stage) btEncerrarSessao.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void entrarVenda() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GerenciamentoVendas.fxml"));
            Parent root = loader.load();
            GerenciamentoVendasController controller = loader.getController();
            controller.setUsuario(usuarioLogado);

            Stage telaGerenciamento = new Stage();
            telaGerenciamento.setTitle("Gerenciamento de Vendas");
            telaGerenciamento.setScene(new Scene(root));
            telaGerenciamento.setResizable(false);
            telaGerenciamento.show();

            Stage stage = ( Stage ) btEntrarProduto.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            System.out.println("Erro ao entrar Venda");
        }
    }

    @FXML
    private void entrarProduto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Produtos.fxml"));
            Parent root = loader.load();
            ProdutoController controller = loader.getController();
            controller.setUsuario(usuarioLogado);

            Stage telaGerenciamento = new Stage();
            telaGerenciamento.setTitle("Gerenciamento de Produtos");
            telaGerenciamento.setScene(new Scene(root));
            telaGerenciamento.setResizable(false);
            telaGerenciamento.show();

            Stage stage = ( Stage ) btEntrarVenda.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            System.out.println("Erro ao entrar Venda");
        }
    }

    @FXML
    private void entrarCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GerenciamentoClientes.fxml"));
            Parent root = loader.load();
            GerenciamentoClientesController controller = loader.getController();
            controller.setUsuario(usuarioLogado);

            Stage telaGerenciamento = new Stage();
            telaGerenciamento.setTitle("Gerenciamento de Clientes");
            telaGerenciamento.setScene(new Scene(root));
            telaGerenciamento.setResizable(false);
            telaGerenciamento.show();

            Stage stage = ( Stage ) btEntrarCliente.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            System.out.println("Erro ao entrar Venda");
        }
    }
}

