package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Default_Dropdown {
    WebDriver driver;

    Select select;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        //Nhận driver là tham số thì sẽ được new ở BeforeClass: ví dụ như Actions; WebDriverWait; JavaScriptExecutor
        //Không new được với element vì element chưa xuất hiện
    }

    @Test
    public void TC_01_Facebook_SignUp() {
        driver.get("https://www.facebook.com/reg/");

        //Dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#day")));

        //Chọn item
        select.selectByVisibleText("16");

        //Verify item
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "16");

        //Verify xem ô select có phải là multiselect hay không
        //Assert.assertFalse(select.isMultiple());

        //Verify số lượng item trong dropdown này là bao nhiêu
        //Assert.assertEquals(select.getOptions().size(), 31);

        //Dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#month")));

        //Chọn item
        select.selectByVisibleText("Nov");

        //Verify item
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Nov");

        //Dropdown xuất hiện
        select = new Select(driver.findElement(By.cssSelector("select#year")));

        //Chọn item
        select.selectByVisibleText("1999");

        //Verify item
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "1999");

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    }