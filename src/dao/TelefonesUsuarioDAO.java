package dao;

import dao.conexao.ConexaoDB;
import model.entity.TelefonesUsuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelefonesUsuarioDAO {
    public void cadastrarTelefonesUsuario(TelefonesUsuario telefonesUsuario) {
        String sql = "INSERT INTO TELEFONES_USUARIO(LOGIN_USUARIO, TELEFONES) VALUES(?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, telefonesUsuario.getLogin_usuario());
            ps.setString(2, telefonesUsuario.getTelefone());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
