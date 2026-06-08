package com.findme.tests;

import com.findme.base.BaseTest;
import com.findme.config.ConfigReader;
import com.findme.pages.LoginPage;
import com.findme.pages.NavigationBar;
import org.junit.jupiter.api.Test;
import com.findme.pages.AccountPage;
import com.findme.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest extends BaseTest {

    @Test
    public void loginPageShouldOpenFromNavigationMenu() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(loginPage.isEmailInputVisible(), "Email input should be visible");
        assertTrue(loginPage.isPasswordInputVisible(), "Password input should be visible");
        assertTrue(loginPage.isSignInButtonVisible(), "Sign In button should be visible");
        assertTrue(loginPage.isRegisterTabVisible(), "Register tab should be visible");
        assertTrue(loginPage.isForgotPasswordLinkVisible(), "Forgot password link should be visible");
        assertTrue(loginPage.isGoogleLoginButtonVisible(), "Google login button should be visible");
    }

    @Test
    public void adminShouldLoginSuccessfully() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );

        AccountPage accountPage = new AccountPage(driver);

        assertTrue(accountPage.isLogoutButtonVisible(), "Logout button should be visible after login");
        assertTrue(accountPage.isMyOffersSectionVisible(), "My Offers section should be visible after login");
        assertTrue(accountPage.isActiveTabVisible(), "Active tab should be visible after login");
        assertTrue(accountPage.isInactiveTabVisible(), "Inactive tab should be visible after login");
    }

    @Test
    public void adminShouldLogoutSuccessfully() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                ConfigReader.get("admin.email"),
                ConfigReader.get("admin.password")
        );

        AccountPage accountPage = new AccountPage(driver);
        assertTrue(accountPage.isLogoutButtonVisible(), "Logout button should be visible after login");

        accountPage.clickLogout();

        assertTrue(navigationBar.isLoginLinkVisible(), "Login link should be visible after logout");
    }

    @Test
    public void registerTabShouldOpenFromLoginPage() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterTab();

        assertTrue(loginPage.isNameInputVisible(), "Name input should be visible on Register tab");
        assertTrue(loginPage.isRegisterEmailInputVisible(), "Email input should be visible on Register tab");
        assertTrue(loginPage.isRegisterPasswordInputVisible(), "Password input should be visible on Register tab");
        assertTrue(loginPage.isSignUpButtonVisible(), "Sign Up/Register button should be visible on Register tab");
    }

    @Test
    public void forgotPasswordShouldOpenFromLoginPage() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        assertTrue(
                loginPage.isResetPasswordTitleVisible(),
                "Reset/Forgot password title should be visible"
        );

        assertTrue(
                loginPage.isResetPasswordEmailInputVisible(),
                "Email input should be visible on reset password form"
        );

        assertTrue(
                loginPage.isResetPasswordSubmitButtonVisible(),
                "Reset password submit button should be visible"
        );
    }

    @Test
    public void userShouldNavigateFromLoginPageBackToHomePage() {
        driver.get(ConfigReader.get("base.url"));

        NavigationBar navigationBar = new NavigationBar(driver);
        navigationBar.clickLogin();

        LoginPage loginPage = new LoginPage(driver);

        assertTrue(
                loginPage.isEmailInputVisible(),
                "Email input should be visible on Login page"
        );

        navigationBar.clickHome();

        HomePage homePage = new HomePage(driver);

        assertTrue(
                homePage.isHeroTitleVisible(),
                "Home page should be visible after clicking Home link from Login page"
        );
    }
}