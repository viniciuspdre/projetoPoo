package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.File;

public class Produto {
    private String codigo;
    private String nome;
    private double preco;
    private int estoque;
    private int vendidos;
    private int estoque_minimo;
    private String categoria;
    private String marca;
    private String descricao;
    private String cnpj_loja;
    private byte[] foto;
    private IntegerProperty quantidadeNoCarrinho = new SimpleIntegerProperty(1);

    public IntegerProperty quantidadeNoCarrinhoProperty() {
        return quantidadeNoCarrinho;
    }

    public int getQuantidadeNoCarrinho() {
        return quantidadeNoCarrinho.get();
    }

    public void setQuantidadeNoCarrinho(int quantidade) {
        this.quantidadeNoCarrinho.set(quantidade);
    }

    public Produto(String codigo, String nome, double preco, int estoque, int estoque_minimo, int vendidos, String categoria, String marca, String descricao, byte[] foto, String cnpj_loja) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.marca = marca;
        this.descricao = descricao;
        this.cnpj_loja = cnpj_loja;
        this.vendidos = vendidos;
        this.estoque = estoque;
        this.estoque_minimo = estoque_minimo;
        this.foto = foto;
    }

    public Produto(){}

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

    public void setEstoque_minimo(int estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    public int getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public byte[] getFoto() {
        return foto;
    }
}