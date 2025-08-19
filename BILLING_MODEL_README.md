# Billing Model Documentation

## Overview
The `BillingInfo` model provides a clean, reusable way to generate and manage billing information for checkout forms. It uses the Faker library to generate realistic random data for testing.

## Key Features
- ✅ **Random Data Generation**: Uses Faker to create realistic test data
- ✅ **Multiple Creation Patterns**: Default, random, and custom billing info
- ✅ **Type Safety**: Strongly typed model with getters
- ✅ **Factory Pattern**: Utility class for common billing scenarios
- ✅ **Backward Compatibility**: Legacy methods still work

## BillingInfo Model

### Creation Methods

```java
// 1. Default billing info (static data)
BillingInfo defaultBilling = BillingInfo.createDefault();

// 2. Random billing info (Faker-generated)
BillingInfo randomBilling = BillingInfo.createRandom();

// 3. Custom billing info (custom name/email + Faker for other fields)
BillingInfo customBilling = BillingInfo.createCustom("John", "Doe", "john@test.com");
```

### Example Output
```
Default: John Doe, john.doe@test.com, 123 Test Street, Test City, CA
Random: Doria Osinski, julian.wilkinson@yahoo.com, 5771 Arden Forest, Coyfurt, HI
Custom: John Doe, john@test.com, 2767 Nicky Station, East Vanceport, NJ
```

## CheckoutPage Methods

### New Methods (Recommended)

```java
CheckoutPage checkoutPage = new CheckoutPage();

// 1. Fill with custom BillingInfo object
BillingInfo billing = BillingInfo.createRandom();
checkoutPage.fillBillingDetails(billing);

// 2. Fill with random data (one-liner)
checkoutPage.fillBillingDetailsWithRandomData();

// 3. Fill with custom data + default payment
checkoutPage.fillBillingDetailsWithCustomData("Jane", "Smith", "jane@test.com");

// 4. Fill with default data + default payment (same as legacy)
checkoutPage.fillBillingDetailsWithDefaultPayment();

// 5. Select payment method separately
checkoutPage.selectDefaultPaymentMethod();
```

### Legacy Method (Still Supported)
```java
// Old hardcoded method still works for backward compatibility
checkoutPage.fillBillingDetailsWithDefaultPaymentLegacy();
```

## BillingDataFactory Utility

Pre-configured billing scenarios for common test cases:

```java
// US customer billing
BillingInfo usBilling = BillingDataFactory.createUSBilling();

// Test automation user
BillingInfo testUser = BillingDataFactory.createTestUserBilling();

// VIP customer
BillingInfo vipCustomer = BillingDataFactory.createVipCustomerBilling();

// Minimal required fields
BillingInfo minimal = BillingDataFactory.createMinimalBilling();
```

## Usage Examples

### Test with Multiple Billing Scenarios
```java
@Test
public void testMultipleBillingScenarios() {
    CheckoutPage checkoutPage = new CheckoutPage();
    
    BillingInfo[] scenarios = {
        BillingInfo.createDefault(),
        BillingInfo.createRandom(),
        BillingDataFactory.createVipCustomerBilling(),
        BillingDataFactory.createTestUserBilling()
    };
    
    for (BillingInfo billing : scenarios) {
        checkoutPage.fillBillingDetails(billing);
        // Verify checkout functionality
    }
}
```

### Data-Driven Testing
```java
@Test
public void testCheckoutWithRandomData() {
    CheckoutPage checkoutPage = new CheckoutPage();
    
    // Each test run will use different random data
    checkoutPage.fillBillingDetailsWithRandomData()
               .selectDefaultPaymentMethod()
               .clickPlaceOrder();
}
```

### Custom Test Scenarios
```java
@Test
public void testVipCustomerCheckout() {
    CheckoutPage checkoutPage = new CheckoutPage();
    
    BillingInfo vipBilling = BillingDataFactory.createVipCustomerBilling();
    checkoutPage.fillBillingDetails(vipBilling)
               .choosePaymentMethod("Credit Card")
               .clickPlaceOrder();
}
```

## Benefits

1. **Maintainable**: Centralized billing data management
2. **Realistic**: Faker generates believable test data
3. **Flexible**: Multiple ways to create billing info
4. **Reusable**: Same model works across different pages
5. **Debuggable**: Easy to log and inspect billing data
6. **Clean**: Removes hardcoded values from page objects

## Migration Guide

### Before (Old Way)
```java
// Hardcoded values in page object method
checkoutPage.fillBillingDetailsWithDefaultPayment(); // Fixed "John Doe"
```

### After (New Way)
```java
// Flexible billing data
BillingInfo billing = BillingInfo.createRandom(); // Different each time
checkoutPage.fillBillingDetails(billing);

// Or one-liner for random data
checkoutPage.fillBillingDetailsWithRandomData();

// Or specific customer type
BillingInfo vip = BillingDataFactory.createVipCustomerBilling();
checkoutPage.fillBillingDetails(vip);
```

## Dependencies
- `com.github.javafaker:javafaker:1.0.2` - For realistic data generation

## Field Mapping
The model includes all common billing fields:
- `firstName` / `lastName` - Personal names
- `email` - Email address
- `address` / `address2` - Street addresses
- `city` / `state` / `zip` - Location details
- `phone` - Phone number
- `country` - Country code (defaults to "US")
