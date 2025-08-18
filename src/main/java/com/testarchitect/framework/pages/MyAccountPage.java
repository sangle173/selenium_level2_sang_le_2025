package com.testarchitect.framework.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * My Account page object
 */
public class MyAccountPage extends BasePage {
    
    // Account navigation elements
    private final SelenideElement accountDashboard = $(".account-dashboard");
    private final SelenideElement ordersLink = $("a[href*='orders']");
    private final SelenideElement profileLink = $("a[href*='profile']");
    private final SelenideElement addressesLink = $("a[href*='addresses']");
    private final SelenideElement logoutLink = $("a[href*='logout']");
    
    @Step("Verify My Account page is displayed")
    public MyAccountPage verifyMyAccountPage() {
        logger.info("Verifying My Account page is displayed");
        accountDashboard.shouldBe(visible);
        return this;
    }
    
    @Step("Navigate to Orders")
    public OrderStatusPage navigateToOrders() {
        logger.info("Navigating to Orders");
        ordersLink.shouldBe(visible).click();
        return new OrderStatusPage();
    }
    
    @Step("Logout from account")
    public LoginPage logout() {
        logger.info("Logging out from account");
        logoutLink.shouldBe(visible).click();
        return new LoginPage();
    }
}
