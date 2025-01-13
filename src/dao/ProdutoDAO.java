package dao;

import dao.conexao.ConexaoDB;
import model.entity.Produto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProdutoDAO {
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO PRODUTO (COD, NOME, PRECO, CATEGORIA, MARCA, DESCRICAO, DATA_ENTREGA, CNPJ_LOJA) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        String dataEntrega = produto.getDataEntrega(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataEntrega, formatter);
        dataEntrega = localDate.toString();

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getCategoria());
            ps.setString(5, produto.getMarca());
            ps.setString(6, produto.getDescricao());
            ps.setString(7, dataEntrega);
            ps.setString(8, produto.getCnpj_loja());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
