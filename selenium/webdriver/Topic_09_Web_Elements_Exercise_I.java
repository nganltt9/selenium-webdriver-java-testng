package webdriver;

import graphql.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_Web_Elements_Exercise_I {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Displayed() {

        //isDisplayed: kiểm tra bất kì 1 element nào có hiển thị hay không
        // Hiển thị: có thể nhìn thấy - có kích thước cụ thể
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextbox.isDisplayed()) {

            System.out.println("Email Textbox is displayed");
            emailTextbox.sendKeys("Automation Testing");

        } else {
            System.out.println("Email Textbox is not displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));

        if (ageUnder18Radio.isDisplayed()) {
            System.out.println("Age Under 18 Radio is displayed");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age Under 18 Radio is not displayed");
        }

        WebElement educationTextarea = driver.findElement(By.cssSelector("textarea#edu"));

        if (educationTextarea.isDisplayed()) {

            System.out.println("Education Textarea is displayed");
            educationTextarea.sendKeys("Automation Testing Class 30");
        } else {
            System.out.println("Education Textarea is not displayed");
        }

        WebElement nameUser05 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));

        if (nameUser05.isDisplayed()) {

            System.out.println("Name user 05 is displayed");
        } else {
            System.out.println("Name user 05 is not displayed");
        }

    }

    @Test
    public void TC_02_Enabled() {

        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#mail"));

        if (emailTextbox.isEnabled()) {

            System.out.println("Email Textbox is enable");

        } else {
            System.out.println("Email Textbox is disable");
        }

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));

        if (ageUnder18Radio.isDisplayed()) {
            System.out.println("Age Under 18 Radio is displayed");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age Under 18 Radio is not displayed");
        }

        if (ageUnder18Radio.isEnabled()) {
            System.out.println("Age Under 18 Radio is enable");
        } else {
            System.out.println("Age Under 18 Radio is disable");
        }

        WebElement passWordTextBox = driver.findElement(By.cssSelector("input#disable_password"));

        if (passWordTextBox.isEnabled()) {
            System.out.println("Password Textbox is enabled");
        } else {
            System.out.println("Password Textbox is disabled");
        }

        WebElement biographyTextarea = driver.findElement(By.cssSelector("textarea#bio"));

        if (biographyTextarea.isEnabled()) {
            System.out.println("Biography Textarea is enabled");
        } else {
            System.out.println("Biography Textarea is disabled");
        }

    }

    @Test
    public void TC_03_Selected() {
        //isSelected: hàm dùng để kiểm tra xem 1 element được chọn thành công (Radio/Checkbox/Dropdown)

        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));

        if (ageUnder18Radio.isSelected()) {
            System.out.println("Age Under 18 Radio is selected");
            ageUnder18Radio.click();
        } else {
            System.out.println("Age Under 18 Radio is de-selected");
        }

        WebElement languageJavaCheckbox = driver.findElement(By.cssSelector("input#java"));

        if (languageJavaCheckbox.isSelected()) {
            System.out.println("Language Java Checkbox is selected");
            languageJavaCheckbox.click();
        } else {
            System.out.println("Language Java Checkbox is de-selected");
        }

        languageJavaCheckbox.isSelected();
        ageUnder18Radio.click();


    }

    @Test
    public void TC_04_MailChimp_Register_Validate() throws InterruptedException {

        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.cssSelector("input#email")).sendKeys("nganleee169@gmail.com");
        driver.findElement(By.cssSelector("input#email")).sendKeys(Keys.TAB);


        //Only number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Only lowercase character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("nganleee");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        //Full
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("ngan99Le!");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Thread.sleep(3000);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();

    }

}
