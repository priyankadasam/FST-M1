package activities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Activity3 {
    // Declare Android driver
    static AndroidDriver<MobileElement> driver;

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
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
    public void add() throws IOException {
        driver.findElementById("calc_keypad_btn_05").click();
        driver.findElementById("calc_keypad_btn_add").click();
        driver.findElementById("calc_keypad_btn_09").click();
        // Perform Calculation
        driver.findElementById("calc_keypad_btn_equal").click();

        // Display Result
        String result = driver.findElementById("calc_edt_formula").getText();
        System.out.println(result);
        takeScreenshot();
        Assert.assertEquals(result, "14");
    }

    @Test
    public void subtract() throws IOException {
        driver.findElementById("calc_keypad_btn_01").click();
        driver.findElementById("calc_keypad_btn_00").click();
        driver.findElementById("calc_keypad_btn_sub").click();
        driver.findElementById("calc_keypad_btn_05").click();
        // Perform Calculation
        driver.findElementById("calc_keypad_btn_equal").click();

        // Display Result
        String result = driver.findElementById("calc_edt_formula").getText();
        System.out.println(result);
        takeScreenshot();
        Assert.assertEquals(result, "5");
    }

    @Test
    public void multiply() throws IOException {
        driver.findElementById("calc_keypad_btn_05").click();
        driver.findElementById("calc_keypad_btn_mul").click();
        driver.findElementById("calc_keypad_btn_01").click();
        driver.findElementById("calc_keypad_btn_00").click();
        driver.findElementById("calc_keypad_btn_00").click();
        // Perform Calculation
        driver.findElementById("calc_keypad_btn_equal").click();

        // Display Result
        String result = driver.findElementById("calc_edt_formula").getText();
        System.out.println(result);
        takeScreenshot();
        Assert.assertEquals(result, "500");
    }

    @Test
    public void divide() throws IOException {
        driver.findElementById("calc_keypad_btn_05").click();
        driver.findElementById("calc_keypad_btn_00").click();
        driver.findElementById("calc_keypad_btn_div").click();
        driver.findElementById("calc_keypad_btn_02").click();
        // Perform Calculation
        driver.findElementById("calc_keypad_btn_equal").click();

        // Display Result
        String result = driver.findElementById("calc_edt_formula").getText();
        System.out.println(result);
        takeScreenshot();
        Assert.assertEquals(result, "25");
    }


    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // This specifies the location the screenshot will be saved
        File screenShotName = new File("src/test/resources/screenshot_"+Calendar.getInstance().getTimeInMillis()+".jpg");
        //File screenShotName = new File("src/test/resources/screenshot.jpg");

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
