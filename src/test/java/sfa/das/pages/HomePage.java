package sfa.das.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
    }



    private final By becomeAFurtherEducationTeacherTile=By.xpath("//div[2]//div[1]//h3[1]//a[1]");
    private final By isTeachingRightForMeTile= By.xpath("//div[@class='dfe-grid-container']//div[1]//div[1]//h3[1]//a[1]");
    private final By searchForATeachingJobTile = By.xpath("//div[3]//div[1]//h3[1]//a[1]");
    private final By findFundingAndTrainingTile= By.xpath("//a[text()='Find funding and training']");
    private final By isTeachingRightForMeMenu=By.xpath("//a[@class='dfe-header__navigation-link'][normalize-space()='Is teaching right for me?']");
    private final By becomeAFETeacherMenu=By.xpath("//a[normalize-space()='Become a FE teacher']");
    private final By talkToAnAdvisorMenu=By.xpath("//a[normalize-space()='Talk to an adviser']");
    private final By searchForATeachingJobMenu=By.xpath("//a[normalize-space()='Search for a teaching job']");
    private final By findFundingAndTrainingMenu=By.xpath("//a[normalize-space()='Find funding and training']");
    private final By howToPrepareMicroTeachLink=By.xpath("//a[contains(text(),'Learn how to prepare a Micro teach for your next i')]");
    private final By findOutMoreAboutFundingAndTrainingOptionsLink=By.xpath("//a[contains(text(),'Find out more about your funding and training opti')]");
    private final By footerContactIcon=By.xpath("//img[@src='../images/phone.png']");
    private final By footerContactNumber=By.xpath("//a[normalize-space()='0800 389 2502']");
    private final By footerEmailIcon=By.xpath("//img[@src='../images/envelope.png']");
    private final By footerEmailText=By.xpath("//a[normalize-space()='teach.fe@education.gov.uk']");
    private final By _DFELogo=By.xpath("//img[@class='dfe-logo']");
    private final By _FELogo=By.xpath("//img[@class='fe-logo']");
    private final By emailTextField=By.xpath("//input[@id='emailAddress']");
    private final By subjectDropDown=By.xpath("//select[@id='subject']");
    private final By whereAreYouInterestedInTeachingDropdown=By.xpath("//select[@id='location']");
    private final By submitButton=By.xpath("//button[normalize-space()='Submit']");
    private final By footerCookiePolicyLink=By.xpath("//a[normalize-space()='Cookies policy']");
    private final By footerAccessibilityStatementLink=By.xpath("//a[normalize-space()='Accessibility statement']");
    private final By footerPrivacyPolicyLink=By.xpath("//a[normalize-space()='Privacy Policies']");

    /*@FindBy(xpath = "")
    private WebElement _DFELogo;*/

    public String homePageTitle="Home page";
    public String isTeachingRightForMePageTitle="Is teaching right for me";
    public String becomeAFETeacherPageTitle="Become";
    public String talkToAnAdvisorPageTitle="Advisor";
    public String searchForATeachingJobPageTitle="Search";
    public String findFundingAndTrainingPageTitle="Find Funding And Training";
    public String howToPrepareMicroTeachPageTitle="Google";
    public String findOutMoreAboutFundingAndTrainingOptionsPageTitle="Home page";
    public String contactNumber="0800 389 2502";
    public String emailValue="teach.fe@education.gov.uk";
    public String cookiePolicyLink="";
    public String accessibilityStatementPageTitle ="Accessibility Statement Page";
    public String privacyPolicyPageTitle ="Privacy policies";

    public By get_FELogo(){
        return _FELogo;
    }

    public By get_DFELogo(){
        return _DFELogo;
    }

    public By getFooterContactNumber(){
        return footerContactNumber;
    }

    public By getFooterEmailIcon(){
        return footerEmailIcon;
    }

    public By getFooterEmailText(){
        return footerEmailText;
    }

    public By getFooterContactIcon(){
        return footerContactIcon;
    }

    public By getFindOutMoreAboutFundingAndTrainingOptionsLink(){
        return findOutMoreAboutFundingAndTrainingOptionsLink;
    }

    public By getHowToPrepareMicroTeachLink(){
        return howToPrepareMicroTeachLink;
    }

    public By getFindFundingAndTrainingMenu(){
        return findFundingAndTrainingMenu;
    }

    public By getSearchForATeachingJobMenu(){
        return searchForATeachingJobMenu;
    }

    public By getTalkToAnAdvisorMenu(){
        return talkToAnAdvisorMenu;
    }

    public By getBecomeAFETeacherMenu(){
        return becomeAFETeacherMenu;
    }

    public By getIsTeachingRightForMeMenu(){
        return isTeachingRightForMeMenu;
    }

    public By getFindFundingAndTrainingTile(){
        return findFundingAndTrainingTile;
    }

    public By getBecomeAFurtherEducationTeacherTile(){
        return becomeAFurtherEducationTeacherTile;
    }

    public By getIsTeachingRightForMeTile(){
        return isTeachingRightForMeTile;
    }

    public By getSearchForATeachingJobTile(){
        return searchForATeachingJobTile;
    }

    public By getEmailTextField() {
        return emailTextField;
    }

    public By getSubjectDropDown() {
        return subjectDropDown;
    }

    public By getWhereAreYouInterestedInTeachingDropdown() {
        return whereAreYouInterestedInTeachingDropdown;
    }

    public By getSubmitButton() {
        return submitButton;
    }

    public By getFooterCookiePolicyLink() {
        return footerCookiePolicyLink;
    }

    public By getFooterAccessibilityStatementLink() {
        return footerAccessibilityStatementLink;
    }

    public By getFooterPrivacyPolicyLink() {
        return footerPrivacyPolicyLink;
    }
}
