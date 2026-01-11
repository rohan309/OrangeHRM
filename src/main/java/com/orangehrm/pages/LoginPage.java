package com.orangehrm.pages;

import com.orangehrm.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass {

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public By usernameField = By.name("username");
    public By passwordField = By.name("password");
    public By loginButton = By.xpath("//button[@type='submit']");
    public By userProfile = By.xpath("//span[@class='oxd-userdropdown-tab']/p");
    public By logoutButton= By.xpath("//a[text()='Logout']");

    public void login(String un, String pwd){
        waitForElementToBeVisible(usernameField);
        enterText(usernameField, un);
        enterText(passwordField, pwd);
        click(loginButton);
    }

}
