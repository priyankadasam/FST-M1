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

public class Activity3 {
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
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        caps.setCapability("noReset", true);

        //Appium Server URL
        URL serverURL = new URL("http://localhost:4723/wd/hub");

        //Driver initialization
        driver = new AndroidDriver<>(serverURL, caps);
        wait = new WebDriverWait(driver, 10);

        // Navigate to the page
        driver.get("https://www.training-support.net/selenium");

    }

    @Test
    public void toDOList() throws IOException, InterruptedException {
        // wait for page to load
        MobileElement pageTitle = driver.findElementById("home_button");
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        // Scroll to To-DO List
        String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
       // driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector()).scrollTextIntoView(\"To-Do List\")"));
        driver.findElement(MobileBy.AndroidUIAutomator(UiScrollable + ".scrollForward(7).scrollIntoView(text(\"To-Do List\"))"));

        driver.findElementByAccessibilityId("To-Do List Elements get added at run time").click();
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[contains(@text, 'Add Task')]")));
        MobileElement inputTask =   driver.findElementByXPath("//android.widget.EditText[contains(@resource-id, 'taskInput')]");


        //Adding Tasks
        String[] tasks={"Add tasks to list","Get number of tasks","Clear the list"};
        for (String task : tasks) {
            inputTask.click();
            inputTask.sendKeys(task);
            driver.findElementByXPath("//android.widget.Button[contains(@text, 'Add Task')]").click();
            Thread.sleep(5000);
        }
        takeScreenshot();

        //Striking out the Added tasks
        List<MobileElement> tasksAdded=driver.findElementsByXPath("//android.view.View[contains(@resource-id, 'tasksList')]/android.view.View");
       /* System.out.println(tasksAdded.size());
        System.out.println(tasksAdded.get(1).getText()+"=="+tasks[0]);
        System.out.println(tasksAdded.get(2).getText()+"=="+tasks[1]);
        System.out.println(tasksAdded.get(3).getText()+"=="+tasks[2]);*/
        for(int i=1;i<tasksAdded.size();i++){
            if(tasksAdded.get(i).getText().equalsIgnoreCase(tasks[i-1])) {
                Reporter.log("<br>Task got added successfully: " + tasks[i-1]);
                System.out.println("Task added: " + tasks[i-1]);
            }
                tasksAdded.get(i).click();

            Reporter.log("<br>Task striked out " + tasks[i-1]);
            System.out.println("Task striked out: " + tasks[i-1]);
        }
        takeScreenshot();
        driver.findElementByXPath("//android.widget.TextView[contains(@text, 'Clear List')]").click();
        Reporter.log("<br>Cleared the List " );
        System.out.println("Cleared the List: ");
        takeScreenshot();
    }



    public static void takeScreenshot() throws IOException {
        // Take screenshot
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // This specifies the location the screenshot will be saved
         File screenShotName = new File("src/test/resources/Activity3_"+ Calendar.getInstance().getTimeInMillis()+".jpg");
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
