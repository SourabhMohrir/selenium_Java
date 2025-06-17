package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Dashboard Elements
    @FindBy(css = ".oxd-grid-item.orangehrm-dashboard-widget")
    private List<WebElement> dashboardWidgets;

    @FindBy(xpath = "//div[contains(@class, 'oxd-grid-item')]//canvas")
    private List<WebElement> graphWidgets;

    @FindBy(xpath = "//p[contains(text(), 'Time at Work')]")
    private WebElement timeAtWorkWidget;

    @FindBy(xpath = "//p[contains(text(), 'My Actions')]")
    private WebElement myActionsWidget;

    @FindBy(xpath = "//p[text()='No Pending Actions to Perform']")
    private WebElement noPendingActionsMessage;

    @FindBy(xpath = "//p[contains(text(), 'Quick Launch')]")
    private WebElement quickLaunchWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employees on Leave Today')]")
    private WebElement employeesOnLeaveWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]")
    private WebElement employeeDistributionWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Location')]")
    private WebElement employeeLocationWidget;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-todo-list')]//p[contains(text(), 'Leave Requests to Approve')]")
    private WebElement leaveRequestsSection;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-todo-list')]//p[contains(text(), 'Timesheets to Approve')]")
    private WebElement timesheetsSection;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-todo-list')]//p[contains(text(), 'Candidates to Interview')]")
    private WebElement candidatesSection;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-todo-list')]//button[.//p[contains(text(), 'Leave Requests to Approve')]]")
    private WebElement leaveRequestsLink;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-todo-list')]//button[.//p[contains(text(), 'Timesheets to Approve')]]")
    private WebElement timesheetsLink;

    @FindBy(xpath = "//div[contains(@class, 'orangehrm-todo-list')]//button[.//p[contains(text(), 'Candidates to Interview')]]")
    private WebElement candidatesLink;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]/ancestor::div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-chart')]")
    private WebElement subUnitChartContainer;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]/ancestor::div[contains(@class, 'oxd-grid-item')]//canvas")
    private WebElement subUnitChart;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]/ancestor::div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-chart-legend')]//span")
    private List<WebElement> subUnitLegendItems;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]/ancestor::div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-chart-legend')]//div[contains(@class, 'orangehrm-chart-legend-row')]")
    private List<WebElement> subUnitLegendRows;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]/ancestor::div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-chart-legend')]//div[contains(@class, 'orangehrm-chart-legend-row')]//i")
    private List<WebElement> subUnitLegendColorBoxes;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isOnDashboardPage() {
        try {
            System.out.println("Waiting for dashboard page to load...");
            wait.until(ExpectedConditions.urlContains("/dashboard/index"));
            wait.until(ExpectedConditions.visibilityOfAllElements(dashboardWidgets));
            String url = driver.getCurrentUrl();
            System.out.println("Current URL: " + url);
            System.out.println("Dashboard widgets found: " + dashboardWidgets.size());
            return true;
        } catch (Exception e) {
            System.out.println("Failed to load dashboard page: " + e.getMessage());
            return false;
        }
    }

    public int getTotalWidgetsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboardWidgets));
        return dashboardWidgets.size();
    }

    public int getGraphWidgetsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(graphWidgets));
        return graphWidgets.size();
    }

    public boolean isTimeAtWorkWidgetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(timeAtWorkWidget)).isDisplayed();
    }

    public boolean isMyActionsWidgetDisplayed() {
        try {
            System.out.println("Checking for My Actions widget...");
            wait.until(ExpectedConditions.visibilityOf(myActionsWidget));
            System.out.println("My Actions widget found");
            return true;
        } catch (Exception e) {
            System.out.println("My Actions widget not found: " + e.getMessage());
            return false;
        }
    }

    public boolean hasNoPendingActions() {
        try {
            System.out.println("Checking for 'No Pending Actions' message...");
            wait.until(ExpectedConditions.visibilityOf(noPendingActionsMessage));
            System.out.println("'No Pending Actions' message found");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("'No Pending Actions' message not found");
            return false;
        }
    }

    public boolean isQuickLaunchWidgetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(quickLaunchWidget)).isDisplayed();
    }

    public boolean isEmployeesOnLeaveWidgetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(employeesOnLeaveWidget)).isDisplayed();
    }

    public boolean isEmployeeDistributionWidgetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(employeeDistributionWidget)).isDisplayed();
    }

    public boolean isEmployeeLocationWidgetDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(employeeLocationWidget)).isDisplayed();
    }

    public boolean hasLeaveRequestsToApprove() {
        if (hasNoPendingActions()) {
            System.out.println("No pending actions found, skipping leave requests check");
            return false;
        }
        try {
            System.out.println("Checking for Leave Requests section...");
            wait.until(ExpectedConditions.visibilityOf(leaveRequestsSection));
            System.out.println("Leave Requests section found");
            return true;
        } catch (Exception e) {
            System.out.println("Leave Requests section not found: " + e.getMessage());
            return false;
        }
    }

    public boolean hasTimesheetsToApprove() {
        if (hasNoPendingActions()) {
            System.out.println("No pending actions found, skipping timesheets check");
            return false;
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(timesheetsSection));
            System.out.println("Timesheets section found");
            return true;
        } catch (Exception e) {
            System.out.println("Timesheets section not found: " + e.getMessage());
            return false;
        }
    }

    public boolean hasCandidatesToInterview() {
        if (hasNoPendingActions()) {
            System.out.println("No pending actions found, skipping candidates check");
            return false;
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(candidatesSection));
            System.out.println("Candidates section found");
            return true;
        } catch (Exception e) {
            System.out.println("Candidates section not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isLeaveRequestsLinkClickable() {
        if (hasNoPendingActions()) {
            System.out.println("No pending actions found, skipping leave requests link check");
            return false;
        }
        try {
            System.out.println("Checking if Leave Requests link is clickable...");
            wait.until(ExpectedConditions.elementToBeClickable(leaveRequestsLink));
            System.out.println("Leave Requests link is clickable");
            return true;
        } catch (Exception e) {
            System.out.println("Leave Requests link is not clickable: " + e.getMessage());
            return false;
        }
    }

    public boolean isTimesheetsLinkClickable() {
        if (hasNoPendingActions()) {
            System.out.println("No pending actions found, skipping timesheets link check");
            return false;
        }
        try {
            wait.until(ExpectedConditions.elementToBeClickable(timesheetsLink));
            System.out.println("Timesheets link is clickable");
            return true;
        } catch (Exception e) {
            System.out.println("Timesheets link is not clickable: " + e.getMessage());
            return false;
        }
    }

    public boolean isCandidatesLinkClickable() {
        if (hasNoPendingActions()) {
            System.out.println("No pending actions found, skipping candidates link check");
            return false;
        }
        try {
            wait.until(ExpectedConditions.elementToBeClickable(candidatesLink));
            System.out.println("Candidates link is clickable");
            return true;
        } catch (Exception e) {
            System.out.println("Candidates link is not clickable: " + e.getMessage());
            return false;
        }
    }

    public boolean validateAllWidgetsPresent() {
        return isTimeAtWorkWidgetDisplayed() &&
               isMyActionsWidgetDisplayed() &&
               isQuickLaunchWidgetDisplayed() &&
               isEmployeesOnLeaveWidgetDisplayed() &&
               isEmployeeDistributionWidgetDisplayed() &&
               isEmployeeLocationWidgetDisplayed();
    }

    // Navigation methods
    public void clickLeaveRequestsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveRequestsLink)).click();
    }

    public void clickTimesheetsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(timesheetsLink)).click();
    }

    public void clickCandidatesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(candidatesLink)).click();
    }

    public boolean isSubUnitChartDisplayed() {
        try {
            System.out.println("Checking if Sub Unit chart is displayed...");
            wait.until(ExpectedConditions.visibilityOf(subUnitChartContainer));
            wait.until(ExpectedConditions.visibilityOf(subUnitChart));
            System.out.println("Sub Unit chart is displayed");
            return true;
        } catch (Exception e) {
            System.out.println("Sub Unit chart is not displayed: " + e.getMessage());
            return false;
        }
    }

    public List<String> getSubUnitLegendItems() {
        try {
            System.out.println("Getting Sub Unit legend items...");
            wait.until(ExpectedConditions.visibilityOfAllElements(subUnitLegendItems));
            List<String> items = subUnitLegendItems.stream()
                .map(WebElement::getText)
                .toList();
            System.out.println("Found " + items.size() + " Sub Unit legend items");
            return items;
        } catch (Exception e) {
            System.out.println("Failed to get Sub Unit legend items: " + e.getMessage());
            return List.of();
        }
    }

    public boolean validateSubUnitChartData() {
        try {
            System.out.println("Validating Sub Unit chart data...");
            // Wait for chart and legend to be visible
            wait.until(ExpectedConditions.visibilityOf(subUnitChart));
            wait.until(ExpectedConditions.visibilityOfAllElements(subUnitLegendItems));

            // Check if we have any legend items
            if (subUnitLegendItems.isEmpty()) {
                System.out.println("No Sub Unit data found in chart");
                return false;
            }

            // Verify each legend item has valid text
            for (WebElement item : subUnitLegendItems) {
                String text = item.getText().trim();
                if (text.isEmpty()) {
                    System.out.println("Found empty legend item");
                    return false;
                }
            }

            System.out.println("Sub Unit chart data is valid");
            return true;
        } catch (Exception e) {
            System.out.println("Failed to validate Sub Unit chart data: " + e.getMessage());
            return false;
        }
    }

    public boolean clickLegendItem(String legendText) {
        try {
            System.out.println("Attempting to click legend item: " + legendText);
            for (WebElement row : subUnitLegendRows) {
                if (row.getText().trim().contains(legendText)) {
                    wait.until(ExpectedConditions.elementToBeClickable(row)).click();
                    System.out.println("Successfully clicked legend item: " + legendText);
                    return true;
                }
            }
            System.out.println("Legend item not found: " + legendText);
            return false;
        } catch (Exception e) {
            System.out.println("Failed to click legend item: " + e.getMessage());
            return false;
        }
    }

    public boolean isLegendItemActive(String legendText) {
        try {
            System.out.println("Checking if legend item is active: " + legendText);
            for (int i = 0; i < subUnitLegendRows.size(); i++) {
                WebElement row = subUnitLegendRows.get(i);
                if (row.getText().trim().contains(legendText)) {
                    WebElement colorBox = subUnitLegendColorBoxes.get(i);
                    // Check if the color box has a strikethrough or inactive class
                    boolean isActive = !colorBox.getAttribute("class").contains("cancelled") &&
                                     !colorBox.getAttribute("class").contains("inactive") &&
                                     !colorBox.getCssValue("text-decoration").contains("line-through");
                    System.out.println("Legend item '" + legendText + "' active status: " + isActive);
                    return isActive;
                }
            }
            System.out.println("Legend item not found: " + legendText);
            return false;
        } catch (Exception e) {
            System.out.println("Failed to check legend item status: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForChartUpdate() {
        try {
            System.out.println("Waiting for chart to update...");
            // Wait for a short time to allow chart animation
            Thread.sleep(1000);
            // Verify chart is still visible
            wait.until(ExpectedConditions.visibilityOf(subUnitChart));
            System.out.println("Chart updated successfully");
            return true;
        } catch (Exception e) {
            System.out.println("Failed to verify chart update: " + e.getMessage());
            return false;
        }
    }
} 