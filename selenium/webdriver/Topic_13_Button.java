package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Button {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Fahasa_Demo() {
        driver.get("https://www.fahasa.com/customer/account/create");

        //1 - Navigate qua tab đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        //2 - Verify button Đăng nhập là button disabled
        By loginButton = By.cssSelector("button.fhs-btn-login");

        //isEnabled: nếu element mà enable thì trả về true; disable thì trả về false
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

//        //3 - Verify button Đăng nhập có background là màu xám: trước tiên cần get dữ liệu của thuộc tính màu background ra;
//        //3.1 - Tìm element
//        WebElement loginButtonElement = driver.findElement(loginButton);
//        //3.2 - GetCSS value của element (lúc này kiểu dữ liệu của nó đang là string RGBA)
//        String loginButtonRGBA = driver.findElement(loginButton).getCssValue("background-color");
//        //3.3 - Chuyển dữ liệu sang kiểu dữ liệu Color
//        Color loginButtonColor = Color.fromString(loginButtonRGBA);
//        //3.4 - Chuyển sang kiểu hexa
//        String loginButtonHexa = loginButtonColor.asHex();
//        //3.5 - Hexa uppercase
//        String loginButtonHexaUpperCase = loginButtonHexa.toUpperCase();

//        //3. - RÚT GỌN CODE thành 3 dòng
//        String loginBackgroundColor = driver.findElement(loginButton).getCssValue("background-color");
//        Color loginColor = Color.fromString(loginBackgroundColor);
//        Assert.assertEquals(loginColor.asHex().toUpperCase(), "#000000");

        //3. - RÚT GỌN CODE thành 1 dòng
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

        //4 - Nhập dữ liệu vào Email; Mật khẩu
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("nganlee169@gmail.com");

        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        //5 - Verify button Đăng nhập enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        //6 - Verify button Đăng nhập có background là màu đỏ
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

        //7- Verify text Đăng nhập trong button Đăng nhập
        Assert.assertEquals(driver.findElement(loginButton).getText(),"Đăng nhập");

        //8 - Verify thông báo lỗi hiển thị
        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(),"Số điện thoại/Email hoặc Mật khẩu sai!");

    }

    @Test
    public void TC_02_Go_Con_Sensus() {
        driver.get("https://play.goconsensus.com/u5d5156df");

        //Verify button Continue là button disabled

        //Verify button Continue có background là tím nhạt

        //Nhập dữ liệu vào các trường input: firstname, last name, email, confirm email, phone, organization, country

        //Verify button Continue enabled

        //Verify button Continue có background là màu đỏ
    }
}