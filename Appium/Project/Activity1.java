package liveProject;

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
import java.util.List;

public class Activity1 {
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
        caps.setCapability("appPackage", "com.google.android.apps.tasks");
        caps.setCapability("appActivity", ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);
        wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void addingGoogleTasks() throws IOException {
        /*String task1="Complete Activity with Google Tasks";
        String task2="Complete Activity with Google Keep";
        String task3="Complete the second Activity Google Keep";*/

        String[] tasks = {"Complete Activity with Google Tasks", "Complete Activity with Google Keep", "Complete the second Activity Google Keep"};

        //Adding Tasks
        for (String task : tasks) {
            driver.findElementByAccessibilityId("Create new task").click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add_task_title")));
            driver.findElementById("add_task_title").sendKeys(task);
            driver.findElementById("add_task_done").click();
        }



        /*driver.findElementByAccessibilityId("Create new task").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_task_title")));
        driver.findElementById("add_task_title").sendKeys(task1);
        driver.findElementById("add_task_done").click();


        driver.findElementByAccessibilityId("Create new task").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_task_title")));
        driver.findElementById("add_task_title").sendKeys(task2);
        driver.findElementById("add_task_done").click();


        driver.findElementByAccessibilityId("Create new task").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("add_task_title")));
        driver.findElementById("add_task_title").sendKeys(task3);
        driver.findElementById("add_task_done").click();*/

        //Tasks verification
        List<MobileElement> tasksAdded=driver.findElementsById("task_name");
        Assert.assertEquals(tasksAdded.get(2).getText(),tasks[0]);
        Reporter.log("Task 1 added successfully. Task1- "+tasksAdded.get(2).getText());
        System.out.println("Task 1 added successfully. Task1- "+tasksAdded.get(2).getText());
        Assert.assertEquals(tasksAdded.get(1).getText(),tasks[1]);
        Reporter.log("<br>Task 2 added successfully. Task2- "+tasksAdded.get(1).getText());
        System.out.println("Task 2 added successfully. Task2- "+tasksAdded.get(1).getText());
        Assert.assertEquals(tasksAdded.get(0).getText(),tasks[2]);
        Reporter.log("<br>Task 3 added successfully. Task3- "+tasksAdded.get(0).getText());
        System.out.println("Task 3 added successfully. Task3- "+tasksAdded.get(0).getText());
        takeScreenshot();
    }


    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // This specifies the location the screenshot will be saved
       // File screenShotName = new File("src/test/resources/screenshot_"+ Calendar.getInstance().getTimeInMillis()+".jpg");
        File screenShotName = new File("src/test/resources/GoogleTasks.jpg");

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
