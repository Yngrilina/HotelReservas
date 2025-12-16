package model;

public class ReservaServico {

    private Servico servico;
    private int quantidade;
    private double valorTotal;

    public ReservaServico(Servico servico, int quantidade) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço inválido");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        this.servico = servico;
        this.quantidade = quantidade;
        this.valorTotal = calcularValor();
    }

    public double calcularValor() {
        valorTotal = quantidade * servico.getValor();
        return valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }
}