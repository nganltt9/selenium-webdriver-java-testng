package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Action_P1 {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser() {
        //Selenium sẽ init  browser lên với config mặc định
        //Tham số để nhận diện đang chạy browser bằng automation test
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(driver.findElement(By.cssSelector("input#age")))
                        .pause(Duration.ofSeconds(3)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        Thread.sleep(5000);

        action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']")))
                .pause(Duration.ofSeconds(3)).perform();
        action.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']")))
                .pause(Duration.ofSeconds(3)).perform();
        action.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Gọt Bút Chì']"))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "GỌT BÚT CHÌ");
    }


}
