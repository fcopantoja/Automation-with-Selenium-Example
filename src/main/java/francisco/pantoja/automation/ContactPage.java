package francisco.pantoja.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactPage {

	@FindBy(id = "cntctfrm_contact_name")
	private WebElement nameInput;

	@FindBy(id = "cntctfrm_contact_email")
	private WebElement emailInput;

	@FindBy(id = "cntctfrm_contact_subject")
	private WebElement subjectInput;

	@FindBy(id = "cntctfrm_contact_message")
	private WebElement messageInput;

	@FindBy(id = "cntctfrm_contact_form")
	private WebElement contactForm;

	@FindBy(id = "cntctfrm_thanks")
	private WebElement thankyouElement;

	@FindBy(xpath = "//div[text()='Please make corrections below and try again.'])")
	private WebElement invalidFormMessage;

	public void setNameInputText(String name) {
		this.nameInput.sendKeys(name);
	}

	public void setEmailInput(String email) {
		this.emailInput.sendKeys(email);
	}

	public void setSubjectInput(String subject) {
		this.subjectInput.sendKeys(subject);
	}

	public void setMessageInput(String message) {
		this.messageInput.sendKeys(message);
	}

	public void submitForm() {
		contactForm.submit();
	}

	public WebElement getThankyouElement() {
		return thankyouElement;
	}

	public WebElement getInvalidFormMessage() {
		return invalidFormMessage;
	}

	public void go(WebDriver driver) {
		driver.get("http://10.28.148.127/wordpress/sample-page/");

	}

	public void submitForm(String name, String email, String subject, String message) {
		nameInput.sendKeys(name);
		emailInput.sendKeys(email);
		subjectInput.sendKeys(subject);
		messageInput.sendKeys(message);
		contactForm.submit();

	}

}