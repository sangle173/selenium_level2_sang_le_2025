package com.testarchitect.framework.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        
        // Firefox specific configuration
        if ("firefox".equalsIgnoreCase(config.getBrowser())) {
            String firefoxBinary = config.getFirefoxBinaryPath();
            if (firefoxBinary != null && !firefoxBinary.isEmpty()) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                // Add additional Firefox preferences for better compatibility
                firefoxOptions.addPreference("browser.startup.page", 0);
                firefoxOptions.addPreference("browser.startup.homepage_override.mstone", "ignore");
                firefoxOptions.addPreference("browser.usedOnWindows10", false);
                firefoxOptions.addPreference("dom.disable_beforeunload", true);
                firefoxOptions.addPreference("network.http.max-connections", 200);
                firefoxOptions.addPreference("network.http.max-connections-per-server", 20);
                firefoxOptions.addPreference("dom.max_script_run_time", 0);
                firefoxOptions.addPreference("dom.max_chrome_script_run_time", 0);
                firefoxOptions.addPreference("browser.cache.disk.enable", false);
                firefoxOptions.addPreference("browser.cache.memory.enable", false);
                firefoxOptions.addPreference("browser.cache.offline.enable", false);
                firefoxOptions.addPreference("network.http.use-cache", false);
                Configuration.browserCapabilities = firefoxOptions;
                Configuration.pageLoadTimeout = 120000; // 2 minutes
                Configuration.timeout = 60000; // 60 seconds
                logger.info("Firefox binary path set to: {}", firefoxBinary);
            }
        }
        
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
