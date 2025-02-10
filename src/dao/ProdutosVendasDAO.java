package dao;

import dao.conexao.ConexaoDB;
import model.ProdutoVendas;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutosVendasDAO {

    public static void cadastrarProdutoVendas(ProdutoVendas produtoVendas) {
        String sql = "INSERT INTO PRODUTO_VENDAS (ID_VENDA, COD_PRODUTO, QUANTIDADE, PRECO_UNITARIO) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = ConexaoDB.getConexao().prepareStatement(sql);
            stmt.setInt(1, produtoVendas.getId_venda());
            stmt.setString(2, produtoVendas.getCod_produto());
            stmt.setInt(3, produtoVendas.getQuantidade());
            stmt.setFloat(4, produtoVendas.getPreco_produto());

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
