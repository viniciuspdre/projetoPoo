package dao;

import dao.conexao.ConexaoDB;
import model.entity.Fornecedor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FornecedorDAO {
    public void cadastrarFornecedor(Fornecedor fornecedor) {
        String sql = "INSERT INTO FORNECEDOR(LOGIN_USUARIO, CNPJ, DATA_FORNECIMENTO, NOME, PAIS, ESTADO, CIDADE, BAIRRO, RUA, CEP, NUMERO) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        String dataFornecimento = fornecedor.getData_fornecimento(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataFornecimento, formatter);
        dataFornecimento = localDate.toString();

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, fornecedor.getLogin_usuario());
            ps.setString(2, fornecedor.getCnpj());
            ps.setString(3, dataFornecimento);
            ps.setString(4, fornecedor.getNome());
            ps.setString(5, fornecedor.getPais());
            ps.setString(6, fornecedor.getEstado());
            ps.setString(7, fornecedor.getCidade());
            ps.setString(8, fornecedor.getBairro());
            ps.setString(9, fornecedor.getRua());
            ps.setString(10, fornecedor.getCep());
            ps.setString(11, fornecedor.getNumero());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
