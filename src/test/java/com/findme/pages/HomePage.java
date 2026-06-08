package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    private final By heroTitle = By.xpath("//*[contains(text(),'One Planet')]");
    private final By searchInput = By.xpath("//*[contains(text(),'Find Your Match') or @placeholder='Find Your Match...']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHeroTitleVisible() {
        return driver.findElement(heroTitle).isDisplayed();
    }

    public boolean isSearchVisible() {
        return driver.findElement(searchInput).isDisplayed();
    }

    public void clickSearch() {
        driver.findElement(searchInput).click();
    }
}