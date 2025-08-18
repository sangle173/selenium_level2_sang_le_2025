package com.testarchitect.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Excel utility class for reading test data from Excel files
 */
public class ExcelUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    
    /**
     * Read test data from Excel file and return as Object[][]
     * @param filePath Path to Excel file
     * @param sheetName Name of the sheet
     * @return Object[][] containing test data
     */
    public static Object[][] getTestData(String filePath, String sheetName) {
        logger.info("Reading test data from Excel file: {} sheet: {}", filePath, sheetName);
        
        List<Map<String, String>> testData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }
            
            // Get header row
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row not found in sheet: " + sheetName);
            }
            
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(getCellValueAsString(cell));
            }
            
            // Read data rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && !isRowEmpty(row)) {
                    Map<String, String> rowData = new HashMap<>();
                    for (int j = 0; j < headers.size(); j++) {
                        Cell cell = row.getCell(j);
                        String cellValue = getCellValueAsString(cell);
                        rowData.put(headers.get(j), cellValue);
                    }
                    testData.add(rowData);
                }
            }
            
        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", filePath, e);
            throw new RuntimeException("Failed to read Excel file: " + filePath, e);
        }
        
        logger.info("Successfully read {} test data rows from Excel", testData.size());
        
        // Convert to Object[][]
        Object[][] result = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            result[i][0] = testData.get(i);
        }
        
        return result;
    }
    
    /**
     * Get test data for specific test case IDs
     * @param filePath Path to Excel file
     * @param sheetName Name of the sheet
     * @param testCaseIds List of test case IDs to filter
     * @return Object[][] containing filtered test data
     */
    public static Object[][] getTestDataByIds(String filePath, String sheetName, String... testCaseIds) {
        logger.info("Reading test data for specific test cases: {}", Arrays.toString(testCaseIds));
        
        Object[][] allData = getTestData(filePath, sheetName);
        List<Object[]> filteredData = new ArrayList<>();
        
        Set<String> targetIds = new HashSet<>(Arrays.asList(testCaseIds));
        
        for (Object[] row : allData) {
            @SuppressWarnings("unchecked")
            Map<String, String> rowData = (Map<String, String>) row[0];
            String testCaseId = rowData.get("TestCaseID");
            
            if (targetIds.contains(testCaseId)) {
                filteredData.add(row);
            }
        }
        
        logger.info("Filtered {} test data rows for specified test cases", filteredData.size());
        return filteredData.toArray(new Object[0][]);
    }
    
    /**
     * Convert cell value to string
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }
    
    /**
     * Check if a row is empty
     */
    private static boolean isRowEmpty(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !getCellValueAsString(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Get all sheet names from Excel file
     */
    public static List<String> getSheetNames(String filePath) {
        List<String> sheetNames = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                sheetNames.add(workbook.getSheetName(i));
            }
            
        } catch (IOException e) {
            logger.error("Error reading sheet names from Excel file: {}", filePath, e);
            throw new RuntimeException("Failed to read sheet names from Excel file: " + filePath, e);
        }
        
        return sheetNames;
    }
}
