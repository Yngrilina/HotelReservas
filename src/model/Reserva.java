package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private String status;

    private List<ReservaServico> servicos;

    public Reserva(Cliente cliente, Quarto quarto,
                   LocalDate dataCheckIn, LocalDate dataCheckOut) {

        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.status = "CONCLUIDA";
        this.servicos = new ArrayList<>();
    }

    public boolean conflitaCom(LocalDate inicio, LocalDate fim) {
        return !(fim.isBefore(dataCheckIn) || inicio.isAfter(dataCheckOut));
    }

    public double calcularValorTotal() {
        long dias = dataCheckIn.until(dataCheckOut).getDays();
        double total = dias * quarto.getPrecoDiaria();

        for (ReservaServico rs : servicos) {
            total += rs.getServico().getPreco();
        }
        return total;
    }

    public void cancelar() {
        this.status = "CANCELADA";
    }

    public LocalDate getDataCheckIn() {
        return dataCheckIn;
    }

    public LocalDate getDataCheckOut() {
        return dataCheckOut;
    }

    public String getStatus() {
        return status;
    }

    public List<ReservaServico> getServicos() {
        return servicos;
    }
}