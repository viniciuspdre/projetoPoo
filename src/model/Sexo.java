package model.entity;

public enum Sexo {
    MASCULINO ("Masculino"), FEMININO ("Feminino"), OUTROS ("Outros");

    private String sexo;
    public String getSexo() {
        return sexo;
    }
    Sexo(String sexo) {
        this.sexo = sexo;
    }
}
