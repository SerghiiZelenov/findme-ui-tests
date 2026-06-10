package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.AccountPage;
import com.findme.pages.CreateOfferPage;
import com.findme.pages.LoginPage;
import com.findme.pages.NavigationBar;
import com.findme.pages.OffersPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateOfferTest extends BaseTest {

    @Test
    public void loggedInUserShouldSeeCreateOfferFormFields() {
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

        assertTrue(
                offersPage.isCreateOfferButtonVisible(),
                "Create Volunteer Offer button should be visible"
        );

        offersPage.clickCreateOfferButton();

        CreateOfferPage createOfferPage = new CreateOfferPage(driver);

        assertTrue(
                createOfferPage.isTitleInputVisible(),
                "Title input should be visible"
        );

        assertTrue(
                createOfferPage.isDescriptionTextareaVisible(),
                "Description textarea should be visible"
        );

        assertTrue(
                createOfferPage.isCityInputVisible(),
                "City input should be visible"
        );

        assertTrue(
                createOfferPage.isCountryInputVisible(),
                "Country input should be visible"
        );

        assertTrue(
                createOfferPage.isLatitudeInputVisible(),
                "Latitude input should be visible"
        );

        assertTrue(
                createOfferPage.isLongitudeInputVisible(),
                "Longitude input should be visible"
        );

        assertTrue(
                createOfferPage.isTagsInputVisible(),
                "Tags input should be visible"
        );

        assertTrue(
                createOfferPage.isUseCurrentLocationButtonVisible(),
                "Use Current Location button should be visible"
        );

        assertTrue(
                createOfferPage.isFindCoordinatesButtonVisible(),
                "Find Coordinates button should be visible"
        );

        assertTrue(
                createOfferPage.isActiveOfferLabelVisible(),
                "Active Offer label should be visible"
        );

        assertTrue(
                createOfferPage.isCreateButtonVisible(),
                "Create button should be visible"
        );

        assertTrue(
                createOfferPage.isCancelButtonVisible(),
                "Cancel button should be visible"
        );
    }

    @Test
    public void cancelButtonShouldCloseCreateOfferForm() {
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

        assertTrue(
                offersPage.isCreateOfferButtonVisible(),
                "Create Volunteer Offer button should be visible"
        );

        offersPage.clickCreateOfferButton();

        CreateOfferPage createOfferPage = new CreateOfferPage(driver);

        assertTrue(
                createOfferPage.isTitleInputVisible(),
                "Create Offer form should be visible before clicking Cancel"
        );

        createOfferPage.clickCancelButton();

        assertTrue(
                createOfferPage.isTitleInputNotVisible(),
                "Create Offer form should be closed after clicking Cancel"
        );
    }
}