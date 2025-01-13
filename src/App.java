import dao.*;
import model.entity.*;

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
        usuario.setNome("Pedro Victor");
        usuario.setLogin("pedrovictor");
        usuario.setSenha("pedrovictor");
        usuario.setCpf("321.654.987-01");
        usuario.setDataNascimento("06/06/2004");
        usuario.setPais("BR");
        usuario.setEstado("PE");
        usuario.setCidade("Belo Jardim");
        usuario.setBairro("Cohab 1");
        usuario.setRua("Agenor Barbosa Maciel");
        usuario.setNumero("135A");
        usuario.setCep("55158-230");
        usuario.setIdade(20);
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

        /*Fornecedor fornecedor = new Fornecedor();
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
        new FornecedorDAO().cadastrarFornecedor(fornecedor);*/

        /*Dependente dependente = new Dependente();
        dependente.setLogin_usuario("pedrovictor");
        dependente.setNome("Dependente Pedrovictor");
        dependente.setSexo("I");
        dependente.setParentesco("Indefinido");
        dependente.setData_nascimento("06/06/2004");
        new DependenteDAO().cadastrarDependente(dependente);*/

        /*Administrador admin = new Administrador();
        admin.setLogin_usuario("admin");
        admin.setCargo("admin");
        new AdministradorDAO().cadastrarAdministrador(admin);*/

        /*Cliente cliente = new Cliente();
        cliente.setLogin_usuario("pedrovictor");
        cliente.setStatus("Ativo");
        new ClienteDAO().cadastrarCliente(cliente);*/

        /*DadosBancariosUsuario dados = new DadosBancariosUsuario();
        dados.setNome_titular("Pedro Victor da Silva Pereira");
        dados.setBanco("Banco do Brasil");
        dados.setAgencia("1234-5");
        dados.setNumeroConta("12345-6");
        dados.setTipo_conta("Corrente");
        dados.setChave_pix("(21)98765-4321");
        dados.setLogin_usuario("pedrovictor");
        new DadosBancariosUsuarioDAO().cadastrarDadosBancariosUsuario(dados);*/

        /*EmailsUsuario emailsUsuario = new EmailsUsuario();
        emailsUsuario.setLogin_usuario("pedrovictor");
        emailsUsuario.setEmail("pedrovictor@gmail.com");
        new EmailsUsuarioDAO().cadastrarEmailUsuario(emailsUsuario);*/

        /*TelefonesUsuario telefonesUsuario = new TelefonesUsuario();
        telefonesUsuario.setLogin_usuario("pedrovictor");
        telefonesUsuario.setTelefone("(21)98765-4321");
        new TelefonesUsuarioDAO().cadastrarTelefonesUsuario(telefonesUsuario);*/

        UsuarioCompraProduto usuarioCompraProduto = new UsuarioCompraProduto();
        usuarioCompraProduto.setLogin_usuario("pedrovictor");
        usuarioCompraProduto.setCod_produto("PRD-2025-00123");
        usuarioCompraProduto.setDataCompra("10/11/2024");
        usuarioCompraProduto.setFormaPagamento("Cartão de Crédito");
        new UsuarioCompraProdutoDAO().inserir(usuarioCompraProduto);
    }
}