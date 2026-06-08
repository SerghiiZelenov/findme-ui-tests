package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.MapPage;
import com.findme.pages.NavigationBar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapPageTest extends BaseTest {

    @Test
    public void mapPageShouldOpenFromNavigationMenu() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickMap();

        MapPage mapPage = new MapPage(driver);

        assertTrue(mapPage.isMapVisible(), "Map should be visible");
        assertTrue(mapPage.isZoomInButtonVisible(), "Map zoom in button should be visible");
        assertTrue(mapPage.isZoomOutButtonVisible(), "Map zoom out button should be visible");
    }
}