package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features",
        glue = {"stepDefinitions"},
        /*tags = "@activity1",
        tags = "@activity2",
        tags = "@activity3,
        tags = "@activity4",*/
        tags = "@activity5",

       plugin = {"pretty", "html:target/cucumber-reports/reports"},
      // plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json"},
       monochrome = true
)

public class TestRunner {
    //Empty
}
