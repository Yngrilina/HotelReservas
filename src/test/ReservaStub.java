package test;

import model.Reserva;
import java.time.LocalDate;

public class ReservaStub extends Reserva {
    private double valorFixoParaTeste;

    public ReservaStub(double valorFixo) {
        // Passamos null para cliente e quarto pois o Stub não os usará
        super(null, null, LocalDate.now(), LocalDate.now().plusDays(1));
        this.valorFixoParaTeste = valorFixo;
    }

    @Override
    public double calcularValorTotal() {
        // Retorna o valor fixo definido no teste, ignorando a lógica real
        return valorFixoParaTeste;
    }
}