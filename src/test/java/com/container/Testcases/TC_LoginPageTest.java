package com.container.Testcases;

import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//import com.container.PageObject.LogisticPlanningPage;
import com.container.PageObject.dashBoardPage;
import com.container.PageObject.loginPage;
//import com.container.PageObject.planningSolutionPage;
//import com.container.PageObject.transporterPerformanceIndexPage;
import com.container.utilities.ReadExcelFile;

public class TC_LoginPageTest extends BaseClass {

	@Test(dataProvider = "LoginDataProvider")
	public void verifyLogin(String userEmail, String userPassword, String expectedUserName) throws InterruptedException, IOException {
	    loginPage lp = new loginPage(driver);
	    dashBoardPage dp = new dashBoardPage(driver);
	    
	    lp.clearInputUserName(userEmail);
	    Thread.sleep(2000);
	   // lp.clearInputPassword(userPassword);

	    lp.enterInputUserName(userEmail);
	    logger.info("Entered username Successfully");
	    
	    lp.clearInputPassword(userPassword);
	    lp.enterInputPassword(userPassword);
	    logger.info("Entered password Successfully");
	    
	    lp.clickLoginButton();
	    logger.info("Clicked On Login Button");
	    
	  

	    if (!dp.isSearchtextDisplayed()) {
	    	
	    	logger.info("login not Successful");
	    	
	        driver.navigate().refresh(); 
	        Thread.sleep(3000);


	        logger.warn("Dashboard not visible, retrying login...");
	        
	        // Retry: Navigate to login page and attempt login again
	        driver.get(url); // Replace with actual login page URL
	       
	    }

	    // Final check for dashboard visibility
	    Assert.assertTrue(dp.isSearchtextDisplayed(), "Home screen text is not visible");
	    logger.info("Home screen text is visible");

	    dp.clickDashboardSideNavButton();
	    logger.info("Dashboard menu button clicked successfully");
	    
	    Thread.sleep(4000);

	    String actualUserName = dp.checkUserName();
	    logger.info("Actual Username from UI: " + actualUserName);
	    logger.info("Expected Username from Data: " + expectedUserName);

	    if (actualUserName.equals(expectedUserName)) {
	        logger.info("Verify Login - Passed");
	        Assert.assertTrue(true);
	        dp.clickLogOutIcon();
	        logger.info("User Signed out");
	    } else {
	        logger.error("Verify Login - Failed");
	        captureScreenShot(driver, "enterDashboard");
	        Assert.fail("Username mismatch! Expected: " + expectedUserName + ", Found: " + actualUserName);
	    }
	}


		// Thread.sleep(9000);
	

		
		/*
		 * @Test(priority = 2, dependsOnMethods = "verifyLogin") public void
		 * enterDashboard(String expectedUsername) throws InterruptedException,
		 * IOException {
		 * 
		 * dashBoardPage dp = new dashBoardPage(driver);
		 * 
		 * Assert.assertTrue(dp.isSearchtextDisplayed(),
		 * "Home screen text is not visible");
		 * logger.info("Home screen text is visible");
		 * 
		 * dp.clickDashboardSideNavButton();
		 * logger.info("Dashboard menu button clicked successfully");
		 * 
		 * String userName = dp.checkUserName(); Assert.assertEquals(userName,
		 * "Drishti Gautam");
		 * 
		 * if (userName.equals("Drishti Gautam")) {
		 * logger.info("Verify Login - Passed"); Assert.assertTrue(true);
		 * dp.clickSignOutIcon(); logger.info("User Signed out"); } else {
		 * logger.info("Verify Login - Failed");
		 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 * captureScreenShot(driver, "enterDashboard"); Assert.assertTrue(true); } }
		 *///	@Test(priority = 3, dependsOnMethods = "enterDashboard")
//	public void enterPlanningSolution() throws InterruptedException {
//		planningSolutionPage psp = new planningSolutionPage(driver);
//
//		psp.clickPlanningSolutionInSideNav();
//		logger.info("planning solution clicked successfully");
//
//		// Thread.sleep(4000);
//
//	}
//
//	@Test(priority = 4, dependsOnMethods = "enterPlanningSolution")
//	public void enterLogisticPlanning() throws InterruptedException {
//		LogisticPlanningPage lpp = new LogisticPlanningPage(driver);
//
//		lpp.clickLogisticPlanning();
//		logger.info("Logistic Planning clicked successfully");
//
//		// Thread.sleep(4000);
//
//	}
//
//	@Test(priority = 5, dependsOnMethods = "enterLogisticPlanning")
//	public void enterTransporterPerformance() throws InterruptedException {
//		transporterPerformanceIndexPage tpi = new transporterPerformanceIndexPage(driver);
//
//		tpi.clickTransporterPerformance();
//		logger.info("Transporter Performance Index tab clicked successfully");
//
//		// Thread.sleep(4000);
//
//	}
	
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataprovider() {
		
	    String fileName = System.getProperty("user.dir")+"\\TestData\\LoginData.xlsx";

	    int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
	    int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");

	    // Handle case when there are no rows or only headers
	    if (ttlRows <= 1 || ttlColumns < 3) {
	        System.err.println("Error: No valid test data found in Excel sheet.");
	        return new String[0][0]; // Return an empty array to avoid errors
	    }

	    String data[][] = new String[ttlRows - 1][3];

	    for (int i = 1; i < ttlRows; i++) { // Start from 1 to skip headers
	        for (int j = 0; j < 3; j++) {
	            data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "LoginTestData", i, j);
	        }
	    }

	    return data;
	}

}
