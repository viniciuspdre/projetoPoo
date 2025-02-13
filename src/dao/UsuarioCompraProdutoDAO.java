package dao;

import dao.conexao.ConexaoDB;
import model.UsuarioCompraProduto;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UsuarioCompraProdutoDAO {
    public void inserir(UsuarioCompraProduto usuarioCompraProduto) {
        String sql = "INSERT INTO USUARIO_COMPRA_PRODUTO (LOGIN_USUARIO, COD_PRODUTO, DATA_COMPRA, FORMA_PAGAMENTO) VALUES(?,?,?,?)";
        PreparedStatement ps = null;

        String dataCompra = usuarioCompraProduto.getDataCompra(); // Data de entrada no formato dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatador para interpretar a entrada
        // Converter a data para o formato SQL
        LocalDate localDate = LocalDate.parse(dataCompra, formatter);
        dataCompra = localDate.toString();

        try{
            ps = ConexaoDB.getConexao().prepareStatement(sql);
            ps.setString(1, usuarioCompraProduto.getLogin_usuario());
            ps.setString(2, usuarioCompraProduto.getCod_produto());
            ps.setString(3, dataCompra);
            ps.setString(4, usuarioCompraProduto.getFormaPagamento());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
