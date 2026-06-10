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
    private final By createButton = By.xpath("//button[normalize-space()='Create']");
    private final By cancelButton = By.xpath("//button[normalize-space()='Cancel']");

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
}