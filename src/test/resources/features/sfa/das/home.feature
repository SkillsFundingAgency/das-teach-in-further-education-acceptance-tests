
Feature: Teach in Further Education - Check Home page anchors

  @home
  Scenario: Verify User is able to launch website
    Given the user navigates to the "HOME" page
    Then User should be redirected to Home Page

  @home
  Scenario: Verify DFE Logo and FE logo in Header
    Given the user navigates to the "HOME" page
    Then DFE and FE Logo should be displayed in header

  @home
  Scenario: Verify 'Is Teaching Right For Me?' Menu on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'Is Teaching Right For Me?' Menu
    Then user should be navigated to 'Is Teaching Right For Me?' Page

  @home
  Scenario: Verify 'Become a FE Teacher' Menu on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'Become a FE Teacher' Menu
    Then user should be navigated to 'Become a FE Teacher' Page

  @home
  Scenario: Verify 'Search for a teaching job' Menu on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'Search for a teaching job' Menu
    Then user should be navigated to 'Search for a teaching job' Page

  @home
  Scenario: Verify 'Find Funding And Training' Menu on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'Find Funding And Training' Menu
    Then user should be navigated to 'Find Funding And Training' Page

  @home
  Scenario: Verify 'Talk to an adviser' Menu on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'Talk to an adviser' Menu
    Then user should be navigated to 'Talk to an adviser' Page

  @home
  Scenario: Verify 'How To Prepare A Micro Teach' Link on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'How To Prepare A Micro Teach' Link
    Then user should be navigated to 'How To Prepare A Micro Teach' Page

  @home
  Scenario: Verify 'Find out more about your funding and training options' Link on Home Page
    Given the user navigates to the "HOME" page
    When user clicks on 'Find out more about your funding and training options' Link
    Then user should be navigated to 'Find out more about your funding and training options' Page

  @home
  Scenario: Verify Contact Details on Home page
    Given the user navigates to the "HOME" page
    Then User should be able to see Phone and Email contact details

  @home
  Scenario: Verify Tile1 link on the Home page
    Given the user navigate to the "Become a further education (FE) teacher" page
    Then user should be navigated to 'Become a further education FE teacher' page

  @home
  Scenario: Verify Tile2 link on the Home page
    Given the user navigate to the "Is teaching right for me?" page
    Then user should be navigated to 'Is teaching right for me?' page

  @home
  Scenario: Verify Tile3 link on the Home page
    Given the user navigate to the "Search for a teaching job" page
    Then user should be navigated to SearchForATeachingJob page

  @home
  Scenario: Verify Tile4 link on the Home page
    Given the user navigate to the "Find funding and training" page
    Then user should be navigated to 'Find funding and training' page

  @home
  Scenario:  Verify Sign Up feature on Home Page
      Given the user navigates to the "Home" page
      When user enters email id in email field
      And user selects subject name in subjects drop down
      And user selects location in location drop down
      Then User should be able to submit the form

   @home
   Scenario:  Verify Cookies Policy Footer Link on Home Page
      Given the user navigates to the "Home" page
      When user clicks on Cookies Policy Link
      Then user should be navigated to Cookies Policy Page

  @home
    Scenario:  Verify Accessibility statement Footer Link on Home Page
    Given the user navigates to the "Home" page
    When user clicks on Accessibility statement Link
    Then user should be navigated to Accessibility statement Page

  @home
    Scenario:  Verify Privacy Policy Footer Link on Home Page
    Given the user navigates to the "Home" page
    When user clicks on Privacy Policy Link
    Then user should be navigated to Privacy Policy Page