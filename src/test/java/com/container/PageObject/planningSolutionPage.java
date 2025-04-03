package com.container.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class planningSolutionPage {
	
	//create object of the class 
		WebDriver ldriver; 
		
		//constructor
		public planningSolutionPage(WebDriver rdriver) {
			
			ldriver = rdriver;
			
			PageFactory.initElements(rdriver, this);
		}

		//identify element
		@FindBy(xpath = "//span[contains(text(), 'Planning Solutions')]")
		WebElement planningSolutionsinSidenav;
		
		public void clickPlanningSolutionInSideNav() {
			planningSolutionsinSidenav.click();
		}

}
