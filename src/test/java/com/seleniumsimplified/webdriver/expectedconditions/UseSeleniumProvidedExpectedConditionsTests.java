package com.seleniumsimplified.webdriver.expectedconditions;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UseSeleniumProvidedExpectedConditionsTests {

    private static WebDriver driver;

    @BeforeClass
    public static void startSelenium(){
        driver = new FirefoxDriver();
    }

    @Test
    public void useInBuiltExpectedConditions(){
        driver.get("http://compendiumdev.co.uk/selenium/calculate.php");
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("Selenium"));
    }

    @Test
    public void withoutUsingInBuildExpectedConditions(){
        driver.get("http://compendiumdev.co.uk/selenium/calculate.php");
        new WebDriverWait(driver,10).
                until(new TitleContainsCondition("Selenium"));
    }

    @Test
    public void withoutUsingInBuildExpectedConditionsAndFactory(){
        driver.get("http://compendiumdev.co.uk/selenium/calculate.php");
        new WebDriverWait(driver,10).until(WaitFor.titleContainsCondition("Selenium"));
    }

    @AfterClass
    public static void closeSelenium(){
        driver.quit();
    }


}
