package sfa.das.base;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import sfa.das.Environments;
import sfa.das.Log;
import sfa.das.driver.DriverFactory;
import sfa.das.utils.WebUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Hooks {


        private static WebDriver driver;
        public static Environments.Environment environment;

        @Before()
        public static void setup(Scenario scenario) {
            String webdriver = System.getProperty("browser", "CHROME_HEADLESS").toLowerCase();
            try {
                driver = DriverFactory.getBaseDriver(DriverFactory.DriverType.value(webdriver));
                driver.manage().window().maximize();
                //need more time for videos to load
                driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        final var env = System.getProperty("env", "live");
        environment = Environments.get(env);
        driver.get(environment.getUrl());
        //WebUtils.acceptCookies();
    }

        public static WebDriver getDriver(){
        return driver;
    }

        @After()
        public void tearDown(Scenario scenario) throws Exception {
        if (scenario.isFailed()) {

            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", String.valueOf(scenario) + UUID.randomUUID());

            LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
            List<LogEntry> logs = logEntries.getAll();
            for (LogEntry entry : logs) {
                Log.Info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }
        }
            driver.quit();
    }





}



