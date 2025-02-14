package dao;

import dao.conexao.ConexaoDB;
import model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static List<String> buscarCPFCliente(){
        String sql = "SELECT CPF FROM cliente WHERE STATUS_CLIENTE = 'ATIVO'";
        List<String> listaCPF = new ArrayList<>();

        try (Connection conexao = ConexaoDB.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { // Adiciona ResultSet aqui

            while (rs.next()) {
                listaCPF.add(rs.getString("CPF"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar CPFs de clientes ativos", e);
        }

        return listaCPF;
    }

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

    public static boolean excluirCliente(String Cpf) throws SQLException{
        String sql = "DELETE FROM CLIENTE WHERE CPF = ?";
        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Cpf);

            return ps.executeUpdate() > 0; // verifica se o comando SQL afetou pelo menos uma linha.
            // retorna true
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        }
    }


    public static boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE CLIENTE SET NOME = ?, SEXO = ?, ESTADO = ?, DATA_NASCIMENTO = ?, DATA_REGISTRO = ?, STATUS_CLIENTE = ? WHERE CPF = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            System.out.println("Tentando atualizar cliente: " + cliente.getCpf());

            String dataNascimento = tratarDataParaBD(cliente);

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSexo());
            ps.setString(3, cliente.getEstado());
            ps.setString(4, dataNascimento);
            ps.setString(5, cliente.getData_registro());
            ps.setString(6, cliente.getStatus_cliente());
            ps.setString(7, cliente.getCpf()); // CPF é usado para localizar o cliente

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Nenhum cliente foi atualizado. Verifique se o CPF existe.");
                return false;
            } else {
                System.out.println("Cliente atualizado com sucesso!");
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String tratarDataParaBD(Cliente cliente) {
        String dataNascimento = cliente.getData_nascimento(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataNascimento, formatter);
        dataNascimento = localDate.toString();
        return dataNascimento;
    }

    public static boolean cpfJaCadastrado(String cpf) {
        String sql = "SELECT COUNT(*) FROM CLIENTE WHERE CPF = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true se existir um cliente com esse CPF
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Cliente> listarClientes() {
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