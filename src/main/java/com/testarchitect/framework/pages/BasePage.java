package com.testarchitect.framework.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Base page class with common functionality
 */
public abstract class BasePage {
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    
    // Common elements that might appear on multiple pages
    protected SelenideElement loadingSpinner = $("[data-testid='loading-spinner']");
    protected SelenideElement errorMessage = $(".error-message");
    protected SelenideElement successMessage = $(".success-message");
    
    @Step("Wait for page to load")
    public void waitForPageToLoad() {
        logger.info("Waiting for page to load");
        if (loadingSpinner.exists()) {
            loadingSpinner.should(disappear);
        }
    }
    
    @Step("Verify error message is displayed: {errorText}")
    public void verifyErrorMessage(String errorText) {
        logger.info("Verifying error message: {}", errorText);
        errorMessage.shouldBe(visible).shouldHave(text(errorText));
    }
    
    @Step("Verify success message is displayed: {successText}")
    public void verifySuccessMessage(String successText) {
        logger.info("Verifying success message: {}", successText);
        successMessage.shouldBe(visible).shouldHave(text(successText));
    }
    
    @Step("Get page title")
    public String getPageTitle() {
        String title = com.codeborne.selenide.Selenide.title();
        logger.info("Page title: {}", title);
        return title;
    }
    
    @Step("Get current URL")
    public String getCurrentUrl() {
        String url = com.codeborne.selenide.WebDriverRunner.url();
        logger.info("Current URL: {}", url);
        return url;
    }
}
