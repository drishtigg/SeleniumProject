package com.container.Testcases;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import com.container.utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;


public class BaseClass {

    // Read values from config.properties
    ReadConfig readconfig = new ReadConfig();
    String url = readconfig.getBaseurl();
    String browser = readconfig.getBrowser();

    public static WebDriver driver;
    public static Logger logger;

    @BeforeClass
    public void setup() {
        // Initialize Logger
        logger = LogManager.getLogger("qacontainerui");

        // Initialize WebDriver based on browser selection
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "msedge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Invalid browser specified in config: " + browser);
        }

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Maximize window for better UI interactions
        driver.manage().window().maximize();
        

        logger.info("Browser launched: " + browser);
        
      //open url
      		driver.get(url);
      		logger.info("url open");
    }

 
     public static void captureScreenShot(WebDriver driver, String testName) throws IOException {
		//step1: convert webdriver object to TakesScreenshot interface
		TakesScreenshot screenshot = ((TakesScreenshot)driver);
		
		//step2: call getScreenshotAs method to create image file
		
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		
		File dest = new File(System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + testName + ".png");
         
	
		//step3: copy image file to destination
		FileUtils.copyFile(src, dest);
	}
     
     @AfterClass
     public void tearDown() {
         //driver.close();
        driver.quit();
         }
	

}
