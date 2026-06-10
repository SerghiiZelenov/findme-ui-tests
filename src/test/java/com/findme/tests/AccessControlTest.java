package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.AccountPage;
import com.findme.pages.LoginPage;
import com.findme.pages.NavigationBar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessControlTest extends BaseTest {

    @Test
    public void guestShouldNotAccessAccountPage() {
        String accountUrl = ConfigReader.get("base.url").replace("/main", "/account");

        driver.get(accountUrl);

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(
                loginPage.isEmailInputVisible(),
                "Guest should be redirected to Login page when trying to access Account page"
        );

        assertTrue(
                loginPage.isPasswordInputVisible(),
                "Password input should be visible after guest is redirected to Login page"
        );
    }

    @Test
    public void regularUserShouldNotSeeAdminPanel() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("user.email"),
                ConfigReader.get("user.password")
        );

        AccountPage accountPage = new AccountPage(driver);

        assertTrue(
                accountPage.isLogoutButtonVisible(),
                "Logout button should be visible after regular user login"
        );

        assertTrue(
                accountPage.isAdminPanelSectionNotVisible(),
                "Admin Panel should not be visible for regular user"
        );
    }
}