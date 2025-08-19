package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import com.testarchitect.framework.models.BillingInfo;
import com.testarchitect.framework.pages.CheckoutPage;
import com.testarchitect.framework.utils.BillingDataFactory;
import org.testng.annotations.Test;

/**
 * Example test showing how to use the new BillingInfo model with CheckoutPage
 */
public class CheckoutWithBillingModelTest extends BaseTest {

    @Test
    public void testCheckoutWithRandomBillingData() {
        // Example: Using random billing data
        CheckoutPage checkoutPage = new CheckoutPage();
        
        // Method 1: Use random billing data
        checkoutPage.fillBillingDetailsWithRandomData();
        
        // Method 2: Use custom billing data
        checkoutPage.fillBillingDetailsWithCustomData("John", "Doe", "john.doe@test.com");
        
        // Method 3: Use factory-generated data
        BillingInfo testUserBilling = BillingDataFactory.createTestUserBilling();
        checkoutPage.fillBillingDetails(testUserBilling);
        
        // Method 4: Use VIP customer data
        BillingInfo vipBilling = BillingDataFactory.createVipCustomerBilling();
        checkoutPage.fillBillingDetails(vipBilling);
        
        // Method 5: Traditional method still works (backward compatibility)
        checkoutPage.fillBillingDetailsWithDefaultPayment();
    }

    @Test
    public void testMultipleBillingScenarios() {
        CheckoutPage checkoutPage = new CheckoutPage();
        
        // Test with different billing scenarios
        BillingInfo[] billingScenarios = {
            BillingInfo.createDefault(),
            BillingInfo.createRandom(),
            BillingDataFactory.createUSBilling(),
            BillingDataFactory.createVipCustomerBilling()
        };
        
        for (BillingInfo billing : billingScenarios) {
            System.out.println("Testing with billing: " + billing.getFullName());
            // In a real test, you would fill the form and verify
            // checkoutPage.fillBillingDetails(billing);
        }
    }
}
