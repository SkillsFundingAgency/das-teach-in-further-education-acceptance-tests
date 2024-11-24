package sfa.das.utils;

import org.junit.Assert;
import org.openqa.selenium.*;
import sfa.das.base.Hooks;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebUtils {

    //public static WebDriver driver;
    private WebElement webElement;

   /* public WebUtils(WebDriver driver) {
        this.driver = driver;
    }*/

    public static List<String> checkAllLinks(WebDriver driver) {
        List<String> brokenLinks = new ArrayList<>();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                if (url.startsWith("mailto:")) {
                    System.out.println("Skipping email link: " + url);
                    continue;
                }
                if (url.startsWith("tel:")) {
                    System.out.println("Skipping email link: " + url);
                    continue;
                }
                if (url.endsWith("/")) {
                    url=url.substring(0,url.length()-1);
                    continue;
                }
                if (!verifyLink(url))
                    brokenLinks.add(url);
            } else {
                System.out.println("Link is empty or null: " + link.getText());
            }

        }
        return brokenLinks;

    }

    private static boolean verifyLink(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            return responseCode == 200;

        } catch (Exception e) {
            System.out.println("Error checking link: " + linkUrl + " - " + e.getMessage());
            return false;
        }
    }

    public WebElement getElement(By locator) {
            return Hooks.getDriver().findElement(locator);
    }

    public void setElement(By locator){
        try{
            this.webElement=Hooks.getDriver().findElement(locator);
        }catch (NoSuchElementException e){
            System.out.println("Element not found: "+locator);
        }
    }

    public static void acceptCookies(){
        try{
            WebElement acceptCookiesButton=Hooks.getDriver().findElement(By.xpath("//button[@id='acceptCookiesButton']"));
            if (acceptCookiesButton.isDisplayed()){
                acceptCookiesButton.click();
                System.out.println("Cookies Accepted.");
            }
        }catch (Exception e){
            System.out.println("No cookies popup found or unable to click.");
        }
    }

    public static void validateLinks(WebDriver driver, String onThisPageLinks){
        List<WebElement> links=driver.findElements(By.xpath(onThisPageLinks));
        for (WebElement link:links){
            String originalUrl=link.getAttribute("href");
            String linkText=link.getText().trim().toLowerCase();
            System.out.println("Original URL: "+originalUrl);
            System.out.println("Link Text: "+linkText);
            link.click();
            String currentUrl=driver.getCurrentUrl();
            System.out.println("Current URL: "+currentUrl);
            if (originalUrl.contains("#")) {
                String[] currentParts = currentUrl.split("#", 2);
                if (currentUrl.length() > 1) {
                    String contentAfterHash = currentParts[1].replace("_"," ");
                    String firstKeywordAfterHash = contentAfterHash.split("\\s+")[0];
                    Assert.assertTrue("Link Text '" + linkText + "' does not contain first keyword after #: '" + firstKeywordAfterHash + "'", linkText.contains(firstKeywordAfterHash));
                }
            }else {
                Assert.assertEquals("The current Url does not match original url: ",currentUrl,originalUrl);
            }
            driver.navigate().back();
        }
    }

}
  /*  public static void clickElement(WebElement element){
        try{
            WebDriverWait wait=new WebDriverWait(Hooks.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch(NoSuchElementException e){
            System.out.println("Element not found: "+e.getMessage());
        }catch (TimeoutException e){
            System.out.println("Element was not clickable within the timeout: "+e.getMessage());
        }catch (Exception e){
            System.out.println("An error occurred while clicking the element: "+e.getMessage());
        }
    }*/



