package com.seleniumsimplified.webdriver.sports;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SportsEventTributeTest {

    @Test
    public void bounceThatWindow(){
        WebDriver driver = new FirefoxDriver();
        driver.get("file://" + System.getProperty("user.dir") + "/jsrunner.html");
        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();

        int changeWidth = 200; int changeHeight = 210;
        int xDir = 8; int yDir = 8; int xDirIncrement = xDir; int yDirIncrement = yDir;

        driver.manage().window().setSize(new Dimension(changeWidth,changeHeight));

        Point position = driver.manage().window().getPosition();

        String banner = "***BANG****........ AND THEY ARE OFF........ Automation can be fun. " +
                " EvilTester.com present a javascript and browser" +
                " animation using Selenium 2 WebDriver tribute to the" +
                " sporting event that cannot be named lest we be sued";
        int bannerStart = 0;

        for(int bounceIterations = 0; bounceIterations < 1000; bounceIterations ++){

            position = position.moveBy(xDir,yDir);
            driver.manage().window().setPosition(position);

            if(position.getX()>(fullScreenSize.getWidth() - changeWidth)){ xDir = -1 * xDirIncrement; }
            if(position.getX()<0){ xDir = xDirIncrement; }
            if(position.getY()>(fullScreenSize.getHeight() - changeHeight)){ yDir = -1 * yDirIncrement; }
            if(position.getY()<0){ yDir = yDirIncrement; }

            //try {Thread.sleep(20);} catch (InterruptedException e) {}
            String displayBanner = banner.substring(bannerStart,bannerStart+30);
            ((JavascriptExecutor)driver).executeScript("document.title='" + displayBanner + "';");
            bannerStart++;
            if(bannerStart > banner.length()-35){banner += banner;}
        }

        driver.quit();
    }
}
