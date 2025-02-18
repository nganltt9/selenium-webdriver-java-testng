package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_20_iFrame {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Map() {
        driver.get("https://www.embedgooglemap.net/");

        // Switch vào iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.gmap_canvas>iframe")));

        String addressName = driver.findElement(By.cssSelector("div.place-name")).getText();
        System.out.println(addressName);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}

