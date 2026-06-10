package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.AccountPage;
import com.findme.pages.LoginPage;
import com.findme.pages.NavigationBar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AccountPageTest extends BaseTest {

    @Test
    public void regularUserShouldSeeAccountSections() {
        String userEmail = System.getenv("FINDME_USER_EMAIL");
        String userPassword = System.getenv("FINDME_USER_PASSWORD");

        assumeTrue(
                userEmail != null && !userEmail.isBlank()
                        && userPassword != null && !userPassword.isBlank(),
                "Regular user credentials are not provided. Set FINDME_USER_EMAIL and FINDME_USER_PASSWORD."
        );

        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        AccountPage accountPage = new AccountPage(driver);

        assertTrue(accountPage.isLogoutButtonVisible(), "Logout button should be visible");
        assertTrue(accountPage.isMyOffersSectionVisible(), "My Offers section should be visible");
        assertTrue(accountPage.isSavedSectionVisible(), "Saved section should be visible");
        assertTrue(accountPage.isChatsSectionVisible(), "Chats section should be visible");
        assertTrue(accountPage.isSettingsSectionVisible(), "Settings section should be visible");

        assertTrue(
                accountPage.isAdminPanelSectionNotVisible(),
                "Admin Panel should not be visible for regular user"
        );
    }

    @Test
    public void logoutShouldProtectAccountPage() {
        String userEmail = System.getenv("FINDME_USER_EMAIL");
        String userPassword = System.getenv("FINDME_USER_PASSWORD");

        assumeTrue(
                userEmail != null && !userEmail.isBlank()
                        && userPassword != null && !userPassword.isBlank(),
                "Regular user credentials are not provided. Set FINDME_USER_EMAIL and FINDME_USER_PASSWORD."
        );

        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userEmail, userPassword);

        AccountPage accountPage = new AccountPage(driver);

        assertTrue(accountPage.isLogoutButtonVisible(), "Logout button should be visible after login");

        accountPage.clickLogout();

        String accountUrl = ConfigReader.get("base.url").replace("/main", "/account");
        driver.get(accountUrl);

        assertTrue(
                loginPage.isEmailInputVisible(),
                "Email input should be visible after logged out user tries to access Account page"
        );

        assertTrue(
                loginPage.isPasswordInputVisible(),
                "Password input should be visible after logged out user tries to access Account page"
        );
    }
}