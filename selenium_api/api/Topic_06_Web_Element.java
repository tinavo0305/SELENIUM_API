package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Web_Element {
	private WebDriver driver;
	By emailTextboxBy = By.xpath("//input[@id='mail']");
	By educationTextAreaBy = By.xpath("//textarea[@id='edu']");
	By ageUnder18RadioBy = By.xpath("//input[@id='under_18']");
	By passwordBy = By.xpath("//input[@id='password']");
    By languagueJavaCheckboxBy = By.xpath("//input[@id='java']");
    By jobRole01By = By.xpath("//select[@id='job1']");
    By jobRole02By = By.xpath("//select[@id='job2']");
    By developmentCheckBoxBy = By.xpath("//input[@id='development']");
    By slider01By = By.xpath("//input[@id='slider-1']");
    By ageRadiobuttonDisabledBy = By.xpath("//input[@id='radio-disabled']");
    By biographyBy = By.xpath("//textarea[@id='bio']");
    By jobRole03By = By.xpath("//select[@id='job3']");
    By interestCheckboxDisabledBy = By.xpath("//input[@id='check-disbaled']");
	By slider02By = By.xpath("//input[@id='slider-2']");
	
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Check_Displayed() {
//		if (driver.findElement(emailTextboxBy).isDisplayed()) {
//			driver.findElement(emailTextboxBy).sendKeys("Automation Testing");

		if (isElementDisplayed(emailTextboxBy)) {
			sendKeyToElement(emailTextboxBy, "Automation Testing");
		}
	
     
  
		if (isElementDisplayed(educationTextAreaBy)) {
			sendKeyToElement(educationTextAreaBy, "Automation Testing");
		}

		if (isElementDisplayed(ageUnder18RadioBy)) {
			clickToElement(ageUnder18RadioBy);
		}
	}
	
	

	@Test
	public void TC_02_Check_Enabled() {
		 // Enabled
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertTrue(isElementEnabled(ageUnder18RadioBy));
		Assert.assertTrue(isElementEnabled(jobRole01By));
		Assert.assertTrue(isElementEnabled(jobRole02By));
		Assert.assertTrue(isElementEnabled(developmentCheckBoxBy));
		Assert.assertTrue(isElementEnabled(slider01By));
		
		// Disabled
		Assert.assertFalse(isElementEnabled(passwordBy));
		Assert.assertFalse(isElementEnabled(ageRadiobuttonDisabledBy));
		Assert.assertFalse(isElementEnabled(biographyBy));
		Assert.assertFalse(isElementEnabled(jobRole03By));
		Assert.assertFalse(isElementEnabled(interestCheckboxDisabledBy));
		Assert.assertFalse(isElementEnabled(slider02By));

			
	}

	@Test
	public void TC_03_Check_Selected() {
		// click to select 
		clickToElement(ageUnder18RadioBy);
		clickToElement(languagueJavaCheckboxBy);
		Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
		Assert.assertTrue(isElementSelected(languagueJavaCheckboxBy));
		
		// click to deselect
		clickToElement(languagueJavaCheckboxBy);
		
		Assert.assertFalse(isElementSelected(languagueJavaCheckboxBy));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element ---- " + by + "---- is displayed");
			return true;
		} else {
			System.out.println("Element ---- " + by + "---- is not displayed");
			return false;
		}
	}

	public void sendKeyToElement (By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element ---- " + by + "---- is enabled");
			return true;
		} else {
			System.out.println("Element ---- " + by + "---- is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element ---- " + by + "---- is selected");
			return true;
		} else {
			System.out.println("Element ---- " + by + "---- is not selected");
			return false;
		}
	}
	
}
