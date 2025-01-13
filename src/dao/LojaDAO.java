package dao;

import dao.conexao.ConexaoDB;
import model.entity.Loja;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LojaDAO {

    public void cadastrarLoja(Loja loja){
        String sql = "INSERT INTO LOJA (CNPJ, CATEGORIA, NOME, PAIS, ESTADO, CIDADE, BAIRRO, RUA, NUMERO) VALUES(?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, loja.getCnpj());
            ps.setString(2, loja.getCategoria());
            ps.setString(3, loja.getNome());
            ps.setString(4, loja.getPais());
            ps.setString(5, loja.getEstado());
            ps.setString(6, loja.getCidade());
            ps.setString(7, loja.getBairro());
            ps.setString(8, loja.getRua());
            ps.setString(9, loja.getNumero());

            ps.execute();
            ps.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
