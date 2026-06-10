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