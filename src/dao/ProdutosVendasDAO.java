package dao;

import dao.conexao.ConexaoDB;
import model.ProdutosVendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosVendasDAO {

    public static void cadastrarProdutoVendas(ProdutosVendas produtoVendas) {
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

    public static void deletarProdutoVendas(int idVenda) {
        String sql = "DELETE FROM PRODUTO_VENDAS WHERE ID_VENDA = ?";

        try (Connection con = ConexaoDB.getConexao();
        PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idVenda);
            int linhas  = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Produto Vendas deletado com sucesso!");
            } else {  System.out.println("Produto Vendas deletado com sucesso!"); }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<ProdutosVendas> listarProdutoVendasId(int idVenda) {
        String sql = "SELECT * FROM PRODUTO_VENDAS WHERE ID_VENDA = ?";
        List<ProdutosVendas> produtoVendas = new ArrayList<>();

        try (Connection conexao = ConexaoDB.getConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idVenda); // Define o parâmetro antes de executar

            try (ResultSet rs = stmt.executeQuery()) { // Executa a consulta após definir os parâmetros
                while (rs.next()) {
                    ProdutosVendas produtoVenda = new ProdutosVendas(
                            rs.getInt("id_venda"),
                            rs.getString("cod_produto"),
                            rs.getInt("quantidade"),
                            rs.getFloat("preco_unitario")
                    );
                    produtoVendas.add(produtoVenda);
                }
            }
            return produtoVendas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
