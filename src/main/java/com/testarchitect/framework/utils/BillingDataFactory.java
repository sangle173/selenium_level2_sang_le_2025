package com.testarchitect.framework.utils;

import com.testarchitect.framework.models.BillingInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for generating test billing data
 */
public class BillingDataFactory {
    private static final Logger logger = LoggerFactory.getLogger(BillingDataFactory.class);

    /**
     * Create billing info for US customers
     */
    public static BillingInfo createUSBilling() {
        return BillingInfo.createRandom();
    }

    /**
     * Create billing info for specific test scenario
     */
    public static BillingInfo createTestUserBilling() {
        return BillingInfo.createCustom("TestUser", "Automation", "testuser@automation.com");
    }

    /**
     * Create billing info for VIP customer
     */
    public static BillingInfo createVipCustomerBilling() {
        return BillingInfo.createCustom("VIP", "Customer", "vip.customer@premium.com");
    }

    /**
     * Create minimal billing info (required fields only)
     */
    public static BillingInfo createMinimalBilling() {
        return BillingInfo.createDefault();
    }

    /**
     * Log billing info for debugging
     */
    public static void logBillingInfo(BillingInfo billingInfo) {
        logger.info("Generated billing info: {}", billingInfo);
    }
}
