package dao;

import dao.conexao.ConexaoDB;
import model.entity.Dependente;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DependenteDAO {
    public void cadastrarDependente(Dependente dependente) {
        String sql = "INSERT INTO DEPENDENTE (LOGIN_USUARIO, NOME, SEXO, DATA_NASCIMENTO, GRAU_PARENTESCO) VALUES (?,?,?,?,?)";
        PreparedStatement ps = null;

        String dataNascimento = dependente.getData_nascimento(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataNascimento, formatter);
        dataNascimento = localDate.toString();

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, dependente.getLogin_usuario());
            ps.setString(2, dependente.getNome());
            ps.setString(3, dependente.getSexo());
            ps.setString(4, dataNascimento);
            ps.setString(5, dependente.getParentesco());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
