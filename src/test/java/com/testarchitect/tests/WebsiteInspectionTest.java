package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

/**
 * Simple test to inspect the actual website structure
 */
public class WebsiteInspectionTest extends BaseTest {

    @Test
    public void inspectWebsiteStructure() {
        // Open the website
        open("https://demo.testarchitect.com/");
        
        // Take a screenshot to see what we're working with
        screenshot("website-homepage");
        
        // Print page title and URL
        System.out.println("Page Title: " + title());
        System.out.println("Current URL: " + webdriver().driver().url());
        
        // Wait for user to manually inspect
        sleep(5000);
        
        // Check if there are common login elements
        if ($("input[name='username']").exists()) {
            System.out.println("Found username field: input[name='username']");
        }
        if ($("input[name='email']").exists()) {
            System.out.println("Found email field: input[name='email']");
        }
        if ($("input[name='password']").exists()) {
            System.out.println("Found password field: input[name='password']");
        }
        if ($("form").exists()) {
            System.out.println("Found form element");
            System.out.println("Form attributes: " + $("form").getAttribute("outerHTML"));
        }
        
        // Check for login links
        if ($("a[href*='login']").exists()) {
            System.out.println("Found login link");
        }
        if ($("a[href*='sign']").exists()) {
            System.out.println("Found sign in/up link");
        }
    }
}
