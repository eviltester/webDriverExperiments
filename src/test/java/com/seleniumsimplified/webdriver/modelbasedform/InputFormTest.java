package com.seleniumsimplified.webdriver.modelbasedform;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public class InputFormTest {

    private WebDriver driver;
    Random random;
    Map<String, By> formFields;
    CountryOptionRandomPicker countryPicker;
    String report="";
    String errorReport = "";

    @Before
    public void startBrowser(){
        driver = new ChromeDriver();
        random = new Random();
        formFields = new HashMap<>();
        formFields.put("firstname", By.id("firstname"));
        formFields.put("lastname", By.id("surname"));
        formFields.put("age", By.id("age"));
        formFields.put("country", By.id("country"));
        formFields.put("notes", By.id("notes"));
        countryPicker = new CountryOptionRandomPicker(driver);
    }

    @Test
    public void aTest(){

        // there are only about 165 countries in the list
        int numberOfTimesToRun=200;

        for(int x=0; x< numberOfTimesToRun; x++) {
            report="";
            reportThis(String.format("%n%n-----%nTest: %d%n-----%n%n", x));
            try {
                visitForm();
                fillFormCorrectly();
                submitForm();
                checkValidInput();
            }catch(Exception e){
                errorReport = errorReport + report;
            }
        }

        System.out.println("\n\n\nFinal Error Report");
        System.out.println("==================");
        System.out.println(errorReport);

        if(errorReport.length()>0){
            Assert.fail();
        }
    }

    private void visitForm() {
        driver.get("https://testpages.herokuapp.com/styled/validation/input-validation.html");
    }

    private void fillFormCorrectly() {
        String[] theFields = {"firstname", "lastname", "age", "country", "notes"};
        List<String> fieldNames = new ArrayList<>();
        fieldNames.addAll(Arrays.asList(theFields));

        while(fieldNames.size()>0){
            String nextFieldToFill = fieldNames.get(
                                    random.nextInt(fieldNames.size()));
            fillFieldWithValidRandomValue(nextFieldToFill);
            fieldNames.remove(nextFieldToFill);
        }
    }

    private void fillFieldWithValidRandomValue(final String nextFieldToFill) {

        String stringValue;

        switch (nextFieldToFill){
            case "firstname":
                // 5 to 89
                stringValue = getRandomLengthStringValue(5, 89, "firstname");
                driver.findElement(formFields.get(nextFieldToFill)).sendKeys(stringValue);
                break;
            case "lastname":
                // 11 to 79
                stringValue = getRandomLengthStringValue(11, 79, "lastname");
                driver.findElement(formFields.get(nextFieldToFill)).sendKeys(stringValue);
                break;
            case "age":
                // 18 to 80
                stringValue = Integer.toString(randomIntBetween(18,80));
                reportThis(String.format("Using for age:%n%s%n", stringValue));
                driver.findElement(formFields.get(nextFieldToFill)).sendKeys(stringValue);
                break;
            case "country":
                // valid country from list
                int countryIndex = countryPicker.getNextRandomCountryIndex();
                Select select = new Select(driver.findElement(formFields.get(nextFieldToFill)));
                select.selectByIndex(countryIndex);
                reportThis(String.format("Using for Country:%n%s%n", select.getFirstSelectedOption().getText()));
                break;
            case "notes":
                // optional, any length
                stringValue = getRandomLengthStringValue(0, 25, "notes");
                driver.findElement(formFields.get(nextFieldToFill)).sendKeys(stringValue);
        }
    }

    private String getRandomLengthStringValue(final int minlen, final int maxlen, final String fieldName) {
        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
        String generatedValue = "";

        reportThis(String.format("Using for %s:%n", fieldName));
        int charCount = randomIntBetween(minlen, maxlen);

        while(charCount>0){
            int charAt = random.nextInt(validChars.length());
            generatedValue = generatedValue + validChars.substring(charAt, charAt+1);
            charCount--;
        }
        reportThis(String.format("%s:%n", generatedValue));
        return generatedValue;
    }

    private void reportThis(final String toReport) {
        report = report + "\n" + toReport;
        System.out.println(toReport);
    }

    private int randomIntBetween(int min, int max){
        return random.nextInt((max-min)+1)+min;
    }

    private void checkValidInput() {

        new WebDriverWait(driver,10).
                until(ExpectedConditions.
                        presenceOfElementLocated(By.id("valid-input")));
    }

    private void submitForm() {
        if(random.nextBoolean()){
            reportThis("Submit Form");
            driver.findElement(By.name("userdata")).submit();
        }else{
            final WebElement button = driver.findElement(By.cssSelector("input[type=submit]"));
            if(random.nextBoolean()){
                reportThis("Submit Button");
                button.submit();
            }else{
                reportThis("Submit Button Click");
                button.click();
            }
        }
    }


    private class CountryOptionRandomPicker {
        private final WebDriver driver;
        List<Integer> unselectedOptions;

        public CountryOptionRandomPicker(final WebDriver driver) {
            this.driver = driver;
            unselectedOptions= new ArrayList<>();
        }

        public int getNextRandomCountryIndex() {
            if(unselectedOptions.size()==0){
                int numberOfOptions = driver.findElement(
                                            formFields.get("country")).
                                            findElements(By.tagName("option")).size();
                for(int x = 0; x<numberOfOptions; x++){
                    unselectedOptions.add(x);
                }
            }
            int indexToReturn = random.nextInt(unselectedOptions.size());;
            int valueToReturn = unselectedOptions.get(indexToReturn);
            unselectedOptions.remove(indexToReturn);
            return valueToReturn;
        }
    }
}
