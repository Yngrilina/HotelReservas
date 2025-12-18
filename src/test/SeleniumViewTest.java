package test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumViewTest {

    private WebDriver driver;
    private String urlSistema;

    @BeforeEach
    void setUp() {
        // 1. Caminho do Driver
        System.setProperty("webdriver.chrome.driver", "C://Users//ynlin//Downloads//selenium//chromedriver-win64//chromedriver.exe");

        // 2. Resolver o caminho do HTML para Absoluto (Isso corrige o erro de não encontrar elementos)
        File file = new File("src/view/buscarQuartos.html");
        urlSistema = "file:///" + file.getAbsolutePath();

        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless");

        driver = new ChromeDriver(options);

        // 3. Espera implícita (ajuda se o PC estiver lento)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(urlSistema);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("CT01 - Busca por Quarto Standard com Sucesso")
    void testBuscaStandardSucesso() {
        driver.findElement(By.id("checkin")).sendKeys("20122025");
        driver.findElement(By.id("checkout")).sendKeys("25122025");
        driver.findElement(By.id("tipo")).sendKeys("STANDARD");
        driver.findElement(By.id("btnBuscar")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();

        assertTrue(msg.contains("Enviando dados"), "Deveria exibir alerta de sucesso");
    }

    @Test
    @Order(2)
    @DisplayName("CT02 - Busca por Quarto de Luxo")
    void testBuscaLuxo() {
        driver.findElement(By.id("tipo")).sendKeys("LUXO");
        driver.findElement(By.id("btnBuscar")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();

        assertTrue(msg.contains("Enviando dados"), "Deveria permitir busca de luxo");
    }

    @Test
    @Order(3)
    @DisplayName("CT03 - Erro: Data de Check-out anterior ao Check-in")
    void testErroDataInvalida() {
        driver.findElement(By.id("checkin")).sendKeys("2025-12-30"); // Formato YYYY-MM-DD costuma ser mais aceito por inputs date
        driver.findElement(By.id("checkout")).sendKeys("2025-12-20");
        driver.findElement(By.id("btnBuscar")).click();

        String erroMsg = driver.findElement(By.id("mensagem-erro")).getText();

        assertEquals("Data de saída não pode ser anterior à entrada", erroMsg);
        assertTrue(driver.findElement(By.id("mensagem-erro")).isDisplayed());
    }

    @Test
    @Order(4)
    @DisplayName("CT04 - Validação de Perfil VIP (Ignorar preço)")
    void testPerfilVIP() {
        driver.findElement(By.id("perfil")).sendKeys("VIP");
        driver.findElement(By.id("btnBuscar")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();

        assertTrue(msg.contains("Perfil VIP detectado"), "Deveria reconhecer alerta do VIP");
    }

    @Test
    @Order(5)
    @DisplayName("CT05 - Filtro de Preço Máximo Reduzido")
    void testPrecoMaximo() {
        driver.findElement(By.id("precoMax")).clear();
        driver.findElement(By.id("precoMax")).sendKeys("150");
        driver.findElement(By.id("btnBuscar")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        alert.accept();

        assertNotNull(msg);
    }
}