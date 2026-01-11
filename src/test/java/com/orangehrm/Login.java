package com.orangehrm;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.utility.BaseClass;
import com.orangehrm.utility.DriverManager;
import com.orangehrm.utility.PropertyHandling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login extends BaseClass {

    PropertyHandling propertyHandling;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {

        DriverManager driverManager = new DriverManager();
        driverManager.launchBrowser("chrome");
        driver = DriverManager.getDriver();
        propertyHandling = new PropertyHandling();
        String url = propertyHandling.getPropertyValue("url");
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void teardown() {

        DriverManager.quitDriver();
    }

    @Test
    public void login() throws InterruptedException {
        waitForElementToBeVisible(loginPage.usernameField);
        enterText(loginPage.usernameField, propertyHandling.getPropertyValue("username"));
        enterText(loginPage.passwordField, propertyHandling.getPropertyValue("password"));
        click(loginPage.loginButton);
        waitForElementToBeVisible(loginPage.userProfile);
        Assert.assertTrue(driver.findElement(loginPage.userProfile).isDisplayed(), "Login failed - User profile not displayed");
        System.out.println("Login Successful");

    }

    @Test(dependsOnMethods = {"login"})
    public void logout() {
        waitForElementToBeVisible(loginPage.userProfile);
        click(loginPage.userProfile);
        waitForElementToBeVisible(loginPage.logoutButton);
        click(loginPage.logoutButton);
        waitForElementToBeVisible(loginPage.loginButton);
        Assert.assertTrue(driver.findElement(loginPage.loginButton).isDisplayed(), "Logout failed - Login button not displayed");
        System.out.println("Logout Successful");
    }

    @Test(dataProvider = "loginDataProvider",priority = 3)
    public void invalidLogin(String username, String password) {
        System.out.println("Testing invalid login with Username: '" + username + "' and Password: '" + password + "'");
        waitForElementToBeVisible(loginPage.usernameField);
        enterText(loginPage.usernameField, username);
        enterText(loginPage.passwordField, password);
        click(loginPage.loginButton);
        driver.navigate().refresh();

    }

    @DataProvider
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {" ", " "},
                {" ", "admin123"},
                {"Admin", " "},
                {"Admin", "password2"},
                {"Admin1", "admin123"},
                {"invalidUser", "invalidPass"},
        };
    }
}
