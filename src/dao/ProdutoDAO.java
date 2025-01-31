package dao;

import dao.conexao.ConexaoDB;
import model.entity.Produto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    public void cadastrarProduto(Produto produto) {
        String sql = "INSERT INTO PRODUTO (COD, NOME, PRECO, ESTOQUE,ESTOQUE_MINIMO, VENDIDOS, CATEGORIA, MARCA, DESCRICAO, FOTO_PRODUTO, CNPJ_LOJA) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getEstoque());
            ps.setInt(5, produto.getEstoque_minimo());
            ps.setInt(6, produto.getVendidos());
            ps.setString(7, produto.getCategoria());
            ps.setString(8, produto.getMarca());
            ps.setString(9, produto.getDescricao());
            ps.setBytes(10, produto.getFoto());
            ps.setString(11, produto.getCnpj_loja());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET  NOME = ?, PRECO = ?, CATEGORIA = ?, MARCA = ?, DESCRICAO = ?, FOTO_PRODUTO = ?, ESTOQUE = ?, ESTOQUE_MINIMO = ?, VENDIDOS = ?, CNPJ_LOJA = ? WHERE COD = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if(produto.getNome() != null && !produto.getNome().isEmpty()){
                ps.setString(1, produto.getNome());
            } else{
                ps.setNull(1, java.sql.Types.VARCHAR);
            }

            if (produto.getPreco() != 0.0) {
                ps.setDouble(2, produto.getPreco());
            } else {
                ps.setNull(2, java.sql.Types.DOUBLE);
            }
            if(produto.getCategoria() != null && !produto.getCategoria().isEmpty()){
                ps.setString(3, produto.getCategoria());
            } else{
                ps.setNull(3, java.sql.Types.VARCHAR);
            }

            if(produto.getMarca() != null && !produto.getMarca().isEmpty()){
                ps.setString(4, produto.getMarca());
            } else{
                ps.setNull(4, java.sql.Types.VARCHAR);
            }

            if(produto.getDescricao() != null && !produto.getDescricao().isEmpty()){
                ps.setString(5, produto.getDescricao());
            } else{
                ps.setNull(5, java.sql.Types.VARCHAR);
            }

            if (produto.getFoto().length != 0) {
                ps.setBytes(6, produto.getFoto());
            } else {
                ps.setNull(6, java.sql.Types.BINARY);
            }

            if (produto.getEstoque() != 0) {
                ps.setInt(7, produto.getEstoque());
            } else {
                ps.setNull(7, java.sql.Types.INTEGER);
            }

            if (produto.getEstoque_minimo() != 0) {
                ps.setInt(8, produto.getEstoque_minimo());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }

            if (produto.getVendidos() != 0) {
                ps.setInt(9, produto.getVendidos());
            } else {
                ps.setNull(9, java.sql.Types.INTEGER);
            }

            if (produto.getCnpj_loja() != null && !produto.getCnpj_loja().isEmpty()) {
                ps.setString(10, produto.getCnpj_loja());
            } else {
                ps.setString(10, produto.getCnpj_loja());
            }

            if (produto.getCodigo() != null && !produto.getCodigo().isEmpty()) {
                ps.setString(11, produto.getCodigo());
            } else {
                ps.setNull(11, java.sql.Types.VARCHAR);
            }

            int rowsAffected = ps.executeUpdate();

            // Retorna verdadeiro se o produto foi atualizado com sucesso
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    public static List<Produto> listarProdutos() {
        String sql = "SELECT COD, NOME, PRECO, ESTOQUE, ESTOQUE_MINIMO, VENDIDOS, CATEGORIA, MARCA, DESCRICAO, FOTO_PRODUTO, CNPJ_LOJA FROM PRODUTO ORDER BY NOME ASC";
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
                        rs.getInt("ESTOQUE_MINIMO"),
                        rs.getInt("VENDIDOS"),
                        rs.getString("CATEGORIA"),
                        rs.getString("MARCA"),
                        rs.getString("DESCRICAO"),
                        rs.getBytes("FOTO_PRODUTO"),
                        rs.getString("CNPJ_LOJA")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Substitua por um logger para melhor controle de erros
            throw new RuntimeException("Erro ao listar os produtos cadastrados.", e);
        }
        return produtos;
    }

    public static List<Produto> listarAlertaEstoque() {
        String sql = "SELECT COD, NOME, PRECO, ESTOQUE, ESTOQUE_MINIMO, VENDIDOS, CATEGORIA, MARCA, DESCRICAO, FOTO_PRODUTO, CNPJ_LOJA FROM PRODUTO WHERE ESTOQUE <= ESTOQUE_MINIMO";
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
                        rs.getInt("ESTOQUE_MINIMO"),
                        rs.getInt("VENDIDOS"),
                        rs.getString("CATEGORIA"),
                        rs.getString("MARCA"),
                        rs.getString("DESCRICAO"),
                        rs.getBytes("FOTO_PRODUTO"),
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

    public String obterUltimoCodigoProduto() {
        String sql = "SELECT COD FROM PRODUTO ORDER BY COD DESC LIMIT 1;"; // Altere "produtos" e "id" conforme sua tabela
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getString("COD");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter o último código: " + e.getMessage());
        }
        return null;
    }

}