package francisco.pantoja.automation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id = "s")
	private WebElement searchInput;

	@FindBy(id = "searchform")
	private WebElement searchForm;

	@FindBy(className = "entry-title")
	private WebElement entryTitle;

	@FindBy(xpath = "//table[@id='wp-calendar']//tbody//tr//td//a")
	private List<WebElement> monthPosts;

	@FindBy(className = "entry-title")
	private List<WebElement> entryTitles;

	@FindBy(className = "entry-date")
	private List<WebElement> entryDates;

	public void go(WebDriver driver, String url) {
		driver.get(url);
	}

	public WebElement getEntryTitle() {
		return entryTitle;
	}

	public List<WebElement> getMonthPosts() {
		return monthPosts;
	}

	public List<WebElement> getEntryTitles() {
		return entryTitles;
	}

	public List<WebElement> getEntryDates() {
		return entryDates;
	}

	public void search(String query) {
		searchInput.sendKeys(query);
		searchForm.submit();
	}

	public void go(WebDriver driver) {
		driver.get("http://10.28.148.127/wordpress");
	}

}