package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OffersPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.xpath("//*[contains(text(),'One Planet') or contains(text(),'Offers')]");
    private final By createOfferButton = By.cssSelector("[class*='addText']");

    private final By offerTitleInput = By.cssSelector("input[placeholder='Enter volunteer offer title']");
    private final By descriptionTextarea = By.cssSelector("textarea[placeholder='Describe your volunteer offer']");
    private final By cityInput = By.cssSelector("input[placeholder='City']");
    private final By countryInput = By.cssSelector("input[placeholder='Country']");
    private final By createButton = By.xpath("//button[normalize-space()='Create']");
    private final By cancelButton = By.xpath("//button[normalize-space()='Cancel']");

    public OffersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPageTitleVisible() {
        return waitUntilAnyElementVisible(pageTitle);
    }

    public boolean isCreateOfferButtonVisible() {
        return waitUntilAnyElementVisible(createOfferButton);
    }

    public void clickCreateOfferButton() {
        WebElement buttonText = waitUntilVisibleElement(createOfferButton);
        buttonText.click();
    }

    public boolean isOfferTitleInputVisible() {
        return waitUntilAnyElementVisible(offerTitleInput);
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
}