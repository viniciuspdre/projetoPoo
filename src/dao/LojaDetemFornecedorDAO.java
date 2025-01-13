package dao;

import dao.conexao.ConexaoDB;
import model.entity.LojaDetemFornecedor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LojaDetemFornecedorDAO {
    public void inserir(LojaDetemFornecedor loja) {
        String sql = "INSERT INTO LOJA_DETEM_FORNECEDOR (CNPJ_LOJA, CNPJ_FORNECEDOR) VALUES (?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, loja.getCnpj_loja());
            ps.setString(2, loja.getCnpj_fornecedor());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
