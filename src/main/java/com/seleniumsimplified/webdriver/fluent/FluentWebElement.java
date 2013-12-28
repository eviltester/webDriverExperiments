package com.seleniumsimplified.webdriver.fluent;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import java.util.ArrayList;
import java.util.List;

public class FluentWebElement implements WrapsElement{

    private WebElement webElement;

    public FluentWebElement(WebElement aWebElement){
        this.webElement = aWebElement;

    }

    // Fluentese
    public FluentWebElement click() {
        webElement.click();
        return this;
    }

    public void submit() {
        webElement.submit();
    }

    public FluentWebElement sendKeys(CharSequence... keysToSend) {
        webElement.sendKeys(keysToSend);
        return this;
    }

    public FluentWebElement clear() {
        webElement.clear();
        return this;
    }


    public FluentWebElement and(){
        return this;
    }

    public FluentWebElement then(){
        return this;
    }

    // The normal world

    public String getTagName() {
        return webElement.getTagName();
    }

    public String getAttribute(String name) {
        return webElement.getAttribute(name);
    }

    public boolean isSelected() {
        return webElement.isSelected();
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    public String getText() {
        return webElement.getText();
    }

    public List<FluentWebElement> findElements(By by) {
        List<WebElement> webElements = webElement.findElements(by);
        List<FluentWebElement> fWebElements = new ArrayList<FluentWebElement>();

        for(WebElement aWebElement : webElements){
            fWebElements.add(new FluentWebElement(aWebElement));
        }
        return fWebElements;
    }

    public FluentWebElement findElement(By by) {
        return new FluentWebElement(webElement.findElement(by));
    }

    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    public Point getLocation() {
        return webElement.getLocation();
    }

    public Dimension getSize() {
        return webElement.getSize();
    }

    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    @Override
    public WebElement getWrappedElement() {
        return webElement;
    }


}
