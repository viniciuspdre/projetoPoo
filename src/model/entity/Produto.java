package model.entity;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
    private FileInputStream foto;
    private long tamanho_imagem;

    public Produto(String codigo, String nome, double preco, int estoque, int estoque_minimo, int vendidos, String categoria, String marca, String descricao, FileInputStream foto, long tamanho_imagem, String cnpj_loja) {
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
        this.tamanho_imagem = tamanho_imagem;
        this.foto = foto;
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

    public void setFoto(FileInputStream foto) {
        this.foto = foto;
    }

    public void setTamanho_imagem(int tamanho_imagem) {
        this.tamanho_imagem = tamanho_imagem;
    }

    public long getTamanho_imagem() {
        return tamanho_imagem;
    }

    public FileInputStream getFoto() {
        return foto;
    }
}