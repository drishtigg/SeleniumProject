package com.container.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {

	//create object of the class 
	WebDriver ldriver; 
	
	//constructor
	public loginPage(WebDriver rdriver) {
		
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	/*
	 * @FindBy(xpath="//*[@id=\"input\"]") WebElement seachbar;
	 * 
	 * public void usesearchBar() { seachbar.click(); }
	 */
	
	//identify webelements 
	@FindBy(name="username")
	WebElement inputusername;
	
	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	WebElement inputpassword;

	@FindBy(name="username")
	WebElement clearInputusername;
	
	@FindBy(xpath = "//input[@placeholder='Enter your password']")
	WebElement clearInputpassword;

	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginButton;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div/div/div[2]/span[1]")
	WebElement searchtext;
	
	
	
	//identify action on webelement
	public void enterInputUserName(String emailenter) {
		inputusername.sendKeys(emailenter);
	}
	
	public void enterInputPassword(String enterPassword) {
		inputpassword.sendKeys(enterPassword);
	}
	
	public void clearInputUserName(String emailenter) {
		clearInputusername.clear();
	}
	
	public void clearInputPassword(String enterPassword) {
		clearInputpassword.clear();
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public boolean isSearchtextDisplayed() {
        return searchtext.isDisplayed();
    }
}
