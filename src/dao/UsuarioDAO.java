package dao;

import dao.conexao.ConexaoDB;
import model.entity.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioDAO {
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (LOGIN, SENHA, NOME, IDADE, CPF, DATA_NASCIMENTO, PAIS, ESTADO, CIDADE, BAIRRO, RUA, NUMERO, CEP, CNPJ_LOJA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        String dataEntrada = usuario.getDataNascimento(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataEntrada, formatter);
        dataEntrada = localDate.toString();

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setInt(4, usuario.getIdade());
            ps.setString(5, usuario.getCpf());
            ps.setString(6, dataEntrada);
            ps.setString(7, usuario.getPais());
            ps.setString(8, usuario.getEstado());
            ps.setString(9, usuario.getCidade());
            ps.setString(10, usuario.getBairro());
            ps.setString(11, usuario.getRua());
            ps.setString(12, usuario.getNumero());
            ps.setString(13, usuario.getCep());
            ps.setString(14, usuario.getCnpj_loja());

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
