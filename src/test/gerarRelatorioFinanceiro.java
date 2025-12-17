package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class gerarRelatorioFinanceiro {

    @Test
    void testDataInicialDepoisDaFinal_T11() {
        Administrador admin = new Administrador(1L, "Admin", "a@a.com", "123", "Gerente");
        LocalDate inicio = LocalDate.now().plusDays(5);
        LocalDate fim = LocalDate.now(); // Fim antes do início

        assertThrows(IllegalArgumentException.class, () -> {
            admin.gerarRelatorioFinanceiro(new ArrayList<>(), inicio, fim, "RECEITA", true, "PAGO");
        }, "Deve lançar exceção para intervalo de datas inválido");
    }

    @Test
    void testRelatorioApenasComReservasConcluidas_T13() {
        Administrador admin = new Administrador(1L, "Admin", "a@a.com", "123", "Gerente");

        // Setup de uma reserva concluída
        Cliente c = new Cliente(1L, "User", "u@u.com", "123");
        Quarto q = new Quarto(1L, "STD", 2, 100.0, true, false, false, 20);
        Reserva r = new Reserva(c, q, LocalDate.now(), LocalDate.now().plusDays(2));
        // Status padrão da Reserva no seu código é "CONCLUIDA"

        List<Reserva> lista = List.of(r);
        RelatorioFinanceiro rel = admin.gerarRelatorioFinanceiro(
                lista, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5),
                "RECEITA", true, "PAGO"
        );

        assertEquals(200.0, rel.getValorTotal(), "O relatório deve somar reservas concluídas");
    }
}