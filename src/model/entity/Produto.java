package model.entity;

public class Produto {
    private String codigo;
    private String nome;
    private double preco;
    private int estoque;
    private int vendidos;
    private String categoria;
    private String marca;
    private String descricao;
    private String dataEntrega;
    private String cnpj_loja;

    public Produto(String codigo, String nome, double preco,int estoque, int vendidos, String categoria, String marca, String descricao, String cnpj_loja) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.marca = marca;
        this.descricao = descricao;
        this.cnpj_loja = cnpj_loja;
        this.vendidos = vendidos;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCnpj_loja() {
        return cnpj_loja;
    }

    public void setCnpj_loja(String cnpj_loja) {
        this.cnpj_loja = cnpj_loja;
    }

    public String getFoto() {

        return "";
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }
}