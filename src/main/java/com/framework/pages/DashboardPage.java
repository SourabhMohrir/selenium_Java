package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

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

    @FindBy(xpath = "//p[contains(text(), 'Quick Launch')]")
    private WebElement quickLaunchWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employees on Leave Today')]")
    private WebElement employeesOnLeaveWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Sub Unit')]")
    private WebElement employeeDistributionWidget;

    @FindBy(xpath = "//p[contains(text(), 'Employee Distribution by Location')]")
    private WebElement employeeLocationWidget;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isOnDashboardPage() {
        return driver.getCurrentUrl().contains("/dashboard");
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
        return wait.until(ExpectedConditions.visibilityOf(myActionsWidget)).isDisplayed();
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

    public boolean validateAllWidgetsPresent() {
        return isTimeAtWorkWidgetDisplayed() &&
               isMyActionsWidgetDisplayed() &&
               isQuickLaunchWidgetDisplayed() &&
               isEmployeesOnLeaveWidgetDisplayed() &&
               isEmployeeDistributionWidgetDisplayed() &&
               isEmployeeLocationWidgetDisplayed();
    }
} 