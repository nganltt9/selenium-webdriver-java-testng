package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_JS_Alert {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(3000);

        //Dùng alert nhiều lần thì khai báo biến
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");

//
//
//       //Accept Alert
//        driver.switchTo().alert().accept();
//
//        //Cancel Alert
//        driver.switchTo().alert().dismiss();
//
//        //Get text bên trong Alert (Description)
//        driver.switchTo().alert().getText();
//
//        //Nhập text vào Alert (Sendkeys)
//        driver.switchTo().alert().sendKeys();
//


    }

    @Test
    public void TC_02_Confirm_Alert() {

    }

    @Test
    public void TC_03_Prompt_Alert() {

    }
}
