package model;

public class ReservaServico {

    private Servico servico;

    public ReservaServico(Servico servico) {
        this.servico = servico;
    }

    public Servico getServico() {
        return servico;
    }
}