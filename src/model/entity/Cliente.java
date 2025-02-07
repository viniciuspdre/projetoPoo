package model.entity;

public class Cliente {
    private int id;
    private String login_usuario;
    private String cpf;
    private String estado;
    private String status_cliente;

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getStatus() {
        return status_cliente;
    }

    public void setStatus(String status) {
        this.status_cliente = status;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

}
