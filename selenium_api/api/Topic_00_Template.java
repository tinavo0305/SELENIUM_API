package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_00_Template {
  private WebDriver driver; 
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	 
	  
  }  
  @Test
  public void TC_01_  () {
	  driver.get(" ");
  }
  @Test
  public void TC_02_   () {
	   
  }
  @Test
  public void TC_03_ () {
  }
  @AfterClass
  public void afterClass() {
	  driver.quit();

  }

}
