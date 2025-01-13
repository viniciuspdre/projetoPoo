package dao;

import dao.conexao.ConexaoDB;
import model.entity.Administrador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDAO {
    public void cadastrarAdministrador(Administrador admin) {
        String sql = "INSERT INTO ADMINISTRADOR (login_usuario, cargo) VALUES (?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, admin.getLogin_usuario());
            ps.setString(2, admin.getCargo());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
