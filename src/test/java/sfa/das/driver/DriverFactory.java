package sfa.das.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.javascript.SilentJavaScriptErrorListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Factory implementation to retrieve a suitable Selenium WebDriver.
 */
@Slf4j
public class DriverFactory {

    private static WebDriver driver;

       public enum DriverType {
        HTMLUNIT("htmlunit"),
        CHROME_HEADLESS(""),
        CHROME("chrome");

        DriverType(String text) {
            this.text = text;
        }

        private String text;

        public static DriverType value(final String text) {
            for (DriverType value : DriverType.values()) {
                if (value.getText().equals(text)) {
                    return value;
                }
            }
            return DriverType.CHROME;
        }
        public String getText() {
            return this.text;
        }
    }

    /**
     * Default no-args constructor.
     */
    private DriverFactory() {
        // private constructor to prevent instantiation
    }

    /**
     * Get Web Driver based on current system properties. These can be set using the "-D"
     * flag, e.g.
     * mvn test -Dbrowser=chrome
     * <p>
     * Valid browser values are:
     * - htmlunit - fast and headless
     * - chrome - Google Chrome browser running in foreground
     * <p>
     * If no value is specified then htmlunit is assumed.
     *
     * @return selenium web driver.
     */

    public static WebDriver getBaseDriver(DriverType driverType) throws Exception {
        while (driver == null) {
            switch (driverType) {
                case HTMLUNIT:
                    var htmlUnitDriver = new HtmlUnitDriver() {
                        @Override
                        protected WebClient modifyWebClient(WebClient webClient) {
                            webClient = super.modifyWebClient(webClient);
                            webClient.getOptions().setThrowExceptionOnScriptError(false);
                            webClient.getOptions().setJavaScriptEnabled(true);
                            webClient.setScriptPreProcessor(new FixTypeErrorScriptPreProcessor());
                            webClient.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
                            return webClient;
                        }
                    };
                    return htmlUnitDriver;

                case CHROME_HEADLESS:
                    WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir") + "/browser").setup();

                    //
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless=new");

                    return new ChromeDriver(options);
                case CHROME:
                    WebDriverManager.chromedriver().cachePath(System.getProperty("user.dir") + "/browser").setup();
                    //driver = new ChromeDriver();
                    return new ChromeDriver();

                default:
                    throw new DriverNotFoundException("Unsupported webdriver");

            }


        }
        return driver;
    }

    /*public static WebDriver getDriver(){
        return driver;
    }*/

    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }

}

