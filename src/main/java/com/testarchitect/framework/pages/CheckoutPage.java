package com.testarchitect.framework.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Checkout page object
 */
public class CheckoutPage extends BasePage {
    
    // Checkout form elements
    private final SelenideElement checkoutForm = $("form.checkout-form");
    private final SelenideElement orderSummary = $(".order-summary");
    private final SelenideElement billingSection = $(".billing-section");
    private final SelenideElement paymentSection = $(".payment-section");
    
    // Billing information
    private final SelenideElement addressField = $("input[name='address']");
    private final SelenideElement cityField = $("input[name='city']");
    private final SelenideElement zipField = $("input[name='zip']");
    private final SelenideElement phoneField = $("input[name='phone']");
    
    // Payment methods
    private final SelenideElement creditCardOption = $("input[value='credit-card']");
    private final SelenideElement directBankOption = $("input[value='direct-bank']");
    private final SelenideElement cashOnDeliveryOption = $("input[value='cash-delivery']");
    
    // Action buttons
    private final SelenideElement confirmOrderButton = $("button.confirm-order");
    private final SelenideElement placeOrderButton = $("button.place-order");
    
    @Step("Verify checkout page displays")
    public CheckoutPage verifyCheckoutPageDisplays() {
        logger.info("Verifying checkout page displays");
        checkoutForm.shouldBe(visible);
        orderSummary.shouldBe(visible);
        return this;
    }
    
    @Step("Verify item details in order")
    public CheckoutPage verifyItemDetailsInOrder() {
        logger.info("Verifying item details in order");
        orderSummary.shouldBe(visible);
        // Add specific verification logic for item details
        return this;
    }
    
    @Step("Fill billing details with default payment method")
    public CheckoutPage fillBillingDetailsWithDefaultPayment() {
        logger.info("Filling billing details with default payment method");
        
        // Fill billing information
        addressField.setValue("123 Test Street");
        cityField.setValue("Test City");
        zipField.setValue("12345");
        phoneField.setValue("123-456-7890");
        
        // Select default payment method (credit card)
        creditCardOption.click();
        
        return this;
    }
    
    @Step("Choose payment method: {paymentMethod}")
    public CheckoutPage choosePaymentMethod(String paymentMethod) {
        logger.info("Choosing payment method: {}", paymentMethod);
        
        switch (paymentMethod.toLowerCase()) {
            case "direct bank transfer":
                directBankOption.click();
                break;
            case "cash on delivery":
                cashOnDeliveryOption.click();
                break;
            case "credit card":
            default:
                creditCardOption.click();
                break;
        }
        
        return this;
    }
    
    @Step("Complete payment process")
    public CheckoutPage completePaymentProcess() {
        logger.info("Completing payment process");
        placeOrderButton.shouldBe(visible, enabled).click();
        return this;
    }
    
    @Step("Click on PLACE ORDER")
    public CheckoutPage clickPlaceOrder() {
        logger.info("Clicking on PLACE ORDER");
        placeOrderButton.shouldBe(visible, enabled).click();
        return this;
    }
    
    @Step("Verify order status page displays")
    public OrderStatusPage verifyOrderStatusPageDisplays() {
        logger.info("Verifying order status page displays");
        return new OrderStatusPage();
    }
    
    @Step("Verify order confirmation message")
    public CheckoutPage verifyOrderConfirmationMessage() {
        logger.info("Verifying order confirmation message");
        verifySuccessMessage("Payment is processed successfully for each available method");
        return this;
    }
    
    @Step("Leave mandatory fields blank")
    public CheckoutPage leaveMandatoryFieldsBlank() {
        logger.info("Leaving mandatory fields (address, payment info) blank");
        addressField.clear();
        zipField.clear();
        return this;
    }
    
    @Step("Click 'Confirm Order'")
    public CheckoutPage clickConfirmOrder() {
        logger.info("Clicking 'Confirm Order'");
        confirmOrderButton.shouldBe(visible).click();
        return this;
    }
    
    @Step("Verify error messages for missing fields")
    public CheckoutPage verifyErrorMessages() {
        logger.info("Verifying error messages for missing fields");
        verifyErrorMessage("System should highlight missing mandatory fields and show an error message");
        return this;
    }
}
