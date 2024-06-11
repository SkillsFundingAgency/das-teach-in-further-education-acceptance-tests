package sfa.das;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features/sfa/das",
        glue = {"sfa.das"},
        plugin = {
                "timeline:target/timeline/",
                "pretty",
                "html:target/feature/cucumber",
                "json:target/feature/json/cucumber.json",
                "junit:target/feature/junit/cucumber.xml",
                "me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        tags =  "not @test")
public class TestRunner {
}

