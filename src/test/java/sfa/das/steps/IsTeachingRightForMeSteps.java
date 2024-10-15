package sfa.das.steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import sfa.das.base.Hooks;
import sfa.das.pages.HomePage;
import sfa.das.pages.IsTeachingRightForMePage;

public class IsTeachingRightForMeSteps {

    private WebDriver driver;
    private IsTeachingRightForMePage isTeachingRightForMePage;

    public IsTeachingRightForMeSteps(){
        this.driver= Hooks.getDriver();
        isTeachingRightForMePage=new IsTeachingRightForMePage(driver);
    }

    @Then("User should be redirected to 'Is Teaching Right To Me' Page")
    public void userShouldBeRedirectedToIsTeachingRightForMePage(){
        Assert.assertEquals("Home page", driver.getTitle());
    }

}
