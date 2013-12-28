package com.seleniumsimplified.webdriver.fluent;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static junit.framework.Assert.assertTrue;

public class FluentWebElementUsageTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        driver = new FirefoxDriver();
        driver.get("http://www.compendiumdev.co.uk/selenium/search.php");
    }

    @Test
    public void whatIfWeHadAFluentWebElementForSearchPage(){

        FluentWebElement searchBox = new FluentWebElement(driver.findElement(By.name("q")));

        searchBox.clear().then().sendKeys("Fluent Programming").and().submit();

        assertTrue(driver.getTitle().contains("Fluent Programming"));
    }

    @Test
    public void whatIfWeDidNotHaveAFluentWebElementForSearchPage(){

        WebElement searchBox = driver.findElement(By.name("q"));

        searchBox.clear();
        searchBox.sendKeys("Normal Programming");

        driver.findElement(By.name("btnG")).click();

        assertTrue(driver.getTitle().contains("Normal Programming"));
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
