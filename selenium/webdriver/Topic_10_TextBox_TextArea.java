package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_10_TextBox_TextArea {
    WebDriver driver;
    Random rand;
    String firstName, lastName, emailAddress, password, fullName;

    @BeforeTest
    public void initialBrowser() {
        driver = new FirefoxDriver();
        rand = new Random();

        firstName = "Testing";
        lastName = "Automation";
        fullName = firstName + "" + lastName;
        emailAddress = "testingautomation" + rand.nextInt(99999) + "@gmail.com";
        password = "123456";

    }


    @Test
    public void TC_01_() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        driver.findElement(By.cssSelector("li.success-msg span")).getText();

        //Tuyệt đối
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");

        String contactInformationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        System.out.println(contactInformationText);

        //Tương đối
        Assert.assertTrue(contactInformationText.contains(firstName) && contactInformationText.contains(lastName));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);

       //Product Review
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//h2/a[@title='Samsung Galaxy']")).click();

        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.xpath("//input[@id='Quality 1_4']")).click();

        driver.findElement(By.cssSelector("textarea.required-entry")).sendKeys("Hello\n" +
                "My name is Ngan");

        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Good one");

        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("Ngan Le");

        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Your review has been accepted for moderation.']")).getText(), "Your review has been accepted for moderation.");

        //Logout
        driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();

        driver.findElement(By.xpath("//a[text()='Log Out']")).click();

        Thread.sleep(6000);

        Assert.assertEquals(driver.getCurrentUrl(), "");

    }
}