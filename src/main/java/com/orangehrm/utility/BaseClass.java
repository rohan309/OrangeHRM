package com.orangehrm.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class BaseClass {

    protected WebDriver driver;

    public void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void clear(By by) {
        driver.findElement(by).clear();
    }

    public void waitForElementToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForAllElementsToBeVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> employees = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(by)
        );
    }
}
