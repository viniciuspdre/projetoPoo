package model.entity;

public enum TipoUsuario {
    ADMIN("admin"),
    PADRAO("padrão");

    private String tipoUsuario;

    TipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}
