package com.testarchitect.framework.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

/**
 * Product page object
 */
public class ProductPage extends BasePage {
    
    // Product details elements
    private final SelenideElement productTitle = $("h1.product-title");
    private final SelenideElement productPrice = $(".product-price");
    private final SelenideElement addToCartButton = $("button.add-to-cart");
    private final SelenideElement quantityInput = $("input[name='quantity']");
    private final SelenideElement productDescription = $(".product-description");
    private final SelenideElement productImage = $(".product-image img");
    
    // Review elements
    private final SelenideElement reviewsSection = $(".reviews-section");
    private final SelenideElement addReviewButton = $("button.add-review");
    private final SelenideElement reviewForm = $("form.review-form");
    private final SelenideElement starRating = $(".star-rating");
    private final SelenideElement reviewText = $("textarea[name='review']");
    private final SelenideElement submitReviewButton = $("button[type='submit']");
    
    @Step("Verify product details are displayed")
    public ProductPage verifyProductDetailsDisplayed() {
        logger.info("Verifying product details are displayed");
        productTitle.shouldBe(visible);
        productPrice.shouldBe(visible);
        addToCartButton.shouldBe(visible);
        return this;
    }
    
    @Step("Add product to cart")
    public ProductPage addToCart() {
        logger.info("Adding product to cart");
        addToCartButton.shouldBe(visible, enabled).click();
        return this;
    }
    
    @Step("Update quantity to: {quantity}")
    public ProductPage updateQuantity(int quantity) {
        logger.info("Updating quantity to: {}", quantity);
        quantityInput.clear();
        quantityInput.setValue(String.valueOf(quantity));
        return this;
    }
    
    @Step("Click on product to view detail")
    public ProductPage clickProductDetail() {
        logger.info("Clicking on product to view detail");
        productTitle.click();
        return this;
    }
    
    @Step("Scroll down to reviews section")
    public ProductPage scrollToReviews() {
        logger.info("Scrolling down to reviews section");
        reviewsSection.scrollTo();
        return this;
    }
    
    @Step("Click on add review button")
    public ProductPage clickAddReview() {
        logger.info("Clicking add review button");
        addReviewButton.shouldBe(visible).click();
        reviewForm.shouldBe(visible);
        return this;
    }
    
    @Step("Submit review with {stars} stars and text: {reviewContent}")
    public ProductPage submitReview(int stars, String reviewContent) {
        logger.info("Submitting review with {} stars", stars);
        
        // Click on star rating
        starRating.$("span:nth-child(" + stars + ")").click();
        
        // Enter review text
        reviewText.setValue(reviewContent);
        
        // Submit review
        submitReviewButton.click();
        
        return this;
    }
    
    @Step("Verify review is displayed")
    public ProductPage verifyReviewDisplayed() {
        logger.info("Verifying review is displayed");
        verifySuccessMessage("Review submitted successfully");
        return this;
    }
    
    @Step("Get product title")
    public String getProductTitle() {
        String title = productTitle.getText();
        logger.info("Product title: {}", title);
        return title;
    }
    
    @Step("Get product price")
    public String getProductPrice() {
        String price = productPrice.getText();
        logger.info("Product price: {}", price);
        return price;
    }
}
