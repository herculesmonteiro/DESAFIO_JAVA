package com.br.program;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;

public class Sortable {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("https://demoqa.com/");
            driver.manage().window().maximize();

            WebElement interactionsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Interactions']")));
            js.executeScript("arguments[0].scrollIntoView(true);", interactionsMenu);
            js.executeScript("arguments[0].click();", interactionsMenu);

            WebElement sortableMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sortable']")));
            js.executeScript("arguments[0].click();", sortableMenu);

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".list-group-item")));

            List<WebElement> itemsLista = driver.findElements(By.cssSelector(".list-group-item"));

            // Função para trocar os dois primeiros elementos
            Actions actions = new Actions(driver);
            WebElement firstElement = itemsLista.get(0);
            WebElement secondElement = itemsLista.get(1);

            // Primeira troca
            js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", firstElement);
            Thread.sleep(500);
            actions.clickAndHold(firstElement).moveToElement(secondElement).release().perform();
            Thread.sleep(5000);

            // Voltar à posição original
            itemsLista = driver.findElements(By.cssSelector(".list-group-item"));
            firstElement = itemsLista.get(1);
            secondElement = itemsLista.get(0);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", firstElement);
            Thread.sleep(500);
            actions.clickAndHold(firstElement).moveToElement(secondElement).release().perform();
            System.out.println("Os elementos foram desordenados com sucesso!");
            Thread.sleep(10000);

            // Segunda troca
            itemsLista = driver.findElements(By.cssSelector(".list-group-item"));
            firstElement = itemsLista.get(0);
            secondElement = itemsLista.get(1);
            js.executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", firstElement);
            Thread.sleep(500);
            actions.clickAndHold(firstElement).moveToElement(secondElement).release().perform();
            System.out.println("Os elementos foram ordenados com sucesso!");
            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
