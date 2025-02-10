package dao;

import dao.conexao.ConexaoDB;
import model.DadosBancariosUsuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DadosBancariosUsuarioDAO {
    public void cadastrarDadosBancariosUsuario(DadosBancariosUsuario dadosBancarios) {
        String sql = "INSERT INTO DADOS_BANCARIOS_USUARIO (LOGIN_USUARIO, NOME_TITULAR, BANCO, AGENCIA, NUMEROCONTA, TIPO_CONTA, CHAVE_PIX) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, dadosBancarios.getLogin_usuario());
            ps.setString(2, dadosBancarios.getNome_titular());
            ps.setString(3, dadosBancarios.getBanco());
            ps.setString(4, dadosBancarios.getAgencia());
            ps.setString(5, dadosBancarios.getNumeroConta());
            ps.setString(6, dadosBancarios.getTipo_conta());
            ps.setString(7, dadosBancarios.getChave_pix());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
