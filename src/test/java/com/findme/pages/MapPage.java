package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MapPage {

    private final WebDriver driver;

    private final By mapContainer = By.cssSelector(".leaflet-container");
    private final By zoomInButton = By.cssSelector(".leaflet-control-zoom-in");
    private final By zoomOutButton = By.cssSelector(".leaflet-control-zoom-out");

    public MapPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isMapVisible() {
        return driver.findElement(mapContainer).isDisplayed();
    }

    public boolean isZoomInButtonVisible() {
        return driver.findElement(zoomInButton).isDisplayed();
    }

    public boolean isZoomOutButtonVisible() {
        return driver.findElement(zoomOutButton).isDisplayed();
    }
}