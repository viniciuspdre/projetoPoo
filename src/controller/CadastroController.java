package controller;

import view.LoginCadastro;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Calendar;

public class CadastroController {
    private LoginCadastro loginCadastro;
    private int flag = 0;

    public CadastroController(LoginCadastro loginCadastro) {
        this.loginCadastro = loginCadastro;

        loginCadastro.getJcMonthCadastro().addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                atualizarCalendario();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                System.out.print("Menu fechado");
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                System.out.print("Menu cancelado");
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
        String senha = String.valueOf(loginCadastro.getJfPasswordCadastro().getPassword());

        if (senha.length() < 8) {
            return false;
        } return true;
    }

    private void atualizarCalendario() {
        Calendar cal = Calendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);

        if (flag == 0) {
            for (int year = 1900; year < anoAtual; year++) {
                loginCadastro.getJcYearCadastro().addItem(String.valueOf(year));
            }

            for (int month = 1; month <= 12; month++) {
                loginCadastro.getJcMonthCadastro().addItem(String.valueOf(month));
            }
            flag = 1;
        }

        if (loginCadastro.getJcMonthCadastro().getSelectedItem() != null && loginCadastro.getJcYearCadastro().getSelectedItem() != null) {
            int mes = Integer.parseInt((String.valueOf(loginCadastro.getJcMonthCadastro().getSelectedItem())));
            int ano = Integer.parseInt(String.valueOf(loginCadastro.getJcYearCadastro().getSelectedItem()));


            cal.set(Calendar.YEAR, ano);
            cal.set(Calendar.MONTH, mes - 1);

            loginCadastro.getJcDayCadastro().removeAllItems();
            for (int dia = 1; dia <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); dia++) {
                loginCadastro.getJcDayCadastro().addItem(String.valueOf(dia));
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
