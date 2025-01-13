package com.br.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;

    public StepDefinitions() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("que eu acesso a página de Web Tables")
    public void acessarPaginaWebTables() {
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Elements']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Web Tables']"))).click();
    }

    @When("eu crio {int} novos registros dinamicamente")
    public void criarNovosRegistros(int quantidade) {
        for (int i = 1; i <= quantidade; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton"))).click();
            driver.findElement(By.id("firstName")).sendKeys("Nome" + i);
            driver.findElement(By.id("lastName")).sendKeys("Sobrenome" + i);
            driver.findElement(By.id("userEmail")).sendKeys("email" + i + "@teste.com");
            driver.findElement(By.id("age")).sendKeys(String.valueOf(20 + i));
            driver.findElement(By.id("salary")).sendKeys(String.valueOf(50000 + i * 1000));
            driver.findElement(By.id("department")).sendKeys("Departamento" + i);
            driver.findElement(By.id("submit")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));
        }
    }

    @Then("eu devo deletar todos os registros criados")
    public void deletarRegistros() {
        for (int i = 4; i <= 15; i++) {
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id='delete-record-" + i + "']")));
            deleteButton.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@id='delete-record-" + i + "']")));
        }
        System.out.println("Todos os registros foram criados e deletados com sucesso!");
    }
}
