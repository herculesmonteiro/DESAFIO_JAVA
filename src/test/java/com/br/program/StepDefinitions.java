package com.br.program;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class StepDefinitions {

    WebDriver driver;

    // Configuração do WebDriver
    @Given("que eu acesso a página de Web Tables")
    public void acessarPaginaWebTables() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//h5[text()='Elements']")).click();
        driver.findElement(By.xpath("//span[text()='Web Tables']")).click();

        // Espera para carregar a página
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Criar 12 novos registros dinamicamente
    @When("eu crio 12 novos registros dinamicamente")
    public void criarNovosRegistros() {
        for (int i = 1; i <= 12; i++) {
            driver.findElement(By.id("addNewRecordButton")).click();
            driver.findElement(By.id("firstName")).sendKeys("Nome" + i);
            driver.findElement(By.id("lastName")).sendKeys("Sobrenome" + i);
            driver.findElement(By.id("userEmail")).sendKeys("email" + i + "@teste.com");
            driver.findElement(By.id("age")).sendKeys(String.valueOf(20 + i));
            driver.findElement(By.id("salary")).sendKeys(String.valueOf(50000 + i * 1000));
            driver.findElement(By.id("department")).sendKeys("Departamento" + i);
            driver.findElement(By.id("submit")).click();

            // Esperar o registro ser adicionado
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Deletar todos os registros criados
    @Then("eu devo deletar todos os registros criados")
    public void deletarRegistros() throws InterruptedException {
        for (int i = 4; i <= 15; i++) {
        	
        	WebElement deleteButton = driver.findElement(By.xpath("//span[@id='delete-record-" + i + "']"));
        	
            deleteButton.click();
            Thread.sleep(500);
        	
       
            // Esperar um pouco antes de deletar o próximo
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Todos os registros foram criados e deletados com sucesso!");
    }
}
