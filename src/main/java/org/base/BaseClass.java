package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.helper.DataUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	
	public static WebDriver ChromeDiver() {
		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		return driver;
	}
	public static WebDriver FireBox() {
		WebDriverManager.firefoxdriver().setup();
		driver =new FirefoxDriver();
		return driver;	
	}
	
	public static WebDriver browserLaunch(String browsername) {
		if(browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver =new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.chromedriver().setup();
			driver =new FirefoxDriver();
		}
		else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}
		return driver;
	}
	public static  void browserName(String browserName) {
		switch(browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":	
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
			break;
		default:
			System.err.println("Invalid Browser Name");
			
		
		}
	}
	
	public static void urlLaunch(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	public static void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	
	}
	public static void sendkeys(WebElement e, String value) {
	e.sendKeys(value);
	}
	public static void click(WebElement e) {
		e.click();
	}
	public static String getCurrentUrl() {
		
		return driver.getCurrentUrl();
	
	}
	public static String getTitle() {
		return driver.getTitle();
	
	}
	public static String getText(WebElement e) {
	return e.getText();
	}
	public static String getattribute(WebElement e) {
		return e.getAttribute("value");		
	}
	public static void quit() {
		driver.quit();
		
	}
	public static void moveToElement(WebElement e) {
			Actions a=new Actions(driver);
			a.moveToElement(e).perform();
	}
	public static void dragAndDrop(WebElement source,WebElement target) {
		Actions a= new Actions(driver);
		a.dragAndDrop(source, target).perform();

	}
	public static String getExcelData (String filename,String sheetname,int rowno,int cellNo) throws IOException {	
	File loc =new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Excel\\"+filename+".xlsx");
	FileInputStream st=new FileInputStream(loc);
	
	Workbook w=new XSSFWorkbook(st);
	Sheet sheet = w.getSheet(sheetname);
	Row row = sheet.getRow(rowno);
	Cell cell = row.getCell(cellNo);
	int type = cell.getCellType();
	String value=null;
	if (type==1) {
		 value = cell.getStringCellValue();
	}
	//else {
	//	if(DataUtil.(cell)) {
		//	value =new SimpleDateFormat("dd-MM-yyyy").format(cell.getDateCellValue());
		//}
		//else {
//			value = String.valueOf(long)cell.getNumericCellValue());   }
	return value;
	
//	}
//	return value;
	}
}
