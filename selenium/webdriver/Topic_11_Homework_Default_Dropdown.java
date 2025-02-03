package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Topic_11_Homework_Default_Dropdown {
    WebDriver driver;

    Random rand;

    String firstName, lastName, emailAddress, password, fullName, companyName;

    Select select;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        rand = new Random();

        firstName = "Testing";
        lastName = "Automation";
        emailAddress = "testingautomation" + rand.nextInt(99999) + "@gmail.com";
        password = "123456";
        companyName = "ABC TNHH 1 THANH VIEN";
    }

    @Test
    public void TC_01_NopCommerce_SignUp() {
        driver.get("https://demo.nopcommerce.com/register");

        //Click register link trên header
        driver.findElement(By.cssSelector("a.ico-register")).click();

        //Điền thông tin vào form đăng ký
        //Chọn Gender
        //driver.findElement(By.cssSelector("input#gender-female")).click();

        //Nhập firstname
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);

        //Nhập lastname
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        //Chọn các giá trị trong dropdown Date of Birth; không cần khai báo biến bởi vì chỉ chọn 1 lần ở màn đăng ký, các màn sau chỉ verify thôi
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText("1");

        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText("May");

        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText("1980");


        //Nhập Email
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);

        //Nhập Email
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);

        //Nhập Password
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);

        //Verify Password
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        //Click Register
        driver.findElement(By.cssSelector("button#register-button")).click();

        //Verify vào trang Homepage sau khi đã đăng kí thành công
        //Sửa lại syntax của xpath, lấy cả thẻ cha , dùng cú pháp css
        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(),"Your registration completed");

        //Click vào trang MyAccount để kiểm tra ngày/tháng/năm nhập vào là đúng
        driver.findElement(By.cssSelector("a.ico-account")).click();

       //Verify các thông tin đã đăng ký. trừ gender, cái này chưa học
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), "1");
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), "May");
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), "1980");

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);

    }

    @Test
    public void TC_02_Rode_Search(){
        driver.get("https://www.rode.com/wheretobuy");

        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");
        driver.findElement(By.xpath("//button[text()='Search']")).click();

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div//h4"));
        Assert.assertEquals(dealers.size(), 16);

        for (WebElement element : dealers){
            System.out.println(element.getText());
        }
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    }