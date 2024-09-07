package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Default_Checkbox_RadioButton {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        //Verify checkbox/radio is enabled/disabled
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isEnabled());

        //Verify checkbox/radio is selected/deselected
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Towbar preparation']/preceding-sibling::span/input")).isSelected());

        //1-2. Click vào checkbox: Dual-zone air conditioning (Dùng hàm if) + verify selected
        By dualZoneAirBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        if (!driver.findElement(dualZoneAirBy).isSelected()) {
            driver.findElement(dualZoneAirBy).click();
        }

        Assert.assertTrue(driver.findElement(dualZoneAirBy).isSelected());

        //3-4. Sau khi checkbox đã được chọn thì bỏ chọn + verify deselected
        if (driver.findElement(dualZoneAirBy).isSelected()) {
            driver.findElement(dualZoneAirBy).click();
        }

        Assert.assertFalse(driver.findElement(dualZoneAirBy).isSelected());

        //5. Truy vập vào trang khác
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        //6. Click vào radio button and verify isSelected
        By petrolKw = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        if (!driver.findElement(petrolKw).isSelected()) {
            driver.findElement(petrolKw).click();
        }

        Assert.assertTrue(driver.findElement(petrolKw).isSelected());

    }

    @Test
    public void TC_02_Angular() {

        //1. Truy cập trang
        driver.get("https://material.angular.io/components/radio/examples");

        //2. CLick vào radio button Summer and verify isSelected

        if (!driver.findElement(By.xpath("//input[@value='Summer']")).isSelected()) {
            driver.findElement(By.xpath("//input[@value='Summer']")).click();
        }

        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());

        //3. Truy cập trang
        driver.get("https://material.angular.io/components/checkbox/examples");

        //4. Click checkbox + verify is Selected
        driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
        driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());

        //5. Deselected + verify is De selected
        driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).click();
        driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).click();

        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Checked']/preceding-sibling::div/input")).isSelected());
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input")).isSelected());

    }

    @Test
    public void TC_03_Multiple() {

        //1. Truy cập trang
        driver.get("https://automationfc.github.io/multiple-fields/");

        //Select all checkbox
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected())
                checkbox.click();
        }

        //Verify tất cả được selected
        for (WebElement checkbox : checkboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        //Deselect all checkboxes
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected())
                checkbox.click();
        }

        //Verify all checkbox deselected
        for (WebElement checkbox : checkboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }

        //Select 1 in all + verify

        //Cách 1
//        driver.findElement(By.xpath("//input[@value='Anemia']")).click();
//        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Anemia']")).isSelected());

        //Cách 2
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected() && checkbox.getAttribute("value").equals("Anemia"))
                checkbox.click();
        }
    }

        @AfterClass
        public void cleanBrowser () {
            driver.quit();
        }
    }