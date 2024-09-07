package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser(){
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_() throws InterruptedException {

        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        selectItemInCustomDropdown("span#speed-button", "ul#speed-menu>li>div", "Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button")).getText(), "Fast");

        selectItemInCustomDropdown("span#files-button", "ul#files-menu>li>div", "jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button")).getText(), "jQuery.js");

        selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "1");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button")).getText(), "1");

        selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu>li>div", "Mr.");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button")).getText(), "Mr.");

    }

    //Dự án thực tế: 1 hàm dùng thao tác cho 1 site
    //Không dùng nhiều cho các application khác nhau
    //Cơ chế của dropdown giống nhau 

    private void selectItemInCustomDropdown(String parentCSS, String childCSS, String textItem) throws InterruptedException {
        //Hành vi thao tác lên dropdown
        //1 - Chờ cho dropdown có thể thao tác lên được (clickable)
        //2 - Click vào element để nó xổ ra cái dropdown
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCSS))).click();

        Thread.sleep(2000);

        //3 - Chờ cho all item được load ra (vì có thể có rất nhiều item bên trong) (presence)
        //4 - Tìm list các item đúng với mong đợi trong dropdown
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCSS)));

        for (WebElement item: allItems){
            if (item.getText().equals(textItem)){
                //5 - Click chọn item
                item.click();
                break;
            }
        }
    }
}