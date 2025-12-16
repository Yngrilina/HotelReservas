package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private String cpf;
    private String telefone;
    private List<Reserva> reservas;

    public Cliente(Long id, String nome, String email, String senha,
                   String cpf, String telefone) {
        super(id, nome, email, senha);
        this.cpf = cpf;
        this.telefone = telefone;
        this.reservas = new ArrayList<>();
    }

    public Reserva realizarReserva(Quarto quarto,
                                   LocalDate dataCheckIn,
                                   LocalDate dataCheckOut) {

        if (quarto == null) {
            throw new IllegalArgumentException("Quarto inválido");
        }

        Reserva reserva = new Reserva(this, quarto, dataCheckIn, dataCheckOut);
        reservas.add(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva inválida");
        }
        reserva.cancelarReserva();
    }

    public List<Reserva> listarReservas() {
        return reservas;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}