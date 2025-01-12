package com.br.program;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.regex.Pattern;

public class ProgressBar {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://demoqa.com/");
            driver.manage().window().maximize();

            WebElement widgetsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Widgets']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", widgetsMenu);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", widgetsMenu);

            WebElement progressBarMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Progress Bar']")));
            progressBarMenu.click();

            WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("startStopButton")));
            startButton.click();

            wait.until(ExpectedConditions.textMatches(By.className("progress-bar"), Pattern.compile("[1-9]\\d?%|100%")));

            wait.until(webDriver -> {
                WebElement barra = webDriver.findElement(By.className("progress-bar"));
                String valorBarra = barra.getText().replace("%", "");
                if (!valorBarra.isEmpty()) {
                    int progress = Integer.parseInt(valorBarra);
                    if (progress >= 20) {
                        webDriver.findElement(By.id("startStopButton")).click();
                        System.out.println("Progresso parado em: " + progress + "%");
                        return true;
                    }
                }
                return false;
            });

            Thread.sleep(8000); // Delay de 8 segundos após clicar no botão stop

            startButton.click();
            Thread.sleep(9000); // Delay de 9 segundos após clicar no botão start

            wait.until(ExpectedConditions.textToBe(By.className("progress-bar"), "100%"));

            WebElement resetButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("resetButton")));
            resetButton.click();
            Thread.sleep(5000); // Delay de 5 segundos após clicar no botão reset

            System.out.println("Barra de progresso foi resetada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
