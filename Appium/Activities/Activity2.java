package activities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Activity2 {
    WebDriverWait wait;
    AndroidDriver<MobileElement> driver = null;

    @BeforeClass
    public void setup() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceID", "RZ8M52TY4RF");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testSearchAppium() {
        // Open page in browser
        driver.get("https://www.training-support.net/");
        System.out.println("Hi: " );
        // Wait for page to load
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId("About Us")));
        System.out.println("Hello " );
        // Get heading on page and print it
        String pageTitle = driver
                .findElementByXPath("//android.view.View[@text='Training Support']")
                .getText();
        System.out.println("Title on Homepage: " + pageTitle);

        // Find About Us button and click it
        MobileElement aboutButton = driver.findElementByXPath("//android.view.View[@content-desc='About Us']");
        aboutButton.click();

        // Wait for new page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                MobileBy.xpath("//android.view.View[@text='About Us']")
        ));

        // Get heading on About Us page and print it
        String newPageTitle = driver
                .findElementByXPath("//android.view.View[@text='About Us']")
                .getText();
        System.out.println("Title on new page: " + newPageTitle);

        // Assertions
        Assert.assertEquals(pageTitle, "Training Support");
        Assert.assertEquals(newPageTitle, "About Us");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
