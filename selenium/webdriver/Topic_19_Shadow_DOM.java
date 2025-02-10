package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Shadow_DOM {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Github() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        // Element không nằm trong Shadow
        String scrollText = driver.findElement(By.xpath("//a[@href='scroll.html']")).getText();
        System.out.println(scrollText);

        // Tìm ra element chứa Shadow đầu tiên
        WebElement firstShadowHostElement = driver.findElement(By.xpath("//div[@id='shadow_host']"));

        // Lấy ra cái Shadow đầu tiên
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        // Element nằm trong Shadow
        String shadowText = firstShadowRoot.findElement(By.cssSelector("a")).getText();
        System.out.println(shadowText);

        firstShadowRoot.findElement(By.cssSelector("input[type='text']")).sendKeys("Selenium");

        //Lấy thông tin trong phần tử khác của Shadow
        System.out.println(firstShadowRoot.findElement(By.cssSelector("span#shadow_content>span")).getText());
    }

    @Test
    public void TC_02_Appspot() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(5000);

        //Tìm phần tử chứa shadow DOM (là shadow host)
        WebElement firstShadowHostElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        //Truy cập vào shadow DOM
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();


        //Lấy phần tử bên trong Shadow DOM
        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(2000);

        //Tiếp tục tìm phần tử chứa shadow DOM
        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("app-toolbar>book-input-decorator"));

        //Truy cập vào shadow dom
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();

        //Lấy phần tử bên trong shadow dom
        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        //Tiếp tục tìm phần tử chứa shadow DOM
        WebElement thirdShadowHostElement = firstShadowRoot.findElement(By.cssSelector("main.main-content>book-explore"));
        //Truy cập vào shadow DOM
        SearchContext thirdShadowRoot = thirdShadowHostElement.getShadowRoot();

        //Lấy phần tử bên trong Shadow DOM
        List<WebElement> forthShadowHostElements = thirdShadowRoot.findElements(By.cssSelector("ul.books>li>book-item"));
        for (WebElement element : forthShadowHostElements) {
            SearchContext shadowRoot = element.getShadowRoot();
            System.out.println(shadowRoot.findElement(By.cssSelector("div.title-container>h2")).getText());
        }
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}

