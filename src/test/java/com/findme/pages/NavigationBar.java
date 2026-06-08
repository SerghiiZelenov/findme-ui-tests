package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {

    private final WebDriver driver;

    private final By homeLink = By.xpath("//a[contains(text(),'Home')]");
    private final By categoriesLink = By.xpath("//a[contains(text(),'Categories')]");
    private final By offersLink = By.xpath("//a[contains(text(),'Offers')]");
    private final By mapLink = By.xpath("//a[contains(text(),'Map')]");
    private final By loginLink = By.xpath("//a[contains(text(),'Login')]");

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomeLinkVisible() {
        return driver.findElement(homeLink).isDisplayed();
    }

    public boolean isCategoriesLinkVisible() {
        return driver.findElement(categoriesLink).isDisplayed();
    }

    public boolean isOffersLinkVisible() {
        return driver.findElement(offersLink).isDisplayed();
    }

    public boolean isMapLinkVisible() {
        return driver.findElement(mapLink).isDisplayed();
    }

    public boolean isLoginLinkVisible() {
        return driver.findElement(loginLink).isDisplayed();
    }

    public void clickCategories() {
        driver.findElement(categoriesLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickMap() {
        driver.findElement(mapLink).click();
    }

    public void clickOffers() {
        driver.findElement(offersLink).click();
    }

    public void clickHome() {
        driver.findElement(homeLink).click();
    }
}