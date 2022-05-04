package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

public class TagsExample extends BaseClass{
    @Given("^user is on the TS homepage$")
    public void openTSHomepage(){
        driver.get("https://www.training-support.net");
    }

    @When("^user clicks on the About Us button$")
    public void clickAboutUsButton(){
        driver.findElement(By.id("about-link")).click();
    }

    @Then("^user is taken to About Us page$")
    public void aboutUspageVerfication(){
        String aboutUsPageTitle=driver.findElement(By.xpath("//h1[@class='ui header']")).getText();
        Assert.assertEquals("About Us", aboutUsPageTitle);
    }

}
