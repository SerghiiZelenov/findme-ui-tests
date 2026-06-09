# FindMe UI Tests

Selenium UI automation project for the FindMe web application.

This repository contains automated UI smoke tests for the FindMe frontend.

## Project Purpose

The goal of this project is to provide a stable UI smoke test suite for the main FindMe user flows.

The current smoke suite checks that the main pages, navigation, login flow, search modal, map page, categories page, offers page, and create-offer entry flow work correctly after local frontend/backend startup.

## Tech Stack

- Java 21
- Maven
- Selenium WebDriver
- JUnit 5
- WebDriverManager
- IntelliJ IDEA
- Chrome

## Repository Scope

This repository contains only the Selenium UI automation project.

Recommended local structure:

```text
FindMe/
├── findme-back
├── findme-front
└── findme-ui-tests
```

## Local Test Environment

Frontend:

```text
http://localhost:3000/main
```

Backend API:

```text
http://localhost:8080/api
```

Before running tests, both frontend and backend should be running locally.

Start backend:

```bash
cd /Users/sergej/QA_Projects/FindMe/findme-back
npm run start:dev
```

Start frontend:

```bash
cd /Users/sergej/QA_Projects/FindMe/findme-front
npm run dev
```

The frontend should use the local backend API through `.env.local`:

```text
NEXT_PUBLIC_API_URL=http://localhost:8080/api
```

## Test Configuration

Test configuration is stored in:

```text
src/test/resources/config.properties
```

Current local example:

```properties
base.url=http://localhost:3000/main
browser=chrome
headless=false

admin.email=admin@example.com
admin.password=88888888
```

## How to Run Tests

Run all tests:

```bash
mvn test
```

Run a single test class:

```bash
mvn -Dtest=LoginPageTest test
```

Run a single test method:

```bash
mvn -Dtest=LoginPageTest#adminShouldLoginSuccessfully test
```

Run Offers page tests:

```bash
mvn -Dtest=OffersPageTest test
```

## Current Smoke Test Coverage

The current smoke suite covers:

- Home page opens
- Home hero/search area is visible
- Navigation menu is visible
- Categories page opens
- Category card opens Find Your Match modal
- Map page opens
- Offers page opens
- Guest user clicks Create Volunteer Offer and is redirected to Login
- Logged-in user clicks Create Volunteer Offer and the create offer form opens
- Login page opens
- Admin login works
- Admin account sections are visible after login
- Admin logout works
- Register tab opens
- Forgot Password page opens
- Navigation from Login page back to Home works
- Find Your Match modal opens from Home search
- Required country behavior in Find Your Match modal
- Radius appears after valid country and city are entered

## Current Test Result

Latest full smoke suite result:

```text
Tests run: 18
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Last verified locally:

```text
2026-06-10
```

## Current Page Objects

Main page objects currently used by the smoke suite:

```text
src/test/java/com/findme/pages/HomePage.java
src/test/java/com/findme/pages/NavigationBar.java
src/test/java/com/findme/pages/CategoriesPage.java
src/test/java/com/findme/pages/FindMatchModal.java
src/test/java/com/findme/pages/MapPage.java
src/test/java/com/findme/pages/LoginPage.java
src/test/java/com/findme/pages/AccountPage.java
src/test/java/com/findme/pages/OffersPage.java
```

Main test classes:

```text
src/test/java/com/findme/tests/HomePageTest.java
src/test/java/com/findme/tests/CategoriesPageTest.java
src/test/java/com/findme/tests/FindMatchModalTest.java
src/test/java/com/findme/tests/MapPageTest.java
src/test/java/com/findme/tests/LoginPageTest.java
src/test/java/com/findme/tests/OffersPageTest.java
```

## Known Technical Notes

Some frontend elements do not yet have stable `data-testid` attributes on the current `dev` branch.

Because of that, some tests temporarily use:

- text locators
- XPath locators
- placeholder-based CSS selectors
- partial CSS module class selectors

Example temporary selector:

```java
By.cssSelector("[class*='addText']")
```

This selector is currently used for the `Create Volunteer Offer` button text because the actual clickable target works reliably on the inner text span.

After stable `data-testid` attributes are merged into `dev`, these locators should be replaced with stable selectors.

Recommended future selector example:

```java
By.cssSelector("[data-testid='create-volunteer-offer-button']")
```

The `data-testid` should be placed on the actual clickable button element.

## Recommended Future Improvements

- Replace temporary CSS/XPath/text locators with stable `data-testid` selectors
- Add smoke tests for regular user account when stable user credentials are available
- Add deeper Create Offer validation tests
- Add Create Offer submit test with test data cleanup
- Add Account page tests for Saved, Chats, Settings
- Move detailed search/filtering checks to regression tests
- Add GitHub Actions CI for automated test runs
- Add screenshots or reports for failed tests
- Add email verification tests after the feature is implemented

## Important Requirement Gap

Email verification after registration is currently an important future requirement.

Expected behavior:

- New user registration should require email verification
- Unverified users should not receive full access to account features
- Unverified users should not be able to create offers or abuse user-facing features

This should be covered by future test cases after implementation.
