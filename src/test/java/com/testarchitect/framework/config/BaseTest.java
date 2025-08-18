package com.testarchitect.framework.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * Base test class that sets up Selenide configuration and Allure integration
 */
public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected ConfigManager config = ConfigManager.getInstance();

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        logger.info("Setting up test suite configuration");
        configureSelenide();
        configureAllure();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        logger.info("Starting test execution");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        logger.info("Cleaning up after test execution");
        closeWebDriver();
    }

    private void configureSelenide() {
        logger.info("Configuring Selenide settings");
        
        // Browser configuration
        Configuration.browser = config.getBrowser();
        Configuration.headless = config.isHeadless();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getTimeout();
        
        // Additional Selenide configurations
        Configuration.screenshots = true;
        Configuration.savePageSource = false;
        Configuration.reopenBrowserOnFail = true;
        Configuration.fastSetValue = true;
        Configuration.clickViaJs = false;
        
        // Remote browser configuration (if using Selenium Grid)
        if (config.isGridEnabled()) {
            Configuration.remote = config.getGridUrl();
        }
        
        logger.info("Selenide configured with browser: {}, headless: {}, size: {}", 
                   config.getBrowser(), config.isHeadless(), config.getBrowserSize());
    }

    private void configureAllure() {
        logger.info("Configuring Allure reporting");
        SelenideLogger.addListener("AllureSelenide", 
            new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true));
    }
}
