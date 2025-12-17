package model;

import java.time.LocalDate;
import java.util.List;

public class Administrador extends Usuario {

    private static final String STATUS_CONCLUIDA = "CONCLUIDA";
    private String cargo;

    public Administrador(Long id, String nome, String email, String senha, String cargo) {
        super(id, nome, email, senha);
        this.cargo = cargo;
    }

    public RelatorioFinanceiro gerarRelatorioFinanceiro(
            List<Reserva> reservas,
            LocalDate inicio,
            LocalDate fim,
            String tipoRelatorio,
            boolean incluirServicos,
            String statusPagamento
    ) {

        RelatorioFinanceiro relatorio = new RelatorioFinanceiro();

        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException("Data inicial inválida");
        }

        for (Reserva r : reservas) {

            if (r.getDataCheckOut().isAfter(inicio)
                    && r.getDataCheckIn().isBefore(fim)
                    && STATUS_CONCLUIDA.equals(r.getStatus())) {

                double valorAgregado = 0;

                if ("RECEITA".equals(tipoRelatorio)) {
                    valorAgregado = r.calcularValorTotal();
                }
                else if ("DESPESA".equals(tipoRelatorio)) {
                    valorAgregado = -100;
                }

                if (statusPagamento == null
                        || "TODOS".equals(statusPagamento)
                        || "PAGO".equals(statusPagamento)) {

                    if (valorAgregado != 0) {
                        relatorio.adicionarValor(valorAgregado);
                    }
                }
            }
        }
        return relatorio;
    }
}