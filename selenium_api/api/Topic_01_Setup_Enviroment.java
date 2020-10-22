package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_01_Setup_Enviroment {
  private WebDriver driver; 
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("https://www.google.com/");
  }
  @Test
  public void TC_01_Check_Google_Title () {
	  String googleTitle = driver.getTitle();
	  Assert.assertEquals(googleTitle, "Google");
  }
  @Test
  public void TC_02_Check_Google_Url () {
	  String googleUrl = driver.getCurrentUrl();
	  Assert.assertEquals(googleUrl, "https://www.google.com/");
	  System.out.println(googleUrl + " my test!");
  }
//  @Test
//  public void TC_03_Check_Google_Logo () {
//	  String googleLogo = driver.get();
//	  Assert.assertEquals(googleLogo, "Logo");
//  }
  @AfterClass
  public void afterClass() {
	  driver.quit();

  }

}
