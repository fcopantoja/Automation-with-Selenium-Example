package francisco.pantoja.automation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage {

	@FindBy(className = "entry-date")
	private WebElement entryDate;

	public WebElement getEntryDate() {
		return entryDate;
	}

}