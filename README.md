# Selenium Test Automation Framework

A robust and scalable Selenium test automation framework using Java, TestNG, and Maven.

## Features

- Page Object Model design pattern
- Thread-safe WebDriver management
- Support for multiple browsers (Chrome, Firefox, Edge)
- Parallel test execution capability
- Explicit waits for better test stability
- Maven for dependency management and build automation

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6 or higher
- Chrome/Firefox/Edge browser installed

## Project Structure

```
selenium-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── framework/
│   │   │           ├── pages/    # Page Object classes
│   │   │           └── utils/    # Utility classes
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── framework/
│       │           └── tests/    # Test classes
│       └── resources/
│           └── testng.xml       # TestNG configuration
└── pom.xml                      # Maven configuration
```

## Setup

1. Clone the repository
2. Install dependencies:
   ```bash
   mvn clean install
   ```

## Running Tests

Run all tests using Maven:
```bash
mvn clean test
```

Run specific test class:
```bash
mvn clean test -Dtest=SampleTest
```

## Configuration

- Browser configuration can be modified in `testng.xml`
- Default timeout settings can be adjusted in `BasePage.java`
- Add new test classes by extending `BaseTest.java`

## Best Practices

1. Create new page objects by extending `BasePage.java`
2. Use explicit waits instead of Thread.sleep()
3. Keep test data separate from test logic
4. Follow the Page Object Model pattern
5. Write clean, maintainable, and reusable code

## Contributing

1. Create a new branch for your feature
2. Make your changes
3. Submit a pull request

## License

This project is licensed under the MIT License. 