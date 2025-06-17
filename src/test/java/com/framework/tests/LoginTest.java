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

    @Test
    public void testInvalidCredentials() {
        loginPage = new LoginPage(WebDriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        // Enter invalid credentials
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("invalid_pass");
        loginPage.clickLoginButton();
        
        // Verify error message
        Assert.assertTrue(loginPage.isOnLoginPage(), "Not on login page after invalid login attempt");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Invalid credentials"), 
            "Expected error message about invalid credentials, but got: " + errorMsg);
    }

    @Test
    public void testEmptyCredentials() {
        loginPage = new LoginPage(WebDriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        // Click login without entering credentials
        loginPage.clickLoginButton();
        
        // Verify error message
        Assert.assertTrue(loginPage.isOnLoginPage(), "Not on login page after empty login attempt");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Required"), 
            "Expected error message about required fields, but got: " + errorMsg);
    }

    @Test
    public void testValidUserInvalidPassword() {
        loginPage = new LoginPage(WebDriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        // Enter valid username but invalid password
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginButton();
        
        // Verify error message
        Assert.assertTrue(loginPage.isOnLoginPage(), "Not on login page after invalid login attempt");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Invalid credentials"), 
            "Expected error message about invalid credentials, but got: " + errorMsg);
    }

    @Test
    public void testInvalidUserValidPassword() {
        loginPage = new LoginPage(WebDriverManager.getDriver());
        loginPage.navigateToLoginPage();
        
        // Enter invalid username but valid password
        loginPage.enterUsername("WrongAdmin");
        loginPage.enterPassword("admin123");
        loginPage.clickLoginButton();
        
        // Verify error message
        Assert.assertTrue(loginPage.isOnLoginPage(), "Not on login page after invalid login attempt");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Invalid credentials"), 
            "Expected error message about invalid credentials, but got: " + errorMsg);
    }
} 