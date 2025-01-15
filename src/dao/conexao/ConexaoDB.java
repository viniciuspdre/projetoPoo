package dao.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String url = "jdbc:mysql://localhost:3306/lojainformatica";
    private static final String user = "root";
    private static final String password = "MakerSpace";

    private static Connection conexao = null;

    public static Connection getConexao() {
        try{
            if(conexao == null){ // verifica se nao há a conexao, se nao houver estabelece
                conexao = DriverManager.getConnection(url, user, password);
            } // por fim ira retornar uma conexao
            return conexao; // coloquei sem o else e com somente esse retorno para simplificar o codigo
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}

