package dao;

import dao.conexao.ConexaoDB;
import model.Administrador;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdministradorDAO {
    public void cadastrarAdministrador(Administrador admin) {
        String sql = "INSERT INTO ADMINISTRADOR (LOGIN_USUARIO, CARGO) VALUES (?,?)"; // aqui ele prepara para fazer o insert, colocando "?" como placeholder
        // que em breve sera trocado no try
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql); // aqui realiza a conexao e prepara para executar a consulta SQL
            ps.setString(1, admin.getLogin_usuario()); // aqui seta o primeiro parametro como login
            ps.setString(2, admin.getCargo()); // segundo parametro como cargo

            ps.execute(); // executa
            ps.close(); // encerra

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
