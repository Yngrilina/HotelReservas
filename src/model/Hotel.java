package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private Long id;
    private String nome;
    private String endereco;
    private String cidade;
    private int categoria;

    private List<Quarto> quartos;
    private List<Avaliacao> avaliacoes;

    public Hotel(Long id, String nome, String endereco, String cidade, int categoria) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.categoria = categoria;
        this.quartos = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto) {
        if (quarto == null) {
            throw new IllegalArgumentException("Quarto inválido");
        }
        quartos.add(quarto);
    }

    public List<Quarto> listarQuartos() {
        return quartos;
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação inválida");
        }
        avaliacoes.add(avaliacao);
    }

    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        int soma = 0;
        for (Avaliacao avaliacao : avaliacoes) {
            soma += avaliacao.getNota();
        }

        return (double) soma / avaliacoes.size();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}