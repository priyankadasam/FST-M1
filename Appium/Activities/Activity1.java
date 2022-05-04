package activities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class Activity1 {
    // Declare Android driver
    AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceID", "RZ8M52TY4RF");
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        caps.setCapability("appActivity", ".Calculator");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);

    }

    @Test
    public void multiplyWithCalc() {
        //Find Number 5

        MobileElement digit5= driver.findElementById("calc_keypad_btn_05");
        //Tap number 5
        digit5.click();
        //Tap multiply
        driver.findElementById("calc_keypad_btn_mul").click();

        //Tap number 5 again
        digit5.click();
        //Tap equal to
        driver.findElementByAccessibilityId("Equal").click();
        //Printing Result
        String result= driver.findElementById("calc_edt_formula").getText();
        Assert.assertEquals(result,"25");
        System.out.println("Result is : "+result);
        Reporter.log("Result is : "+result);


    }

    @AfterClass
    public void tearDown() {
        // Close app
        driver.quit();
    }

}

