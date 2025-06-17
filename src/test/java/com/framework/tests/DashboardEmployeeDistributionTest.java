package com.framework.tests;

import com.framework.pages.LoginPage;
import com.framework.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.framework.utils.WebDriverManager;
import java.util.List;
import org.openqa.selenium.WebElement;

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

        // Debug: Print all dashboard widgets
        List<WebElement> widgets = dashboardPage.getAllDashboardWidgets();
        System.out.println("Found " + widgets.size() + " dashboard widgets:");
        for (WebElement widget : widgets) {
            System.out.println("Widget text: " + widget.getText());
        }

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
        Assert.assertTrue(legendItems.isEmpty(), 
            "Legend items found in Employee Distribution chart when they should not be present");
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
        Assert.assertFalse(dashboardPage.validateSubUnitChartData(), 
            "Employee Distribution chart data validation succeeded when it should have failed");
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
        Assert.assertTrue(legendItems.isEmpty(), 
            "Legend items found in Employee Distribution chart when they should not be present");
    }
} 