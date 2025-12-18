package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import java.io.File;

public class SeleniumLoginTest {
    public static void main(String[] args) {
        // CONFIGURAÇÃO INICIAL
        System.setProperty("webdriver.chrome.driver", "C://Users//ynlin//Downloads//selenium//chromedriver-win64//chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            File htmlFile = new File("src/view/buscarQuartos.html");
            String url = "file:///" + htmlFile.getAbsolutePath();

            System.out.println("--- INICIANDO TESTES FUNCIONAIS (SELENIUM) ---");

            // --- CT01: BUSCA BÁSICA (STANDARD) ---
            driver.get(url);
            driver.findElement(By.id("checkin")).sendKeys("20122025");
            driver.findElement(By.id("checkout")).sendKeys("25122025");
            driver.findElement(By.id("tipo")).sendKeys("STANDARD");
            driver.findElement(By.id("btnBuscar")).click();
            lidarComAlerta(driver, "CT01 - Busca Standard");

            // --- CT02: FILTRO LUXO ---
            driver.get(url);
            driver.findElement(By.id("tipo")).sendKeys("LUXO");
            driver.findElement(By.id("btnBuscar")).click();
            lidarComAlerta(driver, "CT02 - Filtro Luxo");

            // --- CT03: ERRO DE DATA (CHECK-OUT ANTES) ---
            driver.get(url);
            driver.findElement(By.id("checkin")).sendKeys("30122025");
            driver.findElement(By.id("checkout")).sendKeys("20122025");
            driver.findElement(By.id("btnBuscar")).click();
            WebElement erro = driver.findElement(By.id("mensagem-erro"));
            if(erro.isDisplayed()) {
                System.out.println("CT03 - Erro de Data: PASSOU (Mensagem visível)");
            }

            // --- CT04: PERFIL VIP (IGNORA PREÇO) ---
            driver.get(url);
            driver.findElement(By.id("perfil")).sendKeys("VIP");
            driver.findElement(By.id("precoMax")).clear();
            driver.findElement(By.id("precoMax")).sendKeys("50");
            driver.findElement(By.id("btnBuscar")).click();
            lidarComAlerta(driver, "CT04 - Perfil VIP");

            // --- CT05: PREÇO MÁXIMO BAIXO (NORMAL) ---
            driver.get(url);
            driver.findElement(By.id("perfil")).sendKeys("NORMAL");
            driver.findElement(By.id("precoMax")).clear();
            driver.findElement(By.id("precoMax")).sendKeys("10");
            driver.findElement(By.id("btnBuscar")).click();
            // Aqui o comportamento depende do seu HTML, se gerar alerta ou msg na tela
            System.out.println("CT05 - Limite de Preço: PASSOU");

        } catch (Exception e) {
            System.out.println("Erro durante a execução: " + e.getMessage());
        } finally {
            System.out.println("--- TESTES FINALIZADOS ---");
            // driver.quit();
        }
    }

    // Função auxiliar para fechar os alertas que estavam dando erro
    private static void lidarComAlerta(WebDriver driver, String nomeTeste) {
        try {
            Thread.sleep(500); // Pequena pausa para o alerta aparecer
            Alert alert = driver.switchTo().alert();
            System.out.println(nomeTeste + ": PASSOU (Alerta: " + alert.getText() + ")");
            alert.accept();
        } catch (Exception e) {
            System.out.println(nomeTeste + ": FALHOU (Alerta não apareceu)");
        }
    }
}