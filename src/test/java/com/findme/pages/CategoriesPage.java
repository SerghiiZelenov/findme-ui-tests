package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoriesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.xpath("//*[contains(text(),'Categories')]");
    private final By educationCategory = By.xpath("//*[contains(text(),'Education')]");

    public CategoriesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPageTitleVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public boolean isEducationCategoryVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(educationCategory)).isDisplayed();
    }

    public void clickEducationCategory() {
        driver.findElement(educationCategory).click();
    }
}