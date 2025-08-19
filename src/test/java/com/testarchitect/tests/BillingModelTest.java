package com.testarchitect.tests;

import com.testarchitect.framework.models.BillingInfo;
import com.testarchitect.framework.utils.BillingDataFactory;
import org.testng.annotations.Test;

/**
 * Test class to demonstrate BillingInfo model usage
 */
public class BillingModelTest {

    @Test
    public void testBillingModelCreation() {
        // Test different ways to create billing info
        System.out.println("=== Default Billing Info ===");
        BillingInfo defaultBilling = BillingInfo.createDefault();
        System.out.println(defaultBilling);

        System.out.println("\n=== Random Billing Info ===");
        BillingInfo randomBilling = BillingInfo.createRandom();
        System.out.println(randomBilling);

        System.out.println("\n=== Custom Billing Info ===");
        BillingInfo customBilling = BillingInfo.createCustom("Jane", "Smith", "jane.smith@example.com");
        System.out.println(customBilling);

        System.out.println("\n=== Factory Generated Billing Info ===");
        BillingInfo factoryBilling = BillingDataFactory.createTestUserBilling();
        System.out.println(factoryBilling);

        System.out.println("\n=== VIP Customer Billing Info ===");
        BillingInfo vipBilling = BillingDataFactory.createVipCustomerBilling();
        System.out.println(vipBilling);
    }
}
