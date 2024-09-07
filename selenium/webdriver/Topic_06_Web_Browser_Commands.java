package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Web_Browser_Commands {
    WebDriver driver;

    @Test
    public void TC_01_Run_On_Firefox() {
        // Run with browser (local)
        driver = new FirefoxDriver();

        // Run remote (grid/cloud testing/docker)


        // Networking (LAN/WAN/IP/PORT)
        driver.get("https://demo.nopcommerce.com/");
        driver.quit();
    }

    @Test
    public void TC_01() {

        // Mở ra 1 URL bất kỳ, url hợp lệ (lưu ý phải bắt đầu bằng http/https)
        driver.get("https://demo.nopcommerce.com/");

        // Đóng browser, đóng all các tab đang có
        driver.quit();

        // Đóng browser. đóng ở tab nào đang thao tác/đang đứng
        driver.close();

        //Lấy ra title của page hiện tại (tên của tab)
        // Mục đích của việc kiểm tra: những cái mình action có đang đúng hay không, xem kĩ AAA patern
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle, "Home page");

        //1 - Lưu dữ liệu hiện tại rồi kiểm tra sau
        homePageTitle = driver.getTitle();

        // Kiểm tra trực tiếp lun > nên dùng kiểu này, vì đỡ tốn bộ nhớ,Khi dữ liệu chỉ dùng 1 lần thì không nên khai báo biến, dùng 2 lần trở nên thì mới khai báo để tái sử dụng lại

        Assert.assertEquals(driver.getTitle(), "nopCommerce demo store");

        // Get ra cái url của cái page hiện tại
        String homePageUrl = driver.getCurrentUrl();

        // Verify 1 điều kiện đúng
        Assert.assertTrue(homePageUrl.equals("http://live.guru99.com"));

        // Verify 1 điều kiện KHÔNG đúng
        Assert.assertFalse(homePageUrl.equals("http://live.guru99.com/index.php/customer/account/"));

        // Verify 1 điều kiện đầu vào và đầu ra bằng nhau
        Assert.assertEquals(homePageUrl, "http://live.guru99.com");

        // assertTrue/False: Trả về kiểu boolean (true/ false) -> isDisplayed/
        // isEnabled/ isSelected/...
        // assertEquals: Trả về kiểu dữ liệu: String/ int/ double/ float/...

        //Lấy ra page Source Code
        String homepageSourceCode = driver.getPageSource();

        //Verify tương đối là true/false, còn equal là verify tuyệt đối
        Assert.assertTrue(homepageSourceCode.contains("Computers"));

        //Khi mình gọi 1 hàm nào đó ra để sử dụng thì cần kiểm tra xem mình đang đứng ở đâu

        //Lấy ra ID của tab/window đang active
        driver.getWindowHandle();

        //Lấy ra tất cả ID của tất cả các tab/window đang có
        driver.getWindowHandles();


        //2 hàm này là 1 cái cầu nối để từ cái browser mình tìm element để mình thao tác lên cái element đó
        //Đi tìm 1 element
        driver.findElement(By.xpath(""));


        //Đi tìm nhiều element
        driver.findElements(By.xpath(""));


        //Dùng để chờ cho việc tìm Element (findElement/findElements); nghĩa là sao: nghĩa là trong thời gian tìm element nếu k set implicitwait thì tìm k thấy nó fail ngay, nhưng khi set như kia thì sau 15s nó mới báo fail
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Dùng để chờ cho việc page được load xong, ít áp dụng thực tế
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        //Dùng để chờ cho 1 đoạn script được thực thi xong (script là gì: code js để nó thực thi); cũng ít áp dụng thực tế
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        //Thu nhỏ về taskbar để chạy
        driver.manage().window().minimize();

        //Phóng to lên (vẫn còn task bar)
        driver.manage().window().maximize();

        //Tràn màn hình (không có task bar)
        driver.manage().window().fullscreen();

        //Responsive
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().getSize();


        //Xem vị trí của browser đang ở đâu
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().getPosition();

        //Test GUI, giao diện, ít khi dùng cho test Auto

        // driver.navigate().back/refresh/forwar();

        // Tracking lại history
        // driver.navigate().to("http://live.guru99.com");

        // Alert/ Iframe (Frame)/ Windows
        driver.switchTo().alert().accept();
        driver.switchTo().frame("");
        driver.switchTo().window("");


    }
}