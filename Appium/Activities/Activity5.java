package activities;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IExpectedExceptionsHolder;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
public class Activity5 {
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
        caps.setCapability("appPackage", "com.samsung.android.messaging");
        caps.setCapability("appActivity", "com.android.mms.ui.ConversationComposer");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);
        wait = new WebDriverWait(driver, 10);

    }
    @Test
    public void smsTest() throws IOException, InterruptedException {
        // Wait for elements to load
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("description(\"Compose new message\")")));
        // Locate the button to write a new message and click it
        driver.findElement(MobileBy.AndroidUIAutomator("description(\"Compose new message\")")).click();

        // Enter the number to send message to
        String contactBoxLocator = "resourceId(\"com.samsung.android.messaging:id/recipients_editor_to\")";

        // Enter your own phone number
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator(contactBoxLocator)));
        driver.findElement(MobileBy.AndroidUIAutomator(contactBoxLocator)).sendKeys("9441880246");
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));

        // Wait for message box to load
       // wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AndroidUIAutomator("resourceId(\"com.google.android.apps.messaging:id/message_edit_text\")")));
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("message_edit_text")));
        // Type in a message
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.samsung.android.messaging:id/message_edit_text\")")).sendKeys("Hello from Appium");

        // Send the message
       // driver.findElement(MobileBy.AndroidUIAutomator("description(\"Send SMS\")")).click();
        driver.findElement(MobileBy.AndroidUIAutomator("resourceId(\"com.samsung.android.messaging:id/send_button2_container\")")).click();

        // Wait for message to show
        String messageLocator = "resourceId(\"com.samsung.android.messaging:id/content_text_view\")";
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AndroidUIAutomator(messageLocator)));

        // Assertion
        String sentMessageText = driver.findElement(MobileBy.AndroidUIAutomator(messageLocator)).getText();
        Assert.assertEquals(sentMessageText, "Hello from Appium");
    }
    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // This specifies the location the screenshot will be saved
        File screenShotName = new File("src/test/resources/Activity5_"+ Calendar.getInstance().getTimeInMillis()+".jpg");
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
