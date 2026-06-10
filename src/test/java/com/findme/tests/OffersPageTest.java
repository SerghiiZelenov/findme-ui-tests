package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.AccountPage;
import com.findme.pages.LoginPage;
import com.findme.pages.NavigationBar;
import com.findme.pages.OffersPage;
import org.junit.jupiter.api.Test;
import com.findme.pages.CreateOfferPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OffersPageTest extends BaseTest {

    @Test
    public void offersPageShouldOpenFromNavigationMenu() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickOffers();

        OffersPage offersPage = new OffersPage(driver);

        assertTrue(
                offersPage.isPageTitleVisible(),
                "Offers page title should be visible"
        );

        assertTrue(
                offersPage.isCreateOfferButtonVisible(),
                "Create Volunteer Offer button should be visible"
        );
    }

    @Test
    public void guestShouldBeRedirectedToLoginWhenClickingCreateOffer() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickOffers();

        OffersPage offersPage = new OffersPage(driver);
        offersPage.clickCreateOfferButton();

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(
                loginPage.isEmailInputVisible(),
                "Email input should be visible after guest clicks Create Volunteer Offer"
        );

        assertTrue(
                loginPage.isPasswordInputVisible(),
                "Password input should be visible after guest clicks Create Volunteer Offer"
        );
    }
    
    @Test
    public void loggedInUserShouldOpenCreateVolunteerOfferForm() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );

        AccountPage accountPage = new AccountPage(driver);

        assertTrue(
                accountPage.isLogoutButtonVisible(),
                "Logout button should be visible after login"
        );

        navigationBar.clickOffers();

        OffersPage offersPage = new OffersPage(driver);
        offersPage.clickCreateOfferButton();
        CreateOfferPage createOfferPage = new CreateOfferPage(driver);

        assertTrue(
                createOfferPage.isTitleInputVisible(),
                "Offer title input should be visible in Create Volunteer Offer form"
        );

        assertTrue(
                createOfferPage.isDescriptionTextareaVisible(),
                "Description textarea should be visible in Create Volunteer Offer form"
        );

        assertTrue(
                createOfferPage.isCityInputVisible(),
                "City input should be visible in Create Volunteer Offer form"
        );

        assertTrue(
                createOfferPage.isCountryInputVisible(),
                "Country input should be visible in Create Volunteer Offer form"
        );

        assertTrue(
                createOfferPage.isCreateButtonVisible(),
                "Create button should be visible in Create Volunteer Offer form"
        );

        assertTrue(
                createOfferPage.isCancelButtonVisible(),
                "Cancel button should be visible in Create Volunteer Offer form"
        );
    }
}