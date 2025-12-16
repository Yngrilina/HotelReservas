package model;

import java.time.LocalDate;

public class Pagamento {

    private Long id;
    private double valor;
    private String formaPagamento;
    private String statusPagamento;
    private LocalDate dataPagamento;

    public Pagamento(double valor, String formaPagamento) {
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.statusPagamento = "PENDENTE";
    }

    public boolean processarPagamento() {

        if (valor <= 0) {
            return false;
        }

        statusPagamento = "PAGO";
        dataPagamento = LocalDate.now();
        return true;
    }

    public void estornarPagamento() {

        if (!statusPagamento.equals("PAGO")) {
            throw new IllegalStateException("Pagamento não pode ser estornado");
        }

        statusPagamento = "ESTORNADO";
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }
}