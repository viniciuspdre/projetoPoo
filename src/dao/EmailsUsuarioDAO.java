package dao;

import dao.conexao.ConexaoDB;
import model.entity.EmailsUsuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmailsUsuarioDAO {
    public void cadastrarEmailUsuario(EmailsUsuario emailsUsuario) {
        String sql = "INSERT INTO emails_usuario (LOGIN_USUARIO, EMAILS) VALUES (?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, emailsUsuario.getLogin_usuario());
            ps.setString(2, emailsUsuario.getEmail());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
