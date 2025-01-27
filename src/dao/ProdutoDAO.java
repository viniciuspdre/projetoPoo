package dao;

import dao.conexao.ConexaoDB;
import model.entity.Produto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO PRODUTO (COD, NOME, PRECO, ESTOQUE, VENDIDOS, CATEGORIA, MARCA, DESCRICAO, CNPJ_LOJA) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getEstoque());
            ps.setInt(5, produto.getVendidos());
            ps.setString(6, produto.getCategoria());
            ps.setString(7, produto.getMarca());
            ps.setString(8, produto.getDescricao());
            ps.setString(9, produto.getCnpj_loja());

            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Produto> listarProdutos() {
        String sql = "SELECT COD, NOME, PRECO, ESTOQUE, VENDIDOS, CATEGORIA, MARCA, DESCRICAO, CNPJ_LOJA FROM PRODUTO";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conexao = ConexaoDB.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getString("COD"),
                        rs.getString("NOME"),
                        rs.getDouble("PRECO"),
                        rs.getInt("ESTOQUE"),
                        rs.getInt("VENDIDOS"),
                        rs.getString("CATEGORIA"),
                        rs.getString("MARCA"),
                        rs.getString("DESCRICAO"),
                        rs.getString("CNPJ_LOJA")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Substitua por um logger para melhor controle de erros
            throw new RuntimeException("Erro ao listar os produtos cadastrados.", e);
        }
        return produtos;
    }

    public void excluirProduto(String codigo) {
        String sqlProduto = "DELETE FROM PRODUTO WHERE COD = ?";
        String sqlUsuarioCompraProduto = "DELETE FROM usuario_compra_produto WHERE COD_PRODUTO = ?";

        try (PreparedStatement psProduto = ConexaoDB.getConexao().prepareStatement(sqlProduto);
             PreparedStatement psUsuarioCompraProduto = ConexaoDB.getConexao().prepareStatement(sqlUsuarioCompraProduto)) {

            // Deletar da tabela usuario_compra_produto
            psUsuarioCompraProduto.setString(1, codigo);
            psUsuarioCompraProduto.executeUpdate();

            // Deletar da tabela PRODUTO
            psProduto.setString(1, codigo);
            psProduto.executeUpdate();

            System.out.println("Produto excluído das tabelas usuario_compra_produto e PRODUTO com sucesso!");

        } catch (SQLException e) {
            // Log de erro detalhado
            System.err.println("Erro ao excluir produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
