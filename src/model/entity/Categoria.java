package model.entity;

public class Categoria {
    private String nomeCategoria;
    public Categoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    @Override
    public String toString() {
        return getNomeCategoria();
    }
}
