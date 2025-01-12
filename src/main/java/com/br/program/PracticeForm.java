package com.br.program;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

public class PracticeForm {

    public static void clickElementJS(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions"); // Desabilita extensões que podem interferir
        
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            driver.get("https://demoqa.com/");
            driver.manage().window().maximize();

            WebElement formsMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[text()='Forms']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formsMenu);
            clickElementJS(driver, formsMenu);

            WebElement practiceForm = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Practice Form']")));
            clickElementJS(driver, practiceForm);

            // Preencher o formulário
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys("Hercules");
            driver.findElement(By.id("lastName")).sendKeys("teste");
            driver.findElement(By.id("userEmail")).sendKeys("hercules.monteiro@teste.com");

            WebElement maleRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Male']")));
            clickElementJS(driver, maleRadio);

            driver.findElement(By.id("userNumber")).sendKeys("1234567890");

            WebElement dateOfBirth = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateOfBirthInput")));
            clickElementJS(driver, dateOfBirth);

            WebElement mesDropdown = driver.findElement(By.className("react-datepicker__month-select"));
            Select mesSelect = new Select(mesDropdown);
            mesSelect.selectByVisibleText("January");

            WebElement anoDropdown = driver.findElement(By.className("react-datepicker__year-select"));
            Select anoSelect = new Select(anoDropdown);
            anoSelect.selectByValue("2025");

            WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='10']")));
            clickElementJS(driver, dayElement);

            WebElement subjectsInput = driver.findElement(By.id("subjectsInput"));
            subjectsInput.sendKeys("Maths");
            subjectsInput.sendKeys("\n");
            subjectsInput.sendKeys("Arts");
            subjectsInput.sendKeys("\n");

            WebElement sportsCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Sports']")));
            clickElementJS(driver, sportsCheckbox);

            WebElement uploadFile = driver.findElement(By.id("uploadPicture"));
            uploadFile.sendKeys("C:\\PRJ\\DemoQA\\src\\main\\resources\\teste.txt");

            driver.findElement(By.id("currentAddress")).sendKeys("Avenida Paulista 123");

            // Selecionar estado e cidade
            Actions actions = new Actions(driver);

            WebElement estadoDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#state .css-1hwfws3")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", estadoDropdown);
            actions.moveToElement(estadoDropdown).click().perform();
            WebElement estadoInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#state input")));
            estadoInput.sendKeys("NCR");
            estadoInput.sendKeys("\n");

            WebElement cidadeDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#city .css-1hwfws3")));
            actions.moveToElement(cidadeDropdown).click().perform();
            WebElement cidadeInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#city input")));
            cidadeInput.sendKeys("Delhi");
            cidadeInput.sendKeys("\n");
            Thread.sleep(8000); // Delay de 8 segundos antes de clicar no botão submit
            
            WebElement buttonSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
            clickElementJS(driver, buttonSubmit);

            WebElement mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
            if (mensagem.isDisplayed()) {
                System.out.println("Popup foi aberto com sucesso após o submit!!!");
            } else {
                System.out.println("Erro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(8000);
            driver.quit();
        }
    }
}
