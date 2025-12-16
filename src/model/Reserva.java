package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private Long id;
    private Cliente cliente;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private String status;
    private double valorTotal;

    private Pagamento pagamento;
    private List<ReservaServico> servicos;

    public Reserva(Cliente cliente, Quarto quarto,
                   LocalDate dataCheckIn, LocalDate dataCheckOut) {

        if (cliente == null || quarto == null) {
            throw new IllegalArgumentException("Cliente ou quarto inválido");
        }

        if (!quarto.verificarDisponibilidade(dataCheckIn, dataCheckOut)) {
            throw new IllegalStateException("Quarto indisponível para o período");
        }

        this.cliente = cliente;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.status = "ATIVA";
        this.servicos = new ArrayList<>();

        quarto.adicionarReserva(this);
    }

    public double calcularValorTotal() {

        if (dataCheckIn == null || dataCheckOut == null) {
            throw new IllegalArgumentException("Datas inválidas");
        }

        if (!dataCheckOut.isAfter(dataCheckIn)) {
            throw new IllegalArgumentException("Check-out deve ser após check-in");
        }

        long dias = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);

        if (dias <= 0) {
            throw new IllegalArgumentException("Número de diárias inválido");
        }

        double total = dias * quarto.getValorDiaria();

        if (servicos != null && !servicos.isEmpty()) {
            for (ReservaServico rs : servicos) {
                if (rs.getQuantidade() <= 0) {
                    throw new IllegalArgumentException("Quantidade inválida de serviço");
                }
                total += rs.calcularValor();
            }
        }

        this.valorTotal = total;
        return total;
    }

    public void confirmarReserva() {

        if (!status.equals("ATIVA")) {
            throw new IllegalStateException("Reserva não pode ser confirmada");
        }

        if (pagamento == null) {
            throw new IllegalStateException("Pagamento obrigatório");
        }

        boolean pago = pagamento.processarPagamento();

        if (!pago) {
            throw new IllegalStateException("Pagamento não aprovado");
        }

        quarto.atualizarStatus("OCUPADO");
    }

    public void cancelarReserva() {

        if (status.equals("CANCELADA")) {
            throw new IllegalStateException("Reserva já cancelada");
        }

        status = "CANCELADA";

        if (pagamento != null) {
            pagamento.estornarPagamento();
        }

        quarto.atualizarStatus("DISPONIVEL");
    }

    public void adicionarServico(ReservaServico reservaServico) {
        servicos.add(reservaServico);
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
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
}