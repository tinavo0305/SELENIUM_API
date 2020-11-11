package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_DropdrownList {
	private WebDriver driver;
	private Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Default_DropdownList() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

		select.selectByIndex(9);
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		select.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		int jobDropdownItem = select.getOptions().size();
		Assert.assertEquals(jobDropdownItem, 10);

		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());

		select.selectByVisibleText("Automation");
		Thread.sleep(3000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(3000);
		select.selectByVisibleText("Desktop");
		Thread.sleep(3000);

		List<WebElement> jobRole02Selected = select.getAllSelectedOptions();
		for (WebElement select : jobRole02Selected) {
			System.out.println(select.getText());
		}

		Assert.assertEquals(select.getAllSelectedOptions().size(), 3);

		select.deselectAll();
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);

	}

	@Test
	public void TC_02_HTML_DropdownList() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Elsa1");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Frozen");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByVisibleText("3");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1989");

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("elsa1@frozen.com");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();

		String confirmMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
		Assert.assertEquals(confirmMessage, "Your registration completed");

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "3");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "May");

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1989");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
