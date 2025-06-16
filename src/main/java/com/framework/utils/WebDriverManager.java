package com.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverManager() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver has not been initialized. Please call setDriver() first.");
        }
        return driver.get();
    }

    public static void setDriver(String browserType) {
        WebDriver webDriver;
        switch (browserType.toLowerCase()) {
            case "chrome":
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "edge":
                io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
        }
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
} 