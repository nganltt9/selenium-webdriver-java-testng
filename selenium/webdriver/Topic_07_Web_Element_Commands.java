package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_Commands {
    // Khai báo biến driver
    WebDriver driver;
    String firstname, lastname, address, city, searchPlaceholderValue;
    int price, productSize;

    // Chạy đầu tiên: để mở browser/ khởi tạo data test/ khởi tạo biến/..
    @BeforeTest
    public void initData() {
        // Khởi tạo cái browser lên (Mở browser)
        driver = new FirefoxDriver();

    }

    @Test
    // Testcase để thực thi
    public void TC_02_WebElementAPI() {
        // Tìm 1 element (nhiều) vs locator là gì

        // Cách 1: Nếu như mà element này chỉ dùng 1 lần
        driver.findElement(By.id("search")).sendKeys("Samsung");
        driver.findElement(By.id("search")).sendKeys("LG");

        // Cách 2: Nếu như mà element này thao tác nhiều lần -> Khai báo biến
        WebElement searchTextbox = driver.findElement(By.id("search"));

        // Xóa dữ liệu trước khi sendkey, biến được khai báo.
        searchTextbox.clear();

        // Nhập dữ liệu vào 1 textbox/ textarea; biến được khai báo.
        searchTextbox.sendKeys("");

        // Click vào 1 element: button/ link/ radio/ checkbox/..; biến được khai báo.
        searchTextbox.click();

        // Tìm và thao tác vs 1 element: findElement
        searchTextbox.findElement(By.id("search")).click();

        // Tìm và thao tác vs nhiều element: findElements
        searchTextbox.findElements(By.id("search")).get(0).click();

        // 0 1 2 3 4 5 -> index
        // A B C X Y Z -> data

        // tagname[@attribute='value'] = //input[@id='search']
        String searchPlaceholderValue = searchTextbox.getAttribute("placeholder");

        // Test GUI: font/ size/ color/ position/ size/....
        String loginButtonColor = searchTextbox.getCssValue("background");
        // #3399cc

        // Build framework: Chụp hình nhúng vào Report
        // searchTextbox.getScreenshotAs(arg0)

        // String searchTextboxTagname = searchTextbox_.getTagName();
        // searchTextboxTagname = input
        WebElement searchTextbox_ = driver.findElement(By.cssSelector("#search"));

        // Trả về text của 1 element: link/ button/ label/...
        String searchText = searchTextbox.getText();

        // assertTrue/ False
        Assert.assertTrue(searchTextbox.isDisplayed());
        Assert.assertTrue(searchTextbox.isEnabled());

        // Work vs Radio/ Checkbox
        Assert.assertTrue(searchTextbox.isSelected());

        boolean searchTextboxStatus = searchTextbox.isSelected();
        Assert.assertFalse(searchTextboxStatus);

        searchTextbox.click();
        // Work vs form (login/ register) -> Tagname = form
        // searchTextbox.submit();

    }

    // Chạy cuối cùng: đóng browser/ clean data/..
    @AfterTest
    public void cleanData() {
        // Đóng browser sau khi chạy xong all testcases
        driver.quit();
    }
}