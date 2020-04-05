package com.qa.mypages;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.testdata.Excel;

public class HomePage extends MainPage
{
	private By flightstab  = By.xpath("//span[text()='Flights']");
	private By roundtrip = By.xpath("//li[contains(text(),'Round Trip')]");
	private By fromcity = By.id("fromCity");
	private By allcitycode = By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li/div[@class='makeFlex hrtlCenter']/div[2]");
	private By departure = By.xpath("//span[contains(text(),'DEPARTURE')]");
	private By return_date = By.xpath("//span[contains(text(),'RETURN')]");
	private By searchbutton = By.xpath("//a[contains(@class,'primaryBtn font24 latoBlack widgetSearchBtn')]");
	private By allhomepagelinks = By.tagName("a");
	Excel excel;
	
	public HomePage(WebDriver driver) 
	{
		super(driver);
		excel = new Excel();
		excel.getExcelData();
		
	}

	/**
	 * Using Encapsulation concept by using getter
	 * @return
	 */
			
	public WebElement getFlightTabLink()
	{
		return getElement(flightstab);
		
	}
	
	public WebElement getRoundTripLink()
	{
		return getElement(roundtrip);
		
	}
	
	public WebElement getFromCityLink()
	{
		return getElement(fromcity);
		
	}
	
	public List<WebElement> getAllCityCode()
	{
		return getElements(allcitycode);
	}
	
	
	public WebElement getDepartureLink()
	{
		return getElement(departure);
		
	}
	
	public WebElement getReturnDateLink()
	{
		return getElement(return_date);
	}
	
	
	
	public WebElement getsearchbuttonLink()
	{
		return getElement(searchbutton);
		
	}
	
	
	public String getHomePageTitle()
	{
		return getPageTitle();
	}
	
	
	public String selectedTab()
	{
		String text = getFlightTabLink().getText();
		return text;
	}
	
	
	public void selectRoundTrip()
	{
		getRoundTripLink().click();
	}
	
	
	public void selectFromCity()
	{
	
		String from = excel.searchsheet.getRow(1).getCell(1).getStringCellValue();
		getFromCityLink().click();
		int size = getAllCityCode().size();
		
		for(int i = 1; i<=size; i++)
		{
			String citycode = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li["+i+"]/div[@class='makeFlex hrtlCenter']/div[2]")).getText();
			if(citycode.equalsIgnoreCase(from))
			{
				driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li["+i+"]/div[@class='makeFlex hrtlCenter']/div[2]")).click();
				
			}
		}
		
	}
	
	public void selectToCity()
	{
		
		String to = excel.searchsheet.getRow(1).getCell(2).getStringCellValue();
		int size = getAllCityCode().size();
		
		loop:for(int i = 1; i<=size; i++)
		{
			String citycode = driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li["+i+"]/div[@class='makeFlex hrtlCenter']/div[2]")).getText();
			if(citycode.equalsIgnoreCase(to))
			{
				driver.findElement(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li["+i+"]/div[@class='makeFlex hrtlCenter']/div[2]")).click();
				break loop;
			}
		}
		
		
	}
	
	public void selectDates()
	{
		getDepartureLink().click();
		String month_year = excel.searchsheet.getRow(1).getCell(3).getStringCellValue();
		String day = excel.searchsheet.getRow(1).getCell(4).getStringCellValue();
		getDate(month_year, day);
		getReturnDateLink().click();
		String return_month_year = excel.searchsheet.getRow(1).getCell(5).getStringCellValue();
		String return_day = excel.searchsheet.getRow(1).getCell(6).getStringCellValue();
		getDate(return_month_year, return_day);
		try
		{
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	public void clickSearchButton()
	{
		if(getsearchbuttonLink().isDisplayed())
		{
			getsearchbuttonLink().click();
		}
		
	}
	
	public void AllHomePageLinks()
	{
		getAlllinks(allhomepagelinks);
	}

}
