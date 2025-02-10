package dao;

import dao.conexao.ConexaoDB;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static boolean cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO CLIENTE (CPF, NOME, SEXO, ESTADO, DATA_NASCIMENTO, DATA_REGISTRO, STATUS_CLIENTE) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getSexo());
            ps.setString(4, cliente.getEstado());
            ps.setString(5, cliente.getData_nascimento());
            ps.setString(6, cliente.getData_registro());
            ps.setString(7, cliente.getStatus_cliente());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean editarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET CPF = ?,  SEXO = ?, ESTADO = ?, DATA_NASCIMENTO = ?, DATA_REGISTROS = ? , STATUS_CLIENTE = ?  , WHERE NOME = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getSexo());
            ps.setString(3, cliente.getEstado());
            ps.setString(4, cliente.getData_nascimento());
            ps.setString(5, cliente.getData_registro());
            ps.setString(6, cliente.getStatus_cliente());
            ps.setString(7, cliente.getNome());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean excluirCliente(String Cpf) {
        String sql = "DELETE FROM CLIENTE WHERE CPF = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Cpf);

            return ps.executeUpdate() > 0; // verifica se o comando SQL afetou pelo menos uma linha.
            // retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Cliente> listarClientes() {
        String sql = "SELECT * FROM CLIENTE";
        List<Cliente> listaClientes = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultado = ps.executeQuery()) {

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(resultado.getString("CPF"));
                cliente.setNome(resultado.getString("NOME"));
                cliente.setSexo(resultado.getString("SEXO"));
                cliente.setEstado(resultado.getString("ESTADO"));
                cliente.setData_nascimento(resultado.getString("DATA_NASCIMENTO"));
                cliente.setData_registro(resultado.getString("DATA_REGISTRO"));
                cliente.setStatus_cliente(resultado.getString("STATUS_CLIENTE"));
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    public Cliente buscarCliente(String nome) {
        String sql = "SELECT * FROM CLIENTE WHERE NOME = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);

            try (ResultSet resultado = ps.executeQuery()) {
                if (resultado.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCpf(resultado.getString("CPF"));
                    cliente.setNome(resultado.getString("NOME"));
                    cliente.setSexo(resultado.getString("SEXO"));
                    cliente.setEstado(resultado.getString("ESTADO"));
                    cliente.setData_nascimento(resultado.getString("DATA_NASCIMENTO"));
                    cliente.setData_registro(resultado.getString("DATA_REGISTRO"));
                    cliente.setStatus_cliente(resultado.getString("STATUS_CLIENTE"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}