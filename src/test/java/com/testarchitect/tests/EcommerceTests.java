package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import com.testarchitect.framework.pages.*;
import io.qameta.allure.*;
import org.testng.annotations.Test;

/**
 * E-commerce test scenarios
 */
@Epic("E-commerce Platform Testing")
@Feature("Shopping Cart and Checkout Functionality")
public class EcommerceTests extends BaseTest {

    @Test
    @Story("User Registration and Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_01: Verify users can buy an item successfully")
    @TmsLink("TC_01")
    public void testUserCanBuyItemSuccessfully() {
        // Test Details
        String testCaseId = "TC_01";
        String testDescription = "Verify users can buy an item successfully";
        String precondition = "Register a valid account";
        String expectedResult = "Order confirmation message show correctly";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Precondition", precondition);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        logger.info("Precondition: {}", precondition);
        logger.info("Expected Result: {}", expectedResult);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Step 1: Open browser and go to URL
        logger.info("Step 1: Opening browser and navigating to: {}", config.getAppUrl());
        loginPage.openLoginPage(config.getAppUrl());
        
        // Step 2: Login with valid credentials
        logger.info("Step 2: Logging in with valid credentials");
        loginPage.loginWith("validuser@test.com", "password123");
        
        // Step 3: Navigate to All departments section
        logger.info("Step 3: Navigating to All departments section");
        homePage = new HomePage();
        homePage.verifyUserLoggedIn()
                .navigateToAllDepartments();
        
        // Step 4: Select Electronic Components & Supplies
        logger.info("Step 4: Selecting Electronic Components & Supplies category");
        homePage.selectElectronicsCategory();
        
        // Step 5: Verify items should be displayed as a grid
        logger.info("Step 5: Verifying items are displayed as a grid");
        homePage.verifyGridView();
        
        // Step 6: Switch view to list
        logger.info("Step 6: Switching view to list");
        homePage.switchToListView();
        
        // Step 7: Verify the items should be displayed as a list
        logger.info("Step 7: Verifying items are displayed as a list");
        homePage.verifyListView();
        
        // Step 8: Select any item randomly to purchase
        logger.info("Step 8: Selecting random item to purchase");
        productPage = homePage.selectRandomItem();
        
        // Step 9: Click 'Add to Cart'
        logger.info("Step 9: Adding item to cart");
        productPage.addToCart();
        
        // Step 10: Go to the cart
        logger.info("Step 10: Navigating to cart");
        cartPage = homePage.goToCart();
        
        // Step 11: Verify item details in mini content
        logger.info("Step 11: Verifying item details in cart");
        cartPage.verifyItemsInCart();
        
        // Step 12: Click on Checkout
        logger.info("Step 12: Proceeding to checkout");
        checkoutPage = cartPage.proceedToCheckout();
        
        // Step 13: Verify Checkout page displays
        logger.info("Step 13: Verifying checkout page displays");
        checkoutPage.verifyCheckoutPageDisplays();
        
        // Step 14: Verify item details in order
        logger.info("Step 14: Verifying item details in order");
        checkoutPage.verifyItemDetailsInOrder();
        
        // Step 15: Fill billing details with default payment method
        logger.info("Step 15: Filling billing details with default payment method");
        checkoutPage.fillBillingDetailsWithDefaultPayment();
        
        // Step 16: Click on PLACE ORDER
        logger.info("Step 16: Clicking PLACE ORDER");
        checkoutPage.clickPlaceOrder();
        
        // Step 17: Verify the Order details with billing and item information
        logger.info("Step 17: Verifying order details and confirmation");
        OrderStatusPage orderStatusPage = checkoutPage.verifyOrderStatusPageDisplays();
        orderStatusPage.verifyOrderDetails();
        
        // Verification: Order confirmation message show correctly
        logger.info("Final Verification: Checking order confirmation message");
        cartPage.verifyOrderConfirmation();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Shopping Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_02: Verify users can buy multiple items successfully")
    @TmsLink("TC_02")
    public void testUserCanBuyMultipleItemsSuccessfully() {
        // Test Details
        String testCaseId = "TC_02";
        String testDescription = "Verify users can buy multiple items successfully";
        String precondition = "User has valid account and can access product catalog";
        String expectedResult = "Multiple items order is processed successfully";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Precondition", precondition);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Login and navigate to shop
        logger.info("Step 1: Opening browser and logging in");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        logger.info("Step 2: Verifying user login and navigation");
        homePage = new HomePage();
        homePage.verifyUserLoggedIn();
        
        // Select multiple items and add to cart
        logger.info("Step 3: Adding multiple items to cart");
        homePage.addItemToCart(0);
        homePage.addItemToCart(1);
        
        // Go to cart and verify all selected items are present
        logger.info("Step 4: Verifying multiple items in cart");
        cartPage = homePage.goToCart();
        cartPage.verifyItemsInCart();
        
        // Proceed to checkout
        logger.info("Step 5: Proceeding to checkout with multiple items");
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.verifyCheckoutPageDisplays();
        
        // Complete order
        logger.info("Step 6: Completing order with billing details");
        checkoutPage.fillBillingDetailsWithDefaultPayment();
        checkoutPage.clickPlaceOrder();
        
        // Verify order confirmation
        logger.info("Step 7: Verifying multiple items order confirmation");
        cartPage.verifyOrderConfirmation();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Payment Processing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_03: Verify users can buy an item using different payment methods")
    @TmsLink("TC_03")
    public void testUserCanBuyItemWithCreditCardPayment() {
        // Test Details
        String testCaseId = "TC_03";
        String testDescription = "Verify users can buy an item using Credit Card payment method";
        String paymentMethod = "Credit Card";
        String expectedResult = "Payment is processed successfully with Credit Card";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Payment Method", paymentMethod);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Testing Payment Method: {}", paymentMethod);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Login and add item to cart
        logger.info("Step 1: Login and item selection");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        logger.info("Step 2: Adding item to cart");
        homePage = new HomePage();
        productPage = homePage.selectRandomItem();
        productPage.addToCart();
        
        logger.info("Step 3: Proceeding to checkout");
        cartPage = homePage.goToCart();
        checkoutPage = cartPage.proceedToCheckout();
        
        // Choose Credit Card payment method
        logger.info("Step 4: Selecting {} payment method", paymentMethod);
        checkoutPage.choosePaymentMethod(paymentMethod);
        checkoutPage.completePaymentProcess();
        
        // Verify payment success
        logger.info("Step 5: Verifying {} payment success", paymentMethod);
        checkoutPage.verifyOrderConfirmationMessage();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Product Sorting")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_04: Verify users can sort items by price")
    @TmsLink("TC_04")
    public void testUserCanSortItemsByPrice() {
        // Test Details
        String testCaseId = "TC_04";
        String testDescription = "Verify users can sort items by price";
        String expectedResult = "Items are sorted correctly by price";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        
        // Login and navigate to products
        logger.info("Step 1: Opening browser and logging in");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        logger.info("Step 2: Verifying user login and navigating to products");
        homePage = new HomePage();
        homePage.verifyUserLoggedIn();
        
        // Sort items by price
        logger.info("Step 3: Sorting items by price");
        homePage.sortByPrice();
        
        // Verify items are sorted correctly
        logger.info("Step 4: Verifying items are sorted correctly");
        homePage.verifyPriceSorting();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }

    @Test
    @Story("Order History")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_05: Verify orders appear in order history")
    @TmsLink("TC_05")
    public void testOrdersAppearInOrderHistory() {
        // Test Details
        String testCaseId = "TC_05";
        String testDescription = "Verify orders appear in order history";
        String precondition = "User with existing orders";
        String expectedResult = "Order history displays previous orders correctly";
        
        // Add Allure parameters for reporting
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Precondition", precondition);
        Allure.parameter("Expected Result", expectedResult);
        
        // Log test start
        logger.info("=== Starting Test Case: {} ===", testCaseId);
        logger.info("Description: {}", testDescription);
        logger.info("Precondition: {}", precondition);
        
        LoginPage loginPage = new LoginPage();
        OrderStatusPage orderStatusPage;
        
        // Login with user who has placed orders
        logger.info("Step 1: Logging in with user who has existing orders");
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("userWithOrders@test.com", "password123");
        
        // Navigate to My Account page
        logger.info("Step 2: Navigating to My Account page");
        orderStatusPage = new OrderStatusPage();
        MyAccountPage myAccountPage = orderStatusPage.navigateToMyAccount();
        
        // Click on Orders in left navigation
        logger.info("Step 3: Navigating to Orders section");
        orderStatusPage = myAccountPage.navigateToOrders();
        
        // Verify order details
        logger.info("Step 4: Verifying order details in history");
        orderStatusPage.verifyOrderDetailsInHistory();
        
        logger.info("=== Test Case {} completed successfully ===", testCaseId);
    }
}
