package model;

import java.time.LocalDate;
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

    private List<Quarto> getQuartos() {
        return this.quartos;
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

    public List<Quarto> buscarQuartosDisponiveis(
            LocalDate checkin,
            LocalDate checkout,
            String tipo,
            int numAdultos,
            boolean vistaMar,
            String perfilCliente,
            Double precoMax
    ) {

        List<Quarto> todosQuartos = this.getQuartos();
        List<Quarto> disponiveis = new ArrayList<>();

        if (checkin == null || checkout == null) {
            return disponiveis;
        }

        if (checkin.isAfter(checkout) || checkin.isBefore(LocalDate.now())) {
            return disponiveis;
        }

        for (Quarto quarto : todosQuartos) {

            if (quarto.isDisponivel(checkin, checkout)) {

                if (tipo != null && quarto.getTipo().equalsIgnoreCase(tipo)) {

                    if (quarto.getCapacidade() >= numAdultos) {

                        if ("LUXO".equalsIgnoreCase(tipo)) {
                            if (!(quarto.hasVistaMar() && quarto.hasTV())) {
                                continue;
                            }
                        }
                        else if ("SUITE".equalsIgnoreCase(tipo)) {
                            if (!(quarto.hasBanheira() && quarto.getArea() >= 50)) {
                                continue;
                            }
                        }

                        if (!"VIP".equalsIgnoreCase(perfilCliente)) {
                            if (precoMax != null && quarto.getPrecoDiaria() > precoMax) {
                                continue;
                            }
                        }

                        disponiveis.add(quarto);
                    }
                }
            }
        }
        return disponiveis;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public int getCategoria() {
        return categoria;
    }
}
