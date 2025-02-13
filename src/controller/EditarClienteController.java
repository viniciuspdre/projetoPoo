package controller;

import dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Cliente;
import model.entity.Estado;
import model.entity.Sexo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class EditarClienteController {
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoCPF;
    @FXML
    private Button btCancelar;
    @FXML
    private Button btConcluir;
    @FXML
    private ComboBox<String> comboDia;
    @FXML
    private ComboBox<String> comboMes;
    @FXML
    private ComboBox<String> comboAno;
    @FXML
    private ComboBox<Sexo> comboSexo;
    @FXML
    private ComboBox<Estado> comboEstado;
    @FXML
    private ToggleGroup grupoStatus;

    private Cliente clienteSelecionado; // Armazena o cliente que está sendo editado
    private GerenciamentoClientesController gerenciamentoClientesController;

    private int flag = 0;
    private String mesAtual = null;
    private String anoAtual = null;

    @FXML
    public void initialize() {
        atualizarCalendario();
        selecionarSexo();
        selecionarEstado();
        comboEstado.setItems(FXCollections.observableArrayList(Estado.values()));
        comboSexo.setItems(FXCollections.observableArrayList(Sexo.values()));
        campoCPF.setDisable(true);
    }

    private boolean validarCampos() {
        return !(campoCPF.getText().isBlank() || campoNome.getText().isBlank() ||
                comboSexo.getValue() == null || comboEstado.getValue() == null ||
                comboDia.getValue() == null || comboMes.getValue() == null || comboAno.getValue() == null);
    }

    @FXML
    private void atualizarCalendario() {
        Calendar cal = Calendar.getInstance();
        int anoCorrente = cal.getInstance().get(Calendar.YEAR);

        // Carrega os anos e meses apenas uma vez
        if (flag == 0) {
            for (int year = 1900; year <= anoCorrente; year++) {
                comboAno.getItems().add(String.valueOf(year));
            }

            for (int month = 1; month <= 12; month++) {
                comboMes.getItems().add(String.valueOf(month));
            }

            comboAno.getSelectionModel().select(String.valueOf(anoCorrente)); // Seleciona o ano atual
            comboMes.getSelectionModel().select(String.valueOf(cal.get(Calendar.MONTH) + 1)); // Seleciona o mês atual

            flag = 1;
        }

        // Atualiza os dias com base no mês e ano selecionados
        String anoSelecionado = comboAno.getSelectionModel().getSelectedItem();
        String mesSelecionado = comboMes.getSelectionModel().getSelectedItem();

        if (anoSelecionado != null && mesSelecionado != null) {
            if (!mesSelecionado.equals(mesAtual) || !anoSelecionado.equals(anoAtual)) {
                mesAtual = mesSelecionado;
                anoAtual = anoSelecionado;

                int mes = Integer.parseInt(mesSelecionado);
                int ano = Integer.parseInt(anoSelecionado);

                Calendar novocalendario = Calendar.getInstance();
                novocalendario.clear(); // Limpa os campos antigos para evitar interferências
                novocalendario.set(Calendar.YEAR, ano);
                novocalendario.set(Calendar.MONTH, mes - 1); // Meses começam de 0

                int maxDias = novocalendario.getActualMaximum(Calendar.DAY_OF_MONTH);

                cal.set(Calendar.YEAR, ano);
                cal.set(Calendar.MONTH, mes - 1); // Meses começam de 0

                comboDia.getItems().clear(); // Limpa os dias antigos

                for (int dia = 1; dia <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
                    comboDia.getItems().add(String.valueOf(dia)); // Adiciona os dias do mês selecionado
                }
            }
        }
    }

    @FXML
    private void selecionarSexo() {
        comboSexo.setItems(FXCollections.observableArrayList(Sexo.values()));
    }

    @FXML
    private void selecionarEstado() {
        comboEstado.setItems(FXCollections.observableArrayList(Estado.values()));
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    private void enviarValoresEdicao() {
        LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        clienteSelecionado.setNome(campoNome.getText());
        RadioButton radio = (RadioButton) grupoStatus.getSelectedToggle();
        clienteSelecionado.setStatus_cliente(radio.getText());
        String dia = comboDia.getSelectionModel().getSelectedItem().length() == 1 ?
                "0" + comboDia.getSelectionModel().getSelectedItem() : comboDia.getSelectionModel().getSelectedItem();
        String mes = comboMes.getSelectionModel().getSelectedItem().length() == 1 ?
                "0" + comboMes.getSelectionModel().getSelectedItem() : comboMes.getSelectionModel().getSelectedItem();
        String ano = comboAno.getSelectionModel().getSelectedItem();

        clienteSelecionado.setData_nascimento(dia + "/" + mes + "/" + ano);
        clienteSelecionado.setSexo(comboSexo.getSelectionModel().getSelectedItem().name());
        clienteSelecionado.setEstado(comboEstado.getSelectionModel().getSelectedItem().name());
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void receberCliente(Cliente clienteSelecionado, GerenciamentoClientesController gerenciamentoController) {
        this.clienteSelecionado = clienteSelecionado;
        this.gerenciamentoClientesController = gerenciamentoController;

        campoNome.setText(clienteSelecionado.getNome());
        campoCPF.setText(clienteSelecionado.getCpf()); // CPF é exibido e mantido
        comboSexo.setValue(Sexo.valueOf(clienteSelecionado.getSexo()));
        comboEstado.setValue(Estado.valueOf(clienteSelecionado.getEstado()));
    }

    @FXML
    private void salvarEdicao() {
        if (!validarCampos()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Obrigatórios", "Preencha todos os campos.");
            return;
        }

        enviarValoresEdicao();
        ClienteDAO.atualizarCliente(clienteSelecionado); // Chamada correta para atualizar cliente

        mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Edição realizada com sucesso!");

        if (gerenciamentoClientesController != null) {
            gerenciamentoClientesController.carregarTabelaClientes(); // Atualiza a tabela na tela principal
        }

        Stage stage = (Stage) btConcluir.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void concluir() {
        if (!validarCampos()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha todos os campos.");
            return;
        } else {
            Cliente cliente = new Cliente();
            enviarValoresEdicao();
            if (ClienteDAO.atualizarCliente(clienteSelecionado)) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Edição realizada com sucesso!");

                Stage stage = (Stage) btConcluir.getScene().getWindow();
                stage.close();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao editar Cliente.");
            }
        }
    }
}
