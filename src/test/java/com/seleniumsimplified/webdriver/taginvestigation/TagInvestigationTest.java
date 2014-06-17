package com.seleniumsimplified.webdriver.taginvestigation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A quick investigation into getAttribute("text")
 */
public class TagInvestigationTest {

    private static WebDriver driver;


    @Before
    public void setup(){
        driver = new FirefoxDriver();
        driver.get("http://compendiumdev.co.uk/selenium/basic_html_form.html");

    }

    @Test
    public void myStuff(){

        Set<String> tags = new HashSet<String>();

        List<WebElement> elements = driver.findElements(By.cssSelector("*")); // get everything
        System.out.println(" TAG   [  Text    |   Attribute  ]");
        for(WebElement element : elements){
            String getText = element.getText();
            String getAttribute = element.getAttribute("text");

            int getTextLen = 0;
            int maxStringLen = 0;
            int getAttributeLen=0;

            if(getText != null){
                getTextLen = getText.length();
                if(getTextLen>0)
                    maxStringLen = (getTextLen>16) ? 15 : getTextLen -1;
                getText = getText.replaceAll("\n","").substring(0, maxStringLen);
                getText = (getTextLen > 16) ? getText + "..." : getText;
            }

            maxStringLen = 0;
            if(getAttribute != null){
                getAttributeLen = getAttribute.length();
                if(getAttributeLen>0)
                    maxStringLen = (getAttributeLen>16) ? 15 : getAttributeLen -1;
                getAttribute = getAttribute.replaceAll("\n","").substring(0, maxStringLen);
                getAttribute = (getAttributeLen > 16) ? getAttribute + "..." : getAttribute;
            }

            if(!tags.contains(element.getTagName())){
                System.out.println(element.getTagName());
                System.out.println(" - getText : " + "(" + getTextLen + ")" + getText);
                System.out.println(" - getAttribute : " + "(" + getAttributeLen + ")" + getAttribute);
            }
            tags.add(element.getTagName());

            if(getAttributeLen > getTextLen){
                System.out.println("***" + element.getTagName() + "[" + getTextLen + "]" + " vs " + getAttribute);
            }
        }

    }

    @After
    public void cleanup(){
        driver.quit();
    }



}
