package org.test.base;


import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;		

	public static WebDriver ChromeLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;

	}

	public static WebDriver EdgeLaunch() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		return driver;

	}

	public static void browserLanunch(String name) {

		switch (name)

		{
		case "chrome":

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "edge":

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		}
	}

	public static void urlLaunch(String url) {

		driver.get(url);
	}

	public static void impWait(int i) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public static void sendkeys(WebElement e, String data) {

		e.sendKeys(data);

	}

	public static void click(WebElement e) {

		e.click();
	}

	public static String getCurrenturl() {

		String url = driver.getCurrentUrl();
		return url;
	}
	public static void quit() {
		driver.quit();
	}

	public String getAttribute(WebElement e) {

		String s = e.getAttribute("value");

		return s;
	}

	public static void dragandDrop(WebElement from, WebElement to) {

		Actions a = new Actions(driver);

		a.dragAndDrop(from, to).perform();
	}

	public static void actionClick(WebElement target) {

		Actions a = new Actions(driver);
		a.click(target);
		
	}

	public static void contextClick(WebElement right) {

		Actions a = new Actions(driver);
		a.contextClick(right).perform();
	}

	public static void doubleClick(WebElement doub) {

		Actions a = new Actions(driver);
		a.doubleClick(doub).perform();
	}

	public static void simpleAlert() {

		Alert al = driver.switchTo().alert();
		al.accept();
	}

	public static void confirmAlert() {

		Alert al = driver.switchTo().alert();
		al.dismiss();

	}
	public static void promptAlert(String s) {
		
		Alert prompt = driver.switchTo().alert();
		prompt.sendKeys(s);
		prompt.accept();
	}
	
	public static void frames(WebElement e) {
		driver.switchTo().frame(e);
		
	}
	
	
	
	// SCREENSHOT........24

	public static void getScreenshotAs(String name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from1 = ts.getScreenshotAs(OutputType.FILE);
		// Scanner s=new Scanner(System.in);
		// String add = s.next();
		File to1 = new File("C:\\Users\\Anand\\eclipse-workspace\\MavenProject\\ScreenShot\\" + name + ".png");
		//File to1 = new File("user.dir" + name + ".png");
		FileUtils.copyFile(from1, to1);

	}

	public static String XcelSheet(String name, String sheetname, int row, int cell) throws IOException {

		File f = new File("c:\\Users\\Anand\\eclipse-workspace\\MavenProject\\src\\test\\resources\\" + name + ".xlsx");

		FileInputStream fi = new FileInputStream(f);

		Workbook w = new XSSFWorkbook(fi);

		Sheet s = w.getSheet(sheetname);
		Row r = s.getRow(row);
		Cell c = r.getCell(cell);

		int type = c.getCellType();

		String value = null;
		if (type == 1) {
			value = c.getStringCellValue();

		} else {

			if (DateUtil.isCellDateFormatted(c)) {
				Date d = c.getDateCellValue();
				SimpleDateFormat ss = new SimpleDateFormat("dd-mm-yyyy");
				value = ss.format(d);

			} else {
				double db = c.getNumericCellValue();
				long ln = (long) db;
				value = String.valueOf(ln);

			}
		}
		return value;

	}

//	public static void main(String[] args) throws IOException {
//		XcelSheet("Excel", "sheet1", 1, 0);
//		// txtuser.sendkeys()
//		// it get will current project location
//		String k = System.getProperty("user.dir");
//		System.out.println(k);
//
//	}
	
		public static void windowsHandle() {
			//String one = driver.getWindowHandle();
			
			Set<String> all = driver.getWindowHandles();
				
			for(String eachid:all) {
				if(all.equals(eachid))
				{
					driver.switchTo().window(eachid);
				}
			}
		

		}
		
		public static void executeScript(WebElement e, String s) {
		
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].setAttribute('value',"+s+")", e);
		}
		
		
		public static void scrollDown(WebElement e) {
			
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)",e);
		}
		
		private void scrollUp(WebElement e) {
			JavascriptExecutor js= (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(false)",e);
		}
	
	
		public static void selectByIndex(WebElement e,int index) {
			Select s=new Select(e);
			s.selectByIndex(index);
		}
		public static void deselectByIndex(WebElement e,int index) {
			Select s=new Select(e);
			s.deselectByIndex(index);
		}
		public static void deselectByValue(WebElement e,String value) {
			Select s=new Select(e);
			s.deselectByValue(value);
		}
		public static void selectByValue(WebElement e,String value) {
			Select s=new Select(e);
			s.selectByValue(value);
		}
		



	
	
	

}




