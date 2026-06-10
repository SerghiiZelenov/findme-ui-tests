package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CreateOfferPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By titleInput = By.cssSelector("input[placeholder='Enter volunteer offer title']");
    private final By descriptionTextarea = By.cssSelector("textarea[placeholder='Describe your volunteer offer']");
    private final By cityInput = By.cssSelector("input[placeholder='City']");
    private final By countryInput = By.cssSelector("input[placeholder='Country']");
    private final By latitudeInput = By.cssSelector("input[placeholder='Latitude']");
    private final By longitudeInput = By.cssSelector("input[placeholder='Longitude']");
    private final By tagsInput = By.cssSelector("input[placeholder='Enter tags separated by commas']");

    private final By useCurrentLocationButton = By.xpath("//button[normalize-space()='Use Current Location']");
    private final By findCoordinatesButton = By.xpath("//button[normalize-space()='Find Coordinates']");
    private final By activeOfferLabel = By.xpath("//*[normalize-space()='Active Offer']");
    private final By createButton = By.cssSelector("button[type='submit']");
    private final By cancelButton = By.xpath("//form//button[@type='button' and normalize-space()='Cancel']");

    private WebElement waitUntilVisibleElement(By locator) {
        return wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);

            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) {
                        return element;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            return null;
        });
    }

    public CreateOfferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isTitleInputVisible() {
        return waitUntilAnyElementVisible(titleInput);
    }

    public boolean isDescriptionTextareaVisible() {
        return waitUntilAnyElementVisible(descriptionTextarea);
    }

    public boolean isCityInputVisible() {
        return waitUntilAnyElementVisible(cityInput);
    }

    public boolean isCountryInputVisible() {
        return waitUntilAnyElementVisible(countryInput);
    }

    public boolean isLatitudeInputVisible() {
        return waitUntilAnyElementVisible(latitudeInput);
    }

    public boolean isLongitudeInputVisible() {
        return waitUntilAnyElementVisible(longitudeInput);
    }

    public boolean isTagsInputVisible() {
        return waitUntilAnyElementVisible(tagsInput);
    }

    public boolean isUseCurrentLocationButtonVisible() {
        return waitUntilAnyElementVisible(useCurrentLocationButton);
    }

    public boolean isFindCoordinatesButtonVisible() {
        return waitUntilAnyElementVisible(findCoordinatesButton);
    }

    public boolean isActiveOfferLabelVisible() {
        return waitUntilAnyElementVisible(activeOfferLabel);
    }

    public boolean isCreateButtonVisible() {
        return waitUntilAnyElementVisible(createButton);
    }

    public boolean isCancelButtonVisible() {
        return waitUntilAnyElementVisible(cancelButton);
    }

    private boolean waitUntilAnyElementVisible(By locator) {
        try {
            wait.until(driver -> {
                List<WebElement> elements = driver.findElements(locator);

                for (WebElement element : elements) {
                    try {
                        if (element.isDisplayed()) {
                            return true;
                        }
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                }

                return false;
            });

            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickCancelButton() {
        WebElement button = waitUntilVisibleElement(cancelButton);

        try {
            button.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    button
            );
        }
    }

    public boolean isTitleInputNotVisible() {
        try {
            wait.until(driver -> {
                List<WebElement> elements = driver.findElements(titleInput);

                for (WebElement element : elements) {
                    try {
                        if (element.isDisplayed()) {
                            return false;
                        }
                    } catch (StaleElementReferenceException e) {
                        return true;
                    }
                }
                return true;
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickCreateButton() {
        WebElement button = waitUntilVisibleElement(createButton);

        try {
            button.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    button
            );
        }
    }

    public void enterDescription(String description) {
        WebElement input = waitUntilVisibleElement(descriptionTextarea);
        input.clear();
        input.sendKeys(description);
    }

    public void enterCity(String city) {
        WebElement input = waitUntilVisibleElement(cityInput);
        input.clear();
        input.sendKeys(city);
    }

    public void enterCountry(String country) {
        WebElement input = waitUntilVisibleElement(countryInput);
        input.clear();
        input.sendKeys(country);
    }

    public void enterLatitude(String latitude) {
        WebElement input = waitUntilVisibleElement(latitudeInput);
        input.clear();
        input.sendKeys(latitude);
    }

    public void enterLongitude(String longitude) {
        WebElement input = waitUntilVisibleElement(longitudeInput);
        input.clear();
        input.sendKeys(longitude);
    }

    public void enterTags(String tags) {
        WebElement input = waitUntilVisibleElement(tagsInput);
        input.clear();
        input.sendKeys(tags);
    }

    public void enterTitle(String title) {
        WebElement input = waitUntilVisibleElement(titleInput);
        input.clear();
        input.sendKeys(title);
    }
}