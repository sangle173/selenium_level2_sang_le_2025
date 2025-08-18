package com.testarchitect.tests;

import com.testarchitect.framework.config.BaseTest;
import com.testarchitect.framework.utils.ExcelUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

/**
 * Smoke tests to validate framework functionality
 */
@Epic("Framework Validation")
@Feature("Basic Framework Tests")
public class FrameworkValidationTests extends BaseTest {

    @Test
    @Description("Validate Excel data reading functionality")
    public void testExcelDataReading() {
        Allure.step("Read test data from Excel file", () -> {
            Object[][] testData = ExcelUtils.getTestData(
                config.getTestDataPath(), 
                "TestCases"
            );
            
            assert testData.length > 0 : "No test data found in Excel file";
            logger.info("Successfully read {} test cases from Excel file", testData.length);
            
            // Log first test case for validation
            if (testData.length > 0) {
                @SuppressWarnings("unchecked")
                java.util.Map<String, String> firstTestCase = 
                    (java.util.Map<String, String>) testData[0][0];
                logger.info("First test case ID: {}", firstTestCase.get("TestCaseID"));
                logger.info("First test case description: {}", firstTestCase.get("TestDescription"));
            }
        });
    }

    @Test  
    @Description("Validate configuration loading")
    public void testConfigurationLoading() {
        Allure.step("Validate configuration properties", () -> {
            assert config.getAppUrl() != null : "App URL should not be null";
            assert config.getBrowser() != null : "Browser should not be null";
            assert config.getTestDataPath() != null : "Test data path should not be null";
            
            logger.info("App URL: {}", config.getAppUrl());
            logger.info("Browser: {}", config.getBrowser());
            logger.info("Test Data Path: {}", config.getTestDataPath());
            logger.info("Timeout: {}", config.getTimeout());
        });
    }

    @Test
    @Description("Validate Selenide configuration")
    public void testSelenideConfiguration() {
        Allure.step("Check Selenide configuration", () -> {
            // This test validates that Selenide is properly configured
            // without actually opening a browser
            assert com.codeborne.selenide.Configuration.browser != null : "Selenide browser should be configured";
            assert com.codeborne.selenide.Configuration.timeout > 0 : "Selenide timeout should be positive";
            
            logger.info("Selenide browser: {}", com.codeborne.selenide.Configuration.browser);
            logger.info("Selenide timeout: {}", com.codeborne.selenide.Configuration.timeout);
            logger.info("Selenide headless: {}", com.codeborne.selenide.Configuration.headless);
        });
    }
}
