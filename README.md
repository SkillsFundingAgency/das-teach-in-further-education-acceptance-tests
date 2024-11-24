Overview: This project is a test automation framework built using:
* Cucumber for Behavior-Driven-Development(BDD). 
* Selenium WebDriver for browser automation
* Java as the programming language.

Framework supports:
* Writing test scenarios in Gherkin syntax.
* Parameterized scenarios using scenario outlines. 
* Integration with cucumber reporting. 
* Extensible Page Object Model (POM)

Prerequisite:
1. Java JDK 11 or later installed and added to PATH
2. Maven installed and added to PATH (NOTE: We are using maven wrapper.) 
3. To download maven wrapper dependencies run the following command:  mvnw.cmd clean install
4. A supported browser and its WebDriver executable available. 

Framework structure:
* src/test/java 
- StepDefinitions: Contains step definitions for Gherkin scenarios
- pageObjects: Page object model classes for encapsulating web elements.
- utils: Helper classes (e.g. for reusable methods)

* src/test/resources
- features: Gherkin features files for test scenarios.
- environments.yml: Contains URLs for all environments (e.g. live, pp, at, test)

* test-output
- Test reports generated after execution (Test Report Path: target/cucumber/cucumber-html-reports/js/overview-features.html)

Configuration: 
- update env value in hooks.java class as per environments.yml file
- Set the Urls in environments.yml file

Running Tests:
* To execute all scenarios: 
- either by using command mvn test in Intellij terminal or Running the TestRunner class 
- We can run individual Test scenarios from feature file as well

* To run tests by tag:
- mvn clean test -D"cucumber.filter.tags=@Global"

* To generate a report: 
- Test reports generated after execution (Test Report Path: target/cucumber/cucumber-html-reports/js/overview-features.html)

Reporting: This framework supports:
- Cucumber HTML Reports: Automatically generated after test execution

Troubleshooting:
- Update Maven dependencies in the pom.xml if outdated and run the command mvnw clean install through terminal