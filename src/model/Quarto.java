package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Quarto {

    private Long id;
    private int numero;
    private String tipo;
    private int capacidade;
    private double valorDiaria;
    private String status;


    private List<Reserva> reservas;

    public Quarto(Long id, int numero, String tipo, int capacidade, double valorDiaria) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.valorDiaria = valorDiaria;
        this.status = "DISPONIVEL";
        this.reservas = new ArrayList<>();
    }

    public boolean verificarDisponibilidade(LocalDate dataCheckIn, LocalDate dataCheckOut) {

        if (dataCheckIn == null || dataCheckOut == null) {
            throw new IllegalArgumentException("Datas inválidas");
        }

        if (!dataCheckOut.isAfter(dataCheckIn)) {
            return false;
        }

        if (!status.equals("DISPONIVEL")) {
            return false;
        }

        for (Reserva reserva : reservas) {

            if (!reserva.getStatus().equals("ATIVA")) {
                continue;
            }

            LocalDate inicioExistente = reserva.getDataCheckIn();
            LocalDate fimExistente = reserva.getDataCheckOut();

            boolean conflitoInicio =
                    dataCheckIn.isBefore(fimExistente) &&
                            dataCheckIn.isAfter(inicioExistente);

            boolean conflitoFim =
                    dataCheckOut.isAfter(inicioExistente) &&
                            dataCheckOut.isBefore(fimExistente);

            boolean conflitoTotal =
                    dataCheckIn.isEqual(inicioExistente) ||
                            dataCheckOut.isEqual(fimExistente);

            if (conflitoInicio || conflitoFim || conflitoTotal) {
                return false;
            }
        }

        return true;
    }

    public void atualizarStatus(String status) {
        if (!status.equals("DISPONIVEL") && !status.equals("OCUPADO")) {
            throw new IllegalArgumentException("Status inválido");
        }
        this.status = status;
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public String getStatus() {
        return status;
    }
}