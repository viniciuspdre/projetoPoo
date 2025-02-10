package model;

public class Cliente {
    private String nome;
    private String cpf;
    private String estado;
    private String status_cliente;
    private String sexo;
    private String data_registro;
    private String data_nascimento;
    // private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getStatus_cliente() {
        return status_cliente;
    }

    public void setStatus_cliente(String status_cliente) {
        this.status_cliente = status_cliente;
    }

    public String getSexo() {return sexo; }

    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getData_registro() { return data_registro; }

    public void setData_registro(String data_registro) {this.data_registro = data_registro; }

    public String getData_nascimento() { return data_nascimento; }

    public void setData_nascimento(String data_nascimento) {this.data_nascimento = data_nascimento; }

}
