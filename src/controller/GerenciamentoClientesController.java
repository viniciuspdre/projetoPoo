package controller;

import dao.ClienteDAO;
import dao.conexao.ConexaoDB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.entity.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Cliente;
import model.entity.Sexo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ResourceBundle;

import static dao.ClienteDAO.listarClientes;

public class GerenciamentoClientesController implements Initializable {

    @FXML
    private TextField campoPesquisar;
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

        campoPesquisar.textProperty().addListener((observable, oldValue, newValue) -> pesquisarClienteCPF());
    }

    public void carregarTabelaClientes() {
        ClienteDAO clienteDAO = new ClienteDAO();

        List<Cliente> lista = listarClientes();
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
            popupStage.setOnHidden(event -> carregarTabelaClientes());
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
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditarCliente.fxml"));
                Stage popupStage = new Stage();
                popupStage.setScene(new Scene(loader.load()));
                popupStage.setTitle("Editar Cliente");

                EditarClienteController controller = loader.getController();
                controller.receberCliente(clienteSelecionado, this);
                popupStage.setOnHidden(event -> carregarTabelaClientes());
                popupStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Selecione um cliente para editar.");

            System.out.println("Selecione um cliente para editar.");

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecione um cliente para editar.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void excluirCliente() {
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();

        if (clienteSelecionado != null) {
            // Exclui do banco de dados
            boolean removido = ClienteDAO.excluirCliente(clienteSelecionado.getCpf());

            if (removido) {
                // Remove da lista da tabela
                listaClientes.remove(clienteSelecionado);
                System.out.println("Cliente removido do banco: " + clienteSelecionado.getNome());

                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("Sucesso");
                alerta.setHeaderText(null);
                alerta.setContentText("Cliente removido com sucesso!");
                alerta.showAndWait();
            } else {
                System.out.println("Erro ao excluir cliente do banco.");

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Erro");
                alerta.setHeaderText(null);
                alerta.setContentText("Erro ao excluir cliente do banco.");
                alerta.showAndWait();
            }
        } else {
            System.out.println("Selecione um cliente para excluir.");

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecione um cliente para excluir.");
            alerta.showAndWait();
        }
    }


    @FXML
    private void pesquisarClienteCPF() {
        String filtro = campoPesquisar.getText().trim();

        if (filtro.isEmpty()) {
            listarClientes(); // Recarrega todas as vendas caso o campo esteja vazio
            return;
        }

        List<Cliente> listaFiltrada = listarClientes()
                .stream()
                .filter(v -> v.getCpf().contains(filtro))
                .toList(); // Filtra vendas que contenham o CPF digitado

        tabelaClientes.getItems().setAll(listaFiltrada);
    }
}

