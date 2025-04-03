package com.container.Testcases;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.container.PageObject.LogisticPlanningPage;
import com.container.PageObject.TpiScreenPage;
import com.container.PageObject.dashBoardPage;
import com.container.PageObject.loginPage;
import com.container.PageObject.planningSolutionPage;
import com.container.PageObject.transporterPerformanceIndexPage;
import com.container.utilities.ReadExcelFile;

public class TC_TransporterPerformanceIndex extends BaseClass {

	@Test(dataProvider = "LoginDataProvider", priority = 1)
	public void VerifyLogin(String userEmail, String userPassword) throws InterruptedException {
		loginPage lp = new loginPage(driver);

		lp.enterInputUserName(userEmail);
		logger.info("Entered username Successfully");

		lp.enterInputPassword(userPassword);
		logger.info("Entered password Successfully");

		lp.clickLoginButton();
		logger.info("Clicked On Login Button");
	}

	@Test(priority = 2, dependsOnMethods = "VerifyLogin")
	public void enterDashboard() throws InterruptedException {
		dashBoardPage dp = new dashBoardPage(driver);

		Assert.assertTrue(dp.isSearchtextDisplayed(), "Home screen text is not visible");
		logger.info("Home screen text is visible");

		dp.clickDashboardSideNavButton();
		logger.info("Dashboard menu button clicked successfully");
	}

	@Test(priority = 3, dependsOnMethods = "enterDashboard")
	public void enterPlanningSolution() throws InterruptedException {
		planningSolutionPage psp = new planningSolutionPage(driver);

		psp.clickPlanningSolutionInSideNav();
		logger.info("Planning solution clicked successfully");
	}

	@Test(priority = 4, dependsOnMethods = "enterPlanningSolution")
	public void enterLogisticPlanning() throws InterruptedException {
		LogisticPlanningPage lpp = new LogisticPlanningPage(driver);

		lpp.clickLogisticPlanning();
		logger.info("Logistic Planning clicked successfully");
	}

	@Test(priority = 5, dependsOnMethods = "enterLogisticPlanning")
	public void enterTransporterPerformance() throws InterruptedException {
		transporterPerformanceIndexPage tpi = new transporterPerformanceIndexPage(driver);

		tpi.clickTransporterPerformance();
		logger.info("Transporter Performance Index tab clicked successfully");

	}

	@Test(dataProvider = "TPIDataProvider", priority = 6, dependsOnMethods = "enterTransporterPerformance")
	public void testTpiScreen(String targetYear, String month, String plant, String scenerioName, String scenerioDiscription) throws InterruptedException {
		
		TpiScreenPage tpis = new TpiScreenPage(driver);

		
		tpis.toSelectYearField();
		
		logger.info("click year field Successfully");
		
		Thread.sleep(1000);
		
		tpis.selectYear(targetYear);
		logger.info("Year Entered Successfully");
		
		Thread.sleep(2000);
		
		tpis.clickMonthField();
		logger.info("click month field successfully");
		
		Thread.sleep(2000);
		
		
		tpis.selectMonthForYear(targetYear, month);
		logger.info("Month selected Successfully");
		Thread.sleep(2000);
		

	    tpis.selectPlantFromDropdown(plant);
		logger.info("plant selected Successfully"); 
		Thread.sleep(2000);
		
		tpis.clickLoadScenarioButton();
		logger.info("Load Scenario Button Clicked Successfully");
		Thread.sleep(4000);
		 
		tpis.scenerioSelectingandAdding(scenerioName, scenerioDiscription);
		logger.info("selecting or creating scenerio is done");
		Thread.sleep(2000);
	}

	// Data Provider for Login
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataprovider() {
		String fileName = System.getProperty("user.dir") + "\\TestData\\LoginData.xlsx";

		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginForTPI");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginForTPI");

		if (ttlRows <= 1 || ttlColumns < 2) {
			System.err.println("Error: No valid test data found in Excel sheet.");
			return new String[0][0];
		}

		String[][] data = new String[ttlRows - 1][ttlColumns];

		for (int i = 1; i < ttlRows; i++) {
			for (int j = 0; j < ttlColumns; j++) {
				data[i - 1][j] = ReadExcelFile.getCellValue(fileName, "LoginForTPI", i, j);
			}
		}
		return data;
	}

	// Data Provider for Transporter Performance Index Test
	@DataProvider(name = "TPIDataProvider")
	public String[][] TPIDataProvider() {
	    String fileName = System.getProperty("user.dir") + "\\TestData\\LoginData.xlsx";

	    int ttlRows = ReadExcelFile.getRowCount(fileName, "TPIData");
	    int ttlColumns = ReadExcelFile.getColCount(fileName, "TPIData");

	    if (ttlRows <= 1 || ttlColumns < 1) {
	        System.err.println("Error: No valid test data found in Excel sheet.");
	        return new String[0][0];
	    }

	    String[][] data = new String[ttlRows - 1][ttlColumns];

	    for (int i = 1; i < ttlRows; i++) {
	        for (int j = 0; j < ttlColumns; j++) {
	        	System.out.println("Row " + i + ", Col " + j + ": " + ReadExcelFile.getCellValue(fileName, "TPIData", i, j));
	            String cellValue = ReadExcelFile.getCellValue(fileName, "TPIData", i, j);
	            if (cellValue == null) {
	                System.err.println("Warning: Empty/null value found at row " + i + ", column " + j);
	                cellValue = ""; // Set default empty value
	            }
	            data[i - 1][j] = cellValue.trim();
	            System.out.println("Excel Data [" + i + "][" + j + "]: " + data[i - 1][j]);
	        }
	    }
	    return data;
	}
}