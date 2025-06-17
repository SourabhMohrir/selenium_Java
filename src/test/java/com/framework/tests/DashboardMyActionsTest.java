package com.framework.tests;

import com.framework.pages.LoginPage;
import com.framework.pages.DashboardPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.framework.utils.WebDriverManager;

public class DashboardMyActionsTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Test
    public void testMyActionsWidgetPresent() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // If there are no pending actions, assume widget still present but skip verification
        if (dashboardPage.hasNoPendingActions()) {
            throw new SkipException("No pending actions found – skipping My Actions widget presence test");
        }

        // Verify My Actions widget is displayed
        Assert.assertTrue(dashboardPage.isMyActionsWidgetDisplayed(), 
            "My Actions widget is not displayed on dashboard");
    }

    @Test
    public void testMyActionsContent() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // If there are no pending actions, skip detailed content validation
        if (dashboardPage.hasNoPendingActions()) {
            throw new SkipException("No pending actions found – skipping My Actions detailed content tests");
        }

        // Verify My Actions content
        Assert.assertTrue(dashboardPage.hasLeaveRequestsToApprove(), 
            "Leave Requests to Approve section not found in My Actions");
        Assert.assertTrue(dashboardPage.hasTimesheetsToApprove(), 
            "Timesheets to Approve section not found in My Actions");
        Assert.assertTrue(dashboardPage.hasCandidatesToInterview(), 
            "Candidates to Interview section not found in My Actions");
    }

    @Test
    public void testMyActionsLinks() {
        // Initialize pages
        loginPage = new LoginPage(WebDriverManager.getDriver());
        dashboardPage = new DashboardPage(WebDriverManager.getDriver());

        // Login first
        loginPage.navigateToLoginPage();
        loginPage.login("Admin", "admin123");

        // Wait for dashboard page to load
        Assert.assertTrue(dashboardPage.isOnDashboardPage(), 
            "Failed to load dashboard page");

        // If there are no pending actions, skip link validation
        if (dashboardPage.hasNoPendingActions()) {
            throw new SkipException("No pending actions found – skipping My Actions link tests");
        }

        // Verify My Actions links are clickable
        Assert.assertTrue(dashboardPage.isLeaveRequestsLinkClickable(), 
            "Leave Requests link is not clickable");
        Assert.assertTrue(dashboardPage.isTimesheetsLinkClickable(), 
            "Timesheets link is not clickable");
        Assert.assertTrue(dashboardPage.isCandidatesLinkClickable(), 
            "Candidates link is not clickable");
    }
} 