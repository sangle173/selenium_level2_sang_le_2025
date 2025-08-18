package com.testarchitect.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility class to create Excel test data file from CSV data
 */
public class ExcelDataCreator {
    
    public static void createTestDataExcel() {
        String[][] testData = {
            {"TestCaseID", "TestDescription", "Precondition", "TestSteps", "ExpectedResult", "PaymentMethod"},
            {"TC_01", "Verify users can buy an item successfully", "Register a valid account", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Navigate to All departments section\n4. Select Electronic Components & Supplies\n5. Verify items should be displayed as a grid\n6. Switch view to list\n7. Verify the items should be displayed as a list\n8. Select any item randomly to purchase\n9. Click 'Add to Cart'\n10. Go to the cart\n11. Verify item details in mini content\n12. Click on Checkout\n13. Verify Checkout page displays\n14. Verify item details in order\n15. Fill billing details with default payment method\n16. Click on PLACE ORDER\n17. Verify the Order details with billing and item information", 
             "Order confirmation message show correctly", "Credit Card"},
            {"TC_02", "Verify users can buy multiple item successfully", "User is logged in", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Go to Shop page\n4. Select multiple items and add to cart\n5. Go to the cart and verify all selected items\n6. Proceed to checkout page\n7. Verify order confirmation message", 
             "All selected items are purchased, and order confirmation is received", "Credit Card"},
            {"TC_03", "Verify users can buy an item using different payment methods", "User is logged in", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Go to Shop page\n4. Select an item and add to cart\n5. Go to Checkout page\n6. Choose a different payment method (Direct bank transfer, Cash on delivery)\n7. Complete the payment process\n8. Verify order confirmation message", 
             "Payment is processed successfully for each available method", "Direct Bank Transfer"},
            {"TC_04", "Verify users can sort items by price", "User is logged in", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Go to Shop page\n4. Click on 'Sort by' dropdown\n5. Select 'Price' option\n6. Verify the sorting functionality", 
             "The items should be sorted correctly by price", "N/A"},
            {"TC_05", "Verify orders appear in order history", "User has placed 02 orders", 
             "1. Go to My Account page\n2. Click on 'Orders' in left navigation\n3. Verify order details", 
             "The orders are displayed in the user's order history", "N/A"},
            {"TC_06", "Verify users try to buy an item without logging in", "(As a guest)", 
             "1. Open https://demo.testarchitect.com/\n2. Navigate to 'Shop' or 'Products' section\n3. Add a product to cart\n4. Click on Cart button\n5. Proceed to complete order", 
             "Guests should be able to purchase the item successfully", "N/A"},
            {"TC_07", "Ensure proper error handling when mandatory fields are blank", "User is at checkout", 
             "1. Leave mandatory fields (address, payment info) blank\n2. Click 'Confirm Order'\n3. Verify error messages", 
             "System should highlight missing mandatory fields and show an error message", "N/A"},
            {"TC_08", "Verify users can clear the cart", "User added the items into cart", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Go to Shopping cart page\n4. Verify items show in table\n5. Click on Clear shopping cart\n6. Verify empty cart page displays", 
             "YOUR SHOPPING CART IS EMPTY displays", "N/A"},
            {"TC_09", "Verify users can update quantity of products in cart", "User added an item into cart", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Go to Shopping cart page\n4. Add a product\n5. Go to the cart\n6. Verify items show in table\n7. Click on Plus(+) button\n8. Verify quantity of product and SUB TOTAL price\n9. Enter 4 into quantity textbox then click on UPDATE CART button\n10. Verify quantity of product and SUB TOTAL price\n11. Click on Minus(-) button\n12. Verify quantity of product and SUB TOTAL price", 
             "Products quantity and sub total price are updated correctly", "N/A"},
            {"TC_10", "Verify users can post a review", "User is logged in", 
             "1. Open browser and go to https://demo.testarchitect.com/\n2. Login with valid credentials\n3. Go to Shop page\n4. Click on a product to view detail\n5. Scroll down then click on REVIEWS tab\n6. Submit a review\n7. Verify new review", 
             "Number of star & review content are correctly. Number of reviews is updated", "N/A"}
        };
        
        String filePath = "src/test/resources/testdata/testcases.xlsx";
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("TestCases");
            
            for (int i = 0; i < testData.length; i++) {
                Row row = sheet.createRow(i);
                for (int j = 0; j < testData[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(testData[i][j]);
                }
            }
            
            // Auto-size columns
            for (int i = 0; i < testData[0].length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
            
            System.out.println("Excel file created successfully at: " + filePath);
            
        } catch (IOException e) {
            System.err.println("Error creating Excel file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        createTestDataExcel();
    }
}
