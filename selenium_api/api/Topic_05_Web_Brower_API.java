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

public class Topic_05_Web_Brower_API {
	private WebDriver driver;

	public void clickMyAccount() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}
	public void clickCreateAnAccount() {
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_VerifyUrl() {
		driver.get("http://live.demoguru99.com");

		clickMyAccount();
		String currentLoginUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentLoginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		clickCreateAnAccount();
		String currentRegisterUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentRegisterUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_VerifyPageTitle() {
		driver.get("http://live.demoguru99.com");

		clickMyAccount();
		String currentLoginTitle = driver.getTitle();
		Assert.assertEquals(currentLoginTitle, "Customer Login");
	
		
		clickCreateAnAccount();
		String currentRegisterTile = driver.getTitle();
		Assert.assertEquals(currentRegisterTile, "Create New Customer Account");
	}

	@Test
	public void TC_03_NavigateFunction() {
		driver.get("http://live.demoguru99.com");
		
		clickMyAccount();
		clickCreateAnAccount();
		String currentRegisterUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentRegisterUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		String  currentLoginUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentLoginUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		String currentRegisterTile = driver.getTitle();
		Assert.assertEquals(currentRegisterTile, "Create New Customer Account");
		
		
		
	}
	@Test
	public void TC_04_GetPageSource() {
		driver.get("http://live.demoguru99.com");
		
		clickMyAccount();
		String pageSourceLogin = driver.getPageSource();
		Assert.assertTrue(pageSourceLogin.contains("Login or Create an Account"));
		
		
		clickCreateAnAccount();
		String pageSourceRegister = driver.getPageSource();
		Assert.assertTrue(pageSourceRegister.contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}

}
