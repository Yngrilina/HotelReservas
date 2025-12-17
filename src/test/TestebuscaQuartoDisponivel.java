package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import java.time.LocalDate;
import java.util.List;

class TestebuscaQuartoDisponivel {
    private Hotel hotel;
    private Quarto quartoLuxo;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(1L, "Hotel Teste", "Rua A", "JF", 5);
        // Quarto de Luxo com diária de 600.0
        quartoLuxo = new Quarto(1L, "LUXO", 2, 600.0, true, true, true, 60);
        hotel.adicionarQuarto(quartoLuxo);
    }

    @Test
    void testPrecoNoLimiteExato_T4() {
        // Testando exatamente o limite de 600.0
        List<Quarto> resultado = hotel.buscarQuartosDisponiveis(
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                "LUXO", 1, false, "NORMAL", 600.0
        );
        assertEquals(1, resultado.size(), "Deve incluir o quarto no valor limite exato");
    }

    @Test
    void testPrecoAcimaDoLimite_T5() {
        // Testando 599.99 (quarto de 600 não deve aparecer)
        List<Quarto> resultado = hotel.buscarQuartosDisponiveis(
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                "LUXO", 1, false, "NORMAL", 599.99
        );
        assertTrue(resultado.isEmpty(), "Não deve incluir quarto acima do limite máximo");
    }

    @Test
    void testPerfilVIPIgnoraPreco_T12() {
        // Mesmo com preço máximo de 100, se for VIP o quarto de 600 deve aparecer
        List<Quarto> resultado = hotel.buscarQuartosDisponiveis(
                LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                "LUXO", 1, false, "VIP", 100.0
        );
        assertEquals(1, resultado.size(), "Perfil VIP deve ignorar o filtro de preço máximo");
    }
}