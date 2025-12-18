package test;

import model.Administrador;
import model.RelatorioFinanceiro;
import model.ReservaMock;

import java.util.List;
import java.time.LocalDate;

public class TesteAdministradorMock {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO TESTES COM MOCK ---");

        // CENÁRIO 1: Validar soma correta no relatório financeiro
        testarSomaRelatorio();

        // CENÁRIO 2: Verificar se o Administrador realmente consulta o valor da reserva
        testarInteracaoComReserva();
    }

    static void testarSomaRelatorio() {
        Administrador admin = new Administrador(1L, "Admin", "a@a.com", "123", "Gerente");
        ReservaMock mock1 = new ReservaMock(100.0);
        ReservaMock mock2 = new ReservaMock(250.0);

        RelatorioFinanceiro rel = admin.gerarRelatorioFinanceiro(
                List.of(mock1, mock2), LocalDate.now().minusDays(1), LocalDate.now().plusDays(1),
                "RECEITA", true, "PAGO"
        );

        if (rel.getValorTotal() == 350.0) {
            System.out.println("Caso 01 (Soma): PASSOU");
        } else {
            System.out.println("Caso 01 (Soma): FALHOU. Valor: " + rel.getValorTotal());
        }
    }

    static void testarInteracaoComReserva() {
        Administrador admin = new Administrador(1L, "Admin", "a@a.com", "123", "Gerente");
        ReservaMock mock = new ReservaMock(100.0);

        admin.gerarRelatorioFinanceiro(
                List.of(mock), LocalDate.now().minusDays(1), LocalDate.now().plusDays(1),
                "RECEITA", true, "PAGO"
        );

        if (mock.foiChamado()) {
            System.out.println("Caso 02 (Interação Mock): PASSOU (O método calcularValorTotal foi invocado)");
        } else {
            System.out.println("Caso 02 (Interação Mock): FALHOU (O Administrador ignorou o objeto Reserva)");
        }
    }
}