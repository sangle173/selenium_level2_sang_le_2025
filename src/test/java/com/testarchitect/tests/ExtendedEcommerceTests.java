package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import com.testarchitect.framework.pages.*;
import com.testarchitect.framework.utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Additional test scenarios for edge cases and special functionalities
 */
@Epic("E-commerce Platform Testing")
@Feature("Extended Functionality Testing")
public class ExtendedEcommerceTests extends BaseTest {

    @Test(dataProvider = "guestCheckoutTestData", dataProviderClass = TestDataProvider.class)
    @Story("Guest Checkout")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_06: Verify users try to buy an item without logging in")
    public void testUserTryToBuyItemWithoutLoggingIn(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String testDescription = testData.get("TestDescription");
        String expectedResult = testData.get("ExpectedResult");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Expected Result", expectedResult);
        
        LoginPage loginPage = new LoginPage();
        
        // Step 1: Open https://demo.testarchitect.com/
        loginPage.openLoginPage(config.getAppUrl());
        
        // Step 2: Navigate to 'Shop' or 'Products' section
        // Step 3: Add a product to cart
        // Step 4: Click on Cart button
        // Step 5: Proceed to complete order
        
        // Expected: Guests should be able to purchase the item successfully without logging in
        // OR system prompts login requirement
    }

    @Test(dataProvider = "errorHandlingTestData", dataProviderClass = TestDataProvider.class)
    @Story("Error Handling")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_07: Ensure proper error handling when mandatory fields are blank")
    public void testErrorHandlingForMandatoryFields(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String testDescription = testData.get("TestDescription");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Login and add item to cart
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        homePage = new HomePage();
        productPage = homePage.selectRandomItem();
        productPage.addToCart();
        
        cartPage = homePage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
        
        // Step 1: Leave mandatory fields (address, payment info) blank
        checkoutPage.leaveMandatoryFieldsBlank();
        
        // Step 2: Click 'Confirm Order'
        checkoutPage.clickConfirmOrder();
        
        // Step 3: Verify error messages
        checkoutPage.verifyErrorMessages();
    }

    @Test(dataProvider = "cartManagementTestData", dataProviderClass = TestDataProvider.class)
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_08: Verify users can clear the cart")
    public void testUserCanClearCart(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        
        Allure.parameter("Test Case ID", testCaseId);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        CartPage cartPage;
        
        // Login and add items to cart
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        homePage = new HomePage();
        homePage.addItemToCart(0);
        
        cartPage = homePage.goToCart();
        cartPage.verifyItemsShowInTable();
        
        // Clear shopping cart
        cartPage.clearShoppingCart();
        
        // Verify cart is empty
        cartPage.verifyCartIsEmpty();
    }

    @Test(dataProvider = "cartManagementTestData", dataProviderClass = TestDataProvider.class)
    @Story("Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_09: Verify users can update quantity of products in cart")
    public void testUserCanUpdateProductQuantity(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        
        Allure.parameter("Test Case ID", testCaseId);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        
        // Login and add item to cart
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        homePage = new HomePage();
        productPage = homePage.selectRandomItem();
        productPage.addToCart();
        
        cartPage = homePage.goToCart();
        cartPage.verifyItemsShowInTable();
        
        // Update quantity of product
        cartPage.updateProductQuantity(0, 4);
        
        // Verify quantity of product and SUB TOTAL price
        cartPage.verifyQuantityAndPriceUpdated();
    }

    @Test(dataProvider = "reviewTestData", dataProviderClass = TestDataProvider.class)
    @Story("Product Reviews")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_10: Verify users can post a review")
    public void testUserCanPostReview(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String testDescription = testData.get("TestDescription");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        
        // Login
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        // Navigate to shop page
        homePage = new HomePage();
        homePage.verifyUserLoggedIn();
        
        // Click on a product to view detail
        productPage = homePage.selectRandomItem();
        productPage.clickProductDetail();
        
        // Scroll down then click on REVIEWS tab
        productPage.scrollToReviews();
        
        // Submit a review
        productPage.clickAddReview();
        productPage.submitReview(5, "Excellent product! Highly recommended.");
        
        // Verify new review
        productPage.verifyReviewDisplayed();
    }
}
