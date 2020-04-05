package com.qa.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel 
{
	public XSSFSheet searchsheet;
	public XSSFSheet passengerclass;
	
	public void getExcelData()
	{
		String location = "InputFile\\MakeMyTrip.xlsx";
		File src = new File(location);
		try 
		{
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			searchsheet = wb.getSheet("Search");
			passengerclass = wb.getSheet("PassengerClass");
			
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			System.out.println("Not able to locate excel file");
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
			
	}
	
	/*
	 * public void getSerachData() {
	 * searchsheet.getRow(1).getCell(0).getStringCellValue(); }
	 */

}
