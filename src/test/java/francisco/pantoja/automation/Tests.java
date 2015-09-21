package francisco.pantoja.automation;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import francisco.pantoja.automation.ContactPage;
import francisco.pantoja.automation.HomePage;
import francisco.pantoja.automation.PostPage;

public class Tests {
	WebDriver driver;

	@BeforeMethod
	public void before() {
		driver = new FirefoxDriver();
	}

	@AfterMethod
	public void after() {
		driver.quit();
	}

	// Case 1
	@Test
	public void homePageTitle() {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.go(driver);
		assertTrue(driver.getTitle().contains("Automation Training"));
	}

	// Case 2
	@Test
	public void search() {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.go(driver);
		homepage.search("used");
		String entryTitle = homepage.getEntryTitle().getText();

		assertTrue(!entryTitle.equals("Nothing found"));
	}

	// Case 2
	@Test
	public void searchWithNoResults() {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.go(driver);

		homepage.search("complicated search");
		String entryTitle = homepage.getEntryTitle().getText();

		assertTrue(entryTitle.equals("Nothing Found"));
	}

	// Case 3
	@Test
	public void verifiyDisplayedDate() {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.go(driver);

		List<WebElement> entryDates = homepage.getEntryDates();
		assertTrue(entryDates.size() > 0);

		WebElement firstEntryDate = entryDates.get(0);
		String postDate = firstEntryDate.getText();
		firstEntryDate.click();

		PostPage postPage = PageFactory.initElements(driver, PostPage.class);
		WebElement postDateObj = postPage.getEntryDate();

		assertNotNull(postDateObj);
		assertTrue(postDate.equals(postDateObj.getText()));

	}

	// Case 4
	@Test
	public void submitContactForm() {
		ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
		contactPage.go(driver);

		String name = "Francisco Pantoja";
		String email = "francisco.pantoja@globant.com";
		String subject = "A subject";
		String message = "A message";
		contactPage.submitForm(name, email, subject, message);

		String thankYouStr = "Thank you for contacting us.";
		assertTrue(contactPage.getThankyouElement().getText().equals(thankYouStr));

		System.out.println(contactPage.getInvalidFormMessage());

	}

	// Case 5
	@Test
	public void submitContactFormWithErrors() {
		ContactPage contactPage = PageFactory.initElements(driver, ContactPage.class);
		contactPage.go(driver);

		String name = null;
		String email = "francisco.pantoja@globant.com";
		String subject = "A subject";
		String message = "A message";

		contactPage.submitForm(name, email, subject, message);
		assertNotNull(contactPage.getInvalidFormMessage());

		contactPage.setNameInputText("Francisco Pantoja");
		contactPage.submitForm();

		String thankYouStr = "Thank you for contacting us.";
		assertTrue(contactPage.getThankyouElement().getText().equals(thankYouStr));

	}

	// Case 6
	@Test
	public void checkMonthPosts() {
		HomePage homepage = PageFactory.initElements(driver, HomePage.class);
		homepage.go(driver, "http://10.28.148.127/wordpress/2013/10/");

		List<WebElement> days = homepage.getMonthPosts();
		assertTrue(days.size() > 0);
		List<WebElement> entries = homepage.getEntryTitles();

		System.out.println("---------- Post titles----------");

		for (WebElement webElement : entries)
			System.out.println(webElement.getText());

		System.out.println("--------------------------------");

		homepage.go(driver);
		days = homepage.getMonthPosts();
		assertTrue(days.size() == 0);

	}

}
