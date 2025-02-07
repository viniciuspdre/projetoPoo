package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import model.entity.Cliente;
import model.entity.Vendas;

public class GerenciamentoClientesController {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<Cliente, String> colunaLogin;

    @FXML
    private TableColumn<Vendas, String> colunaCpf;

    @FXML
    private TableColumn<Vendas, String> colunaEstado;

    @FXML
    private TableColumn<Vendas, String> colunaStatus;



}

