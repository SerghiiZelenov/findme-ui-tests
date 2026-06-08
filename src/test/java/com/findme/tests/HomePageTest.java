package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.HomePage;
import com.findme.pages.NavigationBar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {

    @Test
    public void homePageShouldOpenSuccessfully() {
        driver.get(ConfigReader.get("base.url"));

        HomePage homePage = new HomePage(driver);

        assertTrue(
                homePage.isHeroTitleVisible(),
                "Home page hero title should be visible"
        );

        assertTrue(
                homePage.isSearchVisible(),
                "Find Your Match search should be visible"
        );
    }

    @Test
    public void navigationMenuShouldBeVisibleOnHomePage() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);

        assertTrue(navigationBar.isHomeLinkVisible(), "Home link should be visible");
        assertTrue(navigationBar.isCategoriesLinkVisible(), "Categories link should be visible");
        assertTrue(navigationBar.isOffersLinkVisible(), "Offers link should be visible");
        assertTrue(navigationBar.isMapLinkVisible(), "Map link should be visible");
        assertTrue(navigationBar.isLoginLinkVisible(), "Login link should be visible");
    }
}