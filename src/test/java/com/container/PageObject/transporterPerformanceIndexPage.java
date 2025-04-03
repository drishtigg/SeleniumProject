package com.container.PageObject;

//import java.time.Duration;
//import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class transporterPerformanceIndexPage {

    // WebDriver instance
    WebDriver ldriver; 

    // Constructor
    public transporterPerformanceIndexPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    // Identify WebElements
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div/div[2]/div")
    WebElement tranporterPerformanceTab;

    
    //click year field
   

    // Method to click Transporter Performance tab
    public void clickTransporterPerformance() {
        tranporterPerformanceTab.click();
    }
    
    
 }

