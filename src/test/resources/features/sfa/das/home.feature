
Feature: Find schemes for your business - check Home page anchors

  Scenario: Confirm  anchors on the HOME page
    Given the user navigates to the "HOME" page
    Then all HOME anchors link to the correct pages

  Scenario: Confirm  anchors on the HOME page
    Given the user navigates to the "What is FE teaching?" page
    Then all FE_Teaching anchors link to the correct pages

  Scenario: Confirm  anchors on the HOME page
    Given the user navigates to the "Hear from current FE teachers" page
    Then all Hear_From_Current_FE_Teachers anchors link to the correct pages

  Scenario: Confirm  anchors on the HOME page
    Given the user navigates to the "Find a job in FE" page
    Then all Find_a_job_in_FE anchors link to the correct pages

  Scenario: Confirm  anchors on the HOME page
    Given the user navigates to the "Find your local colleges" page
    Then all Find_Your_Local_Colleges anchors link to the correct pages

