package controller;

import view.LoginCadastro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;

public class CadastroController {
    private LoginCadastro loginCadastro;

    public CadastroController(LoginCadastro loginCadastro) {
        this.loginCadastro = loginCadastro;

        loginCadastro.getJcMonthCadastro().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCalendario();
            }
        });

        loginCadastro.getJbCadastro().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarUsuario();
            }
        });
    }

    private boolean checarCampos() {
        if (loginCadastro.getJfCPFCadastro().getText().trim().isEmpty() || loginCadastro.getJfNameCadastro().getText().trim().isEmpty() || loginCadastro.getJfPasswordCadastro().getText().trim().isEmpty() || loginCadastro.getJfUserNameCadastro().getText().trim().isEmpty() || loginCadastro.getJcDayCadastro().getSelectedItem() == null || loginCadastro.getJcMonthCadastro().getSelectedItem() == null || loginCadastro.getJcYearCadastro().getSelectedItem() == null) {
            return false;
        }
        return true;
    }

    private boolean checarCPF() {
        String cpf = loginCadastro.getJfCPFCadastro().getText();
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < cpf.length() - 2; i++) {
            soma += (cpf.charAt(i) - '0') * (10-i);
        }
        int resto = soma % 11;
        int primeiroDigito = (resto < 2)  ? 0 : 11 - resto;

        if (primeiroDigito != cpf.charAt(9)) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < cpf.length() - 1; i++) {
            soma += (cpf.charAt(i) - '0') * (11-i);
        }
        resto = soma % 11;
        int segundoDigito = (resto < 2)  ? 0 : 11 - resto;

        return segundoDigito == cpf.charAt(10);
    }

    private boolean checarSenha() {
        String senha = Arrays.toString(loginCadastro.getJfPasswordCadastro().getPassword());

        if (senha.length() < 8) {
            return false;
        } return true;
    }

    private void atualizarCalendario() {
        Calendar cal = Calendar.getInstance();
        for (int year = 1900; year < Calendar.YEAR; year++) {
            loginCadastro.getJcYearCadastro().addItem(year);
        }

        if (loginCadastro.getJcMonthCadastro().getSelectedItem() != null) {
            int mes = (int) loginCadastro.getJcMonthCadastro().getSelectedItem();
            int ano = (int) loginCadastro.getJcYearCadastro().getSelectedItem();


            cal.set(Calendar.YEAR, ano);
            cal.set(Calendar.MONTH, mes - 1);

            loginCadastro.getJcDayCadastro().removeAllItems();
            for (int dia = 1; dia < cal.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
                loginCadastro.getJcDayCadastro().addItem(dia);
            }

        }
    }

    private void cadastrarUsuario() {
        if (!checarCampos()) {
            JOptionPane.showMessageDialog(loginCadastro, "Preencha todos os campos.");
        }
        else if (!checarCPF()) {
            JOptionPane.showMessageDialog(loginCadastro, "Insira um CPF válido.");
        }
        else if (!checarSenha()) {
            JOptionPane.showMessageDialog(loginCadastro, "Insira uma senha com 8 digitos ou mais.");
        }
        else {
            JOptionPane.showMessageDialog(loginCadastro, "Usuário cadastrado com sucesso!");
        }
    }
}
