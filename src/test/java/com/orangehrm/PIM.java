package com.orangehrm;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utility.BaseClass;
import com.orangehrm.utility.DriverManager;
import com.orangehrm.utility.PropertyHandling;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PIM extends BaseClass {
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
        loginPage.login(propertyHandling.getPropertyValue("username"), propertyHandling.getPropertyValue("password"));
    }

    @AfterClass
    public void teardown() {

        DriverManager.quitDriver();
    }

    @Test
    public void getAllEmoployees() {
        PIMPage  pimPage=new PIMPage(driver);
        waitForElementToBeVisible(pimPage.pimModule);
        click(pimPage.pimModule);
        waitForAllElementsToBeVisible(pimPage.employeeList);
        List<WebElement> allEmployees=driver.findElements(pimPage.employeeList);
        System.out.println("Total Employees: "+allEmployees.size());
    }
}
