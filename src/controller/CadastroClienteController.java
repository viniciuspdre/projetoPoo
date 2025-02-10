package controller;


import dao.ClienteDAO;
import dao.UsuarioDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.entity.Cliente;
import model.entity.Estado;
import model.entity.Sexo;
import model.entity.Usuario;

import java.util.Calendar;
import java.util.List;

public class CadastroClienteController {

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
    }

    private boolean validarCampos() {
        if (campoCPF.getText().isBlank() || campoNome.getText().isBlank() || comboSexo.getValue() == null  ||
                comboEstado.getValue() == null  ||
                comboDia.getValue() == null || comboMes.getValue() == null || comboAno.getValue() == null) {
                return false;
            }   return true;
        }

    private boolean validarCPF() {
        String cpf = campoCPF.getText();
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < cpf.length() - 2; i++) {
            soma += ((int)cpf.charAt(i) - '0') * (10-i);
        }
        int resto = soma % 11;
        int primeiroDigito = (resto < 2)  ? 0 : 11 - resto;

        if (primeiroDigito != cpf.charAt(9) - '0') {
            return false;
        }

        soma = 0;
        for (int i = 0; i < cpf.length() - 1; i++) {
            soma += (cpf.charAt(i) - '0') * (11-i);
        }
        resto = soma % 11;
        int segundoDigito = (resto < 2)  ? 0 : 11 - resto;

        return segundoDigito == cpf.charAt(10) - '0';
    }

    @FXML
    private void atualizarCalendario() {
        Calendar cal = Calendar.getInstance();
        int anoCorrente = cal.get(Calendar.YEAR);

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
            limparCampos();

            Stage stage = (Stage) btCancelar.getScene().getWindow();
            stage.close();
    }

    @FXML
    public void atualizarCliente() {
        //tabelaClientes.setItems(FXCollections.observableArrayList(listaClientes));
    }

    @FXML
    private void concluir() {
        String nome = campoNome.getText();
        String cpf = campoCPF.getText();
        Estado estadoSelecionado = comboEstado.getValue();
        Sexo sexoSelecionado = comboSexo.getValue();

        if (!validarCampos()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Obrigatórios", "Preencha todos os campos.");
        } else if (!validarCPF()) {
            mostrarAlerta(Alert.AlertType.ERROR, "CPF Inválido", "Insira um CPF válido.");
        } else {
            // Criando o cliente com os dados preenchidos
            Cliente cliente = new Cliente();
            enviandoValoresCadastro(cliente);

            // Cadastra o cliente (no banco de dados ou na lista de clientes)
            ClienteDAO.cadastrarCliente(cliente); // Ou qualquer método para adicionar à tabela.

            GerenciamentoClientesController gerenciamentoClientesController = new GerenciamentoClientesController = obterGerenciamentoController();
                gerenciamentoClientesController.carregarTabelaClientes();

            // Mostra um alerta informando que o cadastro foi realizado com sucesso
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro realizado com sucesso!");


            limparCampos();
            Stage stage = (Stage) btConcluir.getScene().getWindow();
            stage.close();
        }
    }


    private void limparCampos() {
        campoNome.setText("");
        campoCPF.setText("");
        comboDia.getItems().clear();
        comboMes.getItems().clear();
        comboAno.getItems().clear();
        comboSexo.getItems().clear();
        comboEstado.getItems().clear();
        flag = 0;
    }

    private void enviandoValoresCadastro(Cliente cliente) {
        String cpf = campoCPF.getText();
        cpf = cpf.replaceAll("\\D", "");
        cliente.setCpf(cpf);
        cliente.setNome(campoNome.getText());
        String dia = comboDia.getSelectionModel().getSelectedItem().length() == 1 ?
                0+comboDia.getSelectionModel().getSelectedItem():
                comboDia.getSelectionModel().getSelectedItem();
        String mes = comboMes.getSelectionModel().getSelectedItem().length() == 1 ?
                0+comboMes.getSelectionModel().getSelectedItem() : comboMes.getSelectionModel().getSelectedItem();
        String ano = comboAno.getSelectionModel().getSelectedItem();
        cliente.setData_nascimento(dia+"/"+mes+"/"+ano);
        cliente.setSexo(comboSexo.getSelectionModel().getSelectedItem().name());
        cliente.setEstado(comboEstado.getSelectionModel().getSelectedItem().name());
        cliente.setNome(campoNome.getText());
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void cadastrarUsuario() {
        if (!validarCampos()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Obrigatórios", "Preencha todos os campos.");
        }
        else if (!validarCPF()) {
            mostrarAlerta(Alert.AlertType.ERROR, "CPF Inválido", "Insira um CPF válido.");
        }
        else {
            Cliente cliente = new Cliente();
            enviandoValoresCadastro(cliente);
            ClienteDAO.cadastrarCliente(cliente);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro realizado com sucesso!");
            limparCampos();
        }
    }
}

