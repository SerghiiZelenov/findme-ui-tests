package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccountPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logoutButton = By.xpath("//*[normalize-space()='Logout']");
    private final By myOffersSection = By.xpath("//*[normalize-space()='My Offers']");
    private final By activeTab = By.xpath("//*[normalize-space()='Active']");
    private final By inactiveTab = By.xpath("//*[normalize-space()='Inactive']");

    private final By savedSection = By.xpath("//*[normalize-space()='Saved']");
    private final By chatsSection = By.xpath("//*[normalize-space()='Chats']");
    private final By settingsSection = By.xpath("//*[normalize-space()='Settings']");
    private final By adminPanelSection = By.xpath("//*[normalize-space()='Admin Panel']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isLogoutButtonVisible() {
        return waitUntilAnyElementVisible(logoutButton);
    }

    public boolean isMyOffersSectionVisible() {
        return waitUntilAnyElementVisible(myOffersSection);
    }

    public boolean isActiveTabVisible() {
        return waitUntilAnyElementVisible(activeTab);
    }

    public boolean isInactiveTabVisible() {
        return waitUntilAnyElementVisible(inactiveTab);
    }

    public boolean isSavedSectionVisible() {
        return waitUntilAnyElementVisible(savedSection);
    }

    public boolean isChatsSectionVisible() {
        return waitUntilAnyElementVisible(chatsSection);
    }

    public boolean isSettingsSectionVisible() {
        return waitUntilAnyElementVisible(settingsSection);
    }

    public boolean isAdminPanelSectionVisible() {
        return waitUntilAnyElementVisible(adminPanelSection);
    }

    public void clickLogout() {
        waitUntilVisibleElement(logoutButton).click();
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

    public boolean isAdminPanelSectionNotVisible() {
        return driver.findElements(adminPanelSection)
                .stream()
                .noneMatch(WebElement::isDisplayed);
    }

    public void clickSavedSection() {
        waitUntilVisibleElement(savedSection).click();
    }
}