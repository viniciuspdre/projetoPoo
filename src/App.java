import dao.LojaDAO;
import dao.ProdutoDAO;
import dao.UsuarioDAO;
import model.entity.Loja;
import model.entity.Produto;
import model.entity.Usuario;

public class App {
    public static void main(String[] args) {
        /*Loja heliot = new Loja();
        heliot.setCnpj("23.456.789/0001-95");
        heliot.setNome("HeliotTech");
        heliot.setCategoria("Informática");
        heliot.setPais("BR");
        heliot.setEstado("PE");
        heliot.setCidade("Belo Jardim");
        heliot.setBairro("São Pedro");
        heliot.setRua("Virando a esquina");
        heliot.setNumero("12");
        heliot.setCep("12345-678");
        heliot.setTelefone("(81)91234-5678");
        heliot.setEmail("heliottech@gmail.com");

        new LojaDAO().cadastrarLoja(heliot);

        Usuario usuario = new Usuario();
        usuario.setNome("Administrador");
        usuario.setLogin("admin");
        usuario.setSenha("admin");
        usuario.setCpf("123.456.789-09");
        usuario.setDataNascimento("13/01/2025");
        usuario.setPais("BR");
        usuario.setEstado("PE");
        usuario.setCidade("Belo Jardim");
        usuario.setBairro("São Pedro");
        usuario.setRua("A outra Esquina");
        usuario.setNumero("230");
        usuario.setCep("98765-432");
        usuario.setIdade(40);
        usuario.setCnpj_loja("23.456.789/0001-95");

        new UsuarioDAO().cadastrarUsuario(usuario);*/

        Produto produto = new Produto();
        produto.setCodigo("PRD-2025-00123");
        produto.setNome("Notebook Gamer G15");
        produto.setDescricao("Processador: Intel I5 13º Geração");
        produto.setPreco(5300.00);
        produto.setCategoria("Notebook Gamer");
        produto.setMarca("Dell");
        produto.setDataEntrega("15/02/2025");
        produto.setCnpj_loja("23.456.789/0001-95");

        new ProdutoDAO().cadastrarProduto(produto);
    }
}