package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.CategoriesPage;
import com.findme.pages.NavigationBar;
import org.junit.jupiter.api.Test;
import com.findme.pages.FindMatchModal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriesPageTest extends BaseTest {

    @Test
    public void categoriesPageShouldOpenFromNavigationMenu() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickCategories();

        CategoriesPage categoriesPage = new CategoriesPage(driver);

        assertTrue(
                categoriesPage.isPageTitleVisible(),
                "Categories page title should be visible"
        );

        assertTrue(
                categoriesPage.isEducationCategoryVisible(),
                "Education category should be visible"
        );
    }

    @Test
    public void categoryCardShouldOpenFindMatchModal() throws InterruptedException {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickCategories();

        CategoriesPage categoriesPage = new CategoriesPage(driver);
        categoriesPage.clickEducationCategory();

        FindMatchModal findMatchModal = new FindMatchModal(driver);

        //Thread.sleep(3000); // временно: чтобы визуально увидеть модальное окно

        assertTrue(
                findMatchModal.isModalTitleVisible(),
                "Find Your Match modal should be visible after clicking category card"
        );

        assertTrue(
                findMatchModal.isCountryInputVisible(),
                "Country input should be visible in Find Your Match modal"
        );

        assertTrue(
                findMatchModal.isSearchButtonVisible(),
                "Search button should be visible in Find Your Match modal"
        );
    }
}