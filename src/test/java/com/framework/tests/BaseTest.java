package com.framework.tests;

import org.testng.annotations.*;
import com.framework.utils.WebDriverManager;

public class BaseTest {
    
    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) {
        WebDriverManager.setDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
} 