package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private final WebDriver driver;

    private final By logoutButton = By.xpath("//*[normalize-space()='Logout']");
    private final By myOffersSection = By.xpath("//*[contains(text(),'My Offers')]");
    private final By activeTab = By.xpath("//*[normalize-space()='Active']");
    private final By inactiveTab = By.xpath("//*[normalize-space()='Inactive']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoutButtonVisible() {
        return driver.findElement(logoutButton).isDisplayed();
    }

    public boolean isMyOffersSectionVisible() {
        return driver.findElement(myOffersSection).isDisplayed();
    }

    public boolean isActiveTabVisible() {
        return driver.findElement(activeTab).isDisplayed();
    }

    public boolean isInactiveTabVisible() {
        return driver.findElement(inactiveTab).isDisplayed();
    }

    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }
}