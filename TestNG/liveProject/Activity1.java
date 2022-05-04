package liveProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class Activity1 {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        //Create a new instance of the Firefox driver
        driver = new FirefoxDriver();

        //Open the browser
        driver.get("https://alchemy.hguy.co/crm/");
    }


    @Test(enabled = false)
    @Parameters({"username", "password"})
    public void loginTestCase(String username, String password) {
        //Find username and password fields
        WebElement usernameField = driver.findElement(By.id("user_name"));
        WebElement passwordField = driver.findElement(By.id("username_password"));

        //Enter values
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        //Click Log in
        driver.findElement(By.xpath("//input[@value='Log In']")).click();

    }

    @Test
    public void verifyTitleOfThePage() {
        String title = driver.getTitle();
        Assert.assertEquals(title, "SuiteCRM");
        System.out.println("Page title is: " + title);
        Reporter.log("Logged in and verified page title successfully");
        Reporter.log("<br> Page title is:" + title);
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
