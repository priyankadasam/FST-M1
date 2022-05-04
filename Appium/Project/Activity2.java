package liveProject;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
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

public class Activity2 {
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
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity", ".activities.BrowseActivity");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void addNoteUsingGoogleKeep() throws IOException {
        driver.findElementByAccessibilityId("New text note").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("edit_note_text")));
        driver.findElementById("edit_note_text").sendKeys("My First Note");
        driver.findElementById("editable_title").click();
        driver.findElementById("editable_title").sendKeys("Title Note");
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        Reporter.log("Added Title and notes successfully");

        Assert.assertEquals(driver.findElementById("index_note_title").getText(),"Title Note");
        Reporter.log("Title of the note is displayed as expected: "+driver.findElementById("index_note_title").getText());
        System.out.println("Title of the note is displayed as expected: "+driver.findElementById("index_note_title").getText());
        Assert.assertEquals(driver.findElementById("index_note_text_description").getText(),"My First Note");
        Reporter.log("<br> Description of the note is displayed as expected: "+driver.findElementById("index_note_text_description").getText());
        System.out.println("Description of the note is displayed as expected: "+driver.findElementById("index_note_text_description").getText());
        takeScreenshot();
    }

    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // This specifies the location the screenshot will be saved
        // File screenShotName = new File("src/test/resources/screenshot_"+ Calendar.getInstance().getTimeInMillis()+".jpg");
        File screenShotName = new File("src/test/resources/GoogleKeep.jpg");

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
