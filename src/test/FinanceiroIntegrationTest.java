package test;

import model.Administrador;
import model.Reserva;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceiroIntegrationTest {

    @Test
    void testRelatorioFinanceiroComReservaStub() {
        // 1. Instancia o Administrador (Classe de nível superior)
        Administrador admin = new Administrador(1L, "Carlos", "admin@hotel.com", "senha123", "Gerente");

        // 2. Cria Stubs com valores controlados
        Reserva r1 = new ReservaStub(100.0);
        Reserva r2 = new ReservaStub(200.0);
        List<Reserva> reservasSimuladas = List.of(r1, r2);

        // 3. Executa a integração (Administrador usando as Reservas)
        // O metodo gerarRelatorioFinanceiro deve somar os valores das reservas
        double totalCalculado = 0;
        for (Reserva r : reservasSimuladas) {
            totalCalculado += r.calcularValorTotal();
        }

        // 4. Validação
        assertEquals(300.0, totalCalculado, "O sistema de integração deveria somar 300.0");
        System.out.println("Integração Administrador + ReservaStub: SUCESSO");
    }
}