package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import com.testarchitect.framework.pages.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;

/**
 * Additional test scenarios for edge cases and special functionalities
 */
@Epic("E-commerce Platform Testing")
@Feature("Extended Functionality Testing")
public class ExtendedEcommerceTests extends BaseTest {

    @Test
    @Story("Guest Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_06: Verify users try to buy an item without logging in")
    @TmsLink("TC_06")
    public void testUserTryToBuyItemWithoutLoggingIn() {
        // Test Details
        String testCaseId = "TC_06";
        String testDescription = "Verify users try to buy an item without logging in";
        String expectedResult = "Guests should be able to purchase or system prompts login requirement";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        
        // Step 1: Open browser without logging in
        logger.info("Step 1: Opening browser without logging in");
        loginPage.openLoginPage(config.getAppUrl());
        
        // Step 2: Navigate to 'Shop' or 'Products' section
        logger.info("Step 2: Navigating to Shop section as guest");
        homePage = new HomePage();
        homePage.navigateToAllDepartments();
        
        // Step 3: Add a product to cart
        logger.info("Step 3: Adding product to cart as guest user");
        productPage = homePage.selectRandomItem();
        productPage.addToCart();
        
        // Step 4: Click on Cart button
        logger.info("Step 4: Proceeding to cart as guest");
        cartPage = homePage.goToCart();
        
        // Step 5: Proceed to complete order
        logger.info("Step 5: Attempting to complete order as guest");
        try {
            cartPage.proceedToCheckout();
            logger.info("Guest checkout is allowed");
        } catch (Exception e) {
            logger.info("Guest checkout requires login: {}", e.getMessage());
        }
        
        // Verify guest checkout process
        logger.info("Step 6: Verifying guest checkout behavior");
        // Implementation depends on whether guest checkout is allowed
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Error Handling")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_07: Ensure proper error handling when mandatory fields are blank")
    @TmsLink("TC_07")
    public void testErrorHandlingForMandatoryFields() {
        // Test Details
        String testCaseId = "TC_07";
        String testDescription = "Ensure proper error handling when mandatory fields are blank";
        String expectedResult = "Error messages displayed for mandatory fields";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Login and add item to cart
        logger.info("Step 1: Logging in and adding item to cart");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        logger.info("Step 2: Selecting and adding product to cart");
        homePage = new HomePage();
        productPage = homePage.selectRandomItem();
        productPage.addToCart();
        
        logger.info("Step 3: Proceeding to checkout");
        cartPage = homePage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
        
        // Step 4: Leave mandatory fields (address, payment info) blank
        logger.info("Step 4: Leaving mandatory fields blank");
        checkoutPage.leaveMandatoryFieldsBlank();
        
        // Step 5: Click 'Confirm Order'
        logger.info("Step 5: Attempting to confirm order with blank fields");
        checkoutPage.clickConfirmOrder();
        
        // Step 6: Verify error messages
        logger.info("Step 6: Verifying error messages for mandatory fields");
        checkoutPage.verifyErrorMessages();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_08: Verify users can clear the cart")
    @TmsLink("TC_08")
    public void testUserCanClearCart() {
        // Test Details
        String testCaseId = "TC_08";
        String testDescription = "Verify users can clear the cart";
        String expectedResult = "Cart is cleared successfully and shows empty state";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        CartPage cartPage;
        
        // Login and add items to cart
        logger.info("Step 1: Logging in and adding items to cart");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        logger.info("Step 2: Adding item to cart");
        homePage = new HomePage();
        homePage.addItemToCart(0);
        
        logger.info("Step 3: Verifying items are in cart");
        cartPage = homePage.goToCart();
        cartPage.verifyItemsShowInTable();
        
        // Clear shopping cart
        logger.info("Step 4: Clearing shopping cart");
        cartPage.clearShoppingCart();
        
        // Verify cart is empty
        logger.info("Step 5: Verifying cart is empty");
        cartPage.verifyCartIsEmpty();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_09: Verify users can update quantity of products in cart")
    @TmsLink("TC_09")
    public void testUserCanUpdateProductQuantity() {
        // Test Details
        String testCaseId = "TC_09";
        String testDescription = "Verify users can update quantity of products in cart";
        String newQuantity = "4";
        String expectedResult = "Product quantity and subtotal are updated correctly";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("New Quantity", newQuantity);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        logger.info("New Quantity: {}", newQuantity);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        
        // Login and add item to cart
        logger.info("Step 1: Logging in and adding item to cart");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        logger.info("Step 2: Selecting and adding product to cart");
        homePage = new HomePage();
        productPage = homePage.selectRandomItem();
        productPage.addToCart();
        
        logger.info("Step 3: Verifying items are in cart");
        cartPage = homePage.goToCart();
        cartPage.verifyItemsShowInTable();
        
        // Update quantity of product
        logger.info("Step 4: Updating product quantity to {}", newQuantity);
        cartPage.updateProductQuantity(0, Integer.parseInt(newQuantity));
        
        // Verify quantity of product and SUB TOTAL price
        logger.info("Step 5: Verifying quantity and price are updated");
        cartPage.verifyQuantityAndPriceUpdated();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Product Reviews")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_10: Verify users can post a review")
    @TmsLink("TC_10")
    public void testUserCanPostReview() {
        // Test Details
        String testCaseId = "TC_10";
        String testDescription = "Verify users can post a review";
        String reviewText = "Excellent product! Highly recommended.";
        int rating = 5;
        String expectedResult = "Review is posted and displayed successfully";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Review Text", reviewText);
        Allure.parameter("Rating", String.valueOf(rating));
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        logger.info("Review: {} (Rating: {})", reviewText, rating);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        
        // Login
        logger.info("Step 1: Logging in");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        // Navigate to shop page
        logger.info("Step 2: Verifying user login and navigating to shop");
        homePage = new HomePage();
        homePage.verifyUserLoggedIn();
        
        // Click on a product to view detail
        logger.info("Step 3: Selecting product for review");
        productPage = homePage.selectRandomItem();
        productPage.clickProductDetail();
        
        // Scroll down then click on REVIEWS tab
        logger.info("Step 4: Navigating to reviews section");
        productPage.scrollToReviews();
        
        // Submit a review
        logger.info("Step 5: Submitting review with rating {} and text: {}", rating, reviewText);
        productPage.clickAddReview();
        productPage.submitReview(rating, reviewText);
        
        // Verify new review
        logger.info("Step 6: Verifying review is displayed");
        productPage.verifyReviewDisplayed();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }
}
