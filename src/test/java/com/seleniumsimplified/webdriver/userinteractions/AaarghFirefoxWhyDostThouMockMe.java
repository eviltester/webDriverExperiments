package com.seleniumsimplified.webdriver.userinteractions;

import org.junit.After;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

public class AaarghFirefoxWhyDostThouMockMe {

    public static final String KEY_CLICK_DISPLAY =
            "http://compendiumdev.co.uk/selenium/key_click_display.html";
    WebDriver driver;

    @Test
    public void simpleUserInteractionInFirefox(){

        driver = new FirefoxDriver();

        checkSimpleCtrlBInteractionWorks();


    }

    @Test
    public void simpleUserInteractionInFirefoxNoProfile(){

        driver = new FirefoxDriver();

        checkSimpleCtrlBInteractionWorks();


    }

    private void checkSimpleCtrlBInteractionWorks() {

        driver.get(KEY_CLICK_DISPLAY);

        new Actions(driver).keyDown(Keys.CONTROL).
                sendKeys("b").
                keyUp(Keys.CONTROL).
                perform();

        System.out.println(driver.findElement(By.id("events")).getText());

        assertEquals( "only expected 4 events",
                      4,
                      driver.findElements(
                            By.cssSelector("#events p")).size());
    }

    @Test
    public void simpleUserInteractionInGoogleChrome(){
        String currentDir = System.getProperty("user.dir");

        // amend this for your location of chromedriver
        String chromeDriverLocation = currentDir + "/../tools/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-plugins");
        options.addArguments("disable-extensions");

        driver = new ChromeDriver(options);

        checkSimpleCtrlBInteractionWorks();
    }

    @After
    public void quitDriver(){
        driver.quit();
    }
}
