package sfa.das.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sfa.das.base.Hooks;
import sfa.das.pages.HomePage;

import java.util.List;

public class HomeSteps {

    private WebDriver driver;
    private HomePage homePage;

    public HomeSteps(){
        this.driver= Hooks.getDriver();

        homePage=new HomePage(driver);
    }

    @When("the user navigate to the {string} page")
    public void theUserNavigateToTheHomePage(String pageName){
        if (pageName.equalsIgnoreCase("Become a further education (FE) teacher")){
            driver.findElement(homePage.getBecomeAFurtherEducationTeacherTile()).click();
        }
        if (pageName.equalsIgnoreCase("Is teaching right for me?")){
            driver.findElement(homePage.getIsTeachingRightForMeTile()).click();
        }
        if (pageName.equalsIgnoreCase("Search for a teaching job")){
            driver.findElement(homePage.getSearchForATeachingJobTile()).click();
        }
        if (pageName.equalsIgnoreCase("Find funding and training")){
            driver.findElement(homePage.getFindFundingAndTrainingTile()).click();
        }
    }

    @When("the user navigates to the {string} page")
    public void theUserNavigatesToThePage(String destination) {

        if (destination.equals("HOME")) {
            Assert.assertEquals("Home page", driver.getTitle());
        }

        if (destination.equals("Become a further education (FE) teacher")) {
            driver.findElement(homePage.getBecomeAFurtherEducationTeacherTile());
            Assert.assertEquals("Home page", driver.getTitle());
        }

        if (destination.equalsIgnoreCase("Is teaching right for me?")) {
            driver.findElement(homePage.getIsTeachingRightForMeMenu()).click();
            //WebUtils.clickElement(homePage.getIsTeachingRightForMeTile());
            Assert.assertEquals("Is teaching right for me", driver.getTitle());
        }

        if (destination.equalsIgnoreCase("Check if you can teach in FE")) {
            driver.findElement(homePage.getSearchForATeachingJobTile()).click();
            Assert.assertEquals("Google", driver.getTitle());
        }

        if (destination.equals("Find funding and training")) {
            driver.findElement(homePage.getFindFundingAndTrainingTile()).click();
            Assert.assertEquals("Home page", driver.getTitle());
        }
    }

    @Then("User should be redirected to Home Page")
    public void userShouldBeRedirectedToHomePage(){
        String actualHomePageTitle= driver.getTitle();
        Assert.assertEquals(actualHomePageTitle,homePage.homePageTitle);
    }

    @Then("DFE and FE Logo should be displayed in header")
    public void DFEAndFeLogoShouldBeDisplayedInHeader(){
        Assert.assertTrue(driver.findElement(homePage.get_DFELogo()).isDisplayed());
        Assert.assertTrue(driver.findElement(homePage.get_FELogo()).isDisplayed());
    }

    @When("user clicks on 'Is Teaching Right For Me?' Menu")
    public void userClicksOnIsTeachingRightForMeMenu(){
        driver.findElement(homePage.getIsTeachingRightForMeMenu()).click();
    }

    @Then("user should be navigated to 'Is Teaching Right For Me?' Page")
    public void userShouldBeNavigatedToIsTeachingRightForMePage() {
        String actualIsTeachingRightForMePageTitle= driver.getTitle();
        //Assert.assertEquals(actualIsTeachingRightForMePageTitle,homePage.isTeachingRightForMePageTitle);
        Assert.assertTrue(actualIsTeachingRightForMePageTitle.contains(homePage.isTeachingRightForMePageTitle));
    }

    @When("user clicks on 'Become a FE Teacher' Menu")
    public void userClicksOnBecomeAFETeachersMenu(){
        driver.findElement(homePage.getBecomeAFETeacherMenu()).click();
    }

    @Then("user should be navigated to 'Become a FE Teacher' Page")
    public void userShouldBeNavigatedToBecomeAFETeacherPage() {
        String actualBecomeAFETeacherPageTitle=driver.getTitle();
        //Assert.assertEquals(actualBecomeAFETeacherPageTitle,homePage.becomeAFETeacherPageTitle);
        Assert.assertTrue(actualBecomeAFETeacherPageTitle.contains(homePage.becomeAFETeacherPageTitle));
    }

    @When("user clicks on 'Talk to an adviser' Menu")
    public void userClicksOnTalkToAnAdviserMenu(){
        driver.findElement(homePage.getTalkToAnAdvisorMenu()).click();
    }

    @Then("user should be navigated to 'Talk to an adviser' Page")
    public void userShouldBeNavigatedToTalkToAnAdviserPage(){
        String actualTalkToAnAdvisorPageTitle=driver.getTitle();
        //Assert.assertEquals(actualTalkToAnAdvisorPageTitle,homePage.talkToAnAdvisorPageTitle);
        Assert.assertTrue(actualTalkToAnAdvisorPageTitle.contains(homePage.talkToAnAdvisorPageTitle));
    }

    @When("user clicks on 'Search for a teaching job' Menu")
    public void userClicksOnSearchForATeachingJobMenu(){
        driver.findElement(homePage.getSearchForATeachingJobMenu()).click();
    }

    @Then("user should be navigated to 'Search for a teaching job' Page")
    public void userShouldBeNavigatedTSearchForATeachingJobPage(){
        String actualSearchForATeachingJobPageTitle=driver.getTitle();
        //Assert.assertEquals(actualSearchForATeachingJobPageTitle,homePage.searchForATeachingJobPageTitle);
        Assert.assertTrue(actualSearchForATeachingJobPageTitle.contains(homePage.searchForATeachingJobPageTitle));
    }

    @When("user clicks on 'Find Funding And Training' Menu")
    public void userClicksOnFindFundingAndTrainingMenu(){
        driver.findElement(homePage.getFindFundingAndTrainingMenu()).click();
    }

    @Then("user should be navigated to 'Find Funding And Training' Page")
    public void userShouldBeNavigatedToFindFundingAndTrainingPage(){
        String actualFindFundingAndTrainingPageTitle=driver.getTitle();
        Assert.assertEquals(actualFindFundingAndTrainingPageTitle,homePage.findFundingAndTrainingPageTitle);
    }

    @When("user clicks on 'How To Prepare A Micro Teach' Link")
    public void userClicksOnHowToPrepareAMicroTeachLink(){
        driver.findElement(homePage.getHowToPrepareMicroTeachLink()).click();
    }

    @Then("user should be navigated to 'How To Prepare A Micro Teach' Page")
    public void userShouldBeNavigatedToHowToPrepareAMicroTeachLink(){
        String actualHowToPrepareMicroTeachPageTitle=driver.getTitle();
        Assert.assertEquals(actualHowToPrepareMicroTeachPageTitle,homePage.howToPrepareMicroTeachPageTitle);
    }

    @When("user clicks on 'Find out more about your funding and training options' Link")
    public void userClicksOnFindOutMoreAboutYourFundingAndTrainingOptionsLink(){
        driver.findElement(homePage.getFindOutMoreAboutFundingAndTrainingOptionsLink()).click();
    }

    @Then("user should be navigated to 'Find out more about your funding and training options' Page")
    public void userShouldBeNavigatedToFindOutMoreAboutYourFundingAndTrainingOptionsLink(){
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,homePage.findOutMoreAboutFundingAndTrainingOptionsPageTitle);
    }

    @Then("User should be able to see Phone and Email contact details")
    public void validateContactDetails() {
        WebElement contactIcon=driver.findElement(homePage.getFooterContactIcon());
        contactIcon.isDisplayed();

        WebElement contactNumber=driver.findElement(homePage.getFooterContactNumber());
        String contactNum=contactNumber.getText();
        Assert.assertEquals(contactNum,homePage.contactNumber);

        WebElement emailIcon=driver.findElement(homePage.getFooterEmailIcon());
        emailIcon.isDisplayed();

        WebElement emailText=driver.findElement(homePage.getFooterEmailText());
        String emailValue=emailText.getText();
        Assert.assertEquals(emailValue,homePage.emailValue);

    }

    @Then("user should be navigated to 'Become a further education (FE) teacher' page")
    public void userShouldBeNavigatedToBecomeAFurtherEducationTeacherFETeacherPage() {
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,"Google");
    }

    @Then("user should be navigated to 'Is teaching right for me?' page")
    public  void userShouldBeNavigatedToIsTeachingRightForMePages(){
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,"Google");
    }



    @Then("user should be navigated to SearchForATeachingJob page")
    public  void userShouldBeNavigatedToSearchForATeachingJobPages(){
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,"Home page");
    }

    @Then("user should be navigated to 'Find funding and training' page")
    public  void userShouldBeNavigatedToFindFundingAndTrainingPages(){
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,"Home page");
    }

    @When("user enters email id in email field")
    public void userEntersEmailInEmailField() {

        //Locating Email Field
        WebElement emailTextField = driver.findElement(homePage.getEmailTextField());

        //Sending Email value
        emailTextField.sendKeys(homePage.emailValue);

    }

    @And("user selects subject name in subjects drop down")
    public void userSelectsSubjectNameInSubjectDropDown() {

        //Clicking on Subject drop down
        driver.findElement(homePage.getSubjectDropDown()).click();

        //Storing all options in a List
        List<WebElement> allOptions=driver.findElements(By.cssSelector("select  option"));
        String option ="Accounting or finance";

        //Iterating through all options and upon matching, clicking on "Accounting or finance" drop down
        for (int i = 0; i < allOptions.size(); i++) {
            if (allOptions.get(i).getText().contains(option)){
                allOptions.get(i).click();
                break;
            }

        }
    }

    @And("user selects location in location drop down")
    public void userSelectsLocationInLocationDropDown(){

        //Clicking on "Where are you interested in teaching? (Optional)" drop down
        driver.findElement(homePage.getWhereAreYouInterestedInTeachingDropdown()).click();

        //Storing all options in a List
        List<WebElement> allOptionsOfInterestedInTeaching=driver.findElements(By.xpath("//select[@id='location']//option"));
        String optionOfInterestedInTeaching ="Business management and administration";

        //Iterating through all options and upon matching, clicking on "Business management and administration" drop down
        for (int i = 0; i < allOptionsOfInterestedInTeaching.size(); i++) {
            if (allOptionsOfInterestedInTeaching.get(i).getText().contains(optionOfInterestedInTeaching)){
                allOptionsOfInterestedInTeaching.get(i).click();
                break;
            }
        }

        //clicking on Submit button
        driver.findElement(homePage.getSubmitButton()).click();
    }

    @Then("User should be able to submit the form")
    public void user_should_be_able_to_submit_the_form() {
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,"Home page");
    }

    @When("user clicks on Cookies Policy Link")
    public void user_clicks_on_cookies_policy_link() {
        driver.findElement(homePage.getFooterCookiePolicyLink()).click();
    }
    @Then("user should be navigated to Cookies Policy Page")
    public void user_should_be_navigated_to_cookies_policy_page() {
        String pageTitle=driver.getTitle();
        Assert.assertEquals(pageTitle,"Cookies policy");
    }
    @When("user clicks on Accessibility statement Link")
    public void user_clicks_on_accessibility_statement_link() {
        driver.findElement(homePage.getFooterAccessibilityStatementLink()).click();
    }
    @Then("user should be navigated to Accessibility statement Page")
    public void user_should_be_navigated_to_accessibility_statement_page() {
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,homePage.accessibilityStatementPageTitle);
    }
    @When("user clicks on Privacy Policy Link")
    public void user_clicks_on_privacy_policy_link() {
        driver.findElement(homePage.getFooterPrivacyPolicyLink()).click();
    }
    @Then("user should be navigated to Privacy Policy Page")
    public void user_should_be_navigated_to_privacy_policy_page() {
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,homePage.privacyPolicyPageTitle);
    }
}
