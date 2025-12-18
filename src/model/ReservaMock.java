package model;

import model.Reserva;

import java.time.LocalDate;

// Classe Mock Manual
public class ReservaMock extends Reserva {
    private boolean metodoCalcularChamado = false;
    private double valorRetorno;

    public ReservaMock(double valorParaRetornar) {
        // Passamos nulo para o construtor pai para simplificar
        super(null, null, LocalDate.now(), LocalDate.now().plusDays(1));
        this.valorRetorno = valorParaRetornar;
    }

    @Override
    public double calcularValorTotal() {
        this.metodoCalcularChamado = true; // Registra a interação
        return valorRetorno;
    }

    public boolean foiChamado() {
        return metodoCalcularChamado;
    }
}