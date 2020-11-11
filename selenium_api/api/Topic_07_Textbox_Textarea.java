package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Textbox_Textarea {
	private WebDriver driver;
	JavascriptExecutor js;
	private String email, username = "mngr293246", password = "hUhEzAm", loginPageUrl;
	private String customerName, gender, dob, address, city, state, pin, phone, customerID;
	private String editAddress, editCity, editState, editPin, editPhone, editEmail;
	private By customerNameBy = By.xpath("//input[@name='name']");
	private By genderBy = By.xpath("//input[@name='gender']");
	private By DoBBy = By.xpath("//input[@name='dob']");
	private By addressBy = By.xpath("//textarea[@name='addr']");
	private By cityBy = By.xpath("//input[@name='city']");
	private By stateBy = By.xpath("//input[@name='state']");
	private By pinBy = By.xpath("//input[@name='pinno']");
	private By mobileNumberBy = By.xpath("//input[@name='telephoneno']");
	private By customerEmailBy = By.xpath("//input[@name='emailid']");
	private By customerPasswordBy = By.xpath("//input[@name='password']");
	private By submitButtonBy = By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		email = "tina" + randomNumber() + "@qa.com";
		customerName = "Elsa";
		gender = "male";
		dob = "2020-09-10";
		address = "123 King St";
		city = "Toronto";
		state = "ON";
		pin = "123456";
		phone = "9876542346";
		editAddress = "1243 King St W";
		editCity = "Montreal";
		editState = "Quebec";
		editPin = "234567";
		editPhone = "09484742111";
		editEmail = "hihi" + randomNumber() + "@te.com";
	}

	// @Test
	public void TC_01_RegisterToSystem() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		username = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		System.out.println("Username at Register page =  " + username);
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("Password at Register page =  " + password);
	}

	@Test
	public void TC_02_LoginToPage() {
		// driver.get(loginPageUrl);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		String loginID = driver.findElement(By.xpath("//tr[@class='heading3']/td")).getText();
		// WebElement loginID =
		// driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manager Id :
		// " + username + "']"));
		Assert.assertEquals(loginID, "Manager Id : " + username);
		// Assert.assertTrue(loginID.isDisplayed());

	}

	@Test
	public void TC_03_AddNewCustomer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(customerNameBy).sendKeys(customerName);
		js.executeScript("document.getElementById(\"dob\").removeAttribute(\"type\")");
		driver.findElement(DoBBy).sendKeys(dob);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(mobileNumberBy).sendKeys(phone);
		driver.findElement(customerEmailBy).sendKeys(email);
		driver.findElement(customerPasswordBy).sendKeys(password);
		WebElement element = driver.findElement(submitButtonBy);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
//		driver.findElement(submitButtonBy).click();

		Thread.sleep(2000);
		String successMesssage = driver.findElement(By.xpath("//p[@class='heading3']")).getText();
		Assert.assertEquals(successMesssage, "Customer Registered Successfully!!!");

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

//	@Test
	public void TC_04_() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		Assert.assertFalse((driver.findElement(customerNameBy).isEnabled()));
		Assert.assertFalse((driver.findElement(genderBy).isEnabled()));
		Assert.assertFalse((driver.findElement(DoBBy).isEnabled()));

		driver.findElement(addressBy).clear();
		driver.findElement(addressBy).sendKeys(editAddress);
		driver.findElement(cityBy).clear();
		driver.findElement(cityBy).sendKeys(editCity);
		driver.findElement(stateBy).clear();
		driver.findElement(stateBy).sendKeys(editState);
		driver.findElement(pinBy).clear();
		driver.findElement(pinBy).sendKeys(editPin);
		driver.findElement(mobileNumberBy).clear();
		driver.findElement(mobileNumberBy).sendKeys(editPhone);
		driver.findElement(customerEmailBy).clear();
		driver.findElement(customerEmailBy).sendKeys(editEmail);
		
		
		WebElement SubmitButtonE = driver.findElement(submitButtonBy);
		js.executeScript("arguments[0].scrollIntoView(true);", SubmitButtonE);
		SubmitButtonE.click();
	 
		
		
		String updatedMesssage = driver.findElement(By.xpath("//p[@class='heading3']")).getText();
		Assert.assertEquals(updatedMesssage, "Customer details updated Successfully!!!");

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);

		
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}
