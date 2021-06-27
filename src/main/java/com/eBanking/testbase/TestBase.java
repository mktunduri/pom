package com.eBanking.testbase;



	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class TestBase {


		public static WebDriver driver;
		public static Properties config = new Properties();
		public static FileInputStream fis;
		public static Logger logger;
		
		@BeforeClass
		public void setup()
		{			
			logger = Logger.getLogger("ebanking");
			PropertyConfigurator.configure("Log4j.properties");
			
			if(driver == null) {
				
				try {
					fis  = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\eBanking\\properties\\config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					config.load(fis);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(config.getProperty("br").equals("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
				}
				else if(config.getProperty("br").equals("firefox"))
				{
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				}
				else if(config.getProperty("br").equals("ie"))
				{
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
				}
				
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.get(config.getProperty("baseURL"));
			}
		}
	
		
		@AfterClass
		public void tearDown()
		{
			driver.quit();
		}
		
	}


