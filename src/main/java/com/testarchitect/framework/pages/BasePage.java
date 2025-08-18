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
    
    // Popup elements
    protected SelenideElement popupModal = $(".popup, .modal, [role='dialog']");
    protected SelenideElement popupCloseButton = $(".popup .close, .modal .close, [role='dialog'] .close, .popup-close, .modal-close");
    protected SelenideElement popupCloseIcon = $(".popup [class*='close'], .modal [class*='close'], [role='dialog'] [class*='close']");
    protected SelenideElement popupOverlay = $(".popup-overlay, .modal-overlay, .overlay");
    
    @Step("Handle any popups that might appear")
    public void handlePopups() {
        logger.info("Checking for and handling any popups");
        try {
            // Wait a moment for popups to appear
            Thread.sleep(2000);
            
            // Try multiple strategies to close popups
            if (popupCloseButton.exists() && popupCloseButton.isDisplayed()) {
                logger.info("Found popup close button, clicking it");
                popupCloseButton.click();
            } else if (popupCloseIcon.exists() && popupCloseIcon.isDisplayed()) {
                logger.info("Found popup close icon, clicking it");
                popupCloseIcon.click();
            } else if (popupOverlay.exists() && popupOverlay.isDisplayed()) {
                logger.info("Found popup overlay, clicking it to close");
                popupOverlay.click();
            } else if (popupModal.exists()) {
                logger.info("Found popup modal, trying to press ESC");
                popupModal.pressEscape();
            } else {
                // Try JavaScript to close popup
                logger.info("Trying JavaScript approach to close popup");
                com.codeborne.selenide.Selenide.executeJavaScript(
                    "var popup = document.querySelector('.popup, .modal, [role=\"dialog\"]');" +
                    "if (popup) { popup.style.display = 'none'; }" +
                    "var overlay = document.querySelector('.popup-overlay, .modal-overlay, .overlay');" +
                    "if (overlay) { overlay.style.display = 'none'; }"
                );
            }
            
            // Wait for popup to disappear
            Thread.sleep(1000);
            logger.info("Popup handling completed");
        } catch (Exception e) {
            logger.warn("No popup found or error handling popup: {}", e.getMessage());
        }
    }
    
    @Step("Wait for page to load")
    public void waitForPageToLoad() {
        logger.info("Waiting for page to load");
        if (loadingSpinner.exists()) {
            loadingSpinner.should(disappear);
        }
        // Handle any popups that might appear after page load
        handlePopups();
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
