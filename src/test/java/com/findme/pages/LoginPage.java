package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;

    private final By emailInput = By.cssSelector("input[placeholder='Email']");
    private final By passwordInput = By.cssSelector("input[placeholder='Password']");
    private final By signInButton = By.xpath("//*[normalize-space()='Sign In']");
    private final By registerTab = By.xpath("//*[normalize-space()='Register']");
    private final By forgotPasswordLink = By.xpath("//*[contains(text(),'Forgot password')]");
    private final By googleLoginButton = By.xpath("//*[contains(text(),'Continue with Google')]");
    private final By nameInput = By.cssSelector("input[placeholder='Name']");
    private final By registerEmailInput = By.cssSelector("input[placeholder='Email']");
    private final By registerPasswordInput = By.cssSelector("input[placeholder='Password']");
    private final By signUpButton = By.xpath("//button[normalize-space()='Sign Up' or normalize-space()='Register']");
    private final By resetPasswordTitle = By.xpath("//*[contains(text(),'Reset') or contains(text(),'Forgot')]");
    private final By resetPasswordEmailInput = By.cssSelector("input[placeholder='Email address']");
    private final By resetPasswordSubmitButton = By.xpath("//button[.//span[normalize-space()='Send Reset Link']]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEmailInputVisible() {
        return driver.findElement(emailInput).isDisplayed();
    }

    public boolean isPasswordInputVisible() {
        return driver.findElement(passwordInput).isDisplayed();
    }

    public boolean isSignInButtonVisible() {
        return driver.findElement(signInButton).isDisplayed();
    }

    public boolean isRegisterTabVisible() {
        return driver.findElement(registerTab).isDisplayed();
    }

    public boolean isForgotPasswordLinkVisible() {
        return driver.findElement(forgotPasswordLink).isDisplayed();
    }

    public boolean isGoogleLoginButtonVisible() {
        return driver.findElement(googleLoginButton).isDisplayed();
    }

    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(signInButton).click();
    }

    public void clickRegisterTab() {
        driver.findElement(registerTab).click();
    }

    public boolean isNameInputVisible() {
        return driver.findElement(nameInput).isDisplayed();
    }

    public boolean isRegisterEmailInputVisible() {
        return driver.findElement(registerEmailInput).isDisplayed();
    }

    public boolean isRegisterPasswordInputVisible() {
        return driver.findElement(registerPasswordInput).isDisplayed();
    }

    public boolean isSignUpButtonVisible() {
        return driver.findElement(signUpButton).isDisplayed();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    public boolean isResetPasswordTitleVisible() {
        return driver.findElement(resetPasswordTitle).isDisplayed();
    }

    public boolean isResetPasswordEmailInputVisible() {
        return driver.findElement(resetPasswordEmailInput).isDisplayed();
    }

    public boolean isResetPasswordSubmitButtonVisible() {
        return driver.findElement(resetPasswordSubmitButton).isDisplayed();
    }
}