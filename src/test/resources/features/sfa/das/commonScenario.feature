
Feature: Teach in Further Education - Check common scenarios
  As a user
  I want to be able to give consent to the site using cookies
  so that i have control over the cookies that are used in my browser

  @tife
  Scenario: User has not yet given consent
  Given the current user has not yet given consent to using cookies
  When the user visit a page on the site
  Then they see the cookie consent banner

  @tife
  Scenario: User is able to see the types of cookie we use
  Given the current user has not yet given consent to using cookies
  When the user visit a page on the site
  And they are able to see a cookie banner
  Then the banner contains a link to view the cookies we use

  @tife
  Scenario: User is able to accept the cookies and hide the banner after giving consent
    Given the user visits a page on the site
    When the user accepts the cookies on the site
    Then cookies banner should not be displayed

  @tife
  Scenario: User is able to reject the cookies and hide the banner after refusing the consent
    Given the user visits a page on the site
    When the user rejects the cookies on the site
    Then cookies banner should not be displayed

  @tife
  Scenario: No analytics cookies are written until the user has given consent
    Given the current user has not yet given consent to using cookies
    When the user visit a page on the site
    Then the user does not get given analytics cookies

  @tife
  Scenario: Analytics cookies are written when the user has given consent
    Given the current user has given consent to using cookies
    When the user visit a page on the site
    Then the user does get given analytics cookies

  @tife
  Scenario: Validation required fields error messages appear on the signup form
    Given the user is attempting to signup to a newsletter
    When user leaves all required fields empty
    And user click on the sign-up button
    Then user should see following validation messages:
      |First Name           | Enter your first name                                                 |
      |Last Name            | Enter your last name                                                  |
      |Email                | Enter an email address in the correct format, like name@example.com   |
      |Subject of Interest  | Select a subject that you are interested in teaching.                 |
      |Location of Interest | Select the location where you would like to teach.                    |

  @tife
  Scenario: Page not found message is displayed when someone modifies the url
    Given the user visits a page on the site
    When the user modifies the url
    Then page not found message should be displayed

  @tife
  Scenario: Verify Privacy Policy Footer Link on the bottom of the page
    Given the user navigates to the "Home" page
    When user clicks on Privacy Policy Link
    Then user should be navigated to Privacy Policy Page

  @tife
  Scenario:  Verify Accessibility statement Footer Link on the bottom of the page
    Given the user navigates to the "Home" page
    When user clicks on Accessibility statement Link
    Then user should be navigated to Accessibility statement Page

  @tife
  Scenario:  Verify Cookies Policy Footer Link on the bottom of the page
    Given the user navigates to the "Home" page
    When user clicks on Cookies Policy Link
    Then user should be navigated to Cookies Policy Page

  @tife
  Scenario: Verify open govt license Footer Link on the bottom of the page
    Given the user navigates to the "Home" page
    When user clicks on open govt licence Link
    Then user should be navigated to open govt licence Page

  @tife
  Scenario: Verify crown copy right Footer Link on the bottom of the page
    Given the user navigates to the "Home" page
    When user clicks on crown copy right Link
    Then user should be navigated to crown copy right Page

  @tife
  Scenario: Verify DFE Logo, FE logo and Teaching FE Logo
    Given the user navigates to the "HOME" page
    Then DFE and FE Logo should be displayed in header

  @tife
  Scenario: Verify all broken links on the page
    Given the user navigates to the "HOME" page
    When user retrieve all the links on the page
    Then each link should return a successful status code

  @tife
  Scenario: Verify Contact Details on Home page
    Given the user navigates to the "HOME" page
    Then User should be able to see Phone and Email contact details







