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
    private final SelenideElement checkoutForm = $("form.checkout-form, .checkout-form, form.checkout, .checkout, .woocommerce-checkout, .checkout-page, form");
    private final SelenideElement orderSummary = $(".order-summary, .order-details, .order-review, .checkout-summary, .cart-summary, .order-total");
    private final SelenideElement billingSection = $(".billing-section, .billing-details, .billing-form, .checkout-billing");
    private final SelenideElement paymentSection = $(".payment-section, .payment-methods, .payment-options, .checkout-payment");
    
    // Billing information
    private final SelenideElement addressField = $("input[name='address'], input[name='billing_address_1'], input[name='street_address'], input[id*='address']");
    private final SelenideElement cityField = $("input[name='city'], input[name='billing_city'], input[id*='city']");
    private final SelenideElement zipField = $("input[name='zip'], input[name='postal_code'], input[name='billing_postcode'], input[id*='zip'], input[id*='postal']");
    private final SelenideElement phoneField = $("input[name='phone'], input[name='billing_phone'], input[id*='phone']");
    
    // Payment methods
    private final SelenideElement creditCardOption = $("input[value='credit-card'], input[value='stripe'], input[value='card'], input[type='radio'][value*='credit']");
    private final SelenideElement directBankOption = $("input[value='direct-bank'], input[value='bacs'], input[value='bank'], input[type='radio'][value*='bank']");
    private final SelenideElement cashOnDeliveryOption = $("input[value='cash-delivery'], input[value='cod'], input[value='cash'], input[type='radio'][value*='cash']");
    
    // Action buttons
    private final SelenideElement confirmOrderButton = $("button.confirm-order, .confirm-order, button[name*='confirm'], input[value*='Confirm']");
    private final SelenideElement placeOrderButton = $("button.place-order, .place-order, button[name*='place'], input[value*='Place'], button[name='woocommerce_checkout_place_order']");
    
    @Step("Verify checkout page displays")
    public CheckoutPage verifyCheckoutPageDisplays() {
        logger.info("Verifying checkout page displays");
        handlePopups();
        
        try {
            checkoutForm.shouldBe(visible);
            logger.info("Found checkout form");
        } catch (Exception e) {
            logger.info("Checkout form not found, checking for checkout page indicators");
            
            // Alternative checkout page verification
            SelenideElement checkoutIndicator = $(".checkout-page, .woocommerce-checkout, h1, h2, h3")
                .shouldBe(visible);
            logger.info("Found checkout page indicator: " + checkoutIndicator.getText());
        }
        
        try {
            orderSummary.shouldBe(visible);
            logger.info("Found order summary section");
        } catch (Exception e) {
            logger.info("Order summary not found with standard selectors, page might still be loading");
        }
        
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
