import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.RozetkaMainPage;
import pages.RozetkaSearchResults;
import utils.Config;

public class TestMain {
	
	private WebDriver driver;
	private String goods;
	private String category;
	
	@BeforeClass
	private void init(){		
		String url = Config.getProperty("url");
		goods = Config.getProperty("goods");
		category = Config.getProperty("category");
		int timeout = Integer.parseInt(Config.getProperty("timeout"));
		
		System.setProperty("webdriver.chrome.driver", "/Webdriver/chromedriver.exe");
		driver = initWebDriver();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	private WebDriver initWebDriver(){
		String webDriver = Config.getProperty("webdriver");
		
		if(webDriver == "chrome"){
			return new ChromeDriver();
		}
		if(webDriver == "firefox"){
			return new FirefoxDriver();
		}
		return new ChromeDriver();		
	}
	
	@Test
	public void GeneralTest(){
		RozetkaMainPage main = new RozetkaMainPage(driver);
		main.setCategoryElement(category);
		main.searchGoods(goods);
		
		RozetkaSearchResults resultPage = new RozetkaSearchResults(driver);
		
		resultPage.setMinPrice(1000);
		resultPage.setMaxPrice(10000);
		resultPage.clickPriceSubmitButton();
		
		WebDriverWait myWait = new WebDriverWait(driver, 10);
		myWait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.className("process"))));
		
		System.out.println("here");
		List<WebElement> resultList = resultPage.getResultList();
		Assert.assertTrue(resultList.size() > 0);		
	}	
}
