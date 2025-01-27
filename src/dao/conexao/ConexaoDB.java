package dao.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String url = "jdbc:mysql://localhost:3309/lojainformatica";
    private static final String user = "root";
    private static final String password = "MakerSpace";

    private static Connection conexao = null;

    // Metodo para obter a conexão
    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) { // Verifica se a conexão está fechada
                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("Conexão com o banco de dados estabelecida!");
            }
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados!");
            return null;
        }
    }

    // Metodo para fechar a conexão (caso necessário)
    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


