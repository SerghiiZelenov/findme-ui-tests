package com.findme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.StaleElementReferenceException;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

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

    private final By resetPasswordTitle = By.xpath("//*[normalize-space()='Forgot Password']");
    private final By resetPasswordEmailInput = By.cssSelector("input[placeholder='Email address']");
    private final By resetPasswordSubmitButton = By.xpath("//button[.//span[normalize-space()='Send Reset Link']]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isEmailInputVisible() {
        return waitUntilAnyElementVisible(emailInput);
    }

    public boolean isPasswordInputVisible() {
        return waitUntilAnyElementVisible(passwordInput);
    }

    public boolean isSignInButtonVisible() {
        return waitUntilAnyElementVisible(signInButton);
    }

    public boolean isRegisterTabVisible() {
        return waitUntilAnyElementVisible(registerTab);
    }

    public boolean isForgotPasswordLinkVisible() {
        return waitUntilAnyElementVisible(forgotPasswordLink);
    }

    public boolean isGoogleLoginButtonVisible() {
        return waitUntilAnyElementVisible(googleLoginButton);
    }

    public void login(String email, String password) {
        WebElement visibleEmailInput = waitUntilVisibleElement(emailInput);
        visibleEmailInput.sendKeys(email);

        WebElement visiblePasswordInput = waitUntilVisibleElement(passwordInput);
        visiblePasswordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public void clickRegisterTab() {
        wait.until(ExpectedConditions.elementToBeClickable(registerTab)).click();
    }

    public boolean isNameInputVisible() {
        return waitUntilAnyElementVisible(nameInput);
    }

    public boolean isRegisterEmailInputVisible() {
        return waitUntilAnyElementVisible(registerEmailInput);
    }

    public boolean isRegisterPasswordInputVisible() {
        return waitUntilAnyElementVisible(registerPasswordInput);
    }

    public boolean isSignUpButtonVisible() {
        return waitUntilAnyElementVisible(signUpButton);
    }

    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }

    public boolean isResetPasswordTitleVisible() {
        return waitUntilAnyElementVisible(resetPasswordTitle);
    }

    public boolean isResetPasswordEmailInputVisible() {
        return waitUntilAnyElementVisible(resetPasswordEmailInput);
    }

    public boolean isResetPasswordSubmitButtonVisible() {
        return waitUntilAnyElementVisible(resetPasswordSubmitButton);
    }

    private boolean waitUntilAnyElementVisible(By locator) {
        try {
            wait.until(driver -> {
                List<WebElement> elements = driver.findElements(locator);

                for (WebElement element : elements) {
                    try {
                        if (element.isDisplayed()) {
                            return true;
                        }
                    } catch (StaleElementReferenceException e) {
                        return false;
                    }
                }
                return false;
            });
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private WebElement waitUntilVisibleElement(By locator) {
        return wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);

            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) {
                        return element;
                    }
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }
            return null;
        });
    }
}