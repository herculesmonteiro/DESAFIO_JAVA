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

public class WebTables {
    public static void main(String[] args) {
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

            WebElement elementsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Elements']")));
            js.executeScript("arguments[0].scrollIntoView(true);", elementsButton);
            js.executeScript("arguments[0].click();", elementsButton);

            WebElement webTablesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Web Tables']")));
            js.executeScript("arguments[0].click();", webTablesButton);

            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
            js.executeScript("arguments[0].click();", addButton);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys("Hercules");
            driver.findElement(By.id("lastName")).sendKeys("Monteiro");
            driver.findElement(By.id("userEmail")).sendKeys("hercules.monteiro@teste.com");
            driver.findElement(By.id("age")).sendKeys("37");
            driver.findElement(By.id("salary")).sendKeys("10000");
            driver.findElement(By.id("department")).sendKeys("IT");

            // Delay de 8 segundos após incluir
            Thread.sleep(8000);
            
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            js.executeScript("arguments[0].click();", submitButton);

            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='edit-record-4']")));
            js.executeScript("arguments[0].click();", editButton);

            WebElement departmentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("department")));
            departmentField.clear();
            departmentField.sendKeys("Silva");
            // Delay de 5 segundos após editar
            Thread.sleep(5000);
            
            submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            js.executeScript("arguments[0].click();", submitButton);
            // Delay de 5 segundos após deletar
            Thread.sleep(5000);
            
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='delete-record-4']")));
            js.executeScript("arguments[0].click();", deleteButton);
            // Delay de 5 segundos após deletar
            Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
