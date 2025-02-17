package model;

public class ProdutosVendas {
    private int id_venda;
    private String cod_produto;
    private float preco_produto;
    private int quantidade;

    public ProdutosVendas(int idVenda, String codProduto, int quantidade, float precoUnitario) {
        this.id_venda = idVenda;
        this.cod_produto = codProduto;
        this.quantidade = quantidade;
        this.preco_produto = precoUnitario;
    }
    public ProdutosVendas() {}

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(float preco_produto) {
        this.preco_produto = preco_produto;
    }

    public String getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(String cod_produto) {
        this.cod_produto = cod_produto;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }
}
