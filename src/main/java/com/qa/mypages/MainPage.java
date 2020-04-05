package com.qa.mypages;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends Page
{

	public MainPage(WebDriver driver) 
	{
		super(driver);
		
	}

	@Override
	public String getPageTitle() 
	{
		return driver.getTitle();
	}

	@Override
	public WebElement getElement(By locator) 
	{
		WebElement element = null;
		try {
			waitForElementPresent(locator);
			element =  driver.findElement(locator);
			return element;
		}
		catch (Exception e) 
		{
			System.out.println("Some error occured while creating element " + locator.toString());
			e.printStackTrace();
		}
		return element;
	}
	
	@Override
	public List<WebElement> getElements(By locator)
	{
		List<WebElement> element = driver.findElements(locator);
		return element;
		
	}

	@Override
	public String getPageHeader(By locator)
	{
		return getElement(locator).getText();
	}

	@Override
	public void waitForElementPresent(By locator) 
	{
		try {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch (Exception e) 
		{
			System.out.println("Some exception occour while waiting for element " + locator.toString());
			e.printStackTrace();
			
		}
		
	}
	
	// Adding concrete method
	public void taskeScreenshot(String screenshotname) 
	{
		try
		{
			
			TakesScreenshot ts = (TakesScreenshot)driver; 
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("./Screenshots/"+screenshotname+".png"));
		}
		catch(Exception e)
		{
			System.out.println("Exception while taking screenshot");
		}
	}
	
	
	public void getDate(String month_Year, String datevalue)
	{		
		while(true)
		{
			String monthyear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']")).getText();
			if(monthyear.equalsIgnoreCase(month_Year))
			{
				break;
			}
			else
			{
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			}
		}
		List<WebElement> allDates = driver.findElements(By.xpath("//div[@class='DayPicker-wrapper']//div[1]//div[3]//div//div//div[1]"));
		for(WebElement ele : allDates)
		{
			String date_text = ele.getText();
			if(date_text.equalsIgnoreCase(datevalue))
			{
				Actions actions = new Actions(driver);
				actions.doubleClick(ele).build().perform();
				//ele.click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	/**
	 * Generic method to find all the links on the page and to check all the links are reachable.										
	 * @param locator
	 */
	public void getAlllinks(By locator)
	{
		List<WebElement> list = getElements(locator);
		int size = list.size();
		for(int i=0; i<size;i++)
		{
			String link = list.get(i).getAttribute("href");
			if(link != null && link.contains("https"))
			{
				try 
				{
					URL url = new URL(link);
					HttpURLConnection con =  (HttpURLConnection) url.openConnection();
					con.connect();
					int resCode = con.getResponseCode();
					if(resCode==200)
					{
						System.out.println(url + " " + con.getResponseMessage()+ "----> " + resCode);
					}
					else if(resCode!=200)
					{
						System.out.println(url + " " + con.getResponseMessage()+ "----> " + resCode);
					}
				} 
				catch (MalformedURLException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		
	}

}
