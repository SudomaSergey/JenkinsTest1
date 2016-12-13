package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RozetkaSearchResults extends BasePage{
	
	private WebDriver driver;
	
	public RozetkaSearchResults(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(xpath = "//div[contains(@class, 'g-i-tile') and contains(@class, 'g-i-tile-catalog')]")
	private List<WebElement> resultsList;

	@FindBy(id = "price[min]")
	private WebElement minPrice;
	
	@FindBy(id = "price[max]")
	private WebElement maxPrice;
	
	@FindBy(id = "submitprice")
	private WebElement submitPriceBtn;
	
	@FindBy(className = "process")
	private WebElement processSpinner;
	
	
	public List<WebElement> getResultList(){
		return resultsList;
	}
	
	
	public void setMinPrice(int price){
		minPrice.sendKeys(String.valueOf(price));
	}
	
	public void setMaxPrice(int price){
		maxPrice.sendKeys(String.valueOf(price));
	}
	
	public void clickPriceSubmitButton(){
		submitPriceBtn.click();
	}
	
	public WebElement getProcessSpinner(){
		return processSpinner;
	}
}
