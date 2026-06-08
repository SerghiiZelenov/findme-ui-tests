package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.FindMatchModal;
import com.findme.pages.HomePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindMatchModalTest extends BaseTest {

    @Test
    public void findMatchModalShouldOpenFromHomePageSearch() {
        driver.get(ConfigReader.get("base.url"));

        HomePage homePage = new HomePage(driver);
        homePage.clickSearch();

        FindMatchModal findMatchModal = new FindMatchModal(driver);

        assertTrue(findMatchModal.isModalTitleVisible(), "Find Your Match modal title should be visible");
        assertTrue(findMatchModal.isCountryInputVisible(), "Country input should be visible");
        assertTrue(findMatchModal.isCityInputVisible(), "City input should be visible");
        assertTrue(findMatchModal.isSearchButtonVisible(), "Search button should be visible");
    }

    @Test
    public void searchWithoutRequiredCountryShouldKeepModalOpen() {
        driver.get(ConfigReader.get("base.url"));

        HomePage homePage = new HomePage(driver);
        homePage.clickSearch();

        FindMatchModal findMatchModal = new FindMatchModal(driver);
        findMatchModal.clickSearchButton();

        assertTrue(
                findMatchModal.isModalTitleVisible(),
                "Find Your Match modal should remain visible when required country is missing"
        );
    }

    @Test
    public void radiusShouldAppearAfterCountryAndCityAreEntered() {
        driver.get(ConfigReader.get("base.url"));

        HomePage homePage = new HomePage(driver);
        homePage.clickSearch();

        FindMatchModal findMatchModal = new FindMatchModal(driver);

        assertTrue(
                !findMatchModal.isRadiusVisible(),
                "Radius should not be visible before city is entered"
        );

        findMatchModal.selectCountry("Germany");
        findMatchModal.enterCity("Berlin");

        assertTrue(
                findMatchModal.isRadiusVisible(),
                "Radius should be visible after required country and city are entered"
        );
    }
}