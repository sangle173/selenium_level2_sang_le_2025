package com.testarchitect.framework.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Order Status/History page object
 */
public class OrderStatusPage extends BasePage {
    
    // Order status elements
    private final SelenideElement orderStatusContainer = $(".order-status-container");
    private final SelenideElement orderConfirmation = $(".order-confirmation");
    private final SelenideElement orderNumber = $(".order-number");
    private final SelenideElement orderDate = $(".order-date");
    private final SelenideElement orderTotal = $(".order-total");
    
    // Order history elements
    private final SelenideElement myAccountLink = $("a[href*='account']");
    private final SelenideElement ordersLink = $("a[href*='orders']");
    private final ElementsCollection orderHistoryList = $$(".order-history-item");
    private final SelenideElement orderDetailsSection = $(".order-details");
    
    @Step("Verify order details with billing and item information")
    public OrderStatusPage verifyOrderDetails() {
        logger.info("Verifying order details with billing and item information");
        orderStatusContainer.shouldBe(visible);
        orderConfirmation.shouldBe(visible);
        orderNumber.shouldBe(visible);
        orderDate.shouldBe(visible);
        orderTotal.shouldBe(visible);
        return this;
    }
    
    @Step("Navigate to My Account page")
    public MyAccountPage navigateToMyAccount() {
        logger.info("Navigating to My Account page");
        myAccountLink.shouldBe(visible).click();
        return new MyAccountPage();
    }
    
    @Step("Click on Orders in left navigation")
    public OrderStatusPage clickOnOrders() {
        logger.info("Clicking on Orders in left navigation");
        ordersLink.shouldBe(visible).click();
        return this;
    }
    
    @Step("Verify order details")
    public OrderStatusPage verifyOrderDetailsInHistory() {
        logger.info("Verifying order details in history");
        orderDetailsSection.shouldBe(visible);
        verifySuccessMessage("The orders are displayed in the user's order history");
        return this;
    }
    
    @Step("Get order number")
    public String getOrderNumber() {
        String number = orderNumber.getText();
        logger.info("Order number: {}", number);
        return number;
    }
    
    @Step("Get order total")
    public String getOrderTotal() {
        String total = orderTotal.getText();
        logger.info("Order total: {}", total);
        return total;
    }
    
    @Step("Verify order appears in history")
    public OrderStatusPage verifyOrderInHistory() {
        logger.info("Verifying order appears in history");
        orderHistoryList.shouldHave(sizeGreaterThan(0));
        return this;
    }
}
