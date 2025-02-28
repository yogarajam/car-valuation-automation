package com.example.carvaluation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.carvaluation.model.CarDetails;

public class CarValuationPage {
    private WebDriver driver;
    private By registrationInput = By.id("registration-number"); // Example locator, update for motorway.co.uk
    private By searchButton = By.id("search-button"); // Example locator
    private By resultMake = By.className("make"); // Example locator
    private By resultModel = By.className("model"); // Example locator
    private By resultYear = By.className("year"); // Example locator
    private By resultValue = By.className("value"); // Example locator

    public CarValuationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public void enterRegistration(String registration) {
        WebElement input = driver.findElement(registrationInput);
        input.clear();
        input.sendKeys(registration);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public CarDetails getCarDetails() {
        String make = driver.findElement(resultMake).getText();
        String model = driver.findElement(resultModel).getText();
        int year = Integer.parseInt(driver.findElement(resultYear).getText());
        String valueText = driver.findElement(resultValue).getText().replace("£", "").replace(",", "");
        double value = Double.parseDouble(valueText);
        return new CarDetails(null, make, model, year, value); // Registration not returned by website
    }
}