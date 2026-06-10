# FindMe UI Automation Notes

Working notes for the Selenium UI automation project.

This file contains the current automation status, important implementation details, known temporary solutions, and future QA tasks.

## Current Status

The current UI smoke and functional suite is stable and passes locally.

Latest full smoke run:

```text
Tests run: 20
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Last verified locally:

```text
2026-06-10
```

Branch:

```text
serhii
```

Latest relevant commit:

```text
e2a49d3 - Add create offer cancel functional test
```

Repository:

```text
https://github.com/SerghiiZelenov/findme-ui-tests
```

## Local Project Paths

Backend:

```text
/Users/sergej/QA_Projects/FindMe/findme-back
```

Frontend:

```text
/Users/sergej/QA_Projects/FindMe/findme-front
```

UI tests:

```text
/Users/sergej/QA_Projects/FindMe/findme-ui-tests
```

Recommended structure:

```text
FindMe/
├── findme-back
├── findme-front
└── findme-ui-tests
```

## Local Environment

Frontend URL:

```text
http://localhost:3000/main
```

Backend API:

```text
http://localhost:8080/api
```

Frontend `.env.local` should contain:

```text
NEXT_PUBLIC_API_URL=http://localhost:8080/api
```

## Start Commands

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

Run all UI tests:

```bash
cd /Users/sergej/QA_Projects/FindMe/findme-ui-tests
mvn test
```

Run Offers tests only:

```bash
mvn -Dtest=OffersPageTest test
```

Run Login tests only:

```bash
mvn -Dtest=LoginPageTest test
```

## Test Configuration

Config file:

```text
src/test/resources/config.properties
```

Current local config:

```properties
base.url=http://localhost:3000/main
browser=chrome
headless=false

admin.email=admin@example.com
admin.password=88888888
```

Important note:

Regular user credentials are not yet fixed in the test config. Current stable authenticated smoke checks use admin credentials.

## Current Test Classes

```text
src/test/java/com/findme/tests/HomePageTest.java
src/test/java/com/findme/tests/CategoriesPageTest.java
src/test/java/com/findme/tests/FindMatchModalTest.java
src/test/java/com/findme/tests/MapPageTest.java
src/test/java/com/findme/tests/LoginPageTest.java
src/test/java/com/findme/tests/OffersPageTest.java
src/test/java/com/findme/tests/CreateOfferTest.java
```

## Current Page Objects

```text
src/test/java/com/findme/pages/HomePage.java
src/test/java/com/findme/pages/NavigationBar.java
src/test/java/com/findme/pages/CategoriesPage.java
src/test/java/com/findme/pages/FindMatchModal.java
src/test/java/com/findme/pages/MapPage.java
src/test/java/com/findme/pages/LoginPage.java
src/test/java/com/findme/pages/AccountPage.java
src/test/java/com/findme/pages/OffersPage.java
src/test/java/com/findme/pages/CreateOfferPage.java
```

## Current Smoke Coverage

The current smoke suite covers:

```text
Home page opens
Home hero/search area is visible
Navigation menu is visible
Categories page opens
Category card opens Find Your Match modal
Map page opens
Offers page opens
Guest user clicks Create Volunteer Offer and is redirected to Login
Logged-in user clicks Create Volunteer Offer and the create offer form opens
Logged-in user sees all main Create Offer form fields
Cancel button closes the Create Offer form
Login page opens
Admin login works
Admin account sections are visible after login
Admin logout works
Register tab opens
Forgot Password page opens
Navigation from Login page back to Home works
Find Your Match modal opens from Home search
Required country behavior in Find Your Match modal
Radius appears after valid country and city are entered
```

## Important Fixes Already Done

### Login flow

Problem:

The tests sometimes continued before the login state was fully ready.

Fix:

After login, tests now wait for authenticated UI state, especially the `Logout` button.

Important idea:

```text
Do not continue to account/offers checks until Logout is visible.
```

### Account page

Account smoke was updated to check visible authenticated account sections.

Currently checked admin account sections include:

```text
My Offers
Saved
Chats
Settings
Admin Panel
Logout
```

### Offers / Create Volunteer Offer

Problem:

The Create Volunteer Offer button was visible, but Selenium did not reliably open the form when clicking the parent button.

Manual click worked.

Investigation result:

The reliable click target on the current frontend is the inner text span:

```java
By.cssSelector("[class*='addText']")
```

Current temporary solution in `OffersPage.java`:

```java
private final By createOfferButton = By.cssSelector("[class*='addText']");
```

Click method:

```java
public void clickCreateOfferButton() {
    WebElement buttonText = waitUntilVisibleElement(createOfferButton);
    buttonText.click();
}
```

Reason:

The actual working click target during Selenium run was:

```text
SUCCESS with normal click: addText span
```

Important:

This is a temporary selector. It should be replaced later with a stable `data-testid`.

Recommended future selector:

```java
By.cssSelector("[data-testid='create-volunteer-offer-button']")
```

The `data-testid` should be placed on the actual clickable button element.

## Data-testid Status

Stable `data-testid` attributes are not yet available on the current `dev` branch.

A developer branch may already contain some `data-testid` implementation, but it is not yet merged into `dev`.

Until that is merged, current tests use temporary selectors:

```text
text locators
XPath locators
placeholder-based CSS selectors
partial CSS module class selectors
```

After merge, replace temporary selectors gradually.

Priority elements for `data-testid`:

```text
navigation-home-link
navigation-categories-link
navigation-offers-link
navigation-map-link
navigation-login-link
logout-button
login-email-input
login-password-input
login-submit-button
register-tab
forgot-password-link
find-match-search-input
find-match-modal
country-input
city-input
radius-select
category-card
create-volunteer-offer-button
create-offer-title-input
create-offer-description-textarea
create-offer-city-input
create-offer-country-input
create-offer-submit-button
create-offer-cancel-button
```

## Known Warnings

During test runs Selenium prints warnings like:

```text
Unable to find CDP implementation matching 149
```

Current status:

```text
Not blocking
Tests pass successfully
Can be ignored for now
```

Reason:

Chrome version is newer than the Selenium DevTools helper available in the current dependency set.

Possible future improvement:

Update Selenium dependencies or add a matching DevTools artifact if CDP-based features are needed.

## Current Stable Result by Test Class

Latest observed successful run:

```text
OffersPageTest:       3 passed
CreateOfferTest:      2 passed
MapPageTest:          1 passed
CategoriesPageTest:   2 passed
FindMatchModalTest:   3 passed
HomePageTest:         2 passed
LoginPageTest:        7 passed

Total: 20 passed
Failures: 0
Errors: 0
Skipped: 0
```

## Git Status

Latest pushed commit for Create Offer functional coverage:

```text
e2a49d3 - Add create offer cancel functional test
```

After that, README and automation notes were updated to reflect 20 passing tests.

Before committing future changes, always run:

```bash
git status
mvn test
```

Recommended commit for documentation update:

```bash
git add README.md docs/automation-notes.md
git commit -m "Update UI automation documentation"
git push
```

## Smoke vs Regression Decision

Current smoke tests should stay short and stable.

Smoke should check:

```text
main pages open
navigation works
basic login/logout works
critical modals/forms open
guest redirect works
authenticated create-offer entry opens
```

Smoke should not deeply check:

```text
all validation combinations
all search filters
all categories/tags
create offer full submit flow
database cleanup
chat workflows
saved offers management
profile update
password update
delete account
email verification
```

Those belong to regression/e2e tests.

## Future Test Plan

### Near-term

```text
Replace temporary selectors with data-testid after merge into dev
Add regular user credentials to config.properties
Add regular user account smoke
Add Create Offer validation checks
Add Create Offer positive submit test with cleanup
```

### Account page

Future account checks:

```text
Saved section opens
Chats section opens
Settings section opens
Profile update form is visible
Change password form is visible
Delete account action is protected
```

### Offers

Future offers checks:

```text
Create Offer required fields validation
Create Offer category selection
Create Offer tags input
Create Offer location fields
Create Offer active/inactive status
Create Offer submit success
Created offer appears in My Offers
Created offer can be cleaned up after test
```

### Search / Find Your Match

Future regression checks:

```text
country autocomplete after 2 characters
country required validation
city optional behavior
radius appears only after city
category preselection from Categories page
keyword search
no-results modal
redirect to Map after search
```

### Map

Future regression checks:

```text
map loads pins
hover on pin shows offer preview
favorite icon behavior
map controls are visible
day/night mode if stable
```

### Security / Requirement Gap

Email verification after registration is an important future requirement.

Expected behavior:

```text
New user registration requires email verification
Unverified user cannot fully access account functions
Unverified user cannot create offers
Unverified user cannot abuse chats or user-facing features
Verified user can access full functionality
Invalid or expired verification link shows an error
```

This is not implemented yet and should be tracked as a requirement gap / future security test area.

## Important Notes for Future Sessions

Use current `dev` branch for frontend/backend unless specifically testing a feature branch.

Do not assume selectors are stable until `data-testid` attributes are merged into `dev`.

If a UI test fails after frontend changes, first check:

```text
Was the frontend updated?
Was the backend updated?
Is the backend running?
Is the frontend using local backend API?
Did the selector change?
Is the user really authenticated before the next action?
Does the same action work manually?
```

For authenticated UI actions, always verify authenticated state before continuing:

```text
Logout button visible
Account page available
Admin/User name visible in navbar
```
