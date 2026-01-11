package com.orangehrm.pages;

import com.orangehrm.utility.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BaseClass {

    public PIMPage(WebDriver driver){
        this.driver=driver;
    }

    public By pimModule= By.xpath("//span[text()='PIM']");
    public By employeeList= By.xpath("//div[@class='oxd-table-body']/div/div/div[3]");

}
