import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Administrador admin = new Administrador(
                1L,
                "Administrador Geral",
                "admin@hotel.com",
                "123",
                "Gerente"
        );

        Hotel hotel = new Hotel(
                1L,
                "Hotel Central",
                "Rua Principal, 100",
                "Juiz de Fora",
                5
        );

        Quarto q1 = new Quarto(1L, "LUXO", 2, 350.0, true, true, true, 60);
        Quarto q2 = new Quarto(2L, "SUITE", 4, 500.0, true, true, false, 55);
        Quarto q3 = new Quarto(3L, "STANDARD", 2, 200.0, true, false, false, 30);

        hotel.adicionarQuarto(q1);
        hotel.adicionarQuarto(q2);
        hotel.adicionarQuarto(q3);

        Cliente cliente = new Cliente(
                10L,
                "Yngrid",
                "yngrid@email.com",
                "abc123"
        );

        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = LocalDate.now().plusDays(4);

        Reserva reserva = cliente.realizarReserva(q1, checkIn, checkOut);

        Servico cafe = new Servico("Café da manhã", 50.0);
        Servico spa = new Servico("Spa", 120.0);

        reserva.getServicos().add(new ReservaServico(cafe));
        reserva.getServicos().add(new ReservaServico(spa));

        Pagamento pagamento = new Pagamento(reserva, reserva.calcularValorTotal());

        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva);

        RelatorioFinanceiro relatorio = admin.gerarRelatorioFinanceiro(
                reservas,
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                "RECEITA",
                true,
                "PAGO"
        );

        System.out.println("==== RELATÓRIO FINANCEIRO ====");
        System.out.println("Total apurado: R$ " + relatorio.getValorTotal());

        System.out.println("\n==== QUARTOS DISPONÍVEIS ====");

        List<Quarto> disponiveis = hotel.buscarQuartosDisponiveis(
                checkIn.plusDays(10),
                checkOut.plusDays(12),
                "LUXO",
                2,
                false,
                "VIP",
                600.0
        );

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum quarto disponível para os critérios informados.");
        } else {
            for (Quarto q : disponiveis) {
                System.out.println("Quarto: " + q.getTipo() + " | R$ " + q.getPrecoDiaria());
            }
        }
    }
}