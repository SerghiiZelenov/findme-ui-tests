mkdir -p docs

cat > docs/automation-notes.md <<'EOF'
# FindMe UI Automation Notes

## 1. Current automation stack

- Java 21
- Maven
- Selenium WebDriver
- JUnit 5
- WebDriverManager
- IntelliJ IDEA

## 2. Project location

Local automation project:

/Users/sergej/QA_Projects/FindMe/findme-ui-tests

Recommended local structure:

FindMe/
├── findme-back
├── findme-front
└── findme-ui-tests

The Selenium UI automation project is stored separately from frontend and backend repositories.

Reason:

- frontend repository contains application code
- backend repository contains API/server code
- UI automation repository contains Selenium tests
- this structure is cleaner for portfolio and interview demonstration

## 3. Test environment

Frontend local URL:

http://localhost:3000/main

Backend local API:

http://localhost:8080/api

Frontend local configuration file:

findme-front/.env.local

Required frontend environment variable:

NEXT_PUBLIC_API_URL=http://localhost:8080/api

For UI tests to work correctly, both services should be running:

frontend: npm run dev
backend:  npm run start:dev

## 4. Current automation project structure

src/test/java/com/findme
├── base
│   └── BaseTest.java
├── config
│   └── ConfigReader.java
├── pages
│   ├── HomePage.java
│   ├── NavigationBar.java
│   └── CategoriesPage.java
├── tests
│   ├── HomePageTest.java
│   └── CategoriesPageTest.java
└── utils

src/test/resources
└── config.properties

## 5. Current config.properties

base.url=http://localhost:3000/main
browser=chrome
headless=false

admin.email=admin@example.com
admin.password=88888888

## 6. Current implemented tests

### HomePageTest

Current checks:

- Home page opens successfully
- Hero title is visible
- Find Your Match search is visible
- Navigation menu is visible

Current tested navigation links:

- Home
- Categories
- Offers
- Map
- Login

### CategoriesPageTest

Current checks:

- Home page opens
- User clicks Categories in navigation menu
- Categories page opens
- Categories page title is visible
- "Education" category is visible

## 7. Current test result

Current Selenium test status:

Tests run: 3
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS

Implemented working tests:

- HomePageTest
- CategoriesPageTest

## 8. Important note about browser closing

In BaseTest.java, browser closing is controlled by:

@AfterEach
public void tearDown() {
if (driver != null) {
driver.quit();
}
}

For learning/debugging, this block may be temporarily commented out:

//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

Important:

This should be restored later, otherwise Chrome windows will remain open after each test run.

## 9. Current known warnings

During test execution, these warnings may appear:

SLF4J(W): No SLF4J providers were found.

and:

WARNING: Unable to find CDP implementation matching 148

Current decision:

- these warnings do not break tests
- tests are passing
- no immediate action is needed

## 10. Known issue: unstable category assertion

Current CategoriesPageTest checks a specific category name:

Education

This is a temporary assertion.

Risk:

- the category can be renamed
- the category can be deleted from the database
- backend seed data can change
- test data can differ between environments
- this makes the test dependent on specific content instead of page behavior

Better approach:

Instead of checking a specific category name, check that at least one category card is visible.

Recommended future test logic:

Open Home page
Click Categories
Check Categories page title
Check that category cards count > 0

Preferred stable locator:

data-testid="category-card"

Recommended future Selenium logic:

List<WebElement> cards = driver.findElements(By.cssSelector("[data-testid='category-card']"));
assertTrue(cards.size() > 0, "At least one category card should be visible");

## 11. Current locator strategy

At the moment, the frontend does not have stable data-testid attributes for the elements we inspected.

Example from Login email input:

<input placeholder="Email" class="AuthModal-module__...__input" type="email" value="">

Current issue:

- no data-testid
- no id
- no name
- no aria-label
- CSS module classes are auto-generated and unstable

Temporary locators may use:

By.xpath("//a[contains(text(),'Categories')]")
By.xpath("//*[contains(text(),'One Planet')]")
By.cssSelector("input[placeholder='Email']")
By.cssSelector("input[type='password']")

These locators are acceptable temporarily, but should be replaced later.

## 12. Recommended data-testid attributes

To make Selenium tests stable, frontend should add data-testid attributes.

### Navigation

- nav-home-link
- nav-categories-link
- nav-offers-link
- nav-map-link
- nav-login-link
- nav-logout-button

### Home page

- home-hero-title
- home-search-input
- home-search-button

### Find Your Match modal

- find-match-modal
- find-match-keyword-input
- find-match-country-input
- find-match-country-option
- find-match-city-input
- find-match-radius-select
- find-match-category-select
- find-match-search-button
- find-match-close-button

### Categories page

- categories-page-title
- category-card
- category-card-title

### Offers / Create offer

- create-offer-cta
- create-offer-form
- offer-title-input
- offer-description-input
- offer-category-select
- offer-tags-input
- offer-submit-button
- offer-delete-button

### Map page

- map-container
- map-marker
- map-marker-popup
- map-zoom-in-button
- map-zoom-out-button
- map-layer-control
- map-day-night-toggle

### Login / Register

- login-email-input
- login-password-input
- login-submit-button
- register-tab
- register-name-input
- register-email-input
- register-password-input
- register-submit-button
- forgot-password-link
- google-login-button

### Account page

- account-page
- account-email
- account-my-offers-tab
- account-active-offers-tab
- account-inactive-offers-tab
- account-saved-tab
- account-settings-tab

## 13. Current frontend business logic to cover later

### Home search

Home page has a search field:

Find Your Match...

Clicking this field opens the Find Your Match modal.

### Find Your Match modal

Fields:

- keyword/tag search — optional
- country — required
- country autocomplete starts after entering 2 characters
- country input is in English
- city — optional
- radius appears only after city is entered
- category — optional

After clicking Search, user is redirected to Map with offers filtered by selected criteria.

If no offers are found, user sees a modal:

No offers found
Try another category, keyword, city, or a larger search radius.

### Categories

Categories page shows cards of available categories.

Clicking a category card opens the same Find Your Match modal with the selected category prefilled.

### Offers

Offers page will be used for creating volunteer offers.

Expected logic:

- guest user clicks create offer
- guest user is redirected to Login/Register
- registered user clicks create offer
- registered user sees create offer form

### Map

Map page shows available offers as pins/markers.

Expected checks later:

- map opens
- map container is visible
- markers are visible if offers exist
- marker hover shows short offer preview
- map zoom controls are visible
- layer controls are visible
- day/night mode control is visible

### Login

Login page allows:

- login
- registration
- Google login
- password reset

Google login and real password reset email flow should not be automated until the team confirms stable test setup.

## 14. Recommended next test priorities

Recommended order:

1. Improve CategoriesPageTest to check category cards instead of specific category name.
2. Add LoginPage Page Object.
3. Add login test with admin user.
4. Add logout test.
5. Add Find Your Match modal opening test.
6. Add country required validation test.
7. Add country autocomplete test.
8. Add radius visibility after city input test.
9. Add Map page smoke test.
10. Add Offers guest redirect test.

## 15. Temporary decisions

Current temporary decisions:

- Tests are running against local frontend: http://localhost:3000/main
- Frontend uses local backend: http://localhost:8080/api
- Category check uses "Education" temporarily
- Browser closing may be temporarily disabled for learning/debugging
- data-testid attributes are not yet available
- XPath/text/placeholder locators are used temporarily
  EOF
## 16. Smoke suite status update

Date: 2026-06-09

Current full Selenium smoke suite result:

Tests run: 16
Failures: 0
Errors: 0
Skipped: 1
BUILD SUCCESS

Meaning:

- 15 active UI tests passed successfully
- 1 test is skipped intentionally
- No failed tests
- No Selenium errors

The skipped test is related to Offers / Create Offer flow.

Reason:

Offers / Create Offer functionality is not fully implemented on the frontend yet, therefore the test is marked as:

@Disabled("Blocked: Offers/Create Offer functionality is not implemented yet")

## 17. Current active smoke coverage

Current smoke tests cover:

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

## 18. Important implementation notes from smoke phase

### Login route

Login navigation link points to:

/account

It does not use:

/login

Therefore tests should not check that URL contains "login".

Correct approach:

- check that Login UI is visible
- check email input
- check password input
- check Sign In button

### Forgot Password page

Forgot Password page contains:

- title: Forgot Password
- email input placeholder: Email address
- submit button text: Send Reset Link

The button text is inside a span inside the button.

Working locator:

//button[.//span[normalize-space()='Send Reset Link']]

### Find Your Match country autocomplete

Typing country text is not enough.

Country must be selected from autocomplete suggestion.

Working logic:

- type country name
- click exact visible suggestion

Example:

Germany must be clicked as a suggestion, otherwise frontend still shows:

Please select a country

### Radius field

Radius does not appear as a field with text "Radius".

Current temporary locator checks visible "km" text.

This is temporary and should be replaced with data-testid later.

## 19. Current blocked functionality

### Offers / Create Offer

Planned test:

Guest clicks Create Offer → redirected to Login/Register

Current status:

Blocked / skipped

Reason:

Frontend functionality is not implemented yet.

Future expected behavior:

- guest user opens Offers
- guest clicks Create Offer
- user is redirected to Login/Register
- registered user clicks Create Offer
- create offer form opens

## 20. Current cleanup reminder

Temporary Thread.sleep calls should not remain in final smoke tests.

If Thread.sleep was used only for visual debugging, remove it before committing.

Preferred future approach:

- WebDriverWait
- ExpectedConditions
- stable data-testid locators

## 21. Recommended next steps

Next recommended work after first smoke suite:

1. Commit current working Selenium project to Git.
2. Add README.md with setup and run instructions.
3. Replace fragile text/XPath locators with data-testid after frontend team adds them.
4. Move search filtering checks to regression tests.
5. Move Create Offer flow to e2e tests after frontend implementation is ready.
6. Keep smoke suite small, fast, and stable.

