package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;

public class Topic_18_Fixed_Popup {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        action = new Actions(driver);

    }

    @Test
    public void TC_01_NgoaiNgu24h_Fixed_Not_Found_In_DOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        By loginDialog = By.cssSelector("div#custom-dialog div[role='dialog']");

        // Kiểm tra Popup hiển thị
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());

        driver.findElement(By.cssSelector("input[placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.name("password")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[@id='custom-dialog']//button[text()='Đăng nhập']")).click();



        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        // Close popup
        driver.findElement(By.cssSelector("div#custom-dialog h2>button")).click();
        Thread.sleep(3000);

        // Kiểm tra Popup không hiển thị
        Assert.assertEquals(driver.findElements(loginDialog).size(), 0);
    }

    @Test
    public void TC_01_ZingPoll_Fixed_In_DOM() throws InterruptedException {
        driver.get("https://www.zingpoll.com");

        driver.findElement(By.cssSelector("a#Loginform")).click();
        Thread.sleep(2000);

        By loginPopup = By.cssSelector("div#Login div.modal-dialog");

        // Kiểm tra Popup login hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // Close popup
        driver.findElement(By.cssSelector("div#Login div.modal-dialog button.close")).click();
        Thread.sleep(2000);

        // Kiểm tra Popup login không hiển thị
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
    }
    @Test
    public void TC_03_Tiki_Fixed_Not_Found_In_DOM() throws InterruptedException {
        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);

        By loginPopup = By.cssSelector("div.ReactModalPortal div[role='dialog']");

        // Kiểm tra Popup Login hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        // Đóng Popup
        driver.findElement(By.cssSelector("div.ReactModalPortal button.btn-close")).click();
        Thread.sleep(2000);

        // Kiểm tra Popup Login không còn hiển thị
        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}

