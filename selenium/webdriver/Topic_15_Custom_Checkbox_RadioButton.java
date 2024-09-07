package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Custom_Checkbox_RadioButton {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ubuntu() {
        driver.get("https://login.ubuntu.com/");

        //1. Click vào radio button

        //Method - 1 - DÙNG THẺ INPUT ĐỂ SELECT
//        By newUserRadio = By.cssSelector("input#id_new_user");
//        driver.findElement(newUserRadio).click();
//        Assert.assertFalse(driver.findElement(newUserRadio).isSelected());

        //Method - 2 - DÙNG THẺ KHÁC INPUT ĐỂ SELECT
//        By newUserRadio = By.cssSelector("label.new-user");
//        driver.findElement(newUserRadio).click();
//        Assert.assertTrue(driver.findElement(newUserRadio).isSelected());

        //Method - 3 - DÙNG THẺ KHÁC INPUT ĐỂ SELECT VÀ DÙNG THẺ INPUT ĐỂ VERIFY SELECTED
        //Cách này không hay, 1 element không nên define thành nhiều locator; khi maintain code rất cực; gây hiểu lầm cái tên; khó cho 1 người mới join team
//        By newUserInput = By.cssSelector("input#id_new_user");
//        By newUserLabel = By.cssSelector("label.new-user");
//
//        driver.findElement(newUserLabel).click();
//        Assert.assertTrue(driver.findElement(newUserInput).isSelected());

        //Method 4 - KHÔNG DÙNG HÀM CLICK CỦA SELENIUM, DÙNG HÀM CLICK CỦA JAVASCRIPT ĐỂ CLICK
        By newUserInput = By.cssSelector("input#id_new_user");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(newUserInput));
        Assert.assertTrue(driver.findElement(newUserInput).isSelected());

        //Click vào checkbox


        By termCheckbox = By.cssSelector("input#id_accept_tos");

        jsExecutor.executeScript("arguments[0].click();", driver.findElement(termCheckbox));
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
    }

    @Test
    public void TC_02_Docs() {

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        //CHECKBOX, RADIO BUTTON không theo chuẩn chung là có thẻ input, được design bởi thẻ div > nên không verify được hàm isSelected để verify khi click

        //Chọn radio button

        By hcmRadio = By.xpath("//div[@aria-label='Hồ Chí Minh']");

        driver.findElement(hcmRadio).click();
        Assert.assertEquals(driver.findElement(hcmRadio).getAttribute("aria-checked"),"true");


        //Verify isSelected của button

    }

    @Test
    public void TC_03_() {
    }
}