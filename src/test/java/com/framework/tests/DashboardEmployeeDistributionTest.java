package com.framework.tests;

import com.framework.pages.LoginPage;
import com.framework.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.utils.WebDriverManager;
import java.util.List;

public class DashboardEmployeeDistributionTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Test
    public void testSubUnitWidgetPresent() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // Verify Employee Distribution by Sub Unit widget is displayed
        Assert.assertTrue(dashboardPage.isEmployeeDistributionWidgetDisplayed(), 
            "Employee Distribution by Sub Unit widget is not displayed");
    }

    @Test
    public void testSubUnitChartDisplayed() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // Verify chart is displayed
        Assert.assertTrue(dashboardPage.isSubUnitChartDisplayed(), 
            "Employee Distribution chart is not displayed");
    }

    @Test
    public void testSubUnitLegendItems() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // Get legend items
        List<String> legendItems = dashboardPage.getSubUnitLegendItems();
        
        // Verify we have legend items
        Assert.assertFalse(legendItems.isEmpty(), 
            "No legend items found in Employee Distribution chart");

        // Verify each legend item is not empty
        for (String item : legendItems) {
            Assert.assertFalse(item.trim().isEmpty(), 
                "Found empty legend item in Employee Distribution chart");
        }
    }

    @Test
    public void testSubUnitChartData() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // Verify chart data is valid
        Assert.assertTrue(dashboardPage.validateSubUnitChartData(), 
            "Employee Distribution chart data validation failed");
    }

    @Test
    public void testLegendInteractions() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // Get all legend items
        List<String> legendItems = dashboardPage.getSubUnitLegendItems();
        Assert.assertFalse(legendItems.isEmpty(), 
            "No legend items found in Employee Distribution chart");

        // Test clicking each legend item
        for (String legendText : legendItems) {
            // Verify legend item is initially active
            Assert.assertTrue(dashboardPage.isLegendItemActive(legendText),
                "Legend item '" + legendText + "' should be active initially");

            // Click the legend item
            Assert.assertTrue(dashboardPage.clickLegendItem(legendText),
                "Failed to click legend item: " + legendText);

            // Wait for chart to update
            Assert.assertTrue(dashboardPage.waitForChartUpdate(),
                "Chart failed to update after clicking legend: " + legendText);

            // Verify legend item is now inactive
            Assert.assertFalse(dashboardPage.isLegendItemActive(legendText),
                "Legend item '" + legendText + "' should be inactive after clicking");

            // Click again to reactivate
            Assert.assertTrue(dashboardPage.clickLegendItem(legendText),
                "Failed to click legend item again: " + legendText);

            // Wait for chart to update
            Assert.assertTrue(dashboardPage.waitForChartUpdate(),
                "Chart failed to update after clicking legend again: " + legendText);

            // Verify legend item is active again
            Assert.assertTrue(dashboardPage.isLegendItemActive(legendText),
                "Legend item '" + legendText + "' should be active after clicking again");
        }
    }
} 