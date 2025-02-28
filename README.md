Car Valuation Automation Framework Documentation
Overview
This framework automates checking car valuations in the UK by:

Reading car registration numbers from car_input.txt.
Extracting registrations using a regex pattern.
Querying motorway.co.uk for car valuations.
Comparing the results with expected values in car_output.txt.
Failing tests if details are missing or mismatched.

Configure the browser type (Chrome, Firefox, or Edge) in config.properties.

Prerequisites
Java 1.8 (JDK 8, also known as Java SE 8).
Maven for dependency management.
IDE (e.g., IntelliJ IDEA, Eclipse).
Internet connection (for WebDriverManager to download ChromeDriver, GeckoDriver, or EdgeDriver).
Chrome, Firefox, and/or Microsoft Edge browsers installed on the machine (WebDriverManager handles the drivers).

Project Structure

car-valuation-automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/example/carvaluation/
│   │   │   │   ├── config/              # Configuration and WebDriver setup
│   │   │   │   ├── pages/              # Page Object classes
│   │   │   │   └── util/               # Utilities (file reading)
│   │   │   └── model/                  # Data models
│   │   └── resources/
│   │       ├── car_input.txt           # Input file with registration numbers
│   │       ├── car_output.txt          # Expected output file with car details
│   │       └── config.properties       # Configuration settings
│   └── test/
│       ├── java/
│       │   ├── com/example/carvaluation/
│       │   │   └── tests/              # TestNG test cases
│       └── resources/
├── pom.xml                             # Maven configuration
└── README.md                           # This documentation

Run Tests:
Update the locators in CarValuationPage.java by inspecting motorway.co.uk to match the actual website elements (e.g., for registration input, search button, and result fields) for the browser you’re using (Chrome, Firefox, or Edge).
Edit config.properties to set browser.type to chrome, firefox, or edge based on the browser you want to test with.
Run CarValuationTests.java as a TestNG test in your IDE or use mvn test from the command line.
Check TestNG reports in your IDE or console for results (no HTML reports generated without plugins).