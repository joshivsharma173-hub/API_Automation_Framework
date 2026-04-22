# RestAssured API Automation Framework

![API Tests](https://github.com/joshivsharma173-hub/restassured-portfolio/actions/workflows/api-tests.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Maven-3.9.14-blue)
![RestAssured](https://img.shields.io/badge/RestAssured-5.4.0-green)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)

A professional REST API test automation framework built with Java and RestAssured,
demonstrating industry-standard design patterns, data-driven testing, and CI/CD integration.

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Programming language |
| RestAssured | 5.4.0 | API automation library |
| TestNG | 7.9.0 | Test framework |
| Maven | 3.9.14 | Build & dependency management |
| Jackson | 2.16.1 | JSON serialization/deserialization |
| ExtentReports | 5.1.1 | HTML test reporting |
| Apache POI | 5.2.5 | Excel data-driven testing |
| GitHub Actions | - | CI/CD pipeline |

---

## Framework Architecture

```
src/test/java/com/portfolio/
│
├── base/
│   └── BaseTest.java                → RequestSpecification setup, BeforeSuite config
│
├── endpoints/
│   └── UserEndpoints.java           → Service layer — all API calls centralized here
│
├── payload/
│   └── User.java                    → POJO for request/response serialization
│
├── tests/
│   ├── GetUserTest.java             → GET operation tests
│   ├── UserFlowTest.java            → Full CRUD flow with API chaining
│   ├── DataDrivenUserTest.java      → JSON data-driven tests
│   └── ExcelDrivenUserTest.java     → Excel data-driven tests
│
└── utils/
    ├── ConfigReader.java             → Reads config.properties
    ├── JsonDataProvider.java         → Parses JSON test data
    ├── ExcelDataProvider.java        → Parses Excel test data
    ├── ExtentReportManager.java      → Initializes ExtentReports
    └── ExtentTestListener.java       → TestNG listener for auto reporting

src/test/resources/
├── config.properties                → Base URL & auth token (gitignored)
├── testng.xml                       → Test suite configuration
└── testdata/
    ├── users.json                   → JSON test data
    └── users.xlsx                   → Excel test data
```

## Design Patterns Used

**Service Layer Pattern**
All API requests are centralized in `UserEndpoints.java`.
Tests only contain assertions — no request building logic.

**Page Object Model equivalent for APIs**
Each endpoint method is reusable across multiple test classes.

**Singleton Pattern**
`ExtentReportManager` uses singleton to ensure one report instance across all tests.

---

## Key Features

- ✅ **Full CRUD automation** — GET, POST, PUT, DELETE with API chaining
- ✅ **API Chaining** — ID from POST response reused in GET, PUT, DELETE
- ✅ **Data Driven Testing** — JSON and Excel external data sources
- ✅ **RequestSpecification** — BaseURI, headers, auth configured once
- ✅ **POJO Serialization** — Jackson for clean request/response handling
- ✅ **ExtentReports** — Detailed HTML reports with request/response logs
- ✅ **CI/CD Pipeline** — GitHub Actions runs tests on every push
- ✅ **Secure config** — Token stored in GitHub Secrets, not in code

---

## Test Coverage

| Test Class | Tests | Type | Coverage |
|---|---|---|---|
| GetUserTest | 3 | Functional | GET all users, GET by ID, GET invalid ID |
| UserFlowTest | 5 | E2E CRUD | Create → Get → Update → Delete → Verify |
| DataDrivenUserTest | 3 | Data Driven | Create users from JSON file |
| ExcelDrivenUserTest | 3 | Data Driven | Create users from Excel file |
| **Total** | **14** | | **Full CRUD + chaining + data driven** |

---

## How to Run Locally

**Prerequisites:**
- Java 17
- Maven 3.9+
- GoRest API token (free at gorest.co.in)

**Setup:**
```bash
# Clone the repository
git clone https://github.com/joshivsharma173-hub/restassured-portfolio.git
cd restassured-portfolio

# Create config.properties (gitignored - create manually)
# src/test/resources/config.properties
base.url=https://gorest.co.in/public/v2
token=YOUR_GOREST_TOKEN_HERE
```

**Run all tests:**
```bash
mvn test
```

**View report:**
Open: reports/ExtentReport.html in browser

---

## CI/CD Pipeline

Every push to `main` branch triggers GitHub Actions to:
Checkout code
Setup Java 17
Create config.properties from GitHub Secrets
Run mvn test
Upload ExtentReport as artifact

View pipeline: Actions tab on GitHub

---

## API Under Test

**GoRest API** — `https://gorest.co.in/public/v2`

A free, real REST API with actual database persistence.
Supports full CRUD operations with Bearer token authentication.

| Endpoint | Method | Description |
|---|---|---|
| /users | GET | Fetch all users |
| /users/{id} | GET | Fetch user by ID |
| /users | POST | Create new user |
| /users/{id} | PUT | Update user |
| /users/{id} | DELETE | Delete user |

---

## Author

**Joshiv Sharma**
SDET | Java | Selenium | RestAssured | TestNG

---

*Built as a portfolio project to demonstrate API test automation skills*
