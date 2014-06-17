package com.seleniumsimplified.webdriver.cookies;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

public class FirefoxCookiesWorkFineButChromeAddsAdditional {

    WebDriver driver;

    @Test
    public void FirefoxCookiesDeleteAndAddWorksFine(){

        driver = new FirefoxDriver();

        doTheActualCookieDeleteThenAddOnTheBrowser();
    }

    @Test
    public void ChromeAddsAnExtraCookiesWhenItAddsOneWithADomain(){

        // amend to point to your location of chromedriver
        String currentDir = System.getProperty("user.dir");
        String chromeDriverLocation = currentDir + "/../tools/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-plugins");
        options.addArguments("disable-extensions");

        driver = new ChromeDriver(options);

        doTheActualCookieDeleteThenAddOnTheBrowser();

        /*
            Chrome creates an additional cookie of the form below
            when a cookie is added which has a domain specified in
            the cookie to add

            ChromeDriverwjers908fljsdf37459fsdfgdfwru=;
            path=/selenium;
            domain=.compendiumdev.co.uk
         */
    }

    @After
    public void quitDriver(){
        driver.quit();
    }

    private void doTheActualCookieDeleteThenAddOnTheBrowser() {
        driver.get("http://compendiumdev.co.uk/selenium/" + "search.php");

        assertEquals("2 Cookies created on initial visit", 2, driver.manage().getCookies().size());

        // find cookie to cut n paste
        Cookie aCookie = driver.manage().
                getCookieNamed("seleniumSimplifiedSearchNumVisits");

        System.out.println("Going to work with this cookie:");
        System.out.println(aCookie.toString().replaceAll(";",";\n"));

        int cookieCount = driver.manage().getCookies().size();
        // delete cookie
        driver.manage().deleteCookie(aCookie);
        assertEquals("One less cookie expected", cookieCount-1, driver.manage().getCookies().size());

        // recreate the cookie works fine on Firefox, but fails on Chrome
        //driver.manage().addCookie(aCookie);


        // if I build the cookie from scratch then it fails on chrome when I add the domain
        driver.manage().addCookie(
                new Cookie.Builder(aCookie.getName(),
                        aCookie.getValue()).
                        path(aCookie.getPath()).
                        // enable line below to have test fail on chrome
                        // domain(aCookie.getDomain()).
                        expiresOn(aCookie.getExpiry()).
                        isHttpOnly(aCookie.isHttpOnly()).
                        isSecure(aCookie.isSecure()).
                        build());


        System.out.println(driver.manage().getCookies().toString().replaceAll(";",";\n"));
        assertEquals("Same number of cookies expected", cookieCount, driver.manage().getCookies().size());
    }



}
