package dao;

import dao.conexao.ConexaoDB;
import model.entity.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioDAO {
    public static void cadastroInicial(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (LOGIN, SENHA, NOME, CPF, DATA_NASCIMENTO) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;

        String dataNascimento = tratarDataParaBD(usuario);
        try {
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setString(4, usuario.getCpf());
            ps.setString(5, dataNascimento);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String tratarDataParaBD(Usuario usuario) {
        String dataNascimento = usuario.getDataNascimento(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataNascimento, formatter);
        dataNascimento = localDate.toString();
        return dataNascimento;
    }

    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIO (LOGIN, SENHA, NOME, IDADE, CPF, DATA_NASCIMENTO, PAIS, ESTADO, CIDADE, BAIRRO, RUA, NUMERO, CEP, CNPJ_LOJA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        String dataNascimento = tratarDataParaBD(usuario);

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setInt(4, usuario.getIdade());
            ps.setString(5, usuario.getCpf());
            ps.setString(6, dataNascimento);
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
    Connection conexao;

    public ResultSet autenticacaoUsuario(String login, String senha) {
        conexao = new ConexaoDB().getConexao();

        try{
            String sql = "SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?";
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, login);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            return rs;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
            return null;
        }
    }
}
