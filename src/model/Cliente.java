package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private List<Reserva> reservas;

    public Cliente(Long id, String nome, String email, String senha) {
        super(id, nome, email, senha);
        this.reservas = new ArrayList<>();
    }

    public Reserva realizarReserva(Quarto quarto, LocalDate checkin, LocalDate checkout) {
        Reserva reserva = new Reserva(this, quarto, checkin, checkout);
        reservas.add(reserva);
        quarto.adicionarReserva(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva) {
        reservas.remove(reserva);
        reserva.cancelar();
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }
}
