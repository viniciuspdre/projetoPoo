import dao.LojaDAO;
import model.entity.Loja;

public class App {
    public static void main(String[] args) {
        Loja loja = new Loja();
        loja.setCnpj("23.456.789/0001-95");
        loja.setNome("HeliotTech");
        loja.setCategoria("Informática");
        loja.setPais("BR");
        loja.setEstado("PE");
        loja.setCidade("Belo Jardim");
        loja.setBairro("São Pedro");
        loja.setRua("Virando a esquina");
        loja.setNumero("12");

        new LojaDAO().cadastrarLoja(loja);
    }
}