/*package controller;

import dao.ClienteDAO;
import dao.conexao.ConexaoDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import model.entity.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Cliente;
import model.entity.Sexo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ResourceBundle;

public class GerenciamentoClientesController implements Initializable {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaCPF;

    @FXML
    private TableColumn<Cliente, String> colunaEstado;

    @FXML
    private TableColumn<Cliente, String> colunaStatus;

    @FXML
    private TableColumn<Cliente, Sexo> colunaSexo;

    @FXML
    private TableColumn<Cliente, String> colunaData_Nascimento;

    @FXML
    private TableColumn<Cliente, String> colunaData_Registro;

    @FXML
    private TableView<Cliente> tabelaClientes;

    private ObservableList<Cliente> listaClientes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarTabelaClientes();

        colunaCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colunaData_Nascimento.setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));
        colunaData_Registro.setCellValueFactory(new PropertyValueFactory<>("data_registro"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status_cliente"));


        btCadastrar.setOnAction(event -> cadastrarCliente());
        btEditar.setOnAction(event -> editarCliente());
        btExcluir.setOnAction(event -> excluirCliente());
    }

    public void carregarTabelaClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();

        List<Cliente> lista = clienteDAO.listarClientes();
        listaClientes = FXCollections.observableArrayList(lista);
        tabelaClientes.setItems(listaClientes);

    }

    @FXML
    private void cadastrarCliente() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CadastroCliente.fxml"));
            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(loader.load()));
            popupStage.setTitle("Cadastro de Cliente");
            popupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void editarCliente() {
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            System.out.println("Editar cliente: " + clienteSelecionado.getNome());
        } else {  // Aqui pode ser implementada a lógica para abrir uma tela de edição
            System.out.println("Selecione um cliente para editar.");
        }
    }

    @FXML
    private void excluirCliente() {
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            listaClientes.remove(clienteSelecionado);
            System.out.println("Cliente removido: " + clienteSelecionado.getNome());
        } else {
            System.out.println("Selecione um cliente para excluir.");
        }
    }
}
*/
