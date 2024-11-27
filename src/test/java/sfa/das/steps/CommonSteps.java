package sfa.das.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import sfa.das.base.Hooks;
import sfa.das.pages.CommonPage;
import sfa.das.utils.WebUtils;

import java.util.List;
import java.util.Map;

public class CommonSteps {

    private final WebDriver driver;
    private final CommonPage commonPage;

    private static final Logger log = LogManager.getLogger(CommonSteps.class);

    private List<String> brokenLinks;

    public CommonSteps() {
        this.driver = Hooks.getDriver();

        commonPage = new CommonPage(driver);
    }

    @When("the user navigates to the {string} page")
    public void theUserNavigatesToThePage(String destination) {

        if (destination.equals("HOME")) {
            Assert.assertEquals("Home page - Teach in further education - Department for Education", driver.getTitle());
        }

        if (destination.equalsIgnoreCase("Become a FE teacher")) {
            driver.findElement(commonPage.getBecomeAFETeacherMenu()).click();
            log.info("successfully clicked on Become-A-FE-Teacher Menu");
            Assert.assertEquals("Become a Further Education (FE) teacher", driver.getTitle());
        }

        if (destination.equalsIgnoreCase("Is teaching right for me?")) {
            driver.findElement(commonPage.getIsTeachingRightForMeMenu()).click();
            String currentUrl = driver.getCurrentUrl();
            String[] urlParts = currentUrl.split("/");
            String actualEndPart = urlParts[urlParts.length - 1];
            Assert.assertEquals("The URL does not end with the expected value. ", "is-teaching-right-for-me", actualEndPart);
        }

        if (destination.equalsIgnoreCase("Search for a teaching job")) {
            driver.findElement(commonPage.getSearchForATeachingJobMenu()).click();
            String currentUrl = driver.getCurrentUrl();
            String[] urlParts = currentUrl.split("/");
            String actualEndPart = urlParts[urlParts.length - 1];
            Assert.assertEquals("The URL does not end with the expected value. ", "search-for-a-teaching-job", actualEndPart);
        }

        if (destination.equalsIgnoreCase("Find funding and training")) {
            driver.findElement(commonPage.getFindFundingAndTrainingMenu()).click();
            String currentUrl = driver.getCurrentUrl();
            String[] urlParts = currentUrl.split("/");
            String actualEndPart = urlParts[urlParts.length - 1];
            Assert.assertEquals("The URL does not end with the expected value. ", "find-funding-and-training", actualEndPart);
        }
    }


    @Then("DFE and FE Logo should be displayed in header")
    public void DFEAndFeLogoShouldBeDisplayedInHeader() {
        Assert.assertTrue(driver.findElement(commonPage.get_DFELogo()).isDisplayed());
        Assert.assertTrue(driver.findElement(commonPage.get_FELogo()).isDisplayed());
        Assert.assertTrue(driver.findElement(commonPage.get_TeachingFELogo()).isDisplayed());
    }

    @When("user clicks on 'Is Teaching Right For Me?' Menu")
    public void userClicksOnIsTeachingRightForMeMenu() {
        driver.findElement(commonPage.getIsTeachingRightForMeMenu()).click();
    }

    @Then("user should be navigated to 'Is Teaching Right For Me?' Page")
    public void userShouldBeNavigatedToIsTeachingRightForMePage() {
        String actualIsTeachingRightForMePageTitle = driver.getTitle();
        Assert.assertTrue(actualIsTeachingRightForMePageTitle.contains(commonPage.isTeachingRightForMePageTitle));
    }

    @When("user clicks on 'Become a FE Teacher' Menu")
    public void userClicksOnBecomeAFETeachersMenu() {
        driver.findElement(commonPage.getBecomeAFETeacherMenu()).click();
    }

    @Then("user should be navigated to 'Become a FE Teacher' Page")
    public void userShouldBeNavigatedToBecomeAFETeacherPage() {
        String actualBecomeAFETeacherPageTitle = driver.getTitle();
        Assert.assertTrue(actualBecomeAFETeacherPageTitle.contains(commonPage.becomeAFETeacherPageTitle));
    }

    @When("user clicks on 'Talk to an adviser' Menu")
    public void userClicksOnTalkToAnAdviserMenu() {
        driver.findElement(commonPage.getTalkToAnAdvisorMenu()).click();
    }

    @Then("user should be navigated to 'Talk to an adviser' Page")
    public void userShouldBeNavigatedToTalkToAnAdviserPage() {
        String actualTalkToAnAdvisorPageTitle = driver.getTitle();
        Assert.assertTrue(actualTalkToAnAdvisorPageTitle.contains(commonPage.talkToAnAdvisorPageTitle));
    }

    @When("user clicks on 'Search for a teaching job' Menu")
    public void userClicksOnSearchForATeachingJobMenu() {
        driver.findElement(commonPage.getSearchForATeachingJobMenu()).click();
    }

    @Then("user should be navigated to 'Search for a teaching job' Page")
    public void userShouldBeNavigatedTSearchForATeachingJobPage() {
        String actualSearchForATeachingJobPageTitle = driver.getTitle();
        //Assert.assertEquals(actualSearchForATeachingJobPageTitle,homePage.searchForATeachingJobPageTitle);
        Assert.assertTrue(actualSearchForATeachingJobPageTitle.contains(commonPage.searchForATeachingJobPageTitle));
    }

    @When("user clicks on 'Find Funding And Training' Menu")
    public void userClicksOnFindFundingAndTrainingMenu() {
        driver.findElement(commonPage.getFindFundingAndTrainingMenu()).click();
    }

    @Then("user should be navigated to 'Find Funding And Training' Page")
    public void userShouldBeNavigatedToFindFundingAndTrainingPage() {
        String actualFindFundingAndTrainingPageTitle = driver.getTitle();
        Assert.assertEquals(actualFindFundingAndTrainingPageTitle, commonPage.findFundingAndTrainingPageTitle);
    }


    @Then("User should be able to see Phone and Email contact details")
    public void validateContactDetails() {
        WebElement contactIcon = driver.findElement(commonPage.getFooterContactIcon());
        contactIcon.isDisplayed();

        WebElement contactNumber = driver.findElement(commonPage.getFooterContactNumber());
        String contactNum = contactNumber.getText();
        Assert.assertEquals(contactNum, commonPage.contactNumber);

        WebElement emailIcon = driver.findElement(commonPage.getFooterEmailIcon());
        emailIcon.isDisplayed();

        WebElement emailText = driver.findElement(commonPage.getFooterEmailText());
        String emailValue = emailText.getText();
        Assert.assertEquals(emailValue, commonPage.emailValue);

    }

    @When("user clicks on open govt licence Link")
    public void user_clicks_on_open_govt_licence_link() {
        driver.findElement(commonPage.getFooterOpenGovtLicenceLink()).click();
    }

    @Then("user should be navigated to open govt licence Page")
    public void user_should_be_navigated_to_open_govt_licence_page() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Open Government Licence");
    }


    @When("user clicks on crown copy right Link")
    public void user_clicks_on_crown_copy_right_link() {
        driver.findElement(commonPage.getFooterCrownCopyrightLink()).click();
    }

    @Then("user should be navigated to crown copy right Page")
    public void user_should_be_navigated_to_crown_copy_right_page() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Crown copyright - Re-using PSI");
    }


    @When("user clicks on Cookies Policy Link")
    public void user_clicks_on_cookies_policy_link() {
        driver.findElement(commonPage.getFooterCookiePolicyLink()).click();
    }

    @Then("user should be navigated to Cookies Policy Page")
    public void user_should_be_navigated_to_cookies_policy_page() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "Cookies policy - Teach in further education - Department for Education");
    }

    @When("user clicks on Accessibility statement Link")
    public void user_clicks_on_accessibility_statement_link() {
        driver.findElement(commonPage.getFooterAccessibilityStatementLink()).click();
    }

    @Then("user should be navigated to Accessibility statement Page")
    public void user_should_be_navigated_to_accessibility_statement_page() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, commonPage.accessibilityStatementPageTitle);
    }

    @When("user clicks on Privacy Policy Link")
    public void user_clicks_on_privacy_policy_link() {
        driver.findElement(commonPage.getFooterPrivacyPolicyLink()).click();
    }

    @Then("user should be navigated to Privacy Policy Page")
    public void user_should_be_navigated_to_privacy_policy_page() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, commonPage.privacyPolicyPageTitle);
    }

    @When("user retrieve all the links on the page")
    public void userRetrieveAllTheLinksOnThePage() {
        brokenLinks = WebUtils.checkAllLinks(driver);
    }

    @Then("each link should return a successful status code")
    public void eachLinkShouldReturnASuccessfulStatusCode() {
        Assert.assertTrue("There are broken links on the page: " + brokenLinks, brokenLinks.isEmpty());
    }


    @When("the user visit a page on the site")
    public void theUserVisitAPageOnTheSite() {
        driver.findElement(commonPage.getHomeMenu()).click();
    }

    @Then("they see the cookie consent banner")
    public void theySeeTheCookieConsentBanner() {
        Assert.assertTrue("cookie consent banner is not displayed", driver.findElement(commonPage.getCookieHeadingTitle()).isDisplayed());
    }

    @Given("the current user has not yet given consent to using cookies")
    public void theCurrentUserHasNotYetGivenConsentToUsingCookies() {
        Assert.assertTrue("Cookie section is not displayed", driver.findElement(commonPage.getAcceptCookieBtn()).isDisplayed());
    }

    @And("they are able to see a cookie banner")
    public void theyAreAbleToSeeACookieBanner() {
        Assert.assertTrue("cookie consent banner is not displayed", driver.findElement(commonPage.getCookieHeadingTitle()).isDisplayed());
    }

    @Then("the banner contains a link to view the cookies we use")
    public void theBannerContainsALinkToViewTheCookiesWeUse() {
        driver.findElement(commonPage.getViewCookiesLink()).click();
        Assert.assertEquals("Cookies policy - Teach in further education - Department for Education", driver.getTitle());
    }

    @Given("the user visits a page on the site")
    public void theUserVisitsAPageOnTheSite() {
        driver.findElement(commonPage.getHomeMenu()).click();
    }

    @When("the user accepts the cookies on the site")
    public void theUserAcceptsTheCookiesOnTheSite() {
        driver.findElement(commonPage.getAcceptCookieBtn()).click();
        driver.findElement(commonPage.getAcceptCookieBanner()).click();
    }

    @Then("cookies banner should not be displayed")
    public void cookiesBannerShouldNotBeDisplayed() {
        List<WebElement> elements = driver.findElements(commonPage.getCookieHeadingTitle());
        boolean isDisplayed = elements.size() > 0 && elements.get(0).isDisplayed();
        Assert.assertFalse("Cookie Banner is displayed.", isDisplayed);
    }

    @When("the user rejects the cookies on the site")
    public void theUserRejectsTheCookiesOnTheSite() {
        driver.findElement(commonPage.getRejectCookieBtn()).click();
        driver.findElement(commonPage.getRejectCookieBanner()).click();
    }

    @When("the user modifies the url")
    public void theUserModifiesTheUrl() {
        String currentUrl = driver.getCurrentUrl();
        String modifiedUrl = currentUrl + "invalid-page";
        driver.get(modifiedUrl);

    }

    @Then("page not found message should be displayed")
    public void pageNotFoundMessageShouldBeDisplayed() {
        String currentTitle = driver.getTitle();
        Assert.assertEquals("Page not found title is not displayed.", currentTitle, "Page not found");
    }

    @Then("the user does not get given analytics cookies")
    public void theUserDoesNotGetGivenAnalyticsCookies() {
        List<WebElement> scriptTags = driver.findElements(commonPage.getGoogleTagManager());
        boolean isGTMDisplayed = scriptTags.stream().anyMatch(WebElement::isDisplayed);
        Assert.assertFalse("No script with 'googletagmanager.com' should be displayed", isGTMDisplayed);
    }

    @Given("the current user has given consent to using cookies")
    public void theCurrentUserHasGivenConsentToUsingCookies() {
        driver.findElement(commonPage.getAcceptCookieBtn()).click();
        driver.findElement(commonPage.getAcceptCookieBanner()).click();
    }

    @Then("the user does get given analytics cookies")
    public void theUserDoesGetGivenAnalyticsCookies() {
        List<WebElement> scriptTags = driver.findElements(commonPage.getGoogleTagManager());
        Assert.assertFalse("No script with 'googletagmanager.com' should be displayed", scriptTags.isEmpty());
        String srcValue = scriptTags.get(0).getAttribute("src");
        Assert.assertTrue("The Google Tag Manager is not displayed.", srcValue.startsWith("https://www.googletagmanager.com"));
    }


    @Given("the user is attempting to signup to a newsletter")
    public void theUserIsAttemptingToSignupToANewsletter() {

    }

    @When("user leaves all required fields empty")
    public void userLeavesAllRequiredFieldsEmpty() {

    }

    @And("user click on the sign-up button")
    public void userClickOnTheSignUpButton() {
        driver.findElement(commonPage.getSubmitButton()).click();
    }

    @Then("user should see following validation messages:")
    public void userShouldSeeFollowingValidationMessages(DataTable dataTable) {
        Map<String, String> expectedMessages = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : expectedMessages.entrySet()) {
            String field = entry.getKey();
            String expectedMessage = entry.getValue();
            WebElement errorElement;
            switch (field) {
                case "First Name":
                    errorElement = driver.findElement(commonPage.getFirstNameErrorMsg());
                    break;
                case "Last Name":
                    errorElement = driver.findElement(commonPage.getLastNameErrorMsg());
                    break;
                case "Email":
                    errorElement = driver.findElement(commonPage.getEmailErrorMsg());
                    break;
                case "Subject of Interest":
                    errorElement = driver.findElement(commonPage.getSubjectErrorMsg());
                    break;
                case "Location of Interest":
                    errorElement = driver.findElement(commonPage.getLocationErrorMsg());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + field);
            }
            String actualMessage = errorElement.getText().replace("Error:", "").trim();
            Assert.assertEquals("Validation message for: " + field + " did not match", expectedMessage, actualMessage);
        }
    }
}
