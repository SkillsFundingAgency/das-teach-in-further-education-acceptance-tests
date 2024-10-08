package sfa.das.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sfa.das.base.Hooks;
import sfa.das.driver.DriverFactory;
import sfa.das.pages.HomePage;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class WebUtils {

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


}
