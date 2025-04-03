package com.container.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogisticPlanningPage {
	
	WebDriver ldriver; 
	
	//constructor
	public LogisticPlanningPage(WebDriver rdriver) {
		
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath= "//*[@id=\"root\"]/div/div[2]/div/div/div/div/div[2]/div")
	WebElement logisticPlanningTab;

	public void clickLogisticPlanning() {
		logisticPlanningTab.click();
	}

}
