package sfa.das.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPage {

    private final WebDriver driver;

    public CommonPage(WebDriver driver){
        this.driver=driver;
    }


    private final By cookieHeadingTitle =By.xpath("//h2[normalize-space()='Cookies on Teach in Further Education']");
    private final By acceptCookieBtn =By.xpath("//button[@id='acceptCookiesButton']");
    private final By homeMenu =By.xpath("//a[normalize-space()='Home']");
    private final By viewCookiesLink =By.xpath("//a[normalize-space()='View cookies']");
    private final By rejectCookieBtn =By.xpath("//button[@id='rejectCookiesButton']");
    private final By acceptCookieBanner =By.xpath("//button[@id='acceptCookieBanner']");
    private final By rejectCookieBanner =By.xpath("//button[@id='rejectCookieBanner']");
    private final By googleTagManager =By.xpath("//script[contains(@src,'googletagmanager')]");
    private final By firstNameErrorMsg =By.xpath("(//span[@class='govuk-error-message'])[1]");
    private final By lastNameErrorMsg =By.xpath("(//span[@class='govuk-error-message'])[2]");
    private final By emailErrorMsg =By.xpath("(//span[@class='govuk-error-message'])[3]");
    private final By subjectErrorMsg =By.xpath("(//span[@class='govuk-error-message'])[4]");
    private final By locationErrorMsg =By.xpath("(//span[@class='govuk-error-message'])[5]");
    private final By isTeachingRightForMeMenu=By.xpath("//a[@class='dfe-header__navigation-link'][normalize-space()='Is teaching right for me?']");
    private final By becomeAFETeacherMenu=By.xpath("//a[normalize-space()='Become a FE teacher']");
    private final By talkToAnAdvisorMenu=By.xpath("//a[normalize-space()='Talk to an adviser']");
    private final By searchForATeachingJobMenu=By.xpath("//a[normalize-space()='Search for a teaching job']");
    private final By findFundingAndTrainingMenu=By.xpath("//a[normalize-space()='Find funding and training']");
    private final By footerContactIcon=By.xpath("//img[@src='../images/phone.png']");
    private final By footerContactNumber=By.xpath("//a[normalize-space()='0800 389 2502']");
    private final By footerEmailIcon=By.xpath("(//*[name()='svg'][@id='svg-envelope'])[1]");
    private final By footerEmailText=By.xpath("//a[normalize-space()='teach.fe@education.gov.uk']");
    private final By _DFELogo=By.xpath("//img[@class='dfe-logo']");
    private final By _FELogo=By.xpath("//img[@class='fe-logo']");
    private final By _TeachingFELogo=By.xpath("//img[@alt='teaching fe logo']");
    private final By submitButton=By.xpath("//button[normalize-space()='Submit']");
    private final By footerCookiePolicyLink=By.xpath("//a[normalize-space()='Cookies policy']");
    private final By footerOpenGovtLicenceLink =By.xpath("//a[normalize-space()='Open Government Licence v3.0']");
    private final By footerCrownCopyrightLink =By.xpath("//a[normalize-space()='© Crown copyright']");
    private final By footerAccessibilityStatementLink=By.xpath("//a[normalize-space()='Accessibility statement']");
    private final By footerPrivacyPolicyLink=By.xpath("//a[normalize-space()='Privacy Policies']");
    public String isTeachingRightForMePageTitle="Is teaching right for me";
    public String becomeAFETeacherPageTitle="Become";
    public String talkToAnAdvisorPageTitle="Advisor";
    public String searchForATeachingJobPageTitle="Search";
    public String findFundingAndTrainingPageTitle="Find Funding And Training";
    public String contactNumber="0800 389 2502";
    public String emailValue="teach.fe@education.gov.uk";
    public String accessibilityStatementPageTitle ="Accessibility Statement Page - Teach in further education - Department for Education";
    public String privacyPolicyPageTitle ="Privacy policies - Teach in further education - Department for Education";

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

    public By get_TeachingFELogo() {
        return _TeachingFELogo;
    }

    public By getCookieHeadingTitle() {
        return cookieHeadingTitle;
    }

    public By getAcceptCookieBtn() {
        return acceptCookieBtn;
    }

    public By getHomeMenu() {
        return homeMenu;
    }

    public By getViewCookiesLink() {
        return viewCookiesLink;
    }

    public By getRejectCookieBtn() {
        return rejectCookieBtn;
    }

    public By getAcceptCookieBanner() {
        return acceptCookieBanner;
    }

    public By getRejectCookieBanner() {
        return rejectCookieBanner;
    }

    public By getGoogleTagManager() {
        return googleTagManager;
    }

    public By getFirstNameErrorMsg() {
        return firstNameErrorMsg;
    }

    public By getLastNameErrorMsg() {
        return lastNameErrorMsg;
    }

    public By getEmailErrorMsg() {
        return emailErrorMsg;
    }

    public By getSubjectErrorMsg() {
        return subjectErrorMsg;
    }

    public By getLocationErrorMsg() {
        return locationErrorMsg;
    }

    public By getFooterOpenGovtLicenceLink() {
        return footerOpenGovtLicenceLink;
    }

    public By getFooterCrownCopyrightLink() {
        return footerCrownCopyrightLink;
    }
}
