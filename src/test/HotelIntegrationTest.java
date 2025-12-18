package test;

import model.Hotel;
import model.Quarto;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HotelIntegrationTest {

    @Test
    void testBuscaComQuartoStub() {
        // 1. Ajuste do Construtor do Hotel (Conforme o erro: Long, String, String, String, int)
        // Exemplo: ID, Nome, Endereço, Telefone, Estrelas
        Hotel hotel = new Hotel(1L, "Hotel Transilvânia", "Rua X", "1234-5678", 5);

        // 2. Criando o Stub
        Quarto stub1 = new QuartoStub(1L, "STANDARD", 2, 200.0, true, false, false, 20.0);

        // 3. Como você não tem setQuartos, vamos usar o metodo que você provavelmente tem para adicionar quartos
        // Se o seu metodo for adicionarQuarto(quarto), use assim:
        hotel.adicionarQuarto(stub1);

        // 4. Executa a lógica de busca que queremos integrar
        List<Quarto> resultado = hotel.buscarQuartosDisponiveis(
                LocalDate.now(),
                LocalDate.now().plusDays(2),
                "STANDARD", 2, false, "NORMAL", 1000.0
        );

        // 5. Validação do Stub
        assertNotNull(resultado, "A lista de resultados não deve ser nula");
        assertFalse(resultado.isEmpty(), "O Hotel deveria encontrar o QuartoStub que está sempre disponível");
        assertEquals("STANDARD", resultado.get(0).getTipo());

        System.out.println("Integração Top-Down com QuartoStub: SUCESSO");
    }
}