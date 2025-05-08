package tutorialsninja.register;

import java.time.Duration;

//import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TC_RF_001 {
	//user shouldbe logged in n taken to account success page n proper details should be displayed on the page
	//user should be taken to account page

	@Test
	//Run | Debug
	public void verifyRegisteringWithMandatoryFields() {
		// TODO Auto-generated method stub
		//ctrl shift o to import any imports
	
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath	("//a[normalize-space()='Register']")).click();
		driver.findElement(By.xpath	("//input[@id='input-firstname']")).sendKeys("Akhila");
		driver.findElement(By.xpath	("//input[@id='input-lastname']")).sendKeys("Yandapalli");
		String timestampedEmail = "akhilayandapalli" + System.currentTimeMillis() + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(timestampedEmail);
		driver.findElement(By.xpath	("//input[@id='input-telephone']")).sendKeys("9090909090");
		driver.findElement(By.xpath	("//input[@id='input-password']")).sendKeys("pandu");
		driver.findElement(By.xpath	("//input[@id='input-confirm']")).sendKeys("pandu");
		driver.findElement(By.xpath	("//input[@name='agree']")).click();
		driver.findElement(By.xpath	("//input[@value='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		String expectedHeading="Your Account Has Been Created!";
		Assert.assertEquals(driver.findElement(By.xpath	("//div[@id='common-success']//h1")).getText(),expectedHeading);
		String actualproperDetailsone="Congratulations! Your new account has been successfully created!";
		String actualproperDetailstwo="You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String actualproperDetailsthree="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
		String actualproperDetailsfour="contact us";
		String expectedProperDetails=driver.findElement(By.id("content")).getText();
		Assert.assertTrue(expectedProperDetails.contains(actualproperDetailsone));
		Assert.assertTrue(expectedProperDetails.contains(actualproperDetailstwo));
		Assert.assertTrue(expectedProperDetails.contains(actualproperDetailsthree));
		Assert.assertTrue(expectedProperDetails.contains(actualproperDetailsfour));
		
		driver.findElement(By.xpath	("//a[text()='Continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
		
	}
	
	

}
