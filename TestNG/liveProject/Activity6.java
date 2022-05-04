package liveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.time.Duration;

public class Activity6 {
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
    public void checkingMenu() throws InterruptedException {
        //Logging into the application
        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Reporter.log("Able to logged in to the website successfully.");
        System.out.println("Able to logged in to the website successfully.");
        WebElement navMenu=driver.findElement(By.xpath("//div[@id='toolbar']"));
        wait.until(ExpectedConditions.visibilityOf(navMenu));
        Reporter.log("<br>Navigation menu is displayed as expected.");
        System.out.println("Navigation menu is displayed as expected.");

        WebElement activityMenu=driver.findElement(By.xpath("//a[text()='Activities']"));
        wait.until(ExpectedConditions.elementToBeClickable(activityMenu));
        Assert.assertTrue(activityMenu.isDisplayed());
        System.out.println("Activity Menu item is displayed and is clickable");
        Reporter.log("<br> Activity Menu item is displayed and is clickable");
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
