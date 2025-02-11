package dao;

import dao.conexao.ConexaoDB;
import model.Vendas;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendasDAO {

    public static int CadastrarVenda(Vendas venda){
        String sql = "INSERT INTO VENDAS (CNPJ_LOJA, DATA_VENDA, HORARIO, VALOR_TOTAL, FORMA_PAGAMENTO, DATA_VENCIMENTO, ESTADO_VENDA, CPF_CLIENTE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String dataVenda = tratarDataParaBD(venda, 1);
        String dataVencimento = tratarDataParaBD(venda, 2);
        String hora = venda.getHorario();

        try {
            stmt = ConexaoDB.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, venda.getCnpj());
            stmt.setString(2, dataVenda);
            stmt.setString(3, hora);
            stmt.setFloat(4, venda.getValor());
            stmt.setString(5, String.valueOf(venda.getForma_pagamento()));
            stmt.setString(6, dataVencimento);
            stmt.setString(7, venda.getEstado_venda());
            stmt.setString(8, venda.getCPFCliente());

            stmt.executeUpdate();

            rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1); // Retorna o ID gerado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) rs.close();  // Fecha o ResultSet, se não for nulo
                if (stmt != null) stmt.close();  // Fecha o PreparedStatement, se não for nulo
            } catch (SQLException e) {
                e.printStackTrace();  // Caso ocorra um erro ao tentar fechar, ele será impresso no console
            }
        } return -1;
    }

    public static List<Vendas> listarVendas(){
        String sql = "SELECT * FROM VENDAS";
        List<Vendas> vendas = new ArrayList<>();

        try (Connection conexao = ConexaoDB.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vendas.add(new Vendas(
                        rs.getString("CNPJ_LOJA"),
                        rs.getString("DATA_VENDA"),
                        rs.getString("HORARIO"),
                        rs.getFloat("VALOR_TOTAL"),
                        rs.getString("FORMA_PAGAMENTO"),
                        rs.getString("DATA_VENCIMENTO"),
                        rs.getString("ESTADO_VENDA"),
                        rs.getString("CPF_CLIENTE"),
                        rs.getInt("ID_VENDA")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } return vendas;
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
