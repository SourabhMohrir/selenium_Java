package com.framework.tests;

import com.framework.pages.LoginPage;
import com.framework.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.utils.WebDriverManager;

public class DashboardTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Test
    public void testDashboardGraphsPresent() {
        // Initialize page objects
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Verify we're on dashboard
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), "Not on dashboard page");

        // Verify total number of widgets
        Assert.assertEquals(dashboardPage.getTotalWidgetsCount(), 7, 
            "Expected 7 dashboard widgets but found different number");

        // Verify graph widgets count
        Assert.assertEquals(dashboardPage.getGraphWidgetsCount(), 1, 
            "Expected 1 graph widget but found different number");

        // Verify all individual widgets are present
        Assert.assertTrue(dashboardPage.validateAllWidgetsPresent(), 
            "Not all expected widgets are present on dashboard");

        // Verify specific graph widgets
        Assert.assertTrue(dashboardPage.isEmployeeDistributionWidgetDisplayed(), 
            "Employee Distribution graph not displayed");
        Assert.assertTrue(dashboardPage.isEmployeeLocationWidgetDisplayed(), 
            "Employee Location graph not displayed");
    }
} 