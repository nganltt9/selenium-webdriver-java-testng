package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {

    //Khai báo
    WebDriver driver;

    //Khởi tạo biến driver lên
    String fullName = "Selenium Locator";

    @BeforeClass
    public void initialBrowser(){
        //Khởi tạo biến driver lên
        driver = new FirefoxDriver();
        //Mở app lên đến màn hình register
        driver.get("https://demo.nopcommerce.com/register");

    }
    @Test
    public void TC_01_ID(){
        driver.findElement(By.id("small-searchterms"));
    }
    @Test
    public void TC_02_Class(){
        //Không lấy hết tòan bộ gía trị khi có khoảng trắng
        driver.findElement(By.className("search-box-button"));
    }

    @Test
    public void TC_03_Name(){
        driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_LinkText(){
        //Chỉ làm việc với element và link
        //
        driver.findElement(By.linkText("Search"));
    }
    @Test
    public void TC_05_Partial_Link_Text(){
        driver.findElement(By.partialLinkText("Search"));
    }
    @Test
    public void TC_06_TagName(){
        //Tên thẻ (HTML)
        //Mục đích sử dụng: tìm tất cả các element giống nhau: ví dụ tìm all các button; checkbox;...
        //Hiểu // + tên thẻ: thì xược xược là tên thẻ
        //tìm này là tìm nhiều
        driver.findElement(By.tagName("button"));
        driver.findElement(By.tagName("input"));
        driver.findElement(By.tagName("a"));
        driver.findElement(By.tagName("form"));
    }
    @Test
    public void TC_07_CSS(){
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));
        driver.findElement(By.cssSelector("input[id='Company']"));
    }
    @Test
    public void TC_08_XPath(){
        driver.findElement(By.partialLinkText("Search"));
    }
    @Test
    public void TC_09_Relative_Locator(){
        //Element/By A
        By lastNameTextboxBy = By.cssSelector("input#LastName");
        WebElement lastNameTextbox = driver.findElement(By.cssSelector("input#LastName"));

        //Element/By B
        By eMailTextboxBy = By.cssSelector("input#Email");

        //Element/By C
        By DaySeLectBy = By.cssSelector("select[name='DateOfBirthDay']");

        //Element/By D
        By YearSeLectBy = By.cssSelector("select[name='DateOfBirthYear']");

        //Element/By E
        WebElement MonthSelect = driver.findElement(RelativeLocator.with(By.tagName("select"))
                .above(eMailTextboxBy)
                .below(lastNameTextboxBy)
                .toRightOf(DaySeLectBy)
                .toLeftOf(YearSeLectBy)
        );

        driver.findElement(By.xpath("//button[@class='button-1 search-box-button']"));

        //1. Duy nhất

        //2. Ưu tiên nếu có id/class/name thì dùng trước

        //3. Không có id/class/name thì dùng cái khác

        //4. Giá trị của attribute phải có ý nghĩa - liên quan tới cái element đó

        // > lấy thằng nào tối ưu nhất để dùng
    }

}
