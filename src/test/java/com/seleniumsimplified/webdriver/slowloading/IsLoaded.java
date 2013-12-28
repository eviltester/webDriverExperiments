package com.seleniumsimplified.webdriver.slowloading;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class IsLoaded {
    private final WebDriver driver;

    public IsLoaded(WebDriver driver) {
        this.driver = driver;
    }

    public static IsLoaded forThis(WebDriver driver) {
        IsLoaded isLoaded = new IsLoaded(driver);
        return isLoaded;
    }

    public IsLoaded whenElementIsVisible(By usingBy, String description) {
        try{
            if(driver.findElement(usingBy).isDisplayed()){
                return this;
            }else{
                throw new Error(description + " is not visible");
            }
        }catch(WebDriverException e){
            throw new Error(description + " is not visible", e);
        }
    }

    public IsLoaded whenElementIsEnabled(By usingBy, String description) {
        try{
            if(driver.findElement(usingBy).isEnabled()){
                return this;
            }else{
                throw new Error(description + " is not enabled");
            }
        }catch(WebDriverException e){
            throw new Error(description + " is not enabled", e);
        }
    }
}
