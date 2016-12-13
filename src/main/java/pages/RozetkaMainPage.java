package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaMainPage extends BasePage{
	
	private WebDriver driver;

	public RozetkaMainPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@FindBy(className = "rz-header-search-input-text")
	private WebElement headerSearchInputField;
	
	@FindBy(xpath = "//*[@id='rz-search']/form/div[1]/div[1]/a/span")
	private WebElement categorySelectorButton;
	
	public void setCategoryElement(String elementName){
		categorySelectorButton.click();
		String xpathToCategoryElement = "//div[@class = 'rz-header-search-category']/ul/li/a[text() = '" + elementName + "']";
		WebElement categoryElement = driver.findElement(By.xpath(xpathToCategoryElement));
		categoryElement.click();
	}
	
	public void searchGoods(String goods){
		headerSearchInputField.sendKeys(goods);
		headerSearchInputField.sendKeys(Keys.ENTER);
	}
}
