package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import com.testarchitect.framework.pages.*;
import com.testarchitect.framework.utils.TestDataProvider;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * E-commerce test scenarios
 */
@Epic("E-commerce Platform Testing")
@Feature("Shopping Cart and Checkout Functionality")
public class EcommerceTests extends BaseTest {

    @Test(dataProvider = "loginTestData", dataProviderClass = TestDataProvider.class)
    @Story("User Registration and Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_01: Verify users can buy an item successfully")
    public void testUserCanBuyItemSuccessfully(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String testDescription = testData.get("TestDescription");
        String precondition = testData.get("Precondition");
        String expectedResult = testData.get("ExpectedResult");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        Allure.parameter("Precondition", precondition);
        Allure.parameter("Expected Result", expectedResult);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        ProductPage productPage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Step 1: Open browser and go to URL
        loginPage.openLoginPage(config.getAppUrl());
        
        // Step 2: Login with valid credentials
        loginPage.loginWith("validuser@test.com", "password123");
        
        // Step 3: Navigate to All departments section
        homePage = new HomePage();
        homePage.verifyUserLoggedIn()
                .navigateToAllDepartments();
        
        // Step 4: Select Electronic Components & Supplies
        homePage.selectElectronicsCategory();
        
        // Step 5: Verify items should be displayed as a grid
        homePage.verifyGridView();
        
        // Step 6: Switch view to list
        homePage.switchToListView();
        
        // Step 7: Verify the items should be displayed as a list
        homePage.verifyListView();
        
        // Step 8: Select any item randomly to purchase
        productPage = homePage.selectRandomItem();
        
        // Step 9: Click 'Add to Cart'
        productPage.addToCart();
        
        // Step 10: Go to the cart
        cartPage = homePage.goToCart();
        
        // Step 11: Verify item details in mini content
        cartPage.verifyItemsInCart();
        
        // Step 12: Click on Checkout
        checkoutPage = cartPage.proceedToCheckout();
        
        // Step 13: Verify Checkout page displays
        checkoutPage.verifyCheckoutPageDisplays();
        
        // Step 14: Verify item details in order
        checkoutPage.verifyItemDetailsInOrder();
        
        // Step 15: Fill billing details with default payment method
        checkoutPage.fillBillingDetailsWithDefaultPayment();
        
        // Step 16: Click on PLACE ORDER
        checkoutPage.clickPlaceOrder();
        
        // Step 17: Verify the Order details with billing and item information
        OrderStatusPage orderStatusPage = checkoutPage.verifyOrderStatusPageDisplays();
        orderStatusPage.verifyOrderDetails();
        
        // Verification: Order confirmation message show correctly
        cartPage.verifyOrderConfirmation();
    }

    @Test(dataProvider = "multipleItemsTestData", dataProviderClass = TestDataProvider.class)
    @Story("Shopping Cart Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_02: Verify users can buy multiple items successfully")
    public void testUserCanBuyMultipleItemsSuccessfully(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String testDescription = testData.get("TestDescription");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Description", testDescription);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        CartPage cartPage;
        CheckoutPage checkoutPage;
        
        // Login and navigate to shop
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        homePage = new HomePage();
        homePage.verifyUserLoggedIn();
        
        // Select multiple items and add to cart
        homePage.addItemToCart(0);
        homePage.addItemToCart(1);
        
        // Go to cart and verify all selected items are present
        cartPage = homePage.goToCart();
        cartPage.verifyItemsInCart();
        
        // Proceed to checkout
        checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.verifyCheckoutPageDisplays();
        
        // Complete order
        checkoutPage.fillBillingDetailsWithDefaultPayment();
        checkoutPage.clickPlaceOrder();
        
        // Verify order confirmation
        cartPage.verifyOrderConfirmation();
    }

    @Test(dataProvider = "paymentMethodsTestData", dataProviderClass = TestDataProvider.class)
    @Story("Payment Processing")
    @Severity(SeverityLevel.CRITICAL)
    @Description("TC_03: Verify users can buy an item using different payment methods")
    public void testUserCanBuyItemWithDifferentPaymentMethods(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String paymentMethod = testData.get("PaymentMethod");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Payment Method", paymentMethod);
        
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
        
        // Choose different payment method
        checkoutPage.choosePaymentMethod(paymentMethod);
        checkoutPage.completePaymentProcess();
        
        // Verify payment success
        checkoutPage.verifyOrderConfirmationMessage();
    }

    @Test(dataProvider = "sortingTestData", dataProviderClass = TestDataProvider.class)
    @Story("Product Sorting")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_04: Verify users can sort items by price")
    public void testUserCanSortItemsByPrice(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        
        Allure.parameter("Test Case ID", testCaseId);
        
        LoginPage loginPage = new LoginPage();
        HomePage homePage;
        
        // Login and navigate to products
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("validuser@test.com", "password123");
        
        homePage = new HomePage();
        homePage.verifyUserLoggedIn();
        
        // Sort items by price
        homePage.sortByPrice();
        
        // Verify items are sorted correctly
        homePage.verifyPriceSorting();
    }

    @Test(dataProvider = "orderHistoryTestData", dataProviderClass = TestDataProvider.class)
    @Story("Order History")
    @Severity(SeverityLevel.NORMAL)
    @Description("TC_05: Verify orders appear in order history")
    public void testOrdersAppearInOrderHistory(Map<String, String> testData) {
        String testCaseId = testData.get("TestCaseID");
        String precondition = testData.get("Precondition");
        
        Allure.parameter("Test Case ID", testCaseId);
        Allure.parameter("Precondition", precondition);
        
        LoginPage loginPage = new LoginPage();
        OrderStatusPage orderStatusPage;
        
        // Login with user who has placed orders
        loginPage.openLoginPage(config.getAppUrl());
        loginPage.loginWith("userWithOrders@test.com", "password123");
        
        // Navigate to My Account page
        orderStatusPage = new OrderStatusPage();
        MyAccountPage myAccountPage = orderStatusPage.navigateToMyAccount();
        
        // Click on Orders in left navigation
        orderStatusPage = myAccountPage.navigateToOrders();
        
        // Verify order details
        orderStatusPage.verifyOrderDetailsInHistory();
    }
}
