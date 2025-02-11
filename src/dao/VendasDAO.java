package dao;

import dao.conexao.ConexaoDB;
import model.Vendas;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VendasDAO {

    public static void CadastrarVenda(Vendas venda){
        String sql = "INSERT INTO VENDAS (CNPJ_LOJA, DATA_VENDA, HORARIO, VALOR_TOTAL, FORMA_PAGAMENTO, DATA_VENCIMENTO, ESTADO_VENDA, CPF_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;

        String dataVenda = tratarDataParaBD(venda, 1);
        String dataVencimento = tratarDataParaBD(venda, 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = venda.getHorario().format(formatter);

        try {
            stmt = ConexaoDB.getConexao().prepareStatement(sql);
            stmt.setString(1, venda.getCnpj());
            stmt.setString(2, dataVenda);
            stmt.setString(3, hora);
            stmt.setFloat(4, venda.getValor());
            stmt.setString(5, String.valueOf(venda.getForma_pagamento()));
            stmt.setString(6, dataVencimento);
            stmt.setString(7, venda.getEstado_venda());
            stmt.setString(8, venda.getCPFCliente());

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String tratarDataParaBD(Vendas vendas, int escolha) {
        String dataStr = (escolha == 1) ? vendas.getData_venda() : vendas.getData_vencimento();

        DateTimeFormatter formatterEntrada;

        // Detecta se a data já está no formato correto (yyyy-MM-dd)
        if (dataStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return dataStr; // Já está no formato correto, não precisa converter
        } else {
            formatterEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        }

        LocalDate localDate = LocalDate.parse(dataStr, formatterEntrada);
        return localDate.toString(); // Retorna no formato yyyy-MM-dd
    }
}
