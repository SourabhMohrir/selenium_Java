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

    // Updated Dashboard Elements with new locators
    @FindBy(css = ".oxd-grid-item")
    private List<WebElement> dashboardWidgets;

    @FindBy(css = ".oxd-sheet canvas")
    private List<WebElement> graphWidgets;

    @FindBy(xpath = "//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')]//p[text()='Employee Distribution by Sub Unit']")
    private WebElement employeeDistributionWidget;

    @FindBy(xpath = "//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')][.//p[text()='Employee Distribution by Sub Unit']]//canvas")
    private WebElement subUnitChart;

    @FindBy(xpath = "//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')][.//p[text()='Employee Distribution by Sub Unit']]//div[contains(@class, 'chart-legend')]//div[contains(@class, 'legend-item')]//span")
    private List<WebElement> subUnitLegendItems;

    @FindBy(xpath = "//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')][.//p[text()='Employee Distribution by Sub Unit']]//div[contains(@class, 'chart-legend')]//div[contains(@class, 'legend-item')]")
    private List<WebElement> subUnitLegendRows;

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
            
            // Print all widget titles for debugging
            System.out.println("\nDashboard Widget Titles:");
            for (WebElement widget : dashboardWidgets) {
                try {
                    WebElement titleElement = widget.findElement(By.cssSelector("p"));
                    System.out.println("- " + titleElement.getText());
                } catch (Exception e) {
                    System.out.println("- [No title found]");
                }
            }
            System.out.println();
            
            return true;
        } catch (Exception e) {
            System.out.println("Failed to load dashboard page: " + e.getMessage());
            return false;
        }
    }

    public List<WebElement> getAllDashboardWidgets() {
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboardWidgets));
        return dashboardWidgets;
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

    public boolean isEmployeeDistributionWidgetDisplayed() {
        try {
            System.out.println("Checking for Employee Distribution widget...");
            // Wait for the page to fully load
            Thread.sleep(2000);
            
            // First check if we can find the widget container
            List<WebElement> allWidgets = driver.findElements(By.xpath("//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')]"));
            System.out.println("Found " + allWidgets.size() + " dashboard widget containers");
            
            // Print all widget titles for debugging
            for (WebElement widget : allWidgets) {
                try {
                    WebElement titleElement = widget.findElement(By.tagName("p"));
                    System.out.println("Widget title: " + titleElement.getText());
                } catch (Exception e) {
                    System.out.println("Widget with no title found");
                }
            }
            
            // Wait for the widget to be visible
            wait.until(ExpectedConditions.visibilityOf(employeeDistributionWidget));
            System.out.println("Employee Distribution widget found");
            return true;
        } catch (Exception e) {
            System.out.println("Employee Distribution widget not found: " + e.getMessage());
            return false;
        }
    }

    public boolean isSubUnitChartDisplayed() {
        try {
            System.out.println("Checking if Sub Unit chart is displayed...");
            // Wait for the page to fully load
            Thread.sleep(2000);
            
            // First check if we can find any canvas elements
            List<WebElement> allCanvases = driver.findElements(By.tagName("canvas"));
            System.out.println("Found " + allCanvases.size() + " canvas elements on the page");
            
            // Wait for the chart to be visible
            wait.until(ExpectedConditions.visibilityOf(subUnitChart));
            System.out.println("Sub Unit chart found");
            return true;
        } catch (Exception e) {
            System.out.println("Sub Unit chart is not displayed: " + e.getMessage());
            return false;
        }
    }

    public List<String> getSubUnitLegendItems() {
        System.out.println("Getting Sub Unit legend items...");
        try {
            // Wait for the page to fully load
            Thread.sleep(2000);
            
            // First check if we can find any legend elements
            List<WebElement> allLegendElements = driver.findElements(By.xpath("//div[contains(@class, 'chart-legend')]"));
            System.out.println("Found " + allLegendElements.size() + " legend containers on the page");
            
            // Try to find legend items using a more general approach
            List<WebElement> legendItems = driver.findElements(By.xpath("//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')][.//p[text()='Employee Distribution by Sub Unit']]//div[contains(@class, 'chart-legend')]//div[contains(@class, 'legend-item')]//span"));
            System.out.println("Found " + legendItems.size() + " legend items");
            
            List<String> items = new java.util.ArrayList<>();
            for (WebElement item : legendItems) {
                String text = item.getText().trim();
                System.out.println("Found legend item: " + text);
                if (!text.isEmpty()) {
                    items.add(text);
                }
            }
            System.out.println("Total legend items found: " + items.size());
            return items;
        } catch (Exception e) {
            System.out.println("Failed to get Sub Unit legend items: " + e.getMessage());
            return new java.util.ArrayList<>();
        }
    }

    public boolean validateSubUnitChartData() {
        System.out.println("Validating Sub Unit chart data...");
        try {
            // Wait for the page to fully load
            Thread.sleep(2000);
            
            // First check if the chart is visible
            if (!isSubUnitChartDisplayed()) {
                System.out.println("Chart is not displayed");
                return false;
            }
            
            // Then check for legend items
            List<String> legendItems = getSubUnitLegendItems();
            System.out.println("Found " + legendItems.size() + " legend items");
            
            // Validate that we have both the chart and legend items
            return !legendItems.isEmpty();
        } catch (Exception e) {
            System.out.println("Failed to validate Sub Unit chart data: " + e.getMessage());
            return false;
        }
    }

    public boolean clickLegendItem(String legendText) {
        try {
            System.out.println("Attempting to click legend item: " + legendText);
            // Wait for the page to fully load
            Thread.sleep(2000);
            
            // Try to find the legend item using a more general approach
            List<WebElement> legendItems = driver.findElements(By.xpath("//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')][.//p[text()='Employee Distribution by Sub Unit']]//div[contains(@class, 'chart-legend')]//div[contains(@class, 'legend-item')]"));
            
            for (WebElement item : legendItems) {
                if (item.getText().contains(legendText)) {
                    item.click();
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
            // Wait for the page to fully load
            Thread.sleep(2000);
            
            // Try to find the legend item using a more general approach
            List<WebElement> legendItems = driver.findElements(By.xpath("//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'orangehrm-dashboard-widget')][.//p[text()='Employee Distribution by Sub Unit']]//div[contains(@class, 'chart-legend')]//div[contains(@class, 'legend-item')]"));
            
            for (WebElement item : legendItems) {
                if (item.getText().contains(legendText)) {
                    boolean isActive = !item.getAttribute("class").contains("inactive");
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
            System.out.println("Waiting for chart update...");
            Thread.sleep(2000); // Increased wait time for chart animation
            return true;
        } catch (InterruptedException e) {
            System.out.println("Chart update wait interrupted: " + e.getMessage());
            return false;
        }
    }
} 