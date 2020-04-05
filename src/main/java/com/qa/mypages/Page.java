package com.qa.mypages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page 
{
	WebDriver driver;
	WebDriverWait wait;
	
	
	public Page(WebDriver driver)
	{
		
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);  // Explicitly Wait
	}
	
	
	/**
	 * Abstract Methods
	 * @return
	 */
	
	public abstract String getPageTitle();
	
	public abstract WebElement getElement(By locator);
	
	public abstract List<WebElement> getElements(By locator);
	
	public abstract String getPageHeader(By locator);
	
	public abstract void waitForElementPresent(By locator);

}
