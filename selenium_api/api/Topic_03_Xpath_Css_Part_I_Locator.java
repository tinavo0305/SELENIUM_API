package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_03_Xpath_Css_Part_I_Locator {
  private WebDriver driver; 
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("https://www.facebook.com/");
	       // Application Under Testing
		  // an Element have a HTML code with format:
		  // 1. HTML Tagname
		  // 2. Attribute name: Selenium supports 3 name: id,class,name,type, 
		  // 3. Attribute value
  }  
//  @Test
//  public void TC_01_ID  () throws Exception {
//	 WebElement createNewAccount = driver.findElement(By.id("u_0_2"));
//	 createNewAccount.click();
//	 Thread.sleep(5000);
//	 List<WebElement> links = driver.findElements(By.tagName("a"));
//	 int count = links.size();
//	 System.out.println("Link number = " + count);
//	 
//  }
  @Test
  public void TC_02_Class   () {
	  WebElement createNewAccount = driver.findElement(By.className("_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy"));
	  //boolean result = createNewAccount.isDisplayed();
	  Assert.assertTrue(createNewAccount.isDisplayed(),"Failed!");
  }
  @Test
  public void TC_03_Name () throws InterruptedException {
	  WebElement emailTextBox = driver.findElement(By.name("email"));
      emailTextBox.sendKeys("abc@gmail.com");
 	  Thread.sleep(10000);

  }
  @Test
  public void TC_04_Tagname  () {
	 
  }
  @Test
  public void TC_05_LinkText   () {
	  
  }
  @Test
  public void TC_06_PartialLinkText () {
  }
  @Test
  public void TC_07_Css   () {
	   
  }
  @Test
  public void TC_08_Xpath () {
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();

  }

}
