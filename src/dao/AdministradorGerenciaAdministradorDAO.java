package dao;

import dao.conexao.ConexaoDB;
import model.entity.AdministradorGerenciaAdministrador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorGerenciaAdministradorDAO {
    public void cadastrarAdministrador(AdministradorGerenciaAdministrador administrador) {
        String sql = "INSERT INTO ADMINISTRADOR_GERENCIA_ADMINISTRADOR (LOGIN_USUARIO_ADMINISTRADOR_GERENCIA, LOGIN_USUARIO_ADMINISTRADOR_GERENCIADO) VALUES(?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, administrador.getLoginAdministradorGerencia());
            ps.setString(2, administrador.getLoginAdministradorGerenciado());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
