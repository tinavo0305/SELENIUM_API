package api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Xpath_Css_Part_II {
  private WebDriver driver; 
  WebElement emailTextbox;
  WebElement passTextbox;
  WebElement loginButton;
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  
  }
  
  
  @BeforeMethod
   public void runForEachTestMethod() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  
	  emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
	  passTextbox = driver.findElement(By.xpath("//input[@id='pass']"));
	  loginButton = driver.findElement(By.xpath("//button[@id='send2']"));
  }  
  @Test
  public void TC_01_LoginWithEmptyEmaiAndPassword  () {
	  
	  emailTextbox.sendKeys("");
	  passTextbox.sendKeys("");
	  loginButton.click();
	  
	  WebElement emptyEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
	  String errorTextEmail = emptyEmail.getText();
	  System.out.println(errorTextEmail);
	  Assert.assertEquals(errorTextEmail, "This is a required field.");
	  
	         
	  WebElement emptyPass = driver.findElement(By.id("advice-required-entry-pass"));
	        String errorTextPass = emptyPass.getText();
	        boolean status = errorTextPass.equals("This is a required field.");
	        System.out.println("Status = "+ status);
	        Assert.assertTrue(status);
  }
  @Test
  public void TC_02_LoginWithInvalidEmail   () {
	  emailTextbox.sendKeys("123@123");
	  passTextbox.sendKeys("");
	  loginButton.click();
	  
	  WebElement invalidEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
	  String errorInvalidEmail = invalidEmail.getText();
	  System.out.println(errorInvalidEmail);
	  Assert.assertEquals(errorInvalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");
  
	  WebElement emptyPass = driver.findElement(By.id("advice-required-entry-pass"));
      String errorTextPass = emptyPass.getText();
      boolean status = errorTextPass.equals("This is a required field.");
      System.out.println("Status = "+ status);
      Assert.assertTrue(status);
  }
  @Test
  public void TC_03_LoginWithPasswordLessThan6 () {
	  emailTextbox.sendKeys("automation@gmail.com");
	  passTextbox.sendKeys("123");
	  loginButton.click();
	  
	  WebElement invalidPassword = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"));
	  String errorInvalidPass = invalidPassword.getText();
	  Assert.assertEquals(errorInvalidPass, "Please enter 6 or more characters without leading or trailing spaces.");
	
	  
  }
  @Test
  public void TC_04_LoginWithIncorrectPassword  () {
	  
	  emailTextbox.sendKeys("automation@gmail.com");
	  passTextbox.sendKeys("gsgsdhgsdhgsdhs");
	  loginButton.click();
	  
	  WebElement incorrectPassword = driver.findElement(By.xpath("//li[@class='error-msg']//span"));
	  String errorIncorrectPass = incorrectPassword.getText();
	  Assert.assertEquals(errorIncorrectPass, "Invalid login or password.");
  }
  @Test
  public void TC_05_LoginWithValidEmailAndPassword   () {
	  emailTextbox.sendKeys("automation_13@gmail.com");
	  passTextbox.sendKeys("123123");
	  loginButton.click();
	 
	  
	  WebElement pageTitle = driver.findElement(By.xpath("//div[@class='page-title']//h1[text()='My Dashboard']"));
	  boolean status = pageTitle.isDisplayed();	 
	  Assert.assertTrue(status);
	  
	  WebElement helloMsg = driver.findElement(By.xpath("//strong[text()='Hello, Automation Testing!']"));
		  status = helloMsg.isDisplayed();	 
		  Assert.assertTrue(status);
	  
	  WebElement contactInfo = driver.findElement(By.xpath("//a[text()='Change Password']/parent::p"));
	  String contactInfoText = contactInfo.getText();
	  Assert.assertTrue(contactInfoText.contains("Automation Testing"));
	  Assert.assertTrue(contactInfoText.contains("automation_13@gmail.com"));
	  
	  driver.findElement(By.xpath("//span[text()='Account' and @class='label']")).click(); 
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click(); 
  }
  // This is for testcase 06
  public int randomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(999999);
  }
  
  
  @Test
  public void TC_06_CreateNewUser () {
	  String email = "auto_test" + randomNumber() + "@hotmail.com"; 
	  String fisrtName = "Tester";
	  String lastName = "QA";
	  String password = "12345678";
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(fisrtName);
	  driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName); 
	  driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email); 
	  driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password); 
	  driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password); 
	  driver.findElement(By.xpath("//button[@title='Register']")).click(); 
	  
	  WebElement pageTitle = driver.findElement(By.xpath("//h1[text()='My Dashboard']"));
	  boolean status = pageTitle.isDisplayed();	 
	  Assert.assertTrue(status);
	  
	  WebElement confirmMsg = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
		  String msg = confirmMsg.getText();
		  Assert.assertEquals(msg,"Thank you for registering with Main Website Store.");
	  
	  WebElement contactInfo = driver.findElement(By.xpath("//a[text()='Change Password']/parent::p"));
	  String contactInfoText = contactInfo.getText();
	  Assert.assertTrue(contactInfoText.contains(fisrtName + " " + lastName));
	  Assert.assertTrue(contactInfoText.contains(email));
	  
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();

  }

}
