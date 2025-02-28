package com.example.carvaluation.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomDriverManager {
    private static WebDriver driver;
    private static final Properties config = loadConfig();

    private static Properties loadConfig() {
        Properties props = new Properties();
        try (InputStream input = WebDriverManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
        return props;
    }

    private CustomDriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = config.getProperty("browser.type", "chrome").toLowerCase();
            // Force WebDriverManager to use the latest driver compatible with the browser version
            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup(); // Example Chrome version, adjust as needed
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup(); // Uses latest GeckoDriver compatible with Firefox
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup(); // Uses latest EdgeDriver compatible with Microsoft Edge
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getWebsiteUrl() {
        return config.getProperty("website.url");
    }
}