package controller;


import dao.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.entity.Usuario;

import java.util.Calendar;

public class CadastroController {

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoUsuario;
    @FXML
    private TextField campoCPF;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Button botaoCadastro;
    @FXML
    private ComboBox<String> comboDia;
    @FXML
    private ComboBox<String> comboMes;
    @FXML
    private ComboBox<String> comboAno;
    @FXML
    private ImageView imageLogo;

    private int flag = 0;
    private String mesAtual = null;
    private String anoAtual = null;

    @FXML
    public void initialize() {
        atualizarCalendario();
        Image image = new Image(getClass().getResourceAsStream("/icon/loja-online.png"));
        imageLogo.setImage(image);
    }

    private boolean validarCampos() {
        if (campoCPF.getText().isEmpty() || campoSenha.getText().isEmpty() ||
        campoNome.getText().isEmpty() || campoUsuario.getText().isEmpty() ||
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

    private boolean validarSenha() {
        String senha = campoSenha.getText();

        if (senha.length() < 8 || senha.length() > 15) {
            return false;
        } return true;
    }

    private boolean validarUsuario() {
        String username = campoUsuario.getText();
        if (username == null || username.isEmpty() || username.contains(" ") || username.contains("-")) {
            return false;
        } return true;
    }

    @FXML
    private void atualizarCalendario() {
        Calendar cal = Calendar.getInstance();
        int anoCorrente = cal.get(Calendar.YEAR);

        if (flag == 0) {
            for (int year = 1900; year <= anoCorrente; year++) {
                comboAno.getItems().add(String.valueOf(year));
            }

            for (int month = 1; month <= 12; month++) {
                comboMes.getItems().add(String.valueOf(month));
            }
            flag = 1;
        }

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

                comboDia.getItems().clear();
                for (int dia = 1; dia <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
                    comboDia.getItems().add(String.valueOf(dia));
                }
            }
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoUsuario.setText("");
        campoCPF.setText("");
        campoSenha.setText("");
        comboDia.getItems().clear();
        comboMes.getItems().clear();
        comboAno.getItems().clear();
        flag = 0;
    }

    private void enviandoValoresCadastro(Usuario usuario) {
        String cpf = campoCPF.getText();
        cpf = cpf.replaceAll("\\D", "");
        usuario.setCpf(cpf);
        usuario.setNome(campoNome.getText());
        usuario.setSenha(campoSenha.getText());
        String dia = comboDia.getSelectionModel().getSelectedItem().length() == 1 ?
                0+comboDia.getSelectionModel().getSelectedItem():
                comboDia.getSelectionModel().getSelectedItem();
        String mes = comboMes.getSelectionModel().getSelectedItem().length() == 1 ?
                0+comboMes.getSelectionModel().getSelectedItem() : comboMes.getSelectionModel().getSelectedItem();
        String ano = comboAno.getSelectionModel().getSelectedItem();
        usuario.setDataNascimento(dia+"/"+mes+"/"+ano);
        usuario.setLogin(campoUsuario.getText());
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
        else if (!validarSenha()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Senha Inválida", "Insira uma senha com 8 a 15 dígitos.");
        }
        else if (!validarUsuario()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Usuário Inválido", "Seu nome de usuário é inválido. Tente não usar espaços ou hífens.");
        }
        else {
            Usuario usuario = new Usuario();
            enviandoValoresCadastro(usuario);
            UsuarioDAO.cadastroInicial(usuario);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cadastro realizado com sucesso!");
            limparCampos();
        }
    }
}
