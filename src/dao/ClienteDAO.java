package dao;

import dao.conexao.ConexaoDB;
import model.entity.Cliente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    public void cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (LOGIN_USUARIO, STATUS) VALUES (?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getLogin_usuario());
            ps.setString(2, cliente.getStatus());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
