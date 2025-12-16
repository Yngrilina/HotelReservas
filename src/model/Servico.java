package model;

public class Servico {

    private Long id;
    private String nome;
    private double valor;

    public Servico(Long id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public void atualizarValor(double novoValor) {
        if (novoValor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        this.valor = novoValor;
    }

    public double getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }
}