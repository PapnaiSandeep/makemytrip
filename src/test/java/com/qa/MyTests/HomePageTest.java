package com.qa.MyTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.mypages.HomePage;

public class HomePageTest extends BaseTest
{
	HomePage homepage;
	String tabname;
	
	/*
	 * public HomePageTest() { homepage = new HomePage(driver); }
	 */
	
	@Test(priority = 1, enabled = true)
	public void verifyHomePageTitle()
	{
		String title = new HomePage(driver).getHomePageTitle();
		Assert.assertEquals(title, "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
		
	}
	
	@Test(priority = 2, enabled = true)
	public void verifyCurrentTab()
	{
		tabname = new HomePage(driver).selectedTab();
		if(tabname.equalsIgnoreCase("Flights"))
		{
			System.out.println("Current Tab selected is : " + tabname);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void verifySelectRoundTrip()
	{
		new HomePage(driver).selectRoundTrip();
	}
	
	@Test(priority = 4, enabled = true)
	public void verifySelectFromCity()
	{
		verifySelectRoundTrip();
		new HomePage(driver).selectFromCity();
	}
	
	@Test(priority = 5, enabled = true)
	public void verifySelectToCity()
	{
		verifySelectFromCity();
		new HomePage(driver).selectToCity();
	}
	
	@Test(priority = 6, enabled = true)
	public void verifySelectDates()
	{
		verifySelectToCity();
		new HomePage(driver).selectDates();
		
	}
	
	@Test(priority = 7, enabled = true)
	public void verifyClickSearchButton()
	{
		verifySelectDates();
		if(tabname.equalsIgnoreCase("Flights"))
		{
			new HomePage(driver).clickSearchButton();
			
		}
		else
			System.out.println("Wrong Tab selected");
	}
	
	@Test(priority = 8, enabled = true)
	public void verifyAllHomePageLinks()
	{
		new HomePage(driver).AllHomePageLinks();
	}
}
