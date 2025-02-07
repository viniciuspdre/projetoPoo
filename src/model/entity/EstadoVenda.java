package model.entity;

public enum EstadoVenda {
    concluida("concluído"),
    em_andamento("em andamento");

    private String estado;

    EstadoVenda(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
