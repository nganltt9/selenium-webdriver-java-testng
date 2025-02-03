package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_17_Action_PII {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        action = new Actions(driver);
    }

    @Test
    public void TC_01_ClickAndHold_Fix() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Click chọn 1 số đầu tiên cần tìm (chưa nhả chuột trái)
        // Di chuột đến số cuối cùng
        // Nhả chuột trái ra

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        action.clickAndHold(numbers.get(4)) // Click và giữ chuột tại element 5
                .pause(Duration.ofSeconds(2))
                .moveToElement(numbers.get(11)) // Di chuột đến element 12
                .pause(Duration.ofSeconds(2))
                .release() // Nhả chuột ra
                .perform(); // Thực thi các câu lệnh trên

    }

    @Test
    public void TC_02_ClickAndHold_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Click chọn 1 số đầu tiên cần tìm (chưa nhả chuột trái)
        // Di chuột đến số cuối cùng
        // Nhả chuột trái ra

        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        String osName = System.getProperty("os.name");
        Keys keys = null;

        if (osName.contains("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        action.keyDown(keys).perform(); // Nhấn phím Control xuống

        action.click(numbers.get(2))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(5))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(11))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(13))
                .pause(Duration.ofSeconds(1))
                .click(numbers.get(19))
                .pause(Duration.ofSeconds(1))
                .perform();

        action.keyUp(keys).perform();

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numberSelected.size(), 5);
    }

    @Test
    public void TC_03_DoubleClick() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));

        // Scroll tới element bằng JS
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
        Thread.sleep(3000);

        action.doubleClick(doubleClickButton)
                .pause(Duration.ofSeconds(3))
                .perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_04_RightClick() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        // Verify Quit menu không hiện thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // Click chuột phải
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one")))
                .pause(Duration.ofSeconds(2))
                .perform();

        // Verify Quit menu hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        // Hover vào Quit menu
        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        // Verify Quit menu có sự kiện hover
        Assert.assertTrue(driver.findElement(
                        By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
                .isDisplayed());

        // Click vào Quit menu
        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        Thread.sleep(2000);

        // Accept Alert
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }
}
