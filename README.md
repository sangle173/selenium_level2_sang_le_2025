# Selenium Level 2 Test Automation Framework

## Overview

This is a comprehensive test automation framework built using:
- **Selenide** (Latest version 7.5.0) - Modern Selenium wrapper
- **Java 17** - Programming language
- **Maven** - Build tool and dependency management
- **TestNG** - Testing framework with annotations and data providers
- **Allure Reports** - Rich test reporting with screenshots and logs

## Project Structure

```
selenium_level2_sang_le_2025/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/testarchitect/framework/
│   │           ├── config/          # Configuration classes
│   │           ├── pages/           # Page Object Model classes
│   │           └── utils/           # Utility classes
│   └── test/
│       ├── java/
│       │   └── com/testarchitect/tests/  # Test classes
│       └── resources/
│           ├── testdata/            # Test data files
│           ├── config.properties    # Configuration file
│           ├── testng.xml          # TestNG suite configuration
│           └── logback-test.xml    # Logging configuration
├── pom.xml                         # Maven dependencies and plugins
└── README.md                       # This file
```

## Features

### Framework Features
- **Page Object Model (POM)** - Clean separation of page elements and test logic
- **Data-Driven Testing** - Excel/CSV based test data management
- **Configuration Management** - Externalized configuration with properties file
- **Logging** - Comprehensive logging with Logback
- **Allure Reporting** - Rich HTML reports with screenshots and test steps
- **Cross-Browser Support** - Chrome, Firefox, Edge support
- **Selenium Grid Support** - Remote execution capability
- **Parallel Execution** - TestNG parallel test execution

### Best Practices Implemented
- **Wait Handling** - Selenide built-in smart waits
- **Design Patterns** - Singleton, Factory, Page Object patterns
- **Exception Handling** - Comprehensive error handling
- **Test Data Management** - External data files and parameterization
- **Reporting** - Detailed test reports with Allure

## Test Cases Implementation

Based on the provided Excel test cases, the following scenarios are automated:

### TODO: Test Cases to Implement

#### ✅ Completed Test Cases

1. **TC_01: User Login and Purchase Flow**
   - ✅ User registration and login
   - ✅ Navigate to departments (Electronic Components & Supplies)
   - ✅ Grid/List view verification
   - ✅ Add item to cart and checkout
   - ✅ Order confirmation verification

2. **TC_02: Multiple Items Purchase**
   - ✅ Add multiple items to cart
   - ✅ Verify all items in cart
   - ✅ Complete checkout process

3. **TC_03: Different Payment Methods**
   - ✅ Credit Card payment
   - ✅ Direct Bank Transfer
   - ✅ Cash on Delivery
   - ✅ Payment processing verification

4. **TC_04: Product Sorting**
   - ✅ Sort items by price
   - ✅ Verify sorting functionality

5. **TC_05: Order History**
   - ✅ Navigate to My Account
   - ✅ View order history
   - ✅ Verify order details

#### ⏳ Partially Implemented Test Cases

6. **TC_06: Guest Checkout**
   - ⏳ Framework setup completed
   - 🔄 Need to implement guest checkout flow
   - 🔄 Add guest user journey

7. **TC_07: Error Handling**
   - ✅ Mandatory fields validation
   - ✅ Error message verification
   - 🔄 Need to enhance error scenarios

8. **TC_08: Cart Management - Clear Cart**
   - ✅ Add items to cart
   - ✅ Clear cart functionality
   - ✅ Empty cart verification

9. **TC_09: Cart Management - Update Quantity**
   - ✅ Update product quantity
   - ✅ Price recalculation verification
   - 🔄 Need to add plus/minus button functionality

10. **TC_10: Product Reviews**
    - ✅ Navigate to product details
    - ✅ Submit product review
    - ✅ Review verification
    - 🔄 Need to add star rating verification

#### 🔄 TODO: Additional Enhancements Needed

- **Excel Data Integration**: Convert CSV to Excel format and enhance ExcelUtils
- **Selenium Grid Setup**: Configure for remote execution
- **CI/CD Integration**: Add GitHub Actions or Jenkins pipeline
- **Database Validation**: Add database verification steps
- **API Testing Integration**: Add REST API validation
- **Mobile Testing**: Extend for mobile web testing
- **Performance Testing**: Add page load time validations
- **Accessibility Testing**: Add WCAG compliance checks

## Quick Start

### Prerequisites
- Java 17 installed
- Maven 3.6+ installed
- Chrome browser installed

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd selenium_level2_sang_le_2025
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run tests**
   ```bash
   # Run all tests
   mvn test
   
   # Run specific test suite
   mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
   
   # Run with specific browser
   mvn test -Dbrowser=chrome
   
   # Run in headless mode
   mvn test -Dbrowser.headless=true
   ```

4. **Generate Allure reports**
   ```bash
   # Generate report
   mvn allure:report
   
   # Serve report (opens in browser)
   mvn allure:serve
   ```

## Configuration

### Browser Configuration
- **Default**: Chrome
- **Supported**: chrome, firefox, edge
- **Headless mode**: Configurable via properties

### Test Data
- **Location**: `src/test/resources/testdata/testcases.csv`
- **Format**: CSV (can be extended to Excel)
- **Usage**: Data-driven tests with TestNG DataProvider

### Environment Configuration
- **Config file**: `src/test/resources/config.properties`
- **Override**: System properties override file properties
- **Environments**: QA, UAT, PROD configurations

## Running Tests

### Test Execution Options

```bash
# Run smoke tests only
mvn test -Dgroups=smoke

# Run regression tests
mvn test -Dgroups=regression

# Run tests in parallel
mvn test -DparallelMode=methods -DthreadCount=3

# Run with custom properties
mvn test -Dapp.url=https://demo.testarchitect.com/ -Dbrowser=firefox
```

### Test Reports

After test execution:
1. **Allure Reports**: `target/allure-report/index.html`
2. **TestNG Reports**: `target/surefire-reports/index.html`
3. **Logs**: `target/logs/test-execution.log`

## Troubleshooting

### Common Issues

1. **WebDriver Issues**
   - Ensure Chrome browser is installed
   - Check ChromeDriver compatibility
   - Use `mvn clean install` to refresh dependencies

2. **Test Data Issues**
   - Verify CSV file format
   - Check file permissions
   - Ensure test data path in config.properties

3. **Allure Report Issues**
   - Install Allure CLI: `npm install -g allure-commandline`
   - Generate reports: `mvn allure:report`

## Development Guidelines

### Adding New Test Cases
1. Create test data in CSV file
2. Add data provider method in TestDataProvider
3. Implement page objects if needed
4. Write test method with Allure annotations
5. Add test to TestNG suite XML

### Code Standards
- Follow Page Object Model pattern
- Use Allure annotations for reporting
- Implement proper wait strategies
- Add comprehensive logging
- Write meaningful test descriptions

## Dependencies

### Core Dependencies
- **Selenide**: 7.5.0 (Latest)
- **TestNG**: 7.8.0
- **Allure**: 2.24.0
- **Apache POI**: 5.2.4 (Excel handling)
- **Jackson**: 2.15.2 (JSON processing)
- **Logback**: 1.4.11 (Logging)

### Build Tools
- **Maven**: 3.11.0
- **AspectJ**: 1.9.20.1 (Allure integration)

## Contributing

1. Fork the repository
2. Create a feature branch
3. Implement changes with tests
4. Ensure all tests pass
5. Submit pull request

## Support

For issues and questions:
- Check troubleshooting section
- Review logs in `target/logs/`
- Create GitHub issue with details

---

**Framework Status**: ✅ Phase 1-7 Complete | 🔄 Enhancements in Progress

**Last Updated**: August 18, 2025
