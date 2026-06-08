package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.NavigationBar;
import com.findme.pages.OffersPage;
import org.junit.jupiter.api.Test;
import com.findme.pages.LoginPage;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OffersPageTest extends BaseTest {

    @Test
    public void offersPageShouldOpenFromNavigationMenu() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickOffers();

        OffersPage offersPage = new OffersPage(driver);

        assertTrue(offersPage.isPageTitleVisible(), "Offers page title should be visible");
        assertTrue(offersPage.isCreateOfferButtonVisible(), "Create offer element should be visible");
    }

    @Disabled("Blocked: Offers/Create Offer functionality is not implemented yet")
    @Test
    public void guestShouldBeRedirectedToLoginWhenClickingCreateOffer() throws InterruptedException {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickOffers();

        OffersPage offersPage = new OffersPage(driver);
        offersPage.clickCreateOfferButton();

        Thread.sleep(3000); // временно: смотрим визуально, куда попали

        System.out.println("CURRENT URL AFTER CREATE OFFER CLICK: " + driver.getCurrentUrl());

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(
                loginPage.isEmailInputVisible(),
                "Email input should be visible after guest clicks Create Offer"
        );

        assertTrue(
                loginPage.isPasswordInputVisible(),
                "Password input should be visible after guest clicks Create Offer"
        );
    }
}