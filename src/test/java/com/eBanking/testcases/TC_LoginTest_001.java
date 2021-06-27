package com.eBanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eBanking.pageobjects.LoginPage;
import com.eBanking.testbase.TestBase;


	

	public class TC_LoginTest_001 extends TestBase
	{

		@Test
		public void loginTest() throws IOException 
		{
				
			logger.info("URL is opened");
			
			LoginPage lp=new LoginPage(driver);
			lp.setUserName(config.getProperty("username"));
			logger.info("Entered username");
			
			lp.setPassword(config.getProperty("password"));
			
			lp.clickSubmit();
			
			if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
			{
				Assert.assertTrue(true);
				logger.info("Login test passed");
			}
			else
			{
				Assert.assertTrue(false);
				logger.info("Login test failed");
			}
			
		}
	}

	


