package com.framework.tests;

import com.framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.utils.WebDriverManager;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @Test
    public void testSuccessfulLogin() {
        // Initialize LoginPage
        loginPage = new LoginPage(WebDriverManager.getDriver());
        
        // Navigate to login page
        loginPage.navigateToLoginPage();
        
        // Verify we are on login page
        Assert.assertTrue(loginPage.isOnLoginPage(), "Not on login page");
        
        // Verify logo is displayed
        Assert.assertTrue(loginPage.isLogoDisplayed(), "Login page logo is not displayed");
        
        // Perform login
        loginPage.login("Admin", "admin123");
        
        // Verify successful login by checking URL no longer contains login
        Assert.assertFalse(loginPage.isOnLoginPage(), "Still on login page after login attempt");
        
        // Additional verification that we're on dashboard
        Assert.assertTrue(WebDriverManager.getDriver().getCurrentUrl().contains("/dashboard"), 
            "Not redirected to dashboard after login");
    }
} 