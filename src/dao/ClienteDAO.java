package dao;

import dao.conexao.ConexaoDB;
import model.entity.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static boolean cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (LOGIN_USUARIO, CPF, ESTADO, STATUS) VALUES (?,?,?,?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getLogin_usuario());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEstado());
            ps.setString(4, cliente.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET CPF = ?, ESTADO = ?, STATUS = ? WHERE LOGIN_USUARIO = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getEstado());
            ps.setString(3, cliente.getStatus());
            ps.setString(4, cliente.getLogin_usuario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean excluirCliente(String loginUsuario) {
        String sql = "DELETE FROM CLIENTE WHERE LOGIN_USUARIO = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, loginUsuario);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}