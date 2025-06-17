package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;

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

    @FindBy(css = ".oxd-input-field-error-message")
    private List<WebElement> fieldErrors;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Page Methods
    public void navigateToLoginPage() {
        System.out.println("Navigating to login page: " + PAGE_URL);
        driver.get(PAGE_URL);
        // Wait for page to load
        wait.until(ExpectedConditions.urlContains("/auth/login"));
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    public void enterUsername(String username) {
        if (username != null && !username.isEmpty()) {
            System.out.println("Entering username: " + username);
            try {
                wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
                usernameInput.sendKeys(username);
                System.out.println("Username entered successfully");
            } catch (Exception e) {
                System.out.println("Failed to enter username: " + e.getMessage());
                throw e;
            }
        }
    }

    public void enterPassword(String password) {
        if (password != null && !password.isEmpty()) {
            System.out.println("Entering password");
            try {
                wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
                passwordInput.sendKeys(password);
                System.out.println("Password entered successfully");
            } catch (Exception e) {
                System.out.println("Failed to enter password: " + e.getMessage());
                throw e;
            }
        }
    }

    public void clickLoginButton() {
        System.out.println("Clicking login button");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            System.out.println("Login button clicked successfully");
        } catch (Exception e) {
            System.out.println("Failed to click login button: " + e.getMessage());
            throw e;
        }
    }

    public boolean isLogoDisplayed() {
        try {
            System.out.println("Checking if logo is displayed");
            boolean isDisplayed = wait.until(ExpectedConditions.visibilityOf(loginLogo)).isDisplayed();
            System.out.println("Logo display status: " + isDisplayed);
            return isDisplayed;
        } catch (Exception e) {
            System.out.println("Failed to check logo: " + e.getMessage());
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            // First check for alert error message
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            // If no alert error, check for field errors
            try {
                wait.until(ExpectedConditions.visibilityOfAllElements(fieldErrors));
                StringBuilder errors = new StringBuilder();
                for (WebElement error : fieldErrors) {
                    errors.append(error.getText()).append(" ");
                }
                return errors.toString().trim();
            } catch (Exception e2) {
                return "";
            }
        }
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

    // Get field-specific error messages
    public String getUsernameError() {
        try {
            return driver.findElement(By.xpath("//input[@name='username']/../..//span")).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getPasswordError() {
        try {
            return driver.findElement(By.xpath("//input[@name='password']/../..//span")).getText();
        } catch (Exception e) {
            return "";
        }
    }
} 