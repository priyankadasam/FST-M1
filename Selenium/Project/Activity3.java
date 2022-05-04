package liveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class Activity3 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser
        driver.get("https://alchemy.hguy.co/crm/");

    }

    @Test
    public void verifyCopyRightText() {
        String copyRighttext = driver.findElement(By.id("admin_options")).getText();
        Reporter.log("Website launched successfully ");
        System.out.println("CopyRight Text in footer is: " + copyRighttext);
        Reporter.log("<br> CopyRight Text in footer is: : " + copyRighttext);
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
