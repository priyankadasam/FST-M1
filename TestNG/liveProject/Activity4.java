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

public class Activity4 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser
        driver.get("https://alchemy.hguy.co/crm/");
        System.out.println("Able to launch the website successfully.");
        Reporter.log("Able to launch the website successfully.");
    }



    //@Parameters({"username", "password"})
    //public void loginTestCase(String username, String password) {
    @Test
    public void loginTestCase() {

            //Find username and password fields
        WebElement usernameField = driver.findElement(By.id("user_name"));
        WebElement passwordField = driver.findElement(By.id("username_password"));

        //Enter values
       /* usernameField.sendKeys(username);
        passwordField.sendKeys(password);*/
        usernameField.sendKeys("admin");
        passwordField.sendKeys("pa$$w0rd");
        Reporter.log("<br> Entered login credentials");
        //Click Log in
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Reporter.log("Able to logged in to the website successfully.");
        System.out.println("Able to logged in to the website successfully.");
        WebElement homeIcon=driver.findElement(By.xpath("//a[contains(@class,'home-icon')]"));
        wait.until(ExpectedConditions.visibilityOf(homeIcon));
        Assert.assertTrue(homeIcon.isDisplayed());
        Reporter.log("<br> Home Page is displayed as expected.");
        System.out.println("Home Page is displayed as expected.");
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
