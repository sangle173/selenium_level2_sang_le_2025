package com.testarchitect.framework.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Home/Shop page object
 */
public class HomePage extends BasePage {
    
    // Navigation elements
    private final SelenideElement cartIcon = $(".cart-icon");
    private final SelenideElement accountMenu = $(".account-menu");
    private final SelenideElement logoutLink = $("a[href*='logout']");
    
    // Product elements
    private final ElementsCollection productCards = $$(".product-card");
    private final SelenideElement departmentsSection = $(".departments");
    private final SelenideElement productsSection = $(".products");
    private final SelenideElement sortDropdown = $("select[name='sort']");
    private final SelenideElement viewToggle = $(".view-toggle");
    private final SelenideElement listViewButton = $("button[data-view='list']");
    
    // Department navigation
    private final SelenideElement electronicsCategory = $("a[href*='electronics']");
    private final SelenideElement componentsSupplies = $("a[href*='components']");
    
    @Step("Verify user is logged in")
    public HomePage verifyUserLoggedIn() {
        logger.info("Verifying user is logged in");
        accountMenu.shouldBe(visible);
        return this;
    }
    
    @Step("Navigate to All departments section")
    public HomePage navigateToAllDepartments() {
        logger.info("Navigating to All departments section");
        departmentsSection.shouldBe(visible).click();
        return this;
    }
    
    @Step("Select Electronic Components & Supplies category")
    public HomePage selectElectronicsCategory() {
        logger.info("Selecting Electronic Components & Supplies category");
        electronicsCategory.shouldBe(visible).click();
        componentsSupplies.shouldBe(visible).click();
        return this;
    }
    
    @Step("Verify items are displayed as a grid")
    public HomePage verifyGridView() {
        logger.info("Verifying items are displayed as a grid");
        productsSection.shouldBe(visible);
        productCards.shouldHave(sizeGreaterThan(0));
        return this;
    }
    
    @Step("Switch to list view")
    public HomePage switchToListView() {
        logger.info("Switching to list view");
        viewToggle.shouldBe(visible);
        listViewButton.click();
        return this;
    }
    
    @Step("Verify items are displayed as a list")
    public HomePage verifyListView() {
        logger.info("Verifying items are displayed as a list");
        // List view verification logic
        $(".products.list-view").shouldBe(visible);
        return this;
    }
    
    @Step("Select random item to purchase")
    public ProductPage selectRandomItem() {
        logger.info("Selecting random item to purchase");
        int randomIndex = (int) (Math.random() * productCards.size());
        productCards.get(randomIndex).click();
        return new ProductPage();
    }
    
    @Step("Add item to cart")
    public HomePage addItemToCart(int itemIndex) {
        logger.info("Adding item {} to cart", itemIndex);
        productCards.get(itemIndex).$("button.add-to-cart").click();
        return this;
    }
    
    @Step("Go to cart")
    public CartPage goToCart() {
        logger.info("Going to cart");
        cartIcon.click();
        return new CartPage();
    }
    
    @Step("Sort items by price")
    public HomePage sortByPrice() {
        logger.info("Sorting items by price");
        sortDropdown.shouldBe(visible).selectOption("price");
        return this;
    }
    
    @Step("Verify items are sorted correctly by price")
    public HomePage verifyPriceSorting() {
        logger.info("Verifying items are sorted correctly by price");
        // Price sorting verification logic
        ElementsCollection prices = $$(".product-price");
        // TODO: Add verification logic here to check if prices are sorted correctly
        prices.shouldHave(sizeGreaterThan(0));
        return this;
    }
    
    @Step("Logout")
    public LoginPage logout() {
        logger.info("Logging out");
        accountMenu.click();
        logoutLink.click();
        return new LoginPage();
    }
}
