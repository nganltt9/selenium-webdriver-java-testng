package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_JS_Alert {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(5000);

        //Chờ cho alert xuất hiện trong HTML sau đó switch vào
        Alert alert = new WebDriverWait (driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Thread.sleep(5000);

        //Chờ cho alert xuất hiện trong HTML sau đó switch vào
        Alert alert = new WebDriverWait (driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Thread.sleep(5000);

        //Chờ cho alert xuất hiện trong HTML sau đó switch vào
        Alert alert = new WebDriverWait (driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String name = "Ngan";

        alert.sendKeys(name);

        Thread.sleep(2000);

        alert.accept();

        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + name);

    }
}
