package liveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class Activity2 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser
        driver.get("https://alchemy.hguy.co/crm/");
        Reporter.log("Website Launched successfully");
    }

    @Test
    public void verifyURLofHeaderImage() {
        WebElement img = driver.findElement(By.xpath("//img[@alt='SuiteCRM']"));
        String src = img.getAttribute("src");
        System.out.println("URL of header image is: " + src);
        Reporter.log("<br> URL of header image is: " + src);
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
