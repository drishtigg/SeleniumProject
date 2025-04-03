package com.container.PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.container.utilities.ReadExcelFile;

public class TpiScreenPage {

    WebDriver ldriver;

    // Constructor
    public TpiScreenPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    // locating year field for year range
    @FindBy(xpath = "//input[@placeholder='Select year']")
    WebElement openYearField;

    
    @FindBy(xpath =" //div[@class='ant-picker-header-view']")
    WebElement checkYearRange;

   
    @FindBy(xpath = "//button[contains(@class, 'ant-picker-header-super-prev-btn')]/span[@class='ant-picker-super-prev-icon']")
    WebElement previousButton;

   
    @FindBy(xpath = "//button[contains(@class, 'ant-picker-header-super-next-btn')]/span[@class='ant-picker-super-next-icon']")
    WebElement nextButton;
    
   
    
    //locating month field 
    @FindBy(xpath ="//input[@placeholder='Select month']")
    WebElement openMonthField;
    
     
    @FindBy(xpath="//div[contains(@class, 'ant-picker-header-view')]")
    WebElement selectMonthForTargetYear;
    
    @FindBy(xpath="//button[@class='ant-picker-year-btn']")
    WebElement targetYearButton;
    
   
    @FindBy(xpath="//div[contains(@class, 'ant-picker-month-panel')]/div[contains(@class,'ant-picker-header')]/button[contains(@class,'ant-picker-header-super-prev-btn')]/span[contains(@class,'ant-picker-super-prev-icon')]")
    WebElement monthPreviousButton;
    
   
    @FindBy(xpath="//div[contains(@class, 'ant-picker-month-panel')]/div[contains(@class,'ant-picker-header')]/button[contains(@class,'ant-picker-header-super-next-btn')]/span[contains(@class,'ant-picker-super-next-icon')]")
    WebElement monthNextButton;
    
    
    
    //Select Plant dropDown
    @FindBy(xpath="//div[contains(@class, 'dropdown-container')]//span")
    WebElement selectPlant;
    
	
    @FindBy(xpath="//div[contains(@class, 'dropdown-content')]") 
    WebElement searchPlant;
	  
    @FindBy(xpath="//input[@placeholder='Search' and @aria-describedby='Search']")
    WebElement searchBox;
    
    
    
    //load scenerio button
    @FindBy(xpath="//button[text()='Load Scenerios']")
    WebElement loadScenarioButton;
    
    
    //Scenerio DropDown
    @FindBy(xpath="//span[@class='gray' and contains(text(), 'Select Scenerio')]")
    WebElement dropdownLoadScenerio;
    
    @FindBy(xpath="//input[@aria-describedby='Search']")
    WebElement searchFromScenerioDD;
    
    @FindBy(xpath="//ul[@class='options']//div[@class='item-renderer']//span")
     List<WebElement> scenarioList;
    
    
    //Create new Scenerio button
    @FindBy(xpath="//button[.//img[@src='/verdis-icons/plus-icon-circle-border.svg'] and contains(text(), 'Scenerio')]")
    WebElement createNewScenerioButton;
    
    @FindBy(xpath="//span[text()='Scenario Name']/following::input[1]")
    WebElement scenarioNameInput;

    @FindBy(xpath="//span[text()='Scenario Description']/following::input[1]")
    WebElement scenarioDescriptionInput;

    @FindBy(xpath="//button[text()='Create']")
    WebElement createButtonforScenerio;
    
    @FindBy(xpath="//div[@role='alertdialog' and contains(@class, 'fixed')]")
    WebElement confirmationPopUpForCreateScenerio;
    
    @FindBy(xpath="//button[text()='Cancel']")
    WebElement cancelCreateScenerio;
    
    @FindBy(xpath="//button[span[text()='Confirm']]")
    WebElement confirmCreateScenerio;
    
    
    
    // Click to select year field
    public void toSelectYearField() {
        openYearField.click();
    }

    // Get the current decade range
    public String getYearRange() {
        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
        WebElement yearRange = wait.until(ExpectedConditions.visibilityOf(checkYearRange));
        return yearRange.getText();
    }

    // Click previous button
    public void clickPreviousButton() {
        previousButton.click();
    }

    // Click next button
    public void clickNextButton() {
        nextButton.click();
    }
    
    
    public void clickMonthField() {
    	openMonthField.click();
    }
    
	
	 public void clickPlant() { 
		 selectPlant.click();
	  }
	 
	 
	 public void clickScenerioDD() {
		 loadScenarioButton.click();
	 }
	 
     public void clickLoadScenarioButton() {
    	 WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
    	 WebElement element = wait.until(ExpectedConditions.refreshed(
    	     ExpectedConditions.elementToBeClickable(loadScenarioButton)));
    	 element.click();
     }
    
    
    // Select specific year from year range
    public void selectYear(String targetYear) {
        System.out.println("Target Year received: '" + targetYear + "'");

        // Validate input
        if (targetYear == null || targetYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Target year cannot be null or empty.");
        }

        int year = Integer.parseInt(targetYear.trim());

        while (true) {
            // Get the current decade range
            String yearRangeText = getYearRange();
            System.out.println("Current Year Range: " + yearRangeText);
            String[] decadeParts = yearRangeText.split("-");

            int startYearRange = Integer.parseInt(decadeParts[0].trim());
            int endYearRange = Integer.parseInt(decadeParts[1].trim());

            // If the target year is within the current decade, select it
            if (year >= startYearRange && year <= endYearRange) {
                ldriver.findElement(By.xpath("//div[@class='ant-picker-cell-inner' and text()='" + targetYear + "']")).click();
                System.out.println("Selected Year: " + targetYear);
                break;
            }

            if (year < startYearRange) {
                System.out.println("Target year is earlier, clicking previous...");
                clickPreviousButton();
            } else if (year > endYearRange) {
                System.out.println("Target year is later, clicking next...");
                clickNextButton();
            }
        }
    }
        
        
     // Select a specific month for the given year
    public void selectMonthForYear(String targetYear, String month) {
        openMonthField.click(); // Open the month selection dropdown

        while (true) {
            // Get the displayed year directly from the element
            String displayedYear = targetYearButton.getText();
            
            // If the displayed year matches the target year, select the month
            if (displayedYear.equals(targetYear)) {
                ldriver.findElement(By.xpath("//div[@class='ant-picker-cell-inner' and text()='" + month + "']")).click();
                
                System.out.println("Month selected: " + month);
                break;
            }

            // Navigate to the correct year
            if (Integer.parseInt(displayedYear) > Integer.parseInt(targetYear)) {
                monthPreviousButton.click(); // Click previous if the displayed year is ahead
            } else {
                monthNextButton.click(); // Click next if the displayed year is behind
            }
        }
    }
    
    public void selectPlantFromDropdown(String plant) {
        selectPlant.click(); // Click the dropdown

        WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(searchBox));

        searchBox.sendKeys(plant); // Enter plant name in search box

        // Select plant from dropdown
        String plantXPath = "//li//span[contains(text(),'" + plant + "')]";
        WebElement plantElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(plantXPath)));
        plantElement.click();
    }
    
    public void scenerioSelectingandAdding(String scenerioName, String scenerioDiscription) {
    	WebDriverWait wait = new WebDriverWait(ldriver, Duration.ofSeconds(10));
        
        // Click the scenario dropdown to open it
        wait.until(ExpectedConditions.elementToBeClickable(dropdownLoadScenerio)).click();
        
        // Wait for the search input box to be visible
        wait.until(ExpectedConditions.visibilityOf(searchFromScenerioDD));
        
        // Enter the scenario name in the search box
        searchFromScenerioDD.sendKeys(scenerioName);
        
        // Wait for the scenario options to be loaded
        wait.until(ExpectedConditions.visibilityOfAllElements(scenarioList));

        boolean scenarioFound = false;
        
        // Loop through the scenario list and select the matching one
        for (WebElement scenarioElement : scenarioList) {
            if (scenarioElement.getText().trim().equalsIgnoreCase(scenerioName)) 
            {
            	WebDriverWait wait1 = new WebDriverWait(ldriver, Duration.ofSeconds(20));
            	wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@role='option']//span[text()='" + scenerioName + "']/preceding-sibling::input[@type='checkbox']")));
                scenarioElement.click();
                scenarioFound = true;
                System.out.println("Selected Scenario: " + scenerioName);
                break;
            }
        }

        

        if (!scenarioFound) {
            createNewScenerio(scenerioName, scenerioDiscription);
        }
    }


    // Method to create a new scenario
    public void createNewScenerio(String scenerioName, String scenerioDiscription) {
        createNewScenerioButton.click();
        scenarioNameInput.sendKeys(scenerioName);
        scenarioDescriptionInput.sendKeys(scenerioDiscription);
        createButtonforScenerio.click();
        confirmCreateScenerio.click();
    }
}