package liveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;

public class Activity5 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser
        driver.get("https://alchemy.hguy.co/crm/");
        System.out.println("Able to launch the website successfully.");
    }


    @Test
    public void getColorNavMenu() {
        //Logging into the application
        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Reporter.log("Able to logged in to the website successfully.");
        System.out.println("Able to logged in to the website successfully.");
        wait.until(ExpectedConditions.urlContains("Home"));
        Reporter.log("<br> Home Page is displayed as expected.");
        System.out.println("Home Page is displayed as expected.");

        String navColor=driver.findElement(By.cssSelector("div#toolbar")).getCssValue("color");
        System.out.println("Color of Navigation Menu is : " +navColor);
        Reporter.log("<br> Color of Navigation Menu is : " +navColor);
        String hex = Color.fromString(navColor).asHex();
        System.out.println("Hex code for navbar color:"+hex);
        Reporter.log("<br> Hex code for navbar color : " +hex);
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
