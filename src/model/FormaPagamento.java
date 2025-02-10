package model;

public enum FormaPagamento {
    CARTAODECREDITO("Cartão de crédito"),
    CARTAODEDEBITO("Cartão de débito"),
    PIX("Pix"),
    BOLETO("Boleto");

    private String pagamento;

    FormaPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
    public String getPagamento() {
        return pagamento;
    }
}
