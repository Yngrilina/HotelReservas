package model;

public class Pagamento {

    private Reserva reserva;
    private double valor;
    private String status;

    public Pagamento(Reserva reserva, double valor) {
        this.reserva = reserva;
        this.valor = valor;
        this.status = "PAGO";
    }

    public String getStatus() {
        return status;
    }
}