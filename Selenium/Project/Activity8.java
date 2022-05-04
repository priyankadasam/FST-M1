package liveProject;

import org.openqa.selenium.Alert;
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
import java.util.List;

public class Activity8 {
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


    @Test
    public void traversingTable1() throws InterruptedException {
        //Logging into the application
        driver.findElement(By.id("user_name")).sendKeys("admin");
        driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd");
        driver.findElement(By.xpath("//input[@value='Log In']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Reporter.log("Able to logged in to the website successfully.");
        System.out.println("Able to logged in to the website successfully.");
        WebElement homeIcon=driver.findElement(By.xpath("//a[contains(@class,'home-icon')]"));
        wait.until(ExpectedConditions.visibilityOf(homeIcon));
        Reporter.log("<br> Home Page is displayed as expected.");
        System.out.println("Home Page is displayed as expected.");

        driver.findElement(By.xpath("//a[text()='Sales']")).click();
        Reporter.log("<br> Clicked on Sales Menu: ");
        driver.findElement(By.xpath("(//a[contains(@id,'Accounts')])[1]")).click();
        Reporter.log("<br> Clicked on Accounts Option ");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@title='Additional Details'])[20]")));
       // List<WebElement> firstColValues = driver.findElements(By.xpath("//table[contains(@class, 'table')]/tbody/tr/td[3]/b/a"));
        Reporter.log("<br> Printing names of the first 5 odd numbered rows of the table ");
        for(int i=1;i<=9;i=i+2) {
            String colValue = driver.findElement(By.xpath("(//table[contains(@class, 'table')]/tbody/tr/td[3]/b/a)["+i+"]")).getText();
            Reporter.log("<br> Name of the odd numbered row "+i+" is: "+colValue);
            System.out.println("Name of the odd numbered row "+i+" is: "+colValue);
        }
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
