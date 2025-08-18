package com.testarchitect.framework.utils;

import com.testarchitect.framework.config.ConfigManager;
import org.testng.annotations.DataProvider;

/**
 * Data provider class for TestNG tests
 */
public class TestDataProvider {
    private static final ConfigManager config = ConfigManager.getInstance();
    
    @DataProvider(name = "loginTestData")
    public static Object[][] getLoginTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_01"
        );
    }
    
    @DataProvider(name = "multipleItemsTestData")
    public static Object[][] getMultipleItemsTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_02"
        );
    }
    
    @DataProvider(name = "paymentMethodsTestData")
    public static Object[][] getPaymentMethodsTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_03"
        );
    }
    
    @DataProvider(name = "sortingTestData")
    public static Object[][] getSortingTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_04"
        );
    }
    
    @DataProvider(name = "orderHistoryTestData")
    public static Object[][] getOrderHistoryTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_05"
        );
    }
    
    @DataProvider(name = "guestCheckoutTestData")
    public static Object[][] getGuestCheckoutTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_06"
        );
    }
    
    @DataProvider(name = "errorHandlingTestData")
    public static Object[][] getErrorHandlingTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_07"
        );
    }
    
    @DataProvider(name = "cartManagementTestData")
    public static Object[][] getCartManagementTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_08", "TC_09"
        );
    }
    
    @DataProvider(name = "reviewTestData")
    public static Object[][] getReviewTestData() {
        return ExcelUtils.getTestDataByIds(
            config.getTestDataPath(), 
            "TestCases", 
            "TC_10"
        );
    }
    
    @DataProvider(name = "allTestData")
    public static Object[][] getAllTestData() {
        return ExcelUtils.getTestData(
            config.getTestDataPath(), 
            "TestCases"
        );
    }
}
