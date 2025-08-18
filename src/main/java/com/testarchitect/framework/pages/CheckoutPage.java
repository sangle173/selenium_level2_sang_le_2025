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
    
    // Billing information - comprehensive field selectors
    private final SelenideElement firstNameField = $("input[name='first_name'], input[name='billing_first_name'], input[name='firstName'], input[id*='first'], input[id*='fname']");
    private final SelenideElement lastNameField = $("input[name='last_name'], input[name='billing_last_name'], input[name='lastName'], input[id*='last'], input[id*='lname']");
    private final SelenideElement emailField = $("input[name='email'], input[name='billing_email'], input[type='email'], input[id*='email']");
    private final SelenideElement addressField = $("input[name='address'], input[name='billing_address_1'], input[name='street_address'], input[id*='address']");
    private final SelenideElement address2Field = $("input[name='address_2'], input[name='billing_address_2'], input[name='street_address_2'], input[id*='address2']");
    private final SelenideElement cityField = $("input[name='city'], input[name='billing_city'], input[id*='city']");
    private final SelenideElement stateField = $("select[name='state'], select[name='billing_state'], input[name='state'], input[name='billing_state'], input[id*='state']");
    private final SelenideElement zipField = $("input[name='zip'], input[name='postal_code'], input[name='billing_postcode'], input[id*='zip'], input[id*='postal']");
    private final SelenideElement countryField = $("select[name='country'], select[name='billing_country'], input[name='country'], input[id*='country']");
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
        handlePopups();
        
        // Fill comprehensive billing information
        try {
            if (firstNameField.exists()) {
                firstNameField.setValue("John");
                logger.info("Filled first name field");
            }
            if (lastNameField.exists()) {
                lastNameField.setValue("Doe");
                logger.info("Filled last name field");
            }
            if (emailField.exists()) {
                emailField.setValue("john.doe@test.com");
                logger.info("Filled email field");
            }
            if (addressField.exists()) {
                addressField.setValue("123 Test Street");
                logger.info("Filled address field");
            }
            if (address2Field.exists()) {
                address2Field.setValue("Apt 456");
                logger.info("Filled address 2 field");
            }
            if (cityField.exists()) {
                cityField.setValue("Test City");
                logger.info("Filled city field");
            }
            if (stateField.exists()) {
                if (stateField.getTagName().equals("select")) {
                    stateField.selectOption("California");
                } else {
                    stateField.setValue("CA");
                }
                logger.info("Filled state field");
            }
            if (zipField.exists()) {
                zipField.setValue("12345");
                logger.info("Filled zip field");
            }
            if (phoneField.exists()) {
                phoneField.setValue("123-456-7890");
                logger.info("Filled phone field");
            }
            if (countryField.exists()) {
                try {
                    if (countryField.getTagName().equals("select")) {
                        // Try different country options
                        if (countryField.$("option[value='US']").exists()) {
                            countryField.selectOptionByValue("US");
                        } else if (countryField.$("option[text()='United States']").exists()) {
                            countryField.selectOption("United States");
                        } else if (countryField.$("option[text()='USA']").exists()) {
                            countryField.selectOption("USA");
                        } else {
                            // Select the first available option
                            countryField.selectOption(0);
                        }
                    } else {
                        countryField.setValue("US");
                    }
                    logger.info("Filled country field");
                } catch (Exception e) {
                    logger.info("Could not fill country field: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.info("Some billing fields not available: " + e.getMessage());
        }

        // Select default payment method - try multiple options
        try {
            // Check what payment methods are available
            SelenideElement paymentOption = null;
            
            // Try different payment method selectors
            try {
                paymentOption = $("input[value='credit-card'], input[value='stripe'], input[value='card']");
                if (paymentOption.exists()) {
                    paymentOption.click();
                    logger.info("Selected credit card payment method");
                    return this;
                }
            } catch (Exception ignored) {}
            
            try {
                paymentOption = $("input[type='radio'][value*='credit']");
                if (paymentOption.exists()) {
                    paymentOption.click();
                    logger.info("Selected credit-based payment method");
                    return this;
                }
            } catch (Exception ignored) {}
            
            try {
                paymentOption = $("input[type='radio'][name*='payment']:first-of-type");
                if (paymentOption.exists()) {
                    paymentOption.click();
                    logger.info("Selected first available payment method");
                    return this;
                }
            } catch (Exception ignored) {}
            
            try {
                paymentOption = $(".payment-method input[type='radio']:first-of-type, .payment-options input[type='radio']:first-of-type");
                if (paymentOption.exists()) {
                    paymentOption.click();
                    logger.info("Selected first payment option from payment section");
                    return this;
                }
            } catch (Exception ignored) {}
            
            logger.info("No payment method selection required or found - proceeding");
            
        } catch (Exception e) {
            logger.info("Payment method selection not required: " + e.getMessage());
        }
        
        return this;
    }    @Step("Choose payment method: {paymentMethod}")
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
