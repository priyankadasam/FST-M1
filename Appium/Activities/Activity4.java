package activities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class Activity4 {
    // Declare Android driver
    static AndroidDriver<MobileElement> driver;
    WebDriverWait wait;
    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceID", "RZ8M52TY4RF");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.samsung.android.app.contacts");
        caps.setCapability("appActivity", "com.samsung.android.contacts.contactslist.PeopleActivity");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);
        wait = new WebDriverWait(driver, 10);

    }
    @Test
    public void addContact() throws IOException, InterruptedException {
        // Wait for app to load
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("Create contact")));

        // Click on add new contact floating button
        driver.findElementByAccessibilityId("Create contact").click();

        // Wait for fields to load
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("arrowButton")));
        driver.findElementById("arrowButton").click();


        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.EditText[@text='First name']")));

        // Find the first name, last name, and phone number fields
        MobileElement firstName = driver.findElementByXPath("//android.widget.EditText[@text='First name']");
        MobileElement lastName = driver.findElementByXPath("//android.widget.EditText[@text='Last name']");

        // Enter the text in the fields
        firstName.sendKeys("Aaditya");
        lastName.sendKeys("Varma");

        //Phone
        // Scroll to To-DO List
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
        // driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).scrollTextIntoView(\"To-Do List\")"));
        driver.findElement(MobileBy.AndroidUIAutomator(UiScrollable + ".scrollForward(3).scrollIntoView(text(\"Phone\"))"));
        Thread.sleep(5000);
        //MobileElement phone= driver.findElementByXPath("titleLayout");
        //MobileElement phone= driver.findElementByXPath("(//android.widget.RelativeLayout[contains(@id,'titleLayout')])[2]");
        driver.findElementsById("titleLayout").get(1).click();
        //phone.click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.EditText[@text='Phone']")));
        MobileElement phoneNumber = driver.findElementByXPath("//android.widget.EditText[@text='Phone']");

        phoneNumber.sendKeys("9991284782");
        takeScreenshot();
        // Save the contact
        driver.findElementById("smallLabel").click();

        // Wait for contact card to appear
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id("contact_info_card")));

        // Assertion
        MobileElement mobileCard = driver.findElementById("contact_info_card");
        Assert.assertTrue(mobileCard.isDisplayed());
        takeScreenshot();
        String contactName = driver.findElementById("header").getText();
        Assert.assertEquals(contactName, "Aaditya Varma");
    }
    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // This specifies the location the screenshot will be saved
        File screenShotName = new File("src/test/resources/Activity4_"+ Calendar.getInstance().getTimeInMillis()+".jpg");
        //File screenShotName = new File("src/test/resources/GoogleKeep.jpg");

        // This will copy the screenshot to the file created
        FileUtils.copyFile(scrShot, screenShotName);

        // Set filepath for image
        String filePath = "../" + screenShotName;

        // Set image HTML in the report
        String path = "<img src='" + filePath + "'/>";

        // Show image in report
        Reporter.log(path);
    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}
