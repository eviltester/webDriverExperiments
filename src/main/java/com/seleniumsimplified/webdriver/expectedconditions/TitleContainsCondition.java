package com.seleniumsimplified.webdriver.expectedconditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class TitleContainsCondition implements ExpectedCondition<Boolean> {
    private String subMenuText;

    public TitleContainsCondition(final String subMenuText) {
        this.subMenuText=subMenuText;
    }

    @Override
    public Boolean apply( WebDriver webDriver) {
        return webDriver.getTitle().contains(this.subMenuText);
    }
}
