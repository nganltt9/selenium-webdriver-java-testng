package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

public class Topic_17_Action_PIII {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    String dragDropFilePath = System.getProperty("user.dir") + "/dragDrop/drag_and_drop_helper.js";

    @BeforeClass
    public void initialBrowser() {
//        driver = new EdgeDriver();
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_02_Drag_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        // Trước khi drag and drop
        Assert.assertEquals(targetCircle.getText(), "Drag the small circle here.");

        // Drag and drop
        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(3000);

        // Sau khi drag and drop
        Assert.assertEquals(targetCircle.getText(), "You did great!");

        Assert.assertEquals(Color.fromString(targetCircle
                .getCssValue("background-color")).asHex().toUpperCase(), "#03A9F4");
    }

    @Test
    public void TC_03_Drag_Drop_HTML5_Selenium_4x() throws InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement sourceColumn = driver.findElement(By.cssSelector("div#column-a"));
        WebElement targetColumn = driver.findElement(By.cssSelector("div#column-b"));

        // Drag and drop
        action.dragAndDrop(sourceColumn, targetColumn).perform();
        Thread.sleep(3000);

        // Sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        // Drag and drop
        action.dragAndDrop(sourceColumn, targetColumn).perform();
        Thread.sleep(3000);

        // Sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
    }

    @Test
    public void TC_03_Drag_Drop_HTML5_JQuery() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jqueryContent = getContentFile("dragDropFilePath");

        String sourceCss = "div#column-a";
        String targetCss = "div#column-b";

        // Drag and drop
        jqueryContent = jqueryContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);

        // Sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        // Drag and drop
        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);

        // Sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
    }


    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }
}

