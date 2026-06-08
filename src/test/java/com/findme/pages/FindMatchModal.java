package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindMatchModal {

    private final WebDriver driver;

    private final By modalTitle = By.xpath("//*[contains(text(),'Find your Match') or contains(text(),'Find Your Match')]");
    private final By keywordInput = By.cssSelector("input[placeholder*='keyword'], input[placeholder*='Keyword'], input[placeholder*='tag'], input[placeholder*='Tag']");
    private final By countryInput = By.cssSelector("input[placeholder*='Country'], input[placeholder*='country']");
    private final By cityInput = By.cssSelector("input[placeholder*='City'], input[placeholder*='city']");
    private final By searchButton = By.xpath("//*[normalize-space()='Search']");
    private final By radiusSelect = By.xpath("//*[contains(text(),'km')]");
    private final WebDriverWait wait;

    public FindMatchModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isModalTitleVisible() {
        return driver.findElement(modalTitle).isDisplayed();
    }

    public boolean isKeywordInputVisible() {
        return driver.findElement(keywordInput).isDisplayed();
    }

    public boolean isCountryInputVisible() {
        return driver.findElement(countryInput).isDisplayed();
    }

    public boolean isCityInputVisible() {
        return driver.findElement(cityInput).isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        return driver.findElement(searchButton).isDisplayed();
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public boolean isRadiusVisible() {
        List<WebElement> radiusElements = driver.findElements(radiusSelect);
        return !radiusElements.isEmpty() && radiusElements.getFirst().isDisplayed();
    }

    public void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void selectCountry(String country) {
        driver.findElement(countryInput).sendKeys(country);
        By countrySuggestion = By.xpath("//*[normalize-space()='" + country + "']");
        wait.until(ExpectedConditions.elementToBeClickable(countrySuggestion)).click();
    }
}