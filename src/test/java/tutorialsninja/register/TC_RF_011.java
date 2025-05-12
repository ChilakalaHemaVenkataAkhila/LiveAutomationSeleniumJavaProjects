package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class TC_RF_011 {
	WebDriver driver=new ChromeDriver();
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	//Verifying registering an account by providing an invalid phone number
@Test
	public void chackingPhoneNumber() {
	
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath	("//a[normalize-space()='Register']")).click();
		driver.findElement(By.xpath	("//input[@id='input-firstname']")).sendKeys("Akhila");
		driver.findElement(By.xpath	("//input[@id='input-lastname']")).sendKeys("Yandapalli");
		String timestampedEmail = "akhilayandapalli" + System.currentTimeMillis() + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(timestampedEmail);
		driver.findElement(By.xpath	("//input[@id='input-telephone']")).sendKeys("abcde");
		driver.findElement(By.xpath	("//input[@id='input-password']")).sendKeys("pandu");
		driver.findElement(By.xpath	("//input[@id='input-confirm']")).sendKeys("pandu");
		driver.findElement(By.xpath	("//input[@name='agree']")).click();
		driver.findElement(By.xpath	("//input[@value='Continue']")).click();
		
		String expectedWarningMessage="Telephone number does not appear to be valid";
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarningMessage);
	}

}
