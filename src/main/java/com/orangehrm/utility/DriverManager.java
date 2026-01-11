package com.orangehrm.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverManager {

    // ThreadLocal to make driver thread-safe
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Launch browser based on name
    public void launchBrowser(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver.set(new ChromeDriver(setChromeOptions()));
                break;
            case "firefox":
                driver.set(new FirefoxDriver(setFirefoxOptions()));
                break;
            case "edge":
                driver.set(new EdgeDriver(setEdgeOptions()));
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
    }

    // Get driver for current thread
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Call launchBrowser() first.");
        }
        return driver.get();
    }

    // Quit driver for current thread
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // remove from ThreadLocal to avoid memory leaks
        }
    }

    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        return options;
    }

    private FirefoxOptions setFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--private");
        options.addArguments("--start-maximized");
        return options;
    }

    private EdgeOptions setEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        options.addArguments("--start-maximized");
        return options;
    }
}

