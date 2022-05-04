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

public class Activity9 {
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
        driver.findElement(By.xpath("(//a[contains(@id,'Lead')])[1]")).click();
        Reporter.log("<br> Clicked on Leads Option ");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@title='Additional Details'])[20]")));
        // List<WebElement> firstColValues = driver.findElements(By.xpath("//table[contains(@class, 'table')]/tbody/tr/td[3]//a"));
        Reporter.log("<br> Printing values of the Name Column and User Column of the first 10 values of the table ");
        for(int i=1;i<=10;i++) {
            String name = driver.findElement(By.xpath("(//table[contains(@class, 'table')]/tbody/tr/td[3]//a)["+i+"]")).getText();
            String user = driver.findElement(By.xpath("(//table[contains(@class, 'table')]/tbody/tr/td[8]//a)["+i+"]")).getText();
            Reporter.log("<br> Row "+i+" : Name - "+name+"  | User - "+user);
            System.out.println("Row "+i+" : Name - "+name+"    | User - "+user);
        }
    }

    @AfterClass
    public void tearDown() {
        //Close the browser
        driver.close();
    }

}
