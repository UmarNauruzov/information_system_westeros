package ru.itmo.WesterosTax.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class AddingLandownerTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        String chromeExecutablePath = "C:\\Develop\\test\\chrome-win64\\chrome.exe";
        System.setProperty("webdriver.chrome.driver",
                "C:\\Develop\\test\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.setBinary(chromeExecutablePath);
        driver = new ChromeDriver(options);
    }

    @Test
    public void testCreateLord() throws InterruptedException {
        loginAsLord();
        driver.get("http://localhost:8080/landowner/index");

        // Найти элемент по тексту ссылки или другому уникальному идентификатору
        WebElement linkElement = driver.findElement(By.linkText("+"));

        // Кликнуть по найденному элементу
        linkElement.click();

        driver.get("http://localhost:8080/landowner/create");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("землевладелец");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("4534524524");

        // Пауза в 3 секунды
        // Thread.sleep(3000);

        // Находим <select> элемент
        WebElement selectElement = driver.findElement(By.name("region"));

        // Создаем объект Select
        Select select = new Select(selectElement);

        // Выбираем значение по тексту или по значению
        // Например, по тексту
        select.selectByVisibleText("Васиостковский");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Пауза в 3 секунды
        // Thread.sleep(3000);

    }

    private void loginAsLord() throws InterruptedException {
        driver.get("http://localhost:8080/login");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.sendKeys("верховный_лорд");
        // Пауза в 3 секунды
        // Thread.sleep(3000);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("123455678");

        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        submitButton.click();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
