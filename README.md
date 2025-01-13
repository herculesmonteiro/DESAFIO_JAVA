# DemoQA Automation Project
This project contains automated tests for the DemoQA website using Java, Selenium WebDriver, and Behave (Cucumber for Java).

## Setup
1. Install Java Development Kit (JDK):
2. Install Eclipse IDE:
3. Install Maven:
4. Install Cucumber plugin in Eclipse:
5. Configure WebDriver:
   - Download ChromeDriver compatible with your Chrome version.
   - Create a WebDriverManager class in src/main/java/utils:

## Running
- Right-click on the Java file and select Run As > Java Application.

## Module Explanations

### 1. PracticeForm.java
This module automates filling out a practice form on the DemoQA website. It reads data from a text file, fills the form, submits it, and verifies that a confirmation popup appears.

### 2. BrowserWindows.java
This module tests the functionality of opening a new browser window. It navigates to the Browser Windows page, opens a new window, verifies its content, and then closes it.

### 3. WebTables.java
This module demonstrates interaction with web tables. It creates a new record, edits it, and then deletes it, showcasing basic CRUD operations.

### 4. ProgressBar.java
This module tests a progress bar widget. It starts the progress, stops it before 25%, validates the progress value, then completes and resets the progress bar.

### 5. Sortable.java
This module tests drag-and-drop functionality by sorting a list of items in ascending order.

### 6. create_delete_records.feature
Feature: Create and delete 12 dynamic records
 Scenario: Create and delete 12 records
   Given that I access the Web Tables page
   When I create 12 new records dynamically
   Then I must delete all the created records

### 7. StepDefinitions.java
The StepDefinitions.java file is part of a Cucumber test automation framework. Here's a simplified explanation of what it does:
It defines three main steps for a test scenario:
"Given" step: Opens the Web Tables page on the demoqa.com website.
"When" step: Creates 12 new records dynamically by filling out a form for each record.
"Then" step: Deletes all the created records.

### 8. TestRunner.java
TestRunner.java sets up and launches the Cucumber tests, connecting the feature files with their corresponding step definitions and configuring the test execution environment

## Running Tests
- Right-click on the TestRunner class and select Run As > Maven test


