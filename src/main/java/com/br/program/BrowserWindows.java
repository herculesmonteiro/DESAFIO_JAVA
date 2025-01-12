package com.br.program;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.Set;

public class BrowserWindows {

    // Método para clicar em elementos usando JavaScript
    public static void clickElementJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");

        // Configurar opções do Chrome para bloquear anúncios
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");          
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions"); // Desabilita extensões que podem interferir

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://demoqa.com/");
            driver.manage().window().maximize();

            // Escolher a opção "Alerts, Frame & Windows"
            WebElement alertsFrameWindows = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Alerts, Frame & Windows']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", alertsFrameWindows);
            clickElementJS(driver, alertsFrameWindows);

            // Clicar no submenu "Browser Windows"
            WebElement browserWindows = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Browser Windows']")));
            clickElementJS(driver, browserWindows);

            // Clicar no botão "New Window"
            WebElement novaJanela = wait.until(ExpectedConditions.elementToBeClickable(By.id("windowButton")));
            clickElementJS(driver, novaJanela);
            Thread.sleep(8000); // Delay de 8 segundos após clicar no botão stop
            
            // Obter o identificador da janela original
            String janelaOriginal = driver.getWindowHandle();

            // Verificação da janela aberta
            Set<String> todasJanelas = driver.getWindowHandles();
            for (String item : todasJanelas) {
                if (!item.equals(janelaOriginal)) {
                    driver.switchTo().window(item);
                    WebElement sampleText = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sampleHeading")));
                    String textoJanela = sampleText.getText();
                    if ("This is a sample page".equals(textoJanela)) {
                        System.out.println("Mensagem validada com sucesso: " + textoJanela);
                    } else {
                        System.out.println("Mensagem não encontrada!");
                    }
                    // Fechar a nova janela
                    driver.close();
                    // Voltar para a janela original
                    driver.switchTo().window(janelaOriginal);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(8000);
            driver.quit();
        }
    }
}
