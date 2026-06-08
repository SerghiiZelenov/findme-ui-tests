package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {

    private final WebDriver driver;

    private final By pageTitle = By.xpath("//*[contains(text(),'Offers')]");
    private final By createOfferButton = By.xpath("//*[contains(text(),'Create') or contains(text(),'Offer')]");

    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageTitleVisible() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public boolean isCreateOfferButtonVisible() {
        return driver.findElement(createOfferButton).isDisplayed();
    }

    public void clickCreateOfferButton() {
        driver.findElement(createOfferButton).click();
    }
}