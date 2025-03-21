package model;

public class Vendas {
    private int idVenda;
    private String cnpj;
    private String login_usuario;
    private String nome_usuario;
    private String data_venda;
    private String horario;
    private float valor;
    private String forma_pagamento;
    private String data_vencimento;
    private String CPFCliente;

    public Vendas(String cnpjLoja, String dataVenda, String horario, float valorTotal, String formaPagamento, String dataVencimento, String estadoVenda, String cpfCliente, int idVenda) {
        this.cnpj = cnpjLoja;
        this.idVenda = idVenda;
        this.data_venda = dataVenda;
        this.horario = horario;
        this.valor = valorTotal;
        this.forma_pagamento = formaPagamento;
        this.data_vencimento = dataVencimento;
        this.CPFCliente = cpfCliente;
        this.estado_venda = estadoVenda;
    }

    public Vendas(){}

    public String getCPFCliente() {
        return CPFCliente;
    }

    public void setCPFCliente(String CPFCliente) {
        this.CPFCliente = CPFCliente;
    }

    public String getEstado_venda() {
        return estado_venda;
    }

    public void setEstado_venda(String estado_venda) {
        this.estado_venda = estado_venda;
    }

    private String estado_venda;
    

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getData_venda() {
        return data_venda;
    }

    public void setData_venda(String data_venda) {
        this.data_venda = data_venda;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }
}
