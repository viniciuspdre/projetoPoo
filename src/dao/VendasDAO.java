package dao;

import dao.conexao.ConexaoDB;
import model.Vendas;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VendasDAO {

    public static void CadastrarVenda(Vendas venda){
        String sql = "INSERT INTO VENDAS (CNPJ_LOJA, LOGIN_USUARIO, DATA_VENDA, HORARIO, VALOR_TOTAL, FORMA_PAGAMENTO, DATA_VENCIMENTO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        String dataVenda = tratarDataParaBD(venda, 1);
        String dataVencimento = tratarDataParaBD(venda, 2);

        try {
            stmt = ConexaoDB.getConexao().prepareStatement(sql);
            stmt.setString(1, venda.getCnpj());
            stmt.setString(2, venda.getLogin_usuario());
            stmt.setString(3, dataVenda);
            stmt.setString(4, String.valueOf(venda.getHorario()));
            stmt.setFloat(5, venda.getValor());
            stmt.setString(6, String.valueOf(venda.getForma_pagamento()));
            stmt.setString(7, dataVencimento);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String tratarDataParaBD(Vendas vendas, int escolha) {
        if (escolha == 1) {
            String dataVenda = vendas.getData_venda(); // Data de entrada no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
            // Converter a data para o formato SQL
            LocalDate localDate = LocalDate.parse(dataVenda, formatter);
            dataVenda = localDate.toString();
            return dataVenda;
        } else {
            String dataVencimento = vendas.getData_vencimento(); // Data de entrada no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
            // Converter a data para o formato SQL
            LocalDate localDate = LocalDate.parse(dataVencimento, formatter);
            dataVencimento = localDate.toString();
            return dataVencimento;
        }
    }
}
