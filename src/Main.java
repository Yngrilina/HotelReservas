import model.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // ====== CRIAÇÃO DO CLIENTE ======
        Cliente cliente = new Cliente(
                1L,
                "Nome do Cliente",
                "email@exemplo.com",
                "senha123",
                "00000000000",
                "00000000000"
        );

        Quarto quarto = new Quarto(
                1L,
                101,
                "Tipo",
                2,
                100.0
        );


        Reserva reserva = cliente.realizarReserva(
                quarto,
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 5)
        );

        Servico servico = new Servico(
                1L,
                "Nome do Serviço",
                25.0
        );

        ReservaServico reservaServico = new ReservaServico(
                servico,
                2
        );

        reserva.adicionarServico(reservaServico);

        double valorTotal = reserva.calcularValorTotal();
        System.out.println("Valor total da reserva: R$ " + valorTotal);

        Pagamento pagamento = new Pagamento(
                valorTotal,
                "FORMA_PAGAMENTO"
        );

        reserva.setPagamento(pagamento);

        reserva.confirmarReserva();
        System.out.println("Reserva confirmada com sucesso!");
    }
}
