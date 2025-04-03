package com.container.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class dashBoardPage {
	// create object of the class
	WebDriver ldriver;

	// constructor
	public dashBoardPage(WebDriver rdriver) {

		ldriver = rdriver;

		PageFactory.initElements(rdriver, this);

	}

	// Identify elements

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/span[1]")
	WebElement searchtext;
	
	@FindBy(css = "#root > div > div.flex.p-4.h-18.items-center.shrink-0.gap-16.justify-between > div.flex.items-center.gap-6.w-full > div > button")
	 WebElement sidenavButton;
	
   
    @FindBy(css = "span.text-button-small.leading-button-small.font-extra-bold.text-system-true-grey-800")
    WebElement userName;
    
    @FindBy(xpath = "//img[contains(@src, 'logout.svg')]")
    WebElement logoutIcon;

    


	// Method to click Side Navigation Button with a wait
		public void clickDashboardSideNavButton() {
			sidenavButton.click();
		}
		
		public boolean isSearchtextDisplayed() {
			searchtext.getText();
	        return searchtext.isDisplayed();
	    }
		
		public String checkUserName() {
			userName.getText();
			String text = userName.getText();
			 return text;
		}
		
		public void clickLogOutIcon() {
			logoutIcon.click();
		}
		

	
}
