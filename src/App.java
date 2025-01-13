import dao.FornecedorDAO;
import dao.LojaDAO;
import dao.ProdutoDAO;
import dao.UsuarioDAO;
import model.entity.Fornecedor;
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

        new LojaDAO().cadastrarLoja(heliot);*/

        /*Usuario usuario = new Usuario();
        usuario.setNome("Fornecedor Dell");
        usuario.setLogin("fornecedordell");
        usuario.setSenha("fornecedordell");
        usuario.setCpf("987.654.321-00");
        usuario.setDataNascimento("22/08/1985");
        usuario.setPais("BR");
        usuario.setEstado("PE");
        usuario.setCidade("Belo Jardim");
        usuario.setBairro("São Antônio");
        usuario.setRua("Uma rua qualquer");
        usuario.setNumero("45");
        usuario.setCep("24680-135");
        usuario.setIdade(40);
        usuario.setCnpj_loja("23.456.789/0001-95");

        new UsuarioDAO().cadastrarUsuario(usuario);*/

        /*Produto produto = new Produto();
        produto.setCodigo("PRD-2025-00123");
        produto.setNome("Notebook Gamer G15");
        produto.setDescricao("Processador: Intel I5 13º Geração");
        produto.setPreco(5300.00);
        produto.setCategoria("Notebook Gamer");
        produto.setMarca("Dell");
        produto.setDataEntrega("15/02/2025");
        produto.setCnpj_loja("23.456.789/0001-95");

        new ProdutoDAO().cadastrarProduto(produto);*/

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setLogin_usuario("fornecedordell");
        fornecedor.setCnpj("12.345.678/0001-90");
        fornecedor.setData_fornecimento("20/12/2024");
        fornecedor.setNome("FornecedorDell");
        fornecedor.setPais("BR");
        fornecedor.setEstado("PE");
        fornecedor.setCidade("Belo Jardim");
        fornecedor.setBairro("São Pedro");
        fornecedor.setRua("Outra rua qualquer");
        fornecedor.setCep("24680-135");
        fornecedor.setNumero("40");

        new FornecedorDAO().cadastrarFornecedor(fornecedor);
    }
}