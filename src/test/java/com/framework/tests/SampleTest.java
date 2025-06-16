package com.framework.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.framework.utils.WebDriverManager;

public class SampleTest extends BaseTest {
    
    @Test
    public void sampleGoogleTest() {
        // Navigate to Google
        WebDriverManager.getDriver().get("https://www.google.com");
        
        // Verify the title contains "Google"
        String pageTitle = WebDriverManager.getDriver().getTitle();
        Assert.assertTrue(pageTitle.contains("Google"), 
            "Page title should contain 'Google', but was: " + pageTitle);
    }
} 