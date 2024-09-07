package webdriver;

import org.bouncycastle.jce.exception.ExtCertPathBuilderException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_02_XPath_Css {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser(){
        //Arrange
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void Register_01_Emty_Data(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Action
        driver.findElement(By.id("txtFirstname-error")).getText();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");


    }

    @Test
    public void Register_02_Invalid_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("ngan le");
        driver.findElement(By.id("txtEmail")).sendKeys("@ngan111");
        driver.findElement(By.id("txtCEmail")).sendKeys("@ngan111");
//        driver.findElement(By.id("txtPassword")).sendKeys("123456");
//        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
//        driver.findElement(By.id("txtPhone")).sendKeys("0356956132");

        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_03_Incorrect_Confirm_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("ngan le");
        driver.findElement(By.id("txtEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ngan99@gmail.co");
//        driver.findElement(By.id("txtPassword")).sendKeys("123456");
//        driver.findElement(By.id("txtCPassword")).sendKeys("123456");
//        driver.findElement(By.id("txtPhone")).sendKeys("0356956132");

        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");

    }

    @Test
    public void Register_04_Invalid_Password(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("ngan le");
        driver.findElement(By.id("txtEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("12345");

        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();


        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");


    }

    @Test
    public void Register_05_Incorrect_Confirm_Password(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("ngan le");
        driver.findElement(By.id("txtEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123465");


        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();


        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

    }

    @Test
    public void Register_06_Invalid_Phone_Number(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("ngan le");
        driver.findElement(By.id("txtEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("ngan99@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456");

        //< 10 numbers
        driver.findElement(By.id("txtPhone")).sendKeys("096956132");
        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // > 11 numbers
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("096956132123456");

        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

        // Đầu số không hợp lệ
        driver.findElement(By.id("txtPhone")).clear();

        driver.findElement(By.id("txtPhone")).sendKeys("56132123456");

        //...
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Assert
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
}
