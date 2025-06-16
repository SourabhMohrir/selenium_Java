package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Page URL
    private static final String PAGE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    // Page Elements
    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".orangehrm-login-branding > img")
    private WebElement loginLogo;

    @FindBy(css = ".oxd-alert-content--error")
    private WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Methods
    public void navigateToLoginPage() {
        driver.get(PAGE_URL);
        // Wait for page to load
        wait.until(ExpectedConditions.urlContains("/auth/login"));
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        // Wait for dashboard page
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }

    public boolean isLogoDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginLogo)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    // Convenience method for login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // Verify if we're on the login page
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("/auth/login");
    }
} 