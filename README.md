# FindMe UI Tests

Selenium UI automation project for the FindMe web application.

This repository contains automated UI smoke tests for the FindMe frontend.

## Tech Stack

- Java 21
- Maven
- Selenium WebDriver
- JUnit 5
- WebDriverManager
- IntelliJ IDEA

## Project Purpose

The goal of this project is to provide a stable smoke test suite for the main FindMe UI flows.

The current smoke suite checks that the main pages and key user actions are working after local frontend/backend startup.

## Local Project Structure

Recommended local structure:

FindMe/
├── findme-back
├── findme-front
└── findme-ui-tests

This repository contains only the Selenium UI automation project.

## Test Environment

Frontend:

http://localhost:3000/main

Backend API:

http://localhost:8080/api

Before running tests, both services should be running locally:

Frontend:

npm run dev

Backend:

npm run start:dev

The frontend should use the local backend API through:

NEXT_PUBLIC_API_URL=http://localhost:8080/api

## Configuration

Test configuration is stored in:

src/test/resources/config.properties

Current example:

base.url=http://localhost:3000/main
browser=chrome
headless=false

admin.email=admin@example.com
admin.password=88888888

## How to Run Tests

Run all tests:

mvn test

Run a single test class:

mvn -Dtest=LoginPageTest test

Run a single test method:

mvn -Dtest=LoginPageTest#adminShouldLoginSuccessfully test

## Current Smoke Test Coverage

The current smoke suite covers:

- Home page opens
- Home hero title is visible
- Find Your Match search is visible
- Navigation menu is visible
- Categories page opens
- Category card opens Find Your Match modal
- Offers page opens
- Map page opens
- Login page opens
- Admin login works
- Admin logout works
- Register tab opens
- Forgot Password page opens
- Find Your Match modal opens from Home search
- Required country behavior in Find Your Match modal
- Radius appears after valid country and city are entered
- Navigation from Login page back to Home works

## Current Test Result

Latest full smoke suite result:

Tests run: 16  
Failures: 0  
Errors: 0  
Skipped: 1  
BUILD SUCCESS

## Blocked Test

The test for the Offers / Create Offer flow is currently disabled.

Reason:

The Create Offer frontend functionality is not fully implemented yet.

Expected future behavior:

- Guest user clicks Create Offer
- Guest user is redirected to Login/Register
- Authenticated user clicks Create Offer
- Create offer form opens

## Current Limitations

The frontend currently does not provide stable data-testid attributes for many UI elements.

Because of that, some tests temporarily use:

- text locators
- XPath locators
- placeholder-based CSS selectors

These locators should be replaced later with stable data-testid attributes.

## Recommended Future Improvements

- Add stable data-testid attributes to the frontend
- Replace fragile XPath/text locators
- Move search filtering checks to regression tests
- Add Create Offer e2e tests after frontend implementation is complete
- Add GitHub Actions CI for automated test runs
